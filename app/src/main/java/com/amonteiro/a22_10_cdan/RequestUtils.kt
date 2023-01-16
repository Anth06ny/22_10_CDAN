import RequestUtils.gson
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

const val URL_API_WEATHER = "https://api.openweathermap.org/data/2.5/weather?appid=b80967f0a6bd10d23e44848547b26550&units=metric&lang=fr&q="
const val URL_API_CITY = "https://www.citysearch-api.com/fr/city?login=webserviceexemple&apikey=sof940dd26cf107eabf8bf6827f87c3ca8e8d82546&cp="

object RequestUtils {
    val client = OkHttpClient()
    val gson = Gson()

    fun loadCities(cp:String) : List<CityBean>{
        //Contrôle de donnée
        //construire requete
        //Faire la requete
        val json = sendGet(URL_API_CITY + cp)
        //Parse le résultat
        var weather = gson.fromJson(json, ResultBean::class.java)
        //Contrôle
        //Extraction

        return weather.results
    }

    fun loadWeather(city:String) : WeatherBean{
        //Contrôle de donnée
        //construire requete
        //Faire la requete
        val json = sendGet(URL_API_WEATHER + city)
        //Parse le résultat
        var weather : WeatherBean = gson.fromJson(json, WeatherBean::class.java)
        //Contrôle
        //Extraction

        return weather
    }

    //Méthode qui prend en entrée une url, execute la requête et retourne le code HTML/JSON reçu
    fun sendGet(url: String): String {
        println("url : $url")
        //Création de la requête
        val request = Request.Builder().url(url).build()
        //Execution de la requête
        return client.newCall(request).execute().use { //it:Response
            //use permet de fermer la réponse qu'il y ait ou non une exception
            //Analyse du code retour
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect :${it.code}")
            }
            //Résultat de la requête
            it.body.string()
        }
    }

}
