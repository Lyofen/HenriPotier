package com.remytabardel.henripotier.services.database;

import com.remytabardel.henripotier.services.database.models.Book;

import java.util.List;

/**
 * @author Remy Tabardel
 */

public interface BookDao {
    List<Book> selectAll();

    void deleteAll();
}
