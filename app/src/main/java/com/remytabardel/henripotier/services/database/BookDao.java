package com.remytabardel.henripotier.services.database;

import android.support.v7.graphics.Palette;

import com.remytabardel.henripotier.models.Book;
import com.remytabardel.henripotier.services.network.json.BookJson;

import java.util.List;

/**
 * @author Remy Tabardel
 */

public interface BookDao {
    List<Book> selectAll();

    void deleteAll();

    boolean insertBook(BookJson bookJson);

    boolean insertBookTheme(String isbn, Palette palette);
}
