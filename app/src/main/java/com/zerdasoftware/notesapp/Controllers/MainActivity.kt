package com.zerdasoftware.notesapp.Controllers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.zerdasoftware.notesapp.Models.NotesModel
import com.zerdasoftware.notesapp.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var notesList: ArrayList<NotesModel>
    private lateinit var adapter: NotesAdapter
    private lateinit var refNotes: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.title = "Notes APP"
        setSupportActionBar(toolbar)

        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(this)

        val db = FirebaseDatabase.getInstance()
        refNotes = db.getReference("notes")

        notesList = ArrayList( )

        adapter = NotesAdapter(this,notesList)

        rv.adapter = adapter

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
        refNotes.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(d: DataSnapshot) {
                notesList.clear()
                var total = 0
                for (c in d.children){
                    val note = c.getValue(NotesModel::class.java)
                    if (note != null){
                        note.note_id = c.key
                        notesList.add(note)
                        total = total + (note.note1!! + note.note2!!)/2
                    }
                }
                adapter.notifyDataSetChanged()

                if (total != 0){
                    toolbar.subtitle = "Ortalama : ${total/notesList.size}"
                }
            }
            override fun onCancelled(e: DatabaseError) {

            }
        })
    }
}