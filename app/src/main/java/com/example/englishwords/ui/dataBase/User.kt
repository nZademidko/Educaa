package com.example.englishwords.ui.dataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class UserDataBase {

    @PrimaryKey
    private var id: Int = 0
    private var email: String = ""

    fun getId(): Int = id

    fun getEmail(): String = email

    fun setEmail(email: String) {
        this.email = email
    }

    fun setId(id: Int){
        this.id = id
    }


}