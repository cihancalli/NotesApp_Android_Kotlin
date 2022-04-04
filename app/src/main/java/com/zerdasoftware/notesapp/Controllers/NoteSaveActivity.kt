package com.zerdasoftware.notesapp.Controllers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.zerdasoftware.notesapp.Models.NotesModel
import com.zerdasoftware.notesapp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_note_save.*

class NoteSaveActivity : AppCompatActivity() {

    private lateinit var refNotes: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_save)

        toolbarNoteSave.title = "Notes Save"
        setSupportActionBar(toolbarNoteSave)

        val db = FirebaseDatabase.getInstance()
        refNotes = db.getReference("notes")

        buttonSave.setOnClickListener {

            val lesson_name = editTextLessonSave.text.toString().trim()
            val note1 = editTextNote1Save.text.toString().trim()
            val note2 = editTextNote2Save.text.toString().trim()

            if (TextUtils.isEmpty(lesson_name)){
                Snackbar.make(toolbarNoteSave,"Enter the lesson name",Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(note1)){
                Snackbar.make(toolbarNoteSave,"Enter the note 1",Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(note2)){
                Snackbar.make(toolbarNoteSave,"Enter the note 2",Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val note = NotesModel("",lesson_name,note1.toInt(),note2.toInt())
            refNotes.push().setValue(note)

            startActivity(Intent(this@NoteSaveActivity,MainActivity::class.java))
            finish()
        }
    }
}