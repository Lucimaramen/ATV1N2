package com.example.atv1n2

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity
data class Atividade (
    val titulo: String,
    val descricao: String,
    val data: String,
    val tempo: String,
    val tipos: String,
    val distancia: Double,
    @PrimaryKey (autoGenerate = true)
    val id : Int = 0): Serializable{

   override fun toString(): String {
       return data + "/" + titulo + "/" + tipos
   }

}