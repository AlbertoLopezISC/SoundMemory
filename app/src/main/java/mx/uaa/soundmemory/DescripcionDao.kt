package com.example.prueba

import androidx.room.*
import mx.uaa.soundmemory.Descripcion


@Dao
interface DescripcionDao {
    //recuperar todos los datos de la BD

    //Todas las fun se reemplazan por suspend fun
    @Query(value = "SELECT * FROM Descripcion")
    suspend fun getAll(): MutableList<Descripcion>

    //recuperar un renglon de la BD por su ID
    @Query(value = "SELECT * FROM Descripcion WHERE id= :id")
    suspend fun getById(id: Int): Descripcion

    //actualizar carta por su id
    @Query(value = "UPDATE Descripcion SET descrip = :des, ruta=:ruta WHERE id=:id ")
    suspend fun updateCardById(id:Int?, des: String, ruta: String)

    //Eliminar por id
    @Query(value = "DELETE FROM Descripcion WHERE id=:id" )
    suspend fun deleteCardById(id: Int?)

    //update a la BD
    @Update
    suspend fun update(descripcion: Descripcion)

    //insertar ala BD
    @Insert
    suspend fun insert(descripcion: Descripcion?)

    //intentando insertar solo la descripcion
    // @Query(value = "INSERT *  INTO Descipcion.descrip WHERE descrip= :descrip")

    //eliminar un renglon de la BD
    @Delete
    suspend  fun delete(descripcion: Descripcion)
}