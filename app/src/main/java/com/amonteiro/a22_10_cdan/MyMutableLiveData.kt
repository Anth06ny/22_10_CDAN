package com.amonteiro.a22_10_cdan

var errorMessage = MyMutableLiveData()

fun main(){
   errorMessage.postValue("coucou")
   errorMessage.postValue("coucou2")
   observe()
    errorMessage.postValue("coucou3")
}

fun observe(){
    errorMessage.observer = {
        println(it)
    }
}


class MyMutableLiveData  {

    var data : String = ""
    var observer : ((String) -> Unit)? = null
        set(value) {
            field = value
            //déclanche l'observateur à l'abonnement
            field?.invoke(data)
        }

    fun postValue(newData : String){
        data = newData
        observer?.invoke(newData)
    }

}