package com.remytabardel.henripotier.services.database;

import android.content.Context;
import android.support.v7.graphics.Palette;

import com.remytabardel.henripotier.models.Book;
import com.remytabardel.henripotier.services.network.json.BookJson;

import java.util.List;

/**
 * @author Remy Tabardel
 * interface to avoid link between dbflow and UI
 * provide all requests to implement about books
 */

public interface BookDao {
    List<Book> selectAll();

    void deleteAll();

    boolean exists(String isbn);

    boolean insertBook(BookJson bookJson, int num);

    boolean insertBookTheme(Context context, String isbn, Palette palette);
}
