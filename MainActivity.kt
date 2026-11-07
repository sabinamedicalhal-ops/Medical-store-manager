package com.example.medicalstore

import android.app.Activity
import android.os.Bundle
import android.graphics.Typeface
import android.view.Gravity
import android.widget.*

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = MedicineDbHelper(this)
        val pad = (16 * resources.displayMetrics.density).toInt()

        val page = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(pad, pad, pad, pad)
        }
        val title = TextView(this).apply {
            text = "Medical Store Manager"
            textSize = 24f
            setTypeface(null, Typeface.BOLD)
        }
        val subtitle = TextView(this).apply {
            text = "অফলাইন মেডিকেল স্টোর ম্যানেজমেন্ট"
            textSize = 15f
            setPadding(0, 6, 0, pad)
        }
        val search = EditText(this).apply { hint = "ওষুধ / কোম্পানি / ব্যাচ দিয়ে সার্চ" }
        val results = TextView(this).apply { textSize = 15f; setPadding(0, pad, 0, 0) }

        val addButton = Button(this).apply {
            text = "নতুন ওষুধ যোগ করুন"
            setOnClickListener { showAddMedicineDialog(db, results, search.text.toString()) }
        }
        val searchButton = Button(this).apply {
            text = "সার্চ"
            setOnClickListener { results.text = db.search(search.text.toString()) }
        }
        page.addView(title)
        page.addView(subtitle)
        page.addView(search)
        page.addView(searchButton)
        page.addView(addButton)
        page.addView(results)
        setContentView(ScrollView(this).apply { addView(page) })
        results.text = db.search("")
    }

    private fun showAddMedicineDialog(db: MedicineDbHelper, output: TextView, query: String) {
        val box = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; setPadding(24, 8, 24, 0) }
        val name = EditText(this).apply { hint = "ওষুধের নাম" }
        val company = EditText(this).apply { hint = "কোম্পানির নাম" }
        val batch = EditText(this).apply { hint = "ব্যাচ নম্বর" }
        val qty = EditText(this).apply { hint = "পরিমাণ"; inputType = 2 }
        val expiry = EditText(this).apply { hint = "এক্সপায়ারি (YYYY-MM-DD)" }
        listOf(name, company, batch, qty, expiry).forEach { box.addView(it) }
        AlertDialog.Builder(this)
            .setTitle("নতুন ওষুধ")
            .setView(box)
            .setNegativeButton("বাতিল", null)
            .setPositiveButton("সেভ") { _, _ ->
                if (name.text.isNotBlank()) {
                    db.insert(name.text.toString(), company.text.toString(), batch.text.toString(),
                        qty.text.toString().toIntOrNull() ?: 0, expiry.text.toString())
                    output.text = db.search("")
                } else Toast.makeText(this, "ওষুধের নাম লিখুন", Toast.LENGTH_SHORT).show()
            }.show()
    }
}
