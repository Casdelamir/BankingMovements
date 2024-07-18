package com.example.bankingmovements.data

import android.provider.BaseColumns
import java.util.Date

class Movement (var id: Int, var quantity: Double, var date: String) {

    companion object {
        const val TABLE_NAME = "Movement"
        const val COLUMN_NAME_QUANTITY = "quantity"
        const val COLUMN_NAME_DATE = "date"

        const val SQL_CREATE_TABLE =
            "CREATE TABLE $TABLE_NAME (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COLUMN_NAME_QUANTITY REAL," +
                    "$COLUMN_NAME_DATE TEXT)"

        const val SQL_DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
    }
}