package com.example.app_38249

import java.time.Year

class Model {


    var make : String? = null
    var model : String? = null
    var image : String? = null
    var price : String? = null
    var year : String? = null
    var km : String? = null
    var fuel : String? = null
    var transmission : String? = null
    var type:String? = null
    var seller_name : String? = null
    var contact : String? = null
    var email : String? = null




    constructor(make: String?, model: String?,price : String?,transmission : String?,km : String?,year : String?,image : String?,type : String?) {
        this.make = make
        this.model = model
        this.price = price
        this.year = year
        this.km = km
        this.transmission = transmission
        this.image = image
        this.type = type


    }

    constructor(){

    }


}