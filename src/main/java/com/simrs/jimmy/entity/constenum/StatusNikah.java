package com.simrs.jimmy.entity.constenum;

public enum StatusNikah {
    single("single"), menikah("menikah"), janda("janda"), duda("duda");

    private final String value;

    StatusNikah(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }

}
