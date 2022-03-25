package com.zerdasoftware.notesapp.Controllers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zerdasoftware.notesapp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_note_save.*

class NoteSaveActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_save)

        toolbarNoteSave.title = "Notes Save"
        setSupportActionBar(toolbar)

        buttonSave.setOnClickListener {
            startActivity(Intent(this@NoteSaveActivity,MainActivity::class.java))
            finish()
        }
    }
}