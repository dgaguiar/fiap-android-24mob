package com.example.simplemarketlist.ui.edit

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.simplemarketlist.R
import com.example.simplemarketlist.models.NewUser
import com.example.simplemarketlist.models.Products
import com.example.simplemarketlist.utils.Const.PATH_COLLECTION
import com.example.simplemarketlist.utils.Const.setTimeStamp
import com.google.android.gms.tasks.Task
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.FirebaseFirestore

class EditFragment : Fragment() {

    private var isEdit = false
    private var users: NewUser? = null
    private val mFirestore = FirebaseFirestore.getInstance()
    private val mUsersCollection = mFirestore.collection(PATH_COLLECTION)

    private lateinit var tfProductName: TextInputEditText
    private lateinit var tfProductValue: TextInputEditText
    private lateinit var btUpdate: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        btUpdate.setOnClickListener { saveData() }
        initView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit, container, false)

    }

    private fun initView() {
        //set view jika data di edit maka akan tampil pada form input
        if (isEdit) {

        }
    }

    private fun saveData() {
        setData(users?.username)
    }

    private fun setData(strId: String?) {
//        createUser(strId).addOnCompleteListener {
//            if (it.isSuccessful) {
//                if (isEdit) {
//                    Toast.makeText(this, "Sukses perbarui data", Toast.LENGTH_SHORT).show()
//                } else {
//                    Toast.makeText(this, "Sukses tambah data", Toast.LENGTH_SHORT).show()
//                }
////                finish()
//            } else {
//                Toast.makeText(this, "Gagal tambah data", Toast.LENGTH_SHORT).show()
//            }
//        }.addOnFailureListener {
//            Toast.makeText(this, "Error Added data ${it.message}", Toast.LENGTH_SHORT).show()
//        }
    }

//    fungsi untuk mengambil inputan data dan menyimpannya pada firestore
    private fun createUser(strId: String?): Task<Void> {
        val writeBatch = mFirestore.batch()
        val path = PATH_COLLECTION + setTimeStamp().toString() //exmp hasil : users-43287845
        val id = strId ?: path
        val name = tfProductName.text.toString()
        val value = tfProductValue.text.toString()

        val product = Products(
            strName = name,
            strId = "id",
            intPrice = 99
        )
        writeBatch.set(mUsersCollection.document(id), product) //menyimpan data dengan id yang sudah ditentukan
        return writeBatch.commit()
    }

    private fun setUpView(view: View) {
        tfProductName = view.findViewById(R.id.tfProductName)
        tfProductValue = view.findViewById(R.id.tfProductValue)
        btUpdate = view.findViewById(R.id.btUpdate)
    }

}