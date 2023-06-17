package com.john_halaka.littlelemon



import com.john_halaka.littlelemon.data.MenuItem

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



@Serializable
data class MenuNetwork(
    @SerialName("menu")
    val menu: List<MenuItem>
)
//
//suspend fun fetchMenuItems(): MenuNetwork? {
//    if (isNetworkAvailable()) {
//        val client = HttpClient(Android) {
//            install(Logging) {
//                logger = Logger.DEFAULT
//                level = LogLevel.ALL
//            }
//            install(ContentNegotiation) {
//                json(
//                    json = Json {
//                        ignoreUnknownKeys = true
//                        isLenient = true
//                        prettyPrint = true
//                    },
//                    contentType = ContentType("text", "plain")
//
//                )
//            }
//        }
//        val url = "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"
//        val menuItems : MenuNetwork = client.get(url).body()
//
//        return menuItems
//        }
//    else {
//
//        return null
//    }
//
//}
//fun isNetworkAvailable(context: Context): Boolean {
//    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//    val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
//    return capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
//}