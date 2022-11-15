package com.example.simplemarketlist.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.simplemarketlist.R

private lateinit var tvPrdId: TextView
private lateinit var tvPrdName: TextView
private lateinit var tvPrdPrice: TextView
private lateinit var btnUpdate: Button
private lateinit var btnDelete: Button

class ProductDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        initView()
        setValuesToViews()
    }

    private fun initView() {
        tvPrdId = findViewById(R.id.tvPrdId)
        tvPrdName = findViewById(R.id.tvPrdName)
        tvPrdPrice = findViewById(R.id.tvPrdPrice)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews() {
        tvPrdId.text = intent.getStringExtra("prdId")
        tvPrdName.text = intent.getStringExtra("prdName")
        tvPrdPrice.text = intent.getStringExtra("prdPrice")
    }
}