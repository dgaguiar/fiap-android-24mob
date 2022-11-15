package com.example.simplemarketlist.ui.addProduct

import CustomToast
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.simplemarketlist.R
import com.example.simplemarketlist.models.Products
import com.example.simplemarketlist.ui.home.HomeFragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

private lateinit var etPrdName: EditText
private lateinit var etPrdPrice: EditText
private lateinit var btnSaveData: Button
private lateinit var constraintLayout: ConstraintLayout

private lateinit var dbRef: DatabaseReference

class InsertionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertion)

        setUpView()
        showBanner()
    }

    private fun setUpView() {
        dbRef = FirebaseDatabase.getInstance().getReference("Products")

        etPrdName = findViewById(R.id.etPrdName)
        etPrdPrice = findViewById(R.id.etPrdPrice)
        btnSaveData = findViewById(R.id.btnSave)
        constraintLayout = findViewById(R.id.containerAds)

        btnSaveData.setOnClickListener {
            saveProductData()
        }
    }

    private fun saveProductData() {
        val prdName = etPrdName.text.toString()
        val prdPrice = etPrdPrice.text.toString()

        if (prdName.isEmpty()) {
            etPrdName.error = "Digite um nome de produto"
        }
        if (prdPrice.isEmpty()) {
            etPrdPrice.error = "Digite o preço do produto"
        }

        val prdId = dbRef.push().key!!

        val product = Products(prdId, prdName, prdPrice)

        dbRef.child(prdId).setValue(product)
            .addOnCompleteListener {
                CustomToast.success(this, "Cadastro realizado com sucesso")

                etPrdName.text.clear()
                etPrdPrice.text.clear()

                val intent = Intent(this, HomeFragment::class.java)
                startActivity(intent)
            }.addOnFailureListener { err ->
                CustomToast.error(this, "Erro ${err.message}")
            }
    }

    private fun showBanner() {
        constraintLayout.visibility = if (isFreeVersion()) View.VISIBLE
        else View.GONE
    }

    private fun isFreeVersion(): Boolean {
        return this.getPackageName() == "com.example.simplemarketlist.free"
    }
}