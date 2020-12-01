package mx.uaa.soundmemory

import android.app.Application
import android.content.Context
import androidx.room.Room

class AccederApp(val mContext: Context) : Application() {
    //fallback se puede quitar
    val room = Room.databaseBuilder(mContext, BaseDb::class.java, "descripcion").fallbackToDestructiveMigration().build()
}