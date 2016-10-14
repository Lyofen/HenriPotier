package com.remytabardel.henripotier.services.network.json;

import com.google.gson.annotations.SerializedName;

/**
 * @author Remy Tabardel
 *         Used for HenriPotierApi
 */

public class BookJson {
    @SerializedName("isbn")
    private String mIsbn;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("price")
    private double mPrice;
    @SerializedName("cover")
    private String mCover;

    public BookJson() {
    }

    public BookJson(String isbn, String title, double price, String cover) {
        this.mIsbn = isbn;
        this.mTitle = title;
        this.mPrice = price;
        this.mCover = cover;
    }

    public String getIsbn() {
        return mIsbn;
    }

    public void setIsbn(String mIsbn) {
        this.mIsbn = mIsbn;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double mPrice) {
        this.mPrice = mPrice;
    }

    public String getCover() {
        return mCover;
    }

    public void setCover(String mCover) {
        this.mCover = mCover;
    }

    @Override public String toString() {
        return "BookJson{" +
                "mIsbn='" + mIsbn + '\'' +
                ", mTitle='" + mTitle + '\'' +
                ", mPrice=" + mPrice +
                ", mCover='" + mCover + '\'' +
                '}';
    }
}
