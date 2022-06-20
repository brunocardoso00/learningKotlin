package com.example.guestlist.service.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.guestlist.service.constants.DataBaseConstants

class GuestDataBaseHelper(context: Context) : SQLiteOpenHelper(
    context, NAME, null,
    VERSION
) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_GUEST)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    companion object {
        private const val VERSION = 1
        private const val NAME = "guestdb"

        private const val CREATE_TABLE_GUEST =
            ("CREATE TABLE " + DataBaseConstants.GUEST.TABLE_NAME + "("
                    + DataBaseConstants.GUEST.COLUMNS.ID + " integer primary key autoincrement,"
                    + DataBaseConstants.GUEST.COLUMNS.NAME + " text,"
                    + DataBaseConstants.GUEST.COLUMNS.PRESENCE + " integer);")
    }
}