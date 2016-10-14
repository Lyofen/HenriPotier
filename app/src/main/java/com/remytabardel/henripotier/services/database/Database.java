package com.remytabardel.henripotier.services.database;

import android.content.Context;
import android.os.Environment;

import com.remytabardel.henripotier.utils.FileUtils;

/**
 * @author Remy Tabardel
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

    public boolean export(String exportFolder) {
        if(exportFolder == null)
            return false;

        String inFilename = getPath();
        String outFilename = Environment.getExternalStorageDirectory() + "/" + exportFolder + getFullName();

        return FileUtils.copy(inFilename, outFilename);
    }

    public String getFullName() {
        return NAME + EXT;
    }

}
