package com.simrs.jimmy.entity.constenum;

public enum GolonganDarah {
    A("A"), B("B"), AB("AB"), O("O"), _def("-");

    private final String value;

    GolonganDarah(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
