package com.zerdasoftware.notesapp.Controllers

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.zerdasoftware.notesapp.Models.NotesModel
import com.zerdasoftware.notesapp.R

class NotesAdapter(private val mContext:Context,
                   private val notesList:List<NotesModel>)
    : RecyclerView.Adapter<NotesAdapter.CardDesignHolder>() {

    inner class CardDesignHolder(design:View) : RecyclerView.ViewHolder(design){
        var note_card:CardView
        var textViewLesson:TextView
        var textViewNote1:TextView
        var textViewNote2:TextView

        init {
            note_card = design.findViewById(R.id.note_card)
            textViewLesson = design.findViewById(R.id.textViewLesson)
            textViewNote1 = design.findViewById(R.id.textViewNote1)
            textViewNote2 = design.findViewById(R.id.textViewNote2)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {
        val design = LayoutInflater.from(mContext).inflate(R.layout.card_design,parent,false)
        return CardDesignHolder(design)
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {
        var note = notesList.get(position)
        holder.textViewLesson.text = note.lesson_name
        holder.textViewNote1.text = (note.note1).toString()
        holder.textViewNote2.text = (note.note2).toString()

        holder.note_card.setOnClickListener {
            val intent = Intent(mContext,DetailActivity::class.java)
            intent.putExtra("object",note)
            mContext.startActivity(intent)
        }
    }



}