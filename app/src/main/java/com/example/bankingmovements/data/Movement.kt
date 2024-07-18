package com.example.bankingmovements.data

import android.provider.BaseColumns
import java.util.Date

class Movement (var id: Int, var quantity: Double, var date: String, var positive: Boolean) {

    companion object {
        const val TABLE_NAME = "Movement"
        const val COLUMN_NAME_QUANTITY = "quantity"
        const val COLUMN_NAME_DATE = "date"
        const val COLUMN_NAME_POSITIVE = "POSITIVE"

        const val SQL_CREATE_TABLE =
            "CREATE TABLE $TABLE_NAME (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COLUMN_NAME_QUANTITY REAL," +
                    "$COLUMN_NAME_DATE TEXT," +
                    "$COLUMN_NAME_POSITIVE INTEGER)"


        const val SQL_DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
    }
}