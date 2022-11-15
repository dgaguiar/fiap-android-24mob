package com.example.simplemarketlist.ui.detail

import CustomToast
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.simplemarketlist.R
import com.example.simplemarketlist.models.Products
import com.example.simplemarketlist.ui.home.HomeFragment
import com.google.firebase.database.FirebaseDatabase

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

        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("prdId").toString(),
                intent.getStringExtra("prdName").toString()
            )
        }

        btnDelete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("prdId").toString()
            )
        }
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

    private fun deleteRecord(
        id: String
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Products").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {

            CustomToast.success(this, "Produto excluido com sucesso")

            val intent = Intent(this, HomeFragment::class.java)
            startActivity(intent)
        }.addOnFailureListener { error ->
            CustomToast.error(this, "Erro ao excluir: ${error.message}")
        }
    }

    private fun openUpdateDialog(
        prdId: String,
        prdName: String
    ) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_dialog, null)

        mDialog.setView(mDialogView)

        val etPrdName = mDialogView.findViewById<EditText>(R.id.etPrdName)
        val etPrdPrice = mDialogView.findViewById<EditText>(R.id.etPrdPrice)

        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        etPrdName.setText(intent.getStringExtra("prdName").toString())
        etPrdPrice.setText(intent.getStringExtra("prdPrice").toString())

        mDialog.setTitle("Atualizando $prdName")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updatePrdData(
                prdId,
                etPrdName.text.toString(),
                etPrdPrice.text.toString()
            )

            CustomToast.success(this, "Produto atualizado com sucesso")

            tvPrdName.text = etPrdName.text.toString()
            tvPrdPrice.text = etPrdPrice.text.toString()

            alertDialog.dismiss()
        }
    }

    private fun updatePrdData(
        id: String,
        name: String,
        price: String
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Products").child(id)
        val prdInfo = Products(id, name, price)
        dbRef.setValue(prdInfo)
    }

}