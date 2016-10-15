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
    @PrimaryKey
    String isbn;
    @Column
    int colorVibrantSwatch;
    @Column
    int colorVibrantDarkSwatch;
    @Column
    int colorVibrantLightSwatch;
    @Column
    int colorMutedSwatch;
    @Column
    int colorMutedDarkSwatch;
    @Column
    int colorMutedLightSwatch;

    public BookTheme() {

    }

    public BookTheme(String isbn,
                     int colorVibrantSwatch,
                     int colorVibrantDarkSwatch,
                     int colorVibrantLightSwatch,
                     int colorMutedSwatch,
                     int colorMutedDarkSwatch,
                     int colorMutedLightSwatch) {
        this.isbn = isbn;
        this.colorVibrantSwatch = colorVibrantSwatch;
        this.colorVibrantDarkSwatch = colorVibrantDarkSwatch;
        this.colorVibrantLightSwatch = colorVibrantLightSwatch;
        this.colorMutedSwatch = colorMutedSwatch;
        this.colorMutedDarkSwatch = colorMutedDarkSwatch;
        this.colorMutedLightSwatch = colorMutedLightSwatch;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getColorVibrantSwatch() {
        return colorVibrantSwatch;
    }

    public int getColorVibrantDarkSwatch() {
        return colorVibrantDarkSwatch;
    }

    public int getColorVibrantLightSwatch() {
        return colorVibrantLightSwatch;
    }

    public int getColorMutedSwatch() {
        return colorMutedSwatch;
    }

    public int getColorMutedDarkSwatch() {
        return colorMutedDarkSwatch;
    }

    public int getColorMutedLightSwatch() {
        return colorMutedLightSwatch;
    }
}
