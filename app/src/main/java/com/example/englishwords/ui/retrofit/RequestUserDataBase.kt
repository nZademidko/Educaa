package com.example.englishwords.ui.retrofit


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class User{


    @SerializedName("id")
    @Expose
    private var id: Int = 0

    @SerializedName("email")
    @Expose
    private var email: String = ""


    @SerializedName("status")
    @Expose
    private var status: Int = 1

    @SerializedName("error")
    @Expose
    private var error: String = ""


    open fun getError(): String{
        return error
    }

    open fun getStatus():Int{
        return status
    }

    open fun getId(): Int{
        return id
    }


    open fun getEmail(): String{
        return email
    }

    open fun setId(id: Int){
        this.id = id
    }

    open fun setEmail(){
        this.email = email
    }
}