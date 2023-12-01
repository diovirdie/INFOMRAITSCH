package com.example.infomraitsch
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
class MiFirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Procesar el mensaje recibido y mostrar la notificación
        // Puedes acceder a los datos del mensaje con remoteMessage.data
        mostrarNotificacion(remoteMessage.notification?.title, remoteMessage.notification?.body)
    }
    override fun onNewToken(token: String) {
        // Manejar la generación de nuevo token aquí
        Log.d(TAG, "Nuevo token: $token")

        // Puedes enviar el nuevo token al servidor si es necesario
        // Aquí es donde tendrías lógica para enviar el token a tu servidor
    }
    companion object {
        private const val TAG = "MiFirebaseMessaging"
    }


    private fun mostrarNotificacion(title: String?, body: String?) {
        // Código para mostrar la notificación
        // Puedes utilizar NotificationManager y NotificationCompat para mostrar la notificación
    }

}