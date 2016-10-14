package com.remytabardel.henripotier.services.database.models;


import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.remytabardel.henripotier.services.database.Database;

/**
 * @author Remy Tabardel
 */

@Table(database = Database.class)
public class Book extends BaseModel {
    @PrimaryKey
    String isbn;
    @Column
    String title;
    @Column
    double price;
    @Column
    String cover;

    public Book() {
    }

    public Book(String isbn, String title, double price, String cover) {
        this.isbn = isbn;
        this.title = title;
        this.price = price;
        this.cover = cover;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public String getCover() {
        return cover;
    }
}
