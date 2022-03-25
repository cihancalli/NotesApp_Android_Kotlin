package com.zerdasoftware.notesapp.Controllers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.zerdasoftware.notesapp.Models.ApiUtils
import com.zerdasoftware.notesapp.Models.NotesDAOInterface
import com.zerdasoftware.notesapp.Models.NotesModel
import com.zerdasoftware.notesapp.Models.NotesReply
import com.zerdasoftware.notesapp.R
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var notesList: ArrayList<NotesModel>
    private lateinit var adapter: NotesAdapter

    private lateinit var NDI: NotesDAOInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.title = "Notes APP"
        setSupportActionBar(toolbar)

        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(this)

        NDI = ApiUtils.getNotesDAOInterface()
        AllNotes()

        fab.setOnClickListener {
            startActivity(Intent(this@MainActivity,NoteSaveActivity::class.java))
        }

    }

    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    fun AllNotes(){
        NDI.AllNotes().enqueue(object : Callback<NotesReply>{
            override fun onResponse(call: Call<NotesReply>?, response: Response<NotesReply>?) {
                if (response != null){
                    val list = response.body().notes
                    adapter = NotesAdapter(this@MainActivity,list)
                    rv.adapter = adapter

                    var total = 0.0
                    for (n in list){
                        total = total + (n.note1 + n.note2)/2
                    }
                    toolbar.subtitle = "Average : ${total/list.size}"
                }
            }

            override fun onFailure(call: Call<NotesReply>?, t: Throwable?) {
            }
        })
    }
}