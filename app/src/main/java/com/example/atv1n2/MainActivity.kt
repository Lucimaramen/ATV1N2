package com.example.atv1n2

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.LocusId
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_tela2.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnMais.setOnClickListener() {

            val i = Intent(this, Tela2::class.java)
            startActivity(i)
        }

        var tipos = resources.getStringArray(R.array.tipos)

        var tp = arrayListOf<String>()
        tp.addAll(tipos)
        tp.add("Todos")

        val atv = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, tp)

        spi.adapter = atv

        spi.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                filtrar(spi.selectedItem.toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        lstAtividade.setOnItemClickListener { parent, view, position, id ->

            val atividadeClicada = lstAtividade.getItemAtPosition(position) as Atividade

            val i = Intent(this, DadosActivity2::class.java)

            i.putExtra("atividade", atividadeClicada)

            startActivity(i)

        }

        lstAtividade.setOnItemLongClickListener { paret, view, position, id ->
            val dialog = AlertDialog.Builder(this)

            dialog.setTitle("Atenção!")
            dialog.setMessage("Deseja realmente excluir?")
            dialog.setPositiveButton("Sim") { _: DialogInterface, _: Int ->
                var atividade = lstAtividade.getItemAtPosition(position) as Atividade

                AtividadeDataBase.getInstance(this, )!!.atividadeDao().excluir(atividade)
                dialog.setNegativeButton("Não", null)
                carregaLista("nome", "ASC")
            }

            dialog.show()
            return@setOnItemLongClickListener true

        }

    }

    override fun onResume() {
        super.onResume()
        carregaLista("Atividade", "ASC")
    }

    private fun filtrar(tipo: String) {

        val atividadeDAO = AtividadeDataBase.getInstance(this)?.atividadeDao()
        val atividade: List<Atividade>

        if (tipo.equals("Todos"))
            atividade = atividadeDAO!!.listarTipoAsc()
        else
            atividade = atividadeDAO!!.listarPorTipo(tipo)

        val adp = ArrayAdapter<Atividade>(this, android.R.layout.simple_list_item_1, atividade)

        lstAtividade.adapter = adp

    }

    private fun carregaLista(atributo: String, tipo: String) {

        val atividade: List<Atividade>

        if (tipo.equals("ASC"))
            if (atributo.equals("Atividade"))
                atividade = AtividadeDataBase.getInstance(this)!!.atividadeDao().listarTipoAsc()
            else
                atividade = AtividadeDataBase.getInstance(this)!!.atividadeDao().listarDataAsc()
        else
            if (atributo.equals("Atividade"))
                atividade = AtividadeDataBase.getInstance(this)!!.atividadeDao().listarTipoDesc()
            else
                atividade = AtividadeDataBase.getInstance(this)!!.atividadeDao().listarDataDesc()


        val adp = ArrayAdapter<Atividade>(this, android.R.layout.simple_list_item_1, atividade)

        lstAtividade.adapter = adp

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_lista, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.ordAtividadeAsc -> carregaLista("Atividade", "ASC")
            R.id.ordAtividadeDesc -> carregaLista("Atividade", "DESC")
            R.id.ordDataAsc -> carregaLista("Data", "ASC")
            R.id.ordDataDesc -> carregaLista("Data", "DESC")
        }

        return super.onOptionsItemSelected(item)
    }
}