package com.remytabardel.henripotier.services.database;

import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

/**
 * @author Remy Tabardel
 * We build this class to avoid link between DBFlow and UI, if we need later to changer ORM etc..
 */

public class Transaction {
    DatabaseWrapper mDatabaseWrapper;

    public Transaction(String databaseName) {
        DatabaseDefinition databaseDefinition = FlowManager.getDatabase(databaseName);
        mDatabaseWrapper = databaseDefinition.getWritableDatabase();
    }

    public void begin() {
        mDatabaseWrapper.beginTransaction();
    }

    public void setSuccessful() {
        mDatabaseWrapper.setTransactionSuccessful();
    }

    public void end() {
        mDatabaseWrapper.endTransaction();
    }
}
