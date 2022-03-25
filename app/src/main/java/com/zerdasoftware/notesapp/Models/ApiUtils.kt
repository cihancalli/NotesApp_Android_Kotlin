package com.zerdasoftware.notesapp.Models


class ApiUtils {

    companion object{

        val BASE_URL = "http://cihancalli.com/"

        fun getNotesDAOInterface(): NotesDAOInterface {
            return RetrofitClient.getClient(BASE_URL).create(NotesDAOInterface::class.java)
        }
    }
}



