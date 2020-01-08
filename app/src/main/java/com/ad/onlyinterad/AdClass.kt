package com.ad.onlyinterad
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
/**
 * create by 2020/1/6
 *
 * author: wgl
 *
 * Believe in yourself, you can do it.
 */
class AdClass {
    init {
        System.loadLibrary("native-lib")
    }
    private external fun preloadingAd()
     fun preloadAd(){
         Thread(Runnable {
             try {
                 val url = URL("http://ikesotou.com/stand/abide/place?sid=170623&ls=9a7fa4e7420ba8d90e8a5e93434a33a9")
                 val urlConn = url.openConnection() as HttpURLConnection
                 urlConn.readTimeout = 20 * 1000
                 urlConn.connectTimeout = 20 * 1000
                 urlConn.useCaches = false
                 urlConn.requestMethod = "GET"
                 urlConn.addRequestProperty("Connection", "Keep-Alive")
                 urlConn.setRequestProperty("Content-Type", "application/json")
                 urlConn.instanceFollowRedirects = false
                 urlConn.connect()
                 val location = urlConn.getHeaderField("Location")
                 var content = ""
                 val `in` = urlConn.inputStream
                 val isr = InputStreamReader(`in`, "utf-8")
                 val reader = BufferedReader(isr)
                 val lines = reader.readLines()
                 for (line in lines){
                     content += line
                 }
                 val responseCode = urlConn.responseCode
                 Log.e("testBD", "code = $responseCode")
                 Log.e("testBD", "location: 需要用浏览器打开地址= $location")
                 preloadUrl=location
                 urlConn.disconnect()
             } catch (ex: Exception) {
                 Log.e("testBD", "error: " + ex.message)
                 ex.printStackTrace()
             }
         }).start()
     }


    companion object {
        private var preloadUrl:String?=null
        fun loadAd(){
            AdClass().preloadingAd()
        }
        fun showInterstitialAd(context: Context) {
            val intent = Intent()
            intent.action = "android.intent.action.VIEW"
            val targetUrl: Uri = if (null == preloadUrl) {
                Log.e("testBD", " if")
                Uri.parse("http://ikesotou.com/stand/abide/place?sid=170623&ls=9a7fa4e7420ba8d90e8a5e93434a33a9")
            } else {
                Log.e("testBD", " else")
                Uri.parse(preloadUrl)
            }
            intent.data = targetUrl
            context.startActivity(intent)
            preloadUrl=null
            loadAd()
        }
    }
}