package com.mfatica;


public class SourceFileLine extends FileLine {

    public SourceFileLine(String line) {
        super(line);
    }

    public String getLabel() {
        return getColumn(Constants.SOURCE_COLUMN_LABEL);
    }

    public String getOpcode() {
        return getColumn(Constants.SOURCE_COLUMN_OPCODE);
    }

    public String getOperand() {
        return getColumn(Constants.SOURCE_COLUMN_OPERAND);
    }
}
