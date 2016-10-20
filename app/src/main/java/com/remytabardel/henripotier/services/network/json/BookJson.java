package com.remytabardel.henripotier.services.network.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Remy Tabardel
 *         Used for HenriPotierApi
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookJson {
    private String isbn;
    private String title;
    private float price;
    private String cover;

    public BookJson() {
    }

    public BookJson(String isbn, String title, float price, String cover) {
        this.isbn = isbn;
        this.title = title;
        this.price = price;
        this.cover = cover;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Override public String toString() {
        return "BookJson{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", cover='" + cover + '\'' +
                '}';
    }
}
