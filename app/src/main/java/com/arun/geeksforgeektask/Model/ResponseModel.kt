package com.arun.geeksforgeektask.Model

import java.io.Serializable


/**
// Created by Arun Singh Rawat  on 28-03-2021.



 **/

class ResponseModel : Serializable {
    var status : String ? = null
    var feed = NewsFeed()
    var items = ArrayList<ItemList>()


}

class ItemList : Serializable{
    var title : String ? = null
    var pubDate : String ? = null
    var link : String ? = null
    var guid : String ? = null
    var author : String ? = null
    var thumbnail : String ? = null
    var description : String ? = null
    var enclosure  = Enclosure()
     var categories = ArrayList<String>()


}



class Enclosure : Serializable{
    var link : String ? = null
    var type : String ? = null
    var thumbnail : String ? = null
}

class NewsFeed : Serializable {
    var url : String ? = null
    var title : String ? = null
    var link : String ? = null
    var author : String ? = null
    var description : String ? = null
    var image : String ? = null

}
