package com.example.mycoindiary

import com.google.firebase.database.Exclude

data class coinitem ( var objectId: String,val listyear:String, val listmonth:String, val listday:String, val liststart:String, val listend:String
                      , val listprofit: String, val listmemo:String )
{

    @Exclude
    fun toMap() : HashMap<String, Any> {
        val result: HashMap<String, Any> = HashMap()
        result["objectId"] = objectId
        result["listyear"] = listyear
        result["listmonth"] = listmonth
        result["listday"] = listday
        result["liststart"] = liststart
        result["listend"] = listend
        result["listprofit"] = listprofit
        result["listmemo"] = listmemo
        return result
    }
}
