package com.example.atv1n2

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AtividadeDAO {

    @Insert
    fun salvar(atividade: Atividade)

    @Query("select * from Atividade order by tipos ASC")
    fun listarTipoAsc(): List<Atividade>

    @Query("select * from Atividade order by  tipos DESC")
    fun listarTipoDesc(): List<Atividade>

    @Query("select * from Atividade order by data ASC")
    fun listarDataAsc(): List<Atividade>

    @Query("select * from Atividade order by data DESC")
    fun listarDataDesc(): List<Atividade>

    @Query("select * from Atividade where tipos = :t")
    fun listarPorTipo(t : String): List<Atividade>


    @Delete
    fun excluir(atividade: Atividade)

}