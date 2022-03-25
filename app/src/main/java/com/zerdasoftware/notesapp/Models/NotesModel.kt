package com.zerdasoftware.notesapp.Models

import java.io.Serializable

data class NotesModel (var note_id:Int,
                       var lesson_name:String,
                       var note1:Int,
                       var note2:Int) : Serializable {
}