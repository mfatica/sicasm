package com.mfatica;

public class Constants {
    // The constants below describe the columns
    // For parsing and writing the source and list files
    public final static int SOURCE_COLUMN_LABEL = 0;
    public final static int SOURCE_COLUMN_EXTENDED = 1;
    public final static int SOURCE_COLUMN_OPCODE = 2;
    public final static int SOURCE_COLUMN_OPERAND = 3;

    public final static int[] SOURCE_COLUMN_INDICES = {0, 11, 12, 20};
    public final static int[] SOURCE_COLUMN_LENGTH = {11, 1, 8, 12};

    public final static int LIST_COLUMN_LINE = 0;
    public final static int LIST_COLUMN_LOC = 1;
    public final static int LIST_COLUMN_LABEL = 2;
    public final static int LIST_COLUMN_EXTENDED = 3;
    public final static int LIST_COLUMN_OPCODE = 4;
    public final static int LIST_COLUMN_OPERAND = 5;
    public final static int LIST_COLUMN_OBJECTCODE = 6;

    public final static int[] LIST_COLUMN_INDICES = {0, 6, 14, 25, 26, 34, 46};
    public final static int[] LIST_COLUMN_LENGTH = {6, 8, 11, 1, 8, 12, 12};

    // The opcode data (hex value and size) are stored
    // as global lookup tables
    public final static LookupTable<String, Integer> OPCODE_TABLE = LoadOpcodeTable();
    public final static LookupTable<String, Integer> OPCODE_SIZE_TABLE = LoadOpcodeSizeTable();

    private static LookupTable<String, Integer> LoadOpcodeTable() {
        LookupTable<String, Integer> table = new LookupTable<String, Integer>();
        table.set("ADD", 0x18);
        table.set("ADDR", 0x90);
        table.set("CLEAR", 0xB4);
        table.set("COMP", 0x28);
        table.set("COMPR", 0xA0);
        table.set("DIV", 0x24);
        table.set("J", 0x3C);
        table.set("JEQ", 0x30);
        table.set("JGT", 0x34);
        table.set("JLT", 0x38);
        table.set("JSUB", 0x48);
        table.set("LDA", 0x0);
        table.set("LDB", 0x68);
        table.set("LDCH", 0x50);
        table.set("LDL", 0x8);
        table.set("LDS", 0x6C);
        table.set("LDT", 0x74);
        table.set("LDX", 0x4);
        table.set("MUL", 0x20);
        table.set("RD", 0xD8);
        table.set("RSUB", 0x4C);
        table.set("STA", 0x0C);
        table.set("STB", 0x78);
        table.set("STCH", 0x54);
        table.set("STL", 0x14);
        table.set("STS", 0x7C);
        table.set("STT", 0x84);
        table.set("STX", 0x10);
        table.set("SUB", 0x1C);
        table.set("SUBR", 0x94);
        table.set("TD", 0xE0);
        table.set("TIX", 0x2C);
        table.set("TIXR", 0xB8);
        table.set("WD", 0xDC);
        return table;
    }

    private static LookupTable<String, Integer> LoadOpcodeSizeTable() {
        LookupTable<String, Integer> table = new LookupTable<String, Integer>();
        table.set("ADD", 3);
        table.set("ADDR", 2);
        table.set("CLEAR", 2);
        table.set("COMP", 3);
        table.set("COMPR", 2);
        table.set("DIV", 3);
        table.set("J", 3);
        table.set("JEQ", 3);
        table.set("JGT", 3);
        table.set("JLT", 3);
        table.set("JSUB", 3);
        table.set("LDA", 3);
        table.set("LDB", 3);
        table.set("LDCH", 3);
        table.set("LDL", 3);
        table.set("LDS", 3);
        table.set("LDT", 3);
        table.set("LDX", 3);
        table.set("MOV", 3);
        table.set("MUL", 3);
        table.set("RD", 3);
        table.set("RSUB", 3);
        table.set("STA", 3);
        table.set("STB", 3);
        table.set("STCH", 3);
        table.set("STL", 3);
        table.set("STS", 3);
        table.set("STT", 3);
        table.set("STX", 3);
        table.set("SUB", 3);
        table.set("SUBR", 2);
        table.set("TD", 3);
        table.set("TIX", 3);
        table.set("TIXR", 2);
        table.set("WD", 3);
        return table;
    }
}
