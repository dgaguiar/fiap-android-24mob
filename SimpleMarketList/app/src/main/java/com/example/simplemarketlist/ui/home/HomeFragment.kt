package com.example.simplemarketlist.ui.home

import CustomToast
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simplemarketlist.MainActivity
import com.example.simplemarketlist.R
import com.example.simplemarketlist.adapter.PrdAdapter
import com.example.simplemarketlist.models.Products
import com.example.simplemarketlist.ui.addProduct.InsertionActivity
import com.example.simplemarketlist.ui.base.auth.BaseAuthFragment
import com.example.simplemarketlist.ui.detail.ProductDetailActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*

class HomeFragment : BaseAuthFragment() {
    override val layout = R.layout.fragment_home

    private lateinit var constraintLayout: ConstraintLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnAdd: FloatingActionButton
    private lateinit var prdList: ArrayList<Products>
    private lateinit var dbRef: DatabaseReference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpView(view)
        getProductData()
        showBanner()
    }

    private fun setUpView(view : View){
        prdList = arrayListOf<Products>()
        constraintLayout = view.findViewById(R.id.containerAds)

        recyclerView = view.findViewById(R.id.rv_itens)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        btnAdd = view.findViewById(R.id.fab_add)
        btnAdd.setOnClickListener{
            val intent = Intent(requireContext(), InsertionActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showBanner() {
        constraintLayout.visibility = if (isFreeVersion()) View.VISIBLE
        else View.GONE
    }

    private fun getProductData() {
        dbRef = FirebaseDatabase.getInstance().getReference("Products")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                prdList.clear()
                if (snapshot.exists()) {
                    for (prdSnap in snapshot.children) {
                        val prdData = prdSnap.getValue(Products::class.java)
                        prdList.add(prdData!!)
                    }
                    val mAdapter = PrdAdapter(prdList)
                    recyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : PrdAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {

                            val intent = Intent(requireContext(), ProductDetailActivity::class.java)

                            //put extras
                            intent.putExtra("prdId", prdList[position].prdId)
                            intent.putExtra("prdName", prdList[position].prdName)
                            intent.putExtra("prdPrice", prdList[position].prdPrice)
                            startActivity(intent)
                        }

                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {
                CustomToast.error((activity as MainActivity),"Erro ao buscar os produtos: ${error.message}")
            }
        })
    }
}