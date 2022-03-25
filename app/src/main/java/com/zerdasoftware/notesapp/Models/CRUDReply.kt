package com.zerdasoftware.notesapp.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CRUDReply(@SerializedName("success")
                     @Expose
                     var success:Int,
                     @SerializedName("message")
                     @Expose
                     var message:String) {
}