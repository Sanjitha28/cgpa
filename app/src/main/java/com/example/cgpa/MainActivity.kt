package com.example.cgpa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val etname : EditText = findViewById(R.id.name)
        val etcgpa : EditText = findViewById(R.id.cgpa)
        val btsave : Button = findViewById(R.id.save)
        val btload : Button = findViewById(R.id.load)

        btsave.setOnClickListener {
            val name = etname.text.toString()
            val cgpa = etcgpa.text.toString()

            val file = File(getExternalFilesDir(null),"student.txt")
            val fos = FileOutputStream(file, false)
            fos.write("$name, $cgpa".toByteArray())
            fos.close()
            etname.setText("")
            etcgpa.setText("")
            Toast.makeText(this@MainActivity, "Info Added", Toast.LENGTH_LONG).show()
        }
        btload.setOnClickListener {
            val file = File(getExternalFilesDir(null),"student.txt")
            val fis = FileInputStream(file)
            val isr = InputStreamReader(fis)
            val br = BufferedReader(isr)
            val line : String
            line = br.readLine()
            var parts = line.split(",")
            etname.setText(parts[0])
            etcgpa.setText(parts[1])
            Toast.makeText(this@MainActivity,"Info loaded", Toast.LENGTH_LONG).show()
        }
    }
}