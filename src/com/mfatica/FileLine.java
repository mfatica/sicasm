package com.mfatica;


public abstract class FileLine {
    protected String _data;

    public FileLine(String line) {
        _data = line;
    }

    public final int getSize() {
        String op = getOpcode();
        String target = getOperand();

        if (Constants.OPCODE_SIZE_TABLE.containsKey(op)) {
            boolean extended = isExtended();
            return Constants.OPCODE_SIZE_TABLE.get(op) + (extended ? 1 : 0);
        } else if (op.equals("WORD")) {
            return 3;
        } else if (op.equals("RESW")) {
            return 3 * Integer.parseInt(target);
        } else if (op.equals("RESB")) {
            return Integer.parseInt(target);
        } else if (op.equals("BYTE")) {
            return getSizeOfByte(target);
        }
        return 0;
    }

    protected final String getColumn(int col) {
        int index = Constants.SOURCE_COLUMN_INDICES[col];
        int length = Constants.SOURCE_COLUMN_LENGTH[col];

        return _data.substring(index, index + length).trim().toUpperCase();
    }

    protected static int getSizeOfByte(String target) {
        return target.substring(target.indexOf('\'') + 1, target.lastIndexOf('\'')).length();
    }

    public abstract String getLabel();

    public abstract String getOpcode();

    public abstract String getOperand();

    public final boolean isExtended() {
        return getColumn(Constants.SOURCE_COLUMN_EXTENDED).contains("+");
    }

    public final boolean hasLabel() {
        return getLabel().length() > 0;
    }

    public final boolean isComment() {
        return _data.startsWith(".");
    }

    @Override
    public String toString() {
        return _data;
    }
}
