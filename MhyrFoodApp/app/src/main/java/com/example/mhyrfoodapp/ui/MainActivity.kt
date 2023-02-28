package com.example.mhyrfoodapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.mhyrfoodapp.R
import com.example.mhyrfoodapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //Binding
    private var _bind: ActivityMainBinding? = null
    private val bind get() = _bind

    //Navigation controller
    private lateinit var navHost: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind?.root)
        //Init views
        bind?.apply {
            navHost =
                supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        }
    }

    override fun onStop() {
        super.onStop()
        _bind = null
    }

    override fun onNavigateUp(): Boolean {
        return navHost.navController.navigateUp() || super.onNavigateUp()
    }
}