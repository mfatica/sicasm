package com.mfatica;

public class ListFileLine extends FileLine {
    public ListFileLine(String line) {
        super(line);
    }

    public String getLineNumber() {
        return getColumn(Constants.LIST_COLUMN_LINE);
    }

    public String getAddress() {
        return getColumn(Constants.LIST_COLUMN_LOC);
    }

    public String getLabel() {
        return getColumn(Constants.LIST_COLUMN_LABEL);
    }

    public String getOpcode() {
        return getColumn(Constants.LIST_COLUMN_OPCODE);
    }

    public String getOperand() {
        return getColumn(Constants.LIST_COLUMN_OPERAND);
    }
}
