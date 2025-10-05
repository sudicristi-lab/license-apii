// Minimal companion app (Kotlin) - registers device with server and FCM
package com.example.licensecompanion
import android.app.Application
import com.google.firebase.messaging.FirebaseMessaging
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class LicenseCompanionApp : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) return@addOnCompleteListener
            val token = task.result
            val licenseId = "TEST_LICENSE"
            val deviceId = android.provider.Settings.Secure.getString(contentResolver, android.provider.Settings.Secure.ANDROID_ID)
            sendActivateRequest(token, licenseId, deviceId)
        }
    }
    fun sendActivateRequest(fcmToken: String, licenseId: String, deviceId: String) {
        val client = OkHttpClient()
        val json = JSONObject()
        json.put("licenseId", licenseId)
        json.put("deviceId", deviceId)
        json.put("fcmToken", fcmToken)
        val body = RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), json.toString())
        val req = Request.Builder().url("https://YOUR_SERVER_URL/activate").post(body).build()
        client.newCall(req).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) { response.close() }
        })
    }
}