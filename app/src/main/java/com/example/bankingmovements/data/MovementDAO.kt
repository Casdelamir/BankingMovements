package com.example.bankingmovements.data

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import com.example.bankingmovements.utils.DatabaseManager

class MovementDAO (val context: Context) {

    private val databaseManager: DatabaseManager = DatabaseManager(context)
    private val projection = arrayOf(BaseColumns._ID, Movement.COLUMN_NAME_QUANTITY, Movement.COLUMN_NAME_DATE)

    fun insert(movement: Movement) {
        val db = databaseManager.writableDatabase

        val values = ContentValues()
        values.put(Movement.COLUMN_NAME_QUANTITY, movement.quantity)
        values.put(Movement.COLUMN_NAME_DATE, movement.date)

        val newRowId = db.insert(Movement.TABLE_NAME, null, values)
        movement.id = newRowId.toInt()
    }

    fun update(movement: Movement) {
        val db = databaseManager.writableDatabase

        val values = ContentValues()
        values.put(Movement.COLUMN_NAME_QUANTITY, movement.quantity)
        values.put(Movement.COLUMN_NAME_DATE, movement.date)

        val updatedRows = db.update(
            Movement.TABLE_NAME,
            values,
            "${BaseColumns._ID} = ${movement.id}",
            null
        )
    }

    fun delete(movement: Movement) {
        val db = databaseManager.writableDatabase

        val deletedRows = db.delete(Movement.TABLE_NAME, "${BaseColumns._ID} = ${movement.id}", null)
    }

    @SuppressLint("Range")
    fun find(id: Int): Movement? {
        val db = databaseManager.readableDatabase

        val cursor = db.query(
            Movement.TABLE_NAME,                        // The table to query
            projection,                             // The array of columns to return (pass null to get all)
            BaseColumns._ID,     // The columns for the WHERE clause
            null,                         // The values for the WHERE clause
            null,                            // don't group the rows
            null,                             // don't filter by row groups
            null   // The sort order
        )

        var movement: Movement? = null
        if (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID))
            val quantity = cursor.getDouble(cursor.getColumnIndexOrThrow(Movement.COLUMN_NAME_QUANTITY))
            val date = cursor.getString(cursor.getColumnIndex(Movement.COLUMN_NAME_DATE))

            movement = Movement(id, quantity, date)
        }
        cursor.close()
        db.close()
        return movement
    }

    @SuppressLint("Range")
    fun findAll(): List<Movement> {
        val db = databaseManager.readableDatabase

        val cursor = db.query(
            Movement.TABLE_NAME,                        // The table to query
            projection,                             // The array of columns to return (pass null to get all)
            null,                            // The columns for the WHERE clause
            null,                         // The values for the WHERE clause
            null,                            // don't group the rows
            null,                             // don't filter by row groups
            null       // The sort order
        )

        val movements = mutableListOf<Movement>()
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID))
            val quantity = cursor.getDouble(cursor.getColumnIndexOrThrow(Movement.COLUMN_NAME_QUANTITY))
            val date = cursor.getString(cursor.getColumnIndex(Movement.COLUMN_NAME_DATE))

            val movement = Movement(id, quantity, date)
            movements.add(movement)
        }
        cursor.close()
        db.close()
        return movements
    }
}