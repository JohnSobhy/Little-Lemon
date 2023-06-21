package com.john_halaka.littlelemon.data

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.john_halaka.littlelemon.MenuNetwork
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class MenuRepository(private val menuItemDao: MenuItemDao, private val context: Context) {


    suspend fun getMenuItems(): List<MenuItem> {
        val menuItems = menuItemDao.getAll()
        Log.d("MenuRepository", "getMenuItems returned: $menuItems")
        return menuItemDao.getAll()
    }
    suspend fun insertAll(menuItems: List<MenuItem>) {
        Log.d("MenuRepository", "insertAll called with menuItems: $menuItems")
        menuItemDao.insertAll(menuItems)
        val insertedItems = menuItemDao.getAll()
        Log.d("MenuRepository", "insertAll inserted items: $insertedItems")
    }

    suspend fun fetchMenuItems(): MenuNetwork? {
        Log.d("MenuRepository", "fetchMenuItems called")
        if (isNetworkAvailable(context)) {
            val client = HttpClient(Android) {
                install(Logging) {
                    logger = Logger.DEFAULT
                    level = LogLevel.ALL
                }
                install(ContentNegotiation) {
                    json(
                        json = Json {
                            ignoreUnknownKeys = true
                            isLenient = true
                            prettyPrint = true
                        },
                        contentType = ContentType("text", "plain")
                    )
                }
            }
            val url = "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"
            val menuItems: MenuNetwork = client.get(url).body()
            Log.d("MenuRepository", "menuItems: $menuItems")

            return menuItems
        } else {
            // Handle the absence of an internet connection here
            val menuItems = getMenuItems()
            // Convert the list of MenuItem objects to a MenuNetwork object and return it
            return MenuNetwork(menuItems)

        }
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        private var instance: MenuRepository? = null


        fun getInstance(applicationContext: Context): MenuRepository {
            val tempInstance = instance
            if (tempInstance != null) {
                return tempInstance
            } else {
                synchronized(this) {
                    val myDao = MenuItemDatabase.getInstance(applicationContext).menuItemDao()
                    instance = MenuRepository(myDao, applicationContext)
                    return instance!!
                }
            }

        }
    }
}
