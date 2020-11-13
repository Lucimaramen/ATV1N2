package com.example.atv1n2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_tela2.*

class Tela2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela2)

        val tipos = resources.getStringArray(R.array.tipos)

        val atv = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, tipos)

        spnTipo.adapter = atv


    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

      if (item.itemId == R.id.Salvar){
            salvar()
            finish()
        }
        if(item.itemId == R.id.Cancelar){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun salvar(){
        val titulo = edtTitulo.text.toString()
        val descricao = edtDescricao.text.toString()
        val data = edtData.text.toString()
        val tempo = edtTime.text.toString()
        val tipos = spnTipo.selectedItem.toString()

        var distancia = edtDistancia.text.toString().toDouble()

        if (tipos.equals("Academia"))
            distancia = 0.0


        val a = Atividade(titulo,descricao,data,tempo,tipos,distancia)

        val aDAO = AtividadeDataBase.getInstance(this)!!.atividadeDao()

        aDAO.salvar(a)


    }
}