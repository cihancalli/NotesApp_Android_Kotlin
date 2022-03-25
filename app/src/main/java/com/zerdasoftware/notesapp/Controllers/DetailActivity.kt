package com.zerdasoftware.notesapp.Controllers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import com.zerdasoftware.notesapp.Models.ApiUtils
import com.zerdasoftware.notesapp.Models.CRUDReply
import com.zerdasoftware.notesapp.Models.NotesDAOInterface
import com.zerdasoftware.notesapp.Models.NotesModel
import com.zerdasoftware.notesapp.R
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_note_save.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {
    private lateinit var NDI: NotesDAOInterface
    private lateinit var note:NotesModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        toolbarNoteDetail.title = "Notes Detail"
        setSupportActionBar(toolbarNoteDetail)

        NDI = ApiUtils.getNotesDAOInterface()

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

                        NDI.DeleteNote(note.note_id).enqueue(object: Callback<CRUDReply>{
                            override fun onResponse(call: Call<CRUDReply>?, response: Response<CRUDReply>?) {
                            }
                            override fun onFailure(call: Call<CRUDReply>?, t: Throwable?) {
                            }
                        })

                        tointent()
                    }.show()
                return true
            }
            R.id.action_edit -> {

                var lesson_name = editTextLessonDetail.text.toString().trim()
                var note1 = editTextNote1Detail.text.toString().trim()
                var note2 = editTextNote2Detail.text.toString().trim()

                if (TextUtils.isEmpty(lesson_name)){
                    Snackbar.make(toolbarNoteDetail,"Lesson name cannot be blank",Snackbar.LENGTH_SHORT).show()
                    return false
                }

                if (TextUtils.isEmpty(note1)){
                    Snackbar.make(toolbarNoteDetail,"Note 1 cannot be blank",Snackbar.LENGTH_SHORT).show()
                    return false
                }

                if (TextUtils.isEmpty(note2)){
                    Snackbar.make(toolbarNoteDetail,"Note 2 cannot be blank",Snackbar.LENGTH_SHORT).show()
                    return false
                }

                NDI.UpdateNote(note.note_id,lesson_name,note1.toInt(),note2.toInt()).enqueue(object:Callback<CRUDReply>{
                    override fun onResponse(call: Call<CRUDReply>?, response: Response<CRUDReply>?) {

                    }

                    override fun onFailure(call: Call<CRUDReply>?, t: Throwable?) {
                    }
                })



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