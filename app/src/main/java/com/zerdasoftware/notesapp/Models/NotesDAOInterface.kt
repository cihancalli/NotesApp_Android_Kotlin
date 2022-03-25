package com.zerdasoftware.notesapp.Models

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface NotesDAOInterface {

    @GET("NotesApp/all_notes.php")
    fun AllNotes() : Call<NotesReply>

    @POST("NotesApp/delete_note.php")
    @FormUrlEncoded
    fun DeleteNote(@Field("note_id") note_id:Int) :Call<CRUDReply>

    @POST("NotesApp/insert_note.php")
    @FormUrlEncoded
    fun InsertNote(@Field("lesson_name") lesson_name:String,
                   @Field("note1") note1:Int,
                   @Field("note2") note2:Int) :Call<CRUDReply>

    @POST("NotesApp/update_note.php")
    @FormUrlEncoded
    fun UpdateNote(@Field("note_id") note_id:Int,
                   @Field("lesson_name") lesson_name:String,
                   @Field("note1") note1:Int,
                   @Field("note2") note2:Int) :Call<CRUDReply>

}