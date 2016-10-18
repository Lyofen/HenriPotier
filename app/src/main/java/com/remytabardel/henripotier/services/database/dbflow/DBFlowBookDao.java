package com.remytabardel.henripotier.services.database.dbflow;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.Palette;

import com.raizlabs.android.dbflow.sql.language.OrderBy;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.remytabardel.henripotier.models.Book;
import com.remytabardel.henripotier.models.BookTheme;
import com.remytabardel.henripotier.models.Book_Table;
import com.remytabardel.henripotier.services.database.BookDao;
import com.remytabardel.henripotier.services.network.json.BookJson;

import java.util.List;

/**
 * @author Remy Tabardel
 *         Link between BookDao interface and DBFlow
 */

public class DBFlowBookDao implements BookDao {
    @Override
    public List<Book> selectAll() {
        return SQLite.select()
                .from(Book.class)
                .orderBy(OrderBy.fromProperty(Book_Table.num).ascending())
                .queryList();
    }

    @Override
    public boolean exists(String isbn) {
        Book book = SQLite.select()
                .from(Book.class)
                .where(Book_Table.isbn.is(isbn))
                .querySingle();

        return book != null;
    }

    @Override
    public void deleteAll() {
        SQLite.delete(Book.class).query();
    }

    @Override
    public boolean insertBook(BookJson bookJson, int num) {
        Book book = new Book(bookJson.getIsbn(),
                bookJson.getTitle(),
                bookJson.getPrice(),
                bookJson.getCover(),
                num);

        book.save();

        //at the moment i've not seen a way to check insertion..
        return true;
    }

    @Override
    public boolean insertBookTheme(Context context, String isbn, Palette palette) {
        BookTheme bookTheme;

        if (palette != null) {
            bookTheme = new BookTheme(isbn,
                    ContextCompat.getColor(context, android.R.color.black),
                    palette.getMutedColor(ContextCompat.getColor(context, android.R.color.black)),
                    //sometimes problem with lightvibrant to find color on henri potier et la coupe de feu's cover, so use vibrant instead
                    palette.getLightVibrantColor(palette.getVibrantColor(ContextCompat.getColor(context, android.R.color.white))));

        } else {
            //if no palette we use default colors
            bookTheme = new BookTheme(isbn,
                    ContextCompat.getColor(context, android.R.color.black),
                    ContextCompat.getColor(context, android.R.color.black),
                    ContextCompat.getColor(context, android.R.color.white));

        }

        bookTheme.save();

        //at the moment i've not seen a way to check insertion..
        return true;
    }
}
