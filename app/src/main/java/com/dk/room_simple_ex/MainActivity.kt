package com.dk.room_simple_ex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.dk.room_simple_ex.db.TextDB
import com.dk.room_simple_ex.db.entity.TextEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * auto Migration
 * 자동으로 SQL 쿼리 생성
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = TextDB.getDatabase(this)
        val inputArea = findViewById<EditText>(R.id.textInputArea)

        val insertBtn = findViewById<Button>(R.id.insertBtn)
        val getAllBtn = findViewById<Button>(R.id.getDataBtn)
        val deleteBtn = findViewById<Button>(R.id.deleteBtn)

        insertBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.textDao().insert(TextEntity(0, inputArea.text.toString(), "text2"))
                inputArea.setText("")
            }
        }

        getAllBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                Log.d("MAIN", db.textDao().getAllData().toString())
            }
        }

        deleteBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.textDao().deleteAllData()
            }
        }

    }
}