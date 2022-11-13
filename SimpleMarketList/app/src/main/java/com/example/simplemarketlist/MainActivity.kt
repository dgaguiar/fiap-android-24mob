package com.example.simplemarketlist

import CustomToast
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.navigation.NavigationView
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity(), DrawerController {
    lateinit var drawerLayout: DrawerLayout
    lateinit var imageView: ImageView
    lateinit var navigationView: NavigationView
    lateinit var navController: NavController
    lateinit var linearlayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearlayout = findViewById(R.id.layoutToolBar)
        drawerLayout = findViewById(R.id.drawerLayout)
        imageView = findViewById(R.id.imageMenu)

        imageView.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        /*imageView.setOnClickListener {
            // 1
            FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                // 2
                if (!task.isSuccessful) {
                    Log.w(TAG, "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }
                // 3
                val token = task.result

                // 4
                //val msg = token
                Log.d(TAG, token)
                CustomToast.default(this, token)
            })
        }*/

        navigationView = findViewById(R.id.navigationView)

        navController = Navigation.findNavController(this, R.id.navHostFragment)
        NavigationUI.setupWithNavController(navigationView, navController)

    }

    private fun fullScreen() {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); supportActionBar?.hide()
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
    }

    override fun setDrawer_locked() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        linearlayout.visibility = LinearLayout.GONE
    }

    override fun setDrawer_unlocked() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        linearlayout.visibility = LinearLayout.VISIBLE
    }
}