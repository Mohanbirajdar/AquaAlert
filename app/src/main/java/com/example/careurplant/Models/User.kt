package com.example.careurplant.Models

import android.location.Location

class User {
    var image:String?=null
    var username:String?=null
    var email:String?=null
    var password:String?=null
    var location:String?=null

    constructor()
    constructor(image: String?, UserName: String?, email: String?, password: String?, location: String?) {
        this.image = image
        this.username = UserName
        this.email = email
        this.password = password
        this.location=location
    }
    constructor(UserName: String?, email: String?, password: String?,location: String?) {
        this.username = UserName
        this.email = email
        this.password = password
        this.location=location
    }

    constructor(email: String?, password: String?) {
        this.email = email
        this.password = password
    }


}