package com.example.medicalstore

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MedicineDbHelper(context: Context) :
    SQLiteOpenHelper(context, "medical_store.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            """CREATE TABLE medicines (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                company TEXT,
                batch_no TEXT,
                quantity INTEGER NOT NULL DEFAULT 0,
                expiry_date TEXT
            )"""
        )
        db.execSQL("CREATE INDEX idx_medicine_name ON medicines(name)")
        db.execSQL("CREATE INDEX idx_batch_no ON medicines(batch_no)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Add versioned migration scripts here before releasing updates.
    }

    fun insert(name: String, company: String, batch: String, quantity: Int, expiry: String) {
        val values = ContentValues().apply {
            put("name", name); put("company", company); put("batch_no", batch)
            put("quantity", quantity); put("expiry_date", expiry)
        }
        writableDatabase.insert("medicines", null, values)
    }

    fun search(query: String): String {
        val cursor = readableDatabase.rawQuery(
            "SELECT name, company, batch_no, quantity, expiry_date FROM medicines " +
                "WHERE name LIKE ? OR company LIKE ? OR batch_no LIKE ? ORDER BY name",
            arrayOf("%$query%", "%$query%", "%$query%")
        )
        val lines = mutableListOf<String>()
        cursor.use {
            while (it.moveToNext()) {
                lines.add(
                    "${it.getString(0)} | ${it.getString(1) ?: ""}\n" +
                    "Batch: ${it.getString(2) ?: ""} | Qty: ${it.getInt(3)} | Expiry: ${it.getString(4) ?: ""}"
                )
            }
        }
        return if (lines.isEmpty()) "কোনো ওষুধ পাওয়া যায়নি" else lines.joinToString("\n\n")
    }
}
