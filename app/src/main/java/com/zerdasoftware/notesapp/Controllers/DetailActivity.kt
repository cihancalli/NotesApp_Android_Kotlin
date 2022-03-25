package com.zerdasoftware.notesapp.Controllers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import com.zerdasoftware.notesapp.Models.NotesModel
import com.zerdasoftware.notesapp.R
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        toolbarNoteDetail.title = "Notes Detail"
        setSupportActionBar(toolbarNoteDetail)

        val note = intent.getSerializableExtra("object") as NotesModel

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
                        tointent()
                    }.show()
                return true
            }
            R.id.action_edit -> {
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