package com.runchen.blog.enums;

public enum DeletedStatus {

    NON_DELETED((byte) 0),
    DELETED((byte) 1);

    private final Byte code;

    DeletedStatus(Byte code) {
        this.code = code;
    }

    public Byte getCode() {
        return this.code;
    }
}
