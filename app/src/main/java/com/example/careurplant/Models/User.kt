package com.example.careurplant.Models

class User {
    var image:String?=null
    var username:String?=null
    var email:String?=null
    var password:String?=null

    constructor()
    constructor(image: String?, UserName: String?, email: String?, password: String?) {
        this.image = image
        this.username = UserName
        this.email = email
        this.password = password
    }
    constructor(UserName: String?, email: String?, password: String?) {
        this.username = UserName
        this.email = email
        this.password = password
    }

    constructor(email: String?, password: String?) {
        this.email = email
        this.password = password
    }


}