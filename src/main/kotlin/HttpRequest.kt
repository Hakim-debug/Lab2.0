import com.example.whocall.Observer
import com.google.gson.Gson
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpRequest.newBuilder
import java.net.http.HttpResponse
import java.time.Duration
class HttpRequest(var subscriptions: Subscriptions) : Observer() {
    init {
        this.subscriptions.subscribe(this)
    }
    private fun auth (): String {
        var result=""
        try {
            val body="""
                {
                "email":"dat67.g@fdgmaikdl.com",
                 "password":"Mother_30",
                 "telephoneNumber":"49"
                }
            """.trimIndent()
            val client = HttpClient.newHttpClient()
            val request = newBuilder()
                .uri(URI.create("http://13.48.136.153/v1/users/auth"))
                .header("Content-Type", "application/json").
                timeout(Duration.ofMinutes(1)).POST(HttpRequest.BodyPublishers.ofString(body)). build()

            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply { obj: HttpResponse<*> -> result= obj.body() as String }
                .join()
        }catch (e: ErrorHandler){
             print(e.status400())
        }
        val gson = Gson()
        val userJson = gson.fromJson(result, Data::class.java)
        val user = gson.toJson(userJson.user)
        val token= gson.fromJson(user, Data::class.java)
         return token._token
    }
    fun chatTo ():String{

        var result=""
        val tokenizer=auth();
      try {
          val body="""
                {
                "email":"dat67.g@fdgmaikdl.com",
                 "password":"Mother_30",
                 "telephoneNumber":"49",
                 "text":"Heloo",
                 "fromUser":"dat67.g@fdgmaikdl.com",
                 "toUser":"dot.dotyfdg@fdgmaikdl.com"
                }
            """.trimIndent()
            val client = HttpClient.newHttpClient()
            val request = newBuilder()
                .uri(URI.create("http://13.48.136.153/v1/chats"))
                .setHeader("Authorization","Bearer "+tokenizer)
                .header("Content-Type", "application/json").
                timeout(Duration.ofMinutes(1)).POST(HttpRequest.BodyPublishers.ofString(body)). build()
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply { obj: HttpResponse<*> -> result= obj.body() as String }
                .join()
        }catch (e: ErrorHandler){
            print(e)
        }
        subscriptions.setState(result);
        return result
    }
    fun receivedFrom():String{
        var result=""
        var tokenizer=auth();
        try {
            val body="""
                {
                "email":"dat67.g@fdgmaikdl.com",
                 "password":"Mother_30",
                 "telephoneNumber":"49",
                 "text":"Heloo",
                 "fromUser":"dat67.g@fdgmaikdl.com",
                 "toUser":"dot.dotyfdg@fdgmaikdl.com"
                }
            """.trimIndent()
            val client = HttpClient.newHttpClient()
            val request = newBuilder()
                .uri(URI.create("http://13.48.136.153/v1/chats/received/from"))
                .setHeader("Authorization","Bearer "+tokenizer)
                .header("Content-Type", "application/json").
                timeout(Duration.ofMinutes(1)).POST(HttpRequest.BodyPublishers.ofString(body)). build()
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply { obj: HttpResponse<*> -> result= obj.body() as String }
                .join()
        }catch (e: Exception){
            print(e)
        }
        subscriptions.setState(result);
        return result
    }
    fun chatSentTo():String{
        var result=""
        val tokenizer=auth();
        try {
            val body="""
                {
                "email":"dat67.g@fdgmaikdl.com",
                 "password":"Mother_30",
                 "telephoneNumber":"49",
                 "text":"Heloo",
                 "fromUser":"dat67.g@fdgmaikdl.com",
                 "toUser":"dot.dotyfdg@fdgmaikdl.com"
                }
            """.trimIndent()
            val client = HttpClient.newHttpClient()
            val request = newBuilder()
                .uri(URI.create("http://13.48.136.153/v1/chats/sent/to"))
                .setHeader("Authorization","Bearer "+tokenizer)
                .header("Content-Type", "application/json").
                timeout(Duration.ofMinutes(1)).POST(HttpRequest.BodyPublishers.ofString(body)). build()
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply { obj: HttpResponse<*> -> result= obj.body() as String }
                .join()
        }catch (e: ErrorHandler){
            print(e)
        }
        subscriptions.setState(result);
        return result
    }

    override fun update() {
        println(subscriptions.getState())
    }
}
data class Data(
    val userId: Int,
    val password: String,
    val telephoneNumber: String,
    val gender: Int,
    val birthday: Long,
    val age: Int,
    val province: String,
    val city: String,
    val district: String,
    val workStatus: String,
    val userType: Int,
    var user:Any,
    var _token:String,
    val items: Array<String>,
    val text: String,
    val fromUser: String,
    val email:String,

    val toUser: String,
)