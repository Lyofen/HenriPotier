package com.remytabardel.henripotier.services.database.dbflow;

import com.raizlabs.android.dbflow.sql.language.OrderBy;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.remytabardel.henripotier.models.Book;
import com.remytabardel.henripotier.models.Book_Table;
import com.remytabardel.henripotier.services.database.BookDao;
import com.remytabardel.henripotier.services.network.json.BookJson;

import java.util.List;

/**
 * @author Remy Tabardel
 */

public class DBFlowBookDao implements BookDao {
    @Override public List<Book> selectAll() {
        return SQLite.select()
                .from(Book.class)
                .orderBy(OrderBy.fromProperty(Book_Table.title).ascending())
                .queryList();
    }

    @Override public void deleteAll() {
        SQLite.delete(Book.class).query();
    }

    @Override public boolean insert(BookJson bookJson) {
        Book book = new Book(bookJson.getIsbn(),
                bookJson.getTitle(),
                bookJson.getPrice(),
                bookJson.getCover());

        book.save();

        //for the moment i've not seen a way to check insertion..
        return true;
    }
}
