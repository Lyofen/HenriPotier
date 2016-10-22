package com.remytabardel.henripotier.services.database;

import android.content.Context;
import android.os.Environment;

import com.remytabardel.henripotier.utils.FileUtils;

/**
 * @author Remy Tabardel
 * provide all usefull methods to manage database
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

    /**
     * export to external directory, need WRITE_STORAGE permission
     * @param exportFolder
     * @return
     */
    public boolean export(String exportFolder) {
        if (exportFolder == null)
            return false;

        if (exportFolder.endsWith("/") == false)
            exportFolder += "/";

        String inFilename = getPath();
        String outFilename = getExportDirectory() + exportFolder + getFullName();

        return FileUtils.copy(inFilename, outFilename);
    }

    public String getExportDirectory() {
        return Environment.getExternalStorageDirectory() + "/";
    }

    public String getFullName() {
        return NAME + EXT;
    }


    public Transaction newTransaction() {
        return new Transaction(NAME);
    }

}
