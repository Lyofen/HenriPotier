package com.remytabardel.henripotier.services.network.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Remy Tabardel
 *         Used for HenriPotierApi
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class OfferJson {

    private String type;
    private float value;
    private float sliceValue;

    public OfferJson() {
    }

    public OfferJson(String type, float value, float sliceValue) {
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

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getSliceValue() {
        return sliceValue;
    }

    public void setSliceValue(float sliceValue) {
        this.sliceValue = sliceValue;
    }
}
