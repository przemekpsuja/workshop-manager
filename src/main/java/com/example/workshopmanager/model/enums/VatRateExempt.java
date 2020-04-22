package com.example.workshopmanager.model.enums;

public enum VatRateExempt {
    EXEMPT("zw");

    private final String exempt;

    VatRateExempt(String exempt) {
        this.exempt = exempt;
    }

    public String getExempt() {
        return exempt;
    }
}
