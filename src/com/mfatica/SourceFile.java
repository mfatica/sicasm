package com.mfatica;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SourceFile {
    private String _programName;
    private int _startAddress;
    private int _locationCounter;
    private int _programLength;

    private String _fileName;
    private String[] _sourceLines;

    private List<String> _objectFile;
    private List<String> _listFile;

    private static LookupTable<String, Integer> _symbolTable;

    public SourceFile(String fileName) {
        _fileName = StringOps.StripExtension(fileName);
        _sourceLines = FileIO.ReadAllLines(fileName);

        _programName = _fileName.toUpperCase();
        _startAddress = 0;
        _locationCounter = 0;
        _programLength = 0;

        _symbolTable = new LookupTable<String, Integer>();
        _objectFile = new ArrayList<String>();
        _listFile = new ArrayList<String>();
    }

    public void BuildOutputFiles() throws IOException {
        // Pass one - builds symbol table and fills _listFile
        // with intermediate source listing to be used by
        // pass two when building object and list files
        PassOne();

        // Pass two - does something idk yet
        //PassTwo();
    }

    public void WriteOutputFiles() throws FileNotFoundException {
        FileIO.WriteFileLines(_fileName + ".lst", _listFile);
        FileIO.WriteFileLines(_fileName + ".obj", _objectFile);
    }

    private void PassOne() throws IOException {
        _listFile.add(BuildListFileHeader());

        for (int i = 0; i < _sourceLines.length; i++) {
            SourceFileLine line = new SourceFileLine(_sourceLines[i]);

            String outputLine = "";
            String lineNumber = String.valueOf(i * 5);

            if (line.getOpcode().equals("START")) {
                // if START directive is found,
                // set starting address to operand
                _locationCounter = Integer.parseInt(line.getOperand());

                // if the START directive has a label,
                // use it for program name
                if (line.hasLabel()) _programName = line.getLabel();

                outputLine = FormatListFileLine(lineNumber, line.getOperand(), line.toString());
            } else if (!line.isComment()) { // comment lines start with a . and are ignored

                // if this line has a label, add it to the symbol table
                if (line.hasLabel()) UpdateSymbolTable(line.getLabel(), _locationCounter);

                // if this line generates object code,
                // build appropriate line with location.
                // otherwise, location is blank
                int size = line.getSize();
                if (size > 0) {
                    outputLine = FormatListFileLine(lineNumber, String.valueOf(_locationCounter), line.toString());
                } else {
                    outputLine = FormatListFileLine(lineNumber, "", line.toString());
                }
            }

            // increment location counter by size of opcode
            _locationCounter += line.getSize();

            _listFile.add(outputLine);
        }
    }

    private void PassTwo() {
        for (int i = 0; i < _listFile.size(); i++) {
            ListFileLine line = new ListFileLine(_listFile.get(i));

            if (!line.getOpcode().equals("START")) {
                if (!line.isComment()) {

                }
            }
        }
    }

    private String FormatListFileLine(String lineNumber, String location, String line) {
        String lineNum = StringOps.LeftPadString(lineNumber, ' ', 4);
        String lineColumn = StringOps.RightPadString(lineNum, ' ', Constants.LIST_COLUMN_LENGTH[Constants.LIST_COLUMN_LINE]);

        // check location if it's blank to avoid padding an empty location to all 0s
        String loc = location.equals("") ? "" : StringOps.LeftPadString(Integer.toHexString(_locationCounter).toUpperCase(), '0', 4);
        String locColumn = StringOps.RightPadString(loc, ' ', Constants.LIST_COLUMN_LENGTH[Constants.LIST_COLUMN_LOC]);

        return lineColumn + locColumn + line;
    }

    private String BuildObjectFileHeader() {
        /*
         * Object file header format:
         * Col 1    'H'
         * Col 2-7  Start Address
         * Col 8-13 Program Length
         */

        return "H" +
                StringOps.RightPadString(_programName, ' ', 7) +
                StringOps.LeftPadString(Integer.toHexString(_startAddress).toUpperCase(), '0', 6) +
                StringOps.LeftPadString(Integer.toHexString(_programLength).toUpperCase(), '0', 6);
    }

    private String BuildListFileHeader() {
        return StringOps.RightPadString("Line", ' ', 6) +
                StringOps.RightPadString("Loc", ' ', 8) +
                StringOps.RightPadString("Label", ' ', 12) +
                StringOps.RightPadString("Opcode", ' ', 8) +
                StringOps.RightPadString("Operand", ' ', 12) +
                StringOps.RightPadString("Object Code", ' ', 12);
    }

    private void UpdateSymbolTable(String label, int value) throws IOException {
        // Check the symbol table for an existing key. Duplicate keys are not allowed.
        if (_symbolTable.containsKey(label)) {
            throw new IOException("Duplicate symbol " + label);
        } else {
            _symbolTable.set(label, value);
        }
    }
}
