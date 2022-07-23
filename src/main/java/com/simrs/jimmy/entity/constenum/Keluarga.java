package com.simrs.jimmy.entity.constenum;

public enum Keluarga {
    ayah("ayah"),
    ibu("ibu"),
    istri("istri"),
    suami("suami"),
    saudara("saudara");

    private String value;

    private Keluarga(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }
}
