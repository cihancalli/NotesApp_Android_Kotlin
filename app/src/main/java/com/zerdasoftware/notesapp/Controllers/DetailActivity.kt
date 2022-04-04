package com.zerdasoftware.notesapp.Controllers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.zerdasoftware.notesapp.Models.NotesModel
import com.zerdasoftware.notesapp.R
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_note_save.*

class DetailActivity : AppCompatActivity() {

    private lateinit var note:NotesModel
    private lateinit var refNotes: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        toolbarNoteDetail.title = "Notes Detail"
        setSupportActionBar(toolbarNoteDetail)

        val db = FirebaseDatabase.getInstance()
        refNotes = db.getReference("notes")

         note = intent.getSerializableExtra("object") as NotesModel

        editTextLessonDetail.setText(note.lesson_name)
        editTextNote1Detail.setText((note.note1).toString())
        editTextNote2Detail.setText((note.note2).toString())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.action_delete -> {
                Snackbar.make(toolbarNoteDetail,"Will you delete it?",Snackbar.LENGTH_SHORT)
                    . setAction("YES"){
                        refNotes.child(note.note_id!!).removeValue()
                        tointent()
                    }.show()
                return true
            }
            R.id.action_edit -> {

                val lesson_name = editTextLessonDetail.text.toString().trim()
                val note1 = editTextNote1Detail.text.toString().trim()
                val note2 = editTextNote2Detail.text.toString().trim()

                if (TextUtils.isEmpty(lesson_name)){
                    Snackbar.make(toolbarNoteDetail,"Enter the lesson name",Snackbar.LENGTH_SHORT).show()
                    return false
                }
                if (TextUtils.isEmpty(note1)){
                    Snackbar.make(toolbarNoteDetail,"Enter the note 1",Snackbar.LENGTH_SHORT).show()
                    return false
                }
                if (TextUtils.isEmpty(note2)){
                    Snackbar.make(toolbarNoteDetail,"Enter the note 2",Snackbar.LENGTH_SHORT).show()
                    return false
                }

                val info = HashMap<String,Any>()
                info.put("lesson_name",lesson_name)
                info.put("note1",note1.toInt())
                info.put("note2",note2.toInt())

                refNotes.child(note.note_id!!).updateChildren(info)

                tointent()
                return true
            }
            else -> return false
        }
    }
    fun tointent() {
        startActivity(Intent(this@DetailActivity,MainActivity::class.java))
        finish()
    }
}