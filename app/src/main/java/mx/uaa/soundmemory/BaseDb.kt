package mx.uaa.soundmemory


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.prueba.DescripcionDao

@Database(
    entities = arrayOf(Descripcion::class),
    version = 1
)

abstract class BaseDb: RoomDatabase() {
    abstract fun descripcionDao(): DescripcionDao
}