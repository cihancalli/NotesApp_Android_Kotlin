package com.zerdasoftware.notesapp.Models

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable
@IgnoreExtraProperties
data class NotesModel (var note_id:String?="",
                       var lesson_name:String?="",
                       var note1:Int?=0,
                       var note2:Int?=0) : Serializable {
}