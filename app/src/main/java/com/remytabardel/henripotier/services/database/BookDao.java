package com.remytabardel.henripotier.services.database;

import com.remytabardel.henripotier.models.Book;
import com.remytabardel.henripotier.services.network.json.BookJson;

import java.util.List;

/**
 * @author Remy Tabardel
 */

public interface BookDao {
    List<Book> selectAll();

    void deleteAll();

    boolean insert(BookJson bookJson);
}
