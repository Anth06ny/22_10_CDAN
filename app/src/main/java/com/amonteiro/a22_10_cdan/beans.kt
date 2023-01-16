import java.util.*

fun main() {
    val randomName = RandomName()
    randomName.add("Toto")
    repeat(10) {
        println(randomName.next() + " ")
    }
}

class RandomName {

    private val list = arrayListOf("Bob", "Gustave", "Bobby")
    private var lastSend = ""

    fun add(s: String) =  if(s.isNotBlank() && s !in list)  list.add(s) else false

    fun next() = list.random()


    fun nextDiff(): String {
        var value = next()
        while(value == lastSend) {
            value = next()
        }
        lastSend = value
        return value
    }

    fun nextDiffV2() = list.filter { it != lastSend }.random().also {
        lastSend = it
    }

    fun next2() = Pair(nextDiff(), nextDiff())
}

class PlaneBean(name : String){
    var id = name.hashCode()
        private set

    var name = name
        set(value) {
            field = value
            id = value.hashCode()
        }
}


class UserBean(val name : String, var note: Int = 0){
    val id = name.hashCode()
    //constructor(name:String ) : this(name, 0)
}

class PrintRandomIntBean(var max : Int){
    var random = Random()

    init {
        println("init")
        println(random.nextInt(max))
        println(random.nextInt(max))
        println(random.nextInt(max))
    }

    constructor() : this(100) {
        println("constructor")
        println(random.nextInt(max))
    }
}

class StudentBean(val name : String) {
    var note : Int = 0
}



class CarBean (var marque : String, var model : String, var couleur : String) {
    var at : String?  = null

}

class CarBean2 {
    var marque : String = ""
    var model : String?  = ""
    var couleur : String? = null
}

/* -------------------------------- */
// API Weather Bean
/* -------------------------------- */

data class WeatherBean(var name:String, var main: TempBean, var wind:WindBean)

data class TempBean(var temp : Double)

data class WindBean(var speed:Double)

/* -------------------------------- */
// API City
/* -------------------------------- */
data class ResultBean(
    val results: List<CityBean>
)

data class CityBean(
    val cp: String,
    val ville: String
)
