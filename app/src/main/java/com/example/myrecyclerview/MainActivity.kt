package com.example.myrecyclerview

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecyclerview.GridHeroAdapter as GridHeroAdapter1

class MainActivity : AppCompatActivity() {
    //pembuatan varibale global yang mana varibale ini dapat digunakan disemua class
    private lateinit var rvHeroes :RecyclerView
    private var list: ArrayList<Hero> = arrayListOf()
    private var title: String = "Mode List"

    //fungsi utama yang pertama kali dilankan
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //memangil main activity untuk ditampilkan
        setContentView(R.layout.activity_main)
        //memangil  fungsi titile
        setActionBarTitle(title)
        //inisialisasi recylevie
        rvHeroes = findViewById(R.id.rv_heroes)
        rvHeroes.setHasFixedSize(true)

        //menambah data di recyleview berdasarkan list data
        list.addAll(HeroesData.listData)
        //memangil fungsi list
        showRecyleList()
    }
    //fungsi untuk memgubah action bar
    private fun setActionBarTitle(title: String){
        if (supportActionBar != null){
            (supportActionBar as ActionBar).title = title
        }
    }
    //fungsi untuk menampilkan list
    private fun showRecyleList() {
        rvHeroes.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListHeroAdapter(list)
        rvHeroes.adapter = listHeroAdapter
        listHeroAdapter.setOnItemClickCallback(object : ListHeroAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Hero) {
                showSelectedHero(data)
            }
        })
    }
    //fungsi untuk menampilkan grid
    private fun showRecyclerGrid() {
        //angka 2 digunakan untuk mengatur jumlah span di dalam layout grid dapat diatur sesuai kebutuhan
        rvHeroes.layoutManager = GridLayoutManager(this, 2)
        val gridHeroAdapter = GridHeroAdapter1(list)
        rvHeroes.adapter = gridHeroAdapter

        gridHeroAdapter.setOnItemClickCallback(object : GridHeroAdapter1.OnItemClickCallback {
            override fun onItemClicked(data: Hero) {
                showSelectedHero(data)
            }
        })
    }
    //fungsi menampilkan cardview
    private fun showRecyclerCardView() {
        rvHeroes.layoutManager = LinearLayoutManager(this)
        val cardViewHeroAdapter = CardViewHeroAdapter(list)
        rvHeroes.adapter = cardViewHeroAdapter


    }
    //fungsi menampilkan nama hero ketika di pilih
    private fun showSelectedHero(hero: Hero) {
        Toast.makeText(this, "Kamu memilih " + hero.name, Toast.LENGTH_SHORT).show()
    }

    //fungsi membuat memnu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    //fungsi  untuk memangil mode berdasarkan idnya
    private fun setMode(selectedMode: Int) {
        when(selectedMode){
            R.id.action_list ->{
                showRecyleList()
                title = "Mode List"
            }
            R.id.action_grid ->{
                showRecyclerGrid()
                title = "Mode Grid"
            }
            R.id.action_cardview ->{
                showRecyclerCardView()
                title = "Mode CardView"
            }
        }

        setActionBarTitle(title)
    }
}
