package com.zerdasoftware.notesapp.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NotesReply(@SerializedName("notes")
                      @Expose
                      var notes:List<NotesModel>,
                      @SerializedName("success")
                      @Expose
                      var success:Int)  {
}