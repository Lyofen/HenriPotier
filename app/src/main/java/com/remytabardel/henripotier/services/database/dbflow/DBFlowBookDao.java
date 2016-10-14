package com.remytabardel.henripotier.services.database.dbflow;

import com.remytabardel.henripotier.services.database.BookDao;
import com.remytabardel.henripotier.services.database.models.Book;

import java.util.List;

/**
 * @author Remy Tabardel
 */

public class DBFlowBookDao implements BookDao{
    @Override public List<Book> selectAll() {
        return null;
    }

    @Override public void deleteAll() {

    }
}
