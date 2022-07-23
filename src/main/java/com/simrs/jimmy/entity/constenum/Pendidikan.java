package com.simrs.jimmy.entity.constenum;

public enum Pendidikan {
    TK("TK"), SD("SD"), SMP("SMP"), SMA("SMA"),
    D1("D1"), D2("D2"), D3("D3"), D4("D4"),
    S1("S1"), S2("S2"), S3("S3");

    private String value;

    private Pendidikan(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }
}
