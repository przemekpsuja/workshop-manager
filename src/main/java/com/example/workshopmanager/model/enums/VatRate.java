package com.example.workshopmanager.model.enums;

public enum VatRate {
    ZERO(0), FIVE(5), EIGHT(8), TWETY_THREE(23);

    private final int vat;

    VatRate(int vat) {
        this.vat = vat;
    }

    public int getVat() {
        return vat;
    }
}
