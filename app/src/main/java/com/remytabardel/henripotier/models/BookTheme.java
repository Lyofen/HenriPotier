package com.remytabardel.henripotier.models;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.remytabardel.henripotier.services.database.Database;

/**
 * @author Remy Tabardel
 */

@Table(database = Database.class)
public class BookTheme extends BaseModel {
    @PrimaryKey String isbn;
    @Column int colorTextTitle;
    @Column int colorTextBody;
    @Column int colorBackground;

    public BookTheme() {

    }

    public BookTheme(String isbn, int colorTextTitle, int colorTextBody, int colorBackground) {
        this.isbn = isbn;
        this.colorTextTitle = colorTextTitle;
        this.colorTextBody = colorTextBody;
        this.colorBackground = colorBackground;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getColorTextTitle() {
        return colorTextTitle;
    }

    public int getColorTextBody() {
        return colorTextBody;
    }

    public int getColorBackground() {
        return colorBackground;
    }
}
