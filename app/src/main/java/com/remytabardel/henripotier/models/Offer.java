package com.remytabardel.henripotier.models;

import com.remytabardel.henripotier.services.network.json.OfferJson;
import com.remytabardel.henripotier.utils.LogUtils;

/**
 * @author Remy Tabardel
 *         dont need to create offer table, so we dont use dbflow annotations here
 */

public class Offer {
    public enum TYPE {
        PERCENTAGE("percentage"),
        MINUS("minus"),
        SLICE("slice");

        String jsonValue;

        TYPE(String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String getJsonValue() {
            return jsonValue;
        }

        public static TYPE getFromJson(String jsonType) {
            for (TYPE type : TYPE.values()) {
                if (type.jsonValue.equals(jsonType)) {
                    return type;
                }
            }

            LogUtils.e("unknown jsonType : " + jsonType);

            return null;
        }
    }

    TYPE type;
    float value;
    float sliceValue;
    float amount;
    float discount;

    public Offer(OfferJson offerJson, float amount) {
        this(TYPE.getFromJson(offerJson.getType()), offerJson.getValue(), offerJson.getSliceValue(), amount);
    }

    public Offer(TYPE type, float value, float sliceValue, float amount) {
        this.type = type;
        this.value = value;
        this.sliceValue = sliceValue;
        computeDiscount(amount);
    }

    public void computeDiscount(float amount) {
        this.amount = amount;

        switch (type) {
            case MINUS:
                discount = value;
                break;
            case PERCENTAGE:
                discount = amount * value / 100;
                break;
            case SLICE:
                for (float currentAmount = amount;
                     currentAmount >= sliceValue;
                     currentAmount -= sliceValue, discount += value)
                    ;
                break;
        }
    }

    public float getDiscount() {
        return discount;
    }
}
