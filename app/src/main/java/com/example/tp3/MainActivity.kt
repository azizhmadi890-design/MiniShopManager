package com.example.tp3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val produits = listOf(
        Produit("📱", "Téléphone", "Smartphone dernière génération"),
        Produit("🎧", "Casque Bluetooth", "Un son clair, sans fil"),
        Produit("⌚", "Montre connectée", "Suivez votre activité au quotidien"),
        Produit("🔌", "Chargeur USB", "Recharge rapide et pratique")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.listViewProduits)
        listView.adapter = ProduitAdapter()

        listView.setOnItemClickListener { _, _, position, _ ->
            val produit = produits[position]
            Toast.makeText(
                this,
                "Produit sélectionné : ${produit.nom}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private inner class ProduitAdapter : BaseAdapter() {
        override fun getCount() = produits.size
        override fun getItem(position: Int) = produits[position]
        override fun getItemId(position: Int) = position.toLong()

        override fun getView(
            position: Int,
            convertView: View?,
            parent: ViewGroup
        ): View {
            val view = convertView ?: LayoutInflater.from(this@MainActivity)
                .inflate(R.layout.item_produit, parent, false)

            val produit = produits[position]
            view.findViewById<TextView>(R.id.iconeProduit).text = produit.icone
            view.findViewById<TextView>(R.id.nomProduit).text = produit.nom
            view.findViewById<TextView>(R.id.descriptionProduit).text =
                produit.description

            return view
        }
    }
}

data class Produit(
    val icone: String,
    val nom: String,
    val description: String
)