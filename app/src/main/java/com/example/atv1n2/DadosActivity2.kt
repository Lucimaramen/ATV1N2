package com.example.atv1n2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_dados2.*

class DadosActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dados2)

        val atividade = intent.getSerializableExtra("atividade") as Atividade

        txtTitulo.text = atividade.titulo
        txtDescricao.text = atividade.descricao
        txtData.text = atividade.data
        txtTempo.text = atividade.tempo
        txtTipo.text = atividade.tipos
        txtDistancia.text = atividade.distancia.toString()

        if (atividade.tipos.equals("Academia")) {
            txtDistancia.visibility = View.INVISIBLE

        }
    }

        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.menuexibe, menu)
            return super.onCreateOptionsMenu(menu)
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {

            if (item.itemId == R.id.Fechar){
                finish()
            }
            return super.onOptionsItemSelected(item)



        }
    }






