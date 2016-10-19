package com.remytabardel.henripotier.models;


import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ColumnIgnore;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
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
    @Column
    int num;
    @ColumnIgnore
    BookTheme bookTheme;

    public Book() {
    }

    public Book(String isbn, String title, double price, String cover, int num) {
        this.isbn = isbn;
        this.title = title;
        this.price = price;
        this.cover = cover;
        this.num = num;
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

    public int getNum() {
        return num;
    }

    public BookTheme getBookTheme() {
        if (bookTheme == null) {
            bookTheme = SQLite.select()
                    .from(BookTheme.class)
                    .where(BookTheme_Table.isbn.eq(isbn))
                    .querySingle();
        }
        return bookTheme;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return isbn.equals(book.isbn);

    }
}
