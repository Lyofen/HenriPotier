package com.remytabardel.henripotier.services.network.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Remy Tabardel
 *         Used for HenriPotierApi
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class OfferJson {
    private String type;
    private double value;
    private double sliceValue;

    public OfferJson() {
    }

    public OfferJson(String type, double value, double sliceValue) {
        this.type = type;
        this.value = value;
        this.sliceValue = sliceValue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getSliceValue() {
        return sliceValue;
    }

    public void setSliceValue(double sliceValue) {
        this.sliceValue = sliceValue;
    }
}
