package models

class User {
    var image:String?=null
    var name:String?=null
    var email:String?=null
    var password:String?=null
    var location:String?=null
    constructor()
    constructor(
        image: String?,
        name: String?,
        email: String?,
        password: String?,
        location: String?
    ) {
        this.image = image
        this.name = name
        this.email = email
        this.password = password
        this.location = location
    }

    constructor(name: String?, email: String?, password: String?, location: String?) {
        this.name = name
        this.email = email
        this.password = password
        this.location = location
    }


}