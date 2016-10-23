package com.remytabardel.henripotier.services.database;

import android.content.Context;

/**
 * @author Remy Tabardel
 *         provide all usefull methods to manage database
 */

@com.raizlabs.android.dbflow.annotation.Database(name = Database.NAME, version = Database.VERSION)
public class Database {
    public static final String NAME = "AppDatabase";
    public static final String EXT = ".db";
    public static final int VERSION = 1;

    private final Context mContext;

    public Database(Context context) {
        mContext = context;
    }

    public String getPath() {
        return mContext.getDatabasePath(getFullName()).getAbsolutePath();
    }

    public String getFullName() {
        return NAME + EXT;
    }


    public Transaction newTransaction() {
        return new Transaction(NAME);
    }

}
