package com.zerdasoftware.notesapp.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NotesModel ( @SerializedName("note_id")
                        @Expose
                        var note_id:Int,
                        @SerializedName("lesson_name")
                        @Expose
                        var lesson_name:String,
                        @SerializedName("note1")
                        @Expose
                        var note1:Int,
                        @SerializedName("note2")
                        @Expose
                        var note2:Int) : Serializable {
}