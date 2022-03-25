package com.zerdasoftware.notesapp.Controllers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.zerdasoftware.notesapp.Models.NotesModel
import com.zerdasoftware.notesapp.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var notesList: ArrayList<NotesModel>
    private lateinit var adapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.title = "Notes APP"
        toolbar.subtitle = "Ortalama : 60"
        setSupportActionBar(toolbar)

        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(this)

        notesList = ArrayList( )

        val n1 = NotesModel(1,"History",50,70)
        val n2 = NotesModel(2,"Mathematics",40,80)
        val n3 = NotesModel(3,"Chemistry",60,60)

        notesList.add(n1)
        notesList.add(n2)
        notesList.add(n3)

        adapter = NotesAdapter(this,notesList)

        rv.adapter = adapter

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
}