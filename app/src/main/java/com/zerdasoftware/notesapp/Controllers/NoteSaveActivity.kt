package com.zerdasoftware.notesapp.Controllers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.google.android.material.snackbar.Snackbar
import com.zerdasoftware.notesapp.Models.ApiUtils
import com.zerdasoftware.notesapp.Models.CRUDReply
import com.zerdasoftware.notesapp.Models.NotesDAOInterface
import com.zerdasoftware.notesapp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_note_save.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NoteSaveActivity : AppCompatActivity() {
    private lateinit var NDI: NotesDAOInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_save)

        toolbarNoteSave.title = "Notes Save"
        setSupportActionBar(toolbar)

        NDI = ApiUtils.getNotesDAOInterface()

        buttonSave.setOnClickListener {

            var lesson_name = editTextLessonSave.text.toString().trim()
            var note1 = editTextNote1Save.text.toString().trim()
            var note2 = editTextNote2Save.text.toString().trim()

            if (TextUtils.isEmpty(lesson_name)){
                Snackbar.make(toolbarNoteSave,"Lesson name cannot be blank",Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(note1)){
                Snackbar.make(toolbarNoteSave,"Note 1 cannot be blank",Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(note2)){
                Snackbar.make(toolbarNoteSave,"Note 2 cannot be blank",Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            NDI.InsertNote(lesson_name,note1.toInt(),note2.toInt()).enqueue(object:Callback<CRUDReply>{
                override fun onResponse(call: Call<CRUDReply>?, response: Response<CRUDReply>?) {

                }

                override fun onFailure(call: Call<CRUDReply>?, t: Throwable?) {
                }
            })

            startActivity(Intent(this@NoteSaveActivity,MainActivity::class.java))
            finish()
        }
    }
}