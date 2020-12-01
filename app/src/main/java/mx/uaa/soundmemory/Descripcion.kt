package mx.uaa.soundmemory

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Descripcion(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    var descrip: String,
    val ruta: String
): Serializable