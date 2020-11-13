package com.example.atv1n2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Atividade::class], version = 1)
abstract class AtividadeDataBase: RoomDatabase() {

    abstract fun atividadeDao(): AtividadeDAO

    companion object {
       private val database:  AtividadeDataBase? = null
        private val DATABASE = "AtividadeDB"

       fun getInstance(context: Context): AtividadeDataBase? {


          if(database == null)
               return criaBanco(context)
          else
                return database
       }
        private fun criaBanco(context: Context): AtividadeDataBase{
            return Room.databaseBuilder(context,AtividadeDataBase::class.java, DATABASE).allowMainThreadQueries().build()
        }
    }
}


