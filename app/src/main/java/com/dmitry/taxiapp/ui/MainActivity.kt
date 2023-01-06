package com.dmitry.taxiapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dmitry.taxiapp.databinding.ActivityMainBinding
import com.dmitry.taxiapp.utils.NetworkUtil
import com.dmitry.taxiapp.utils.applyVisibility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val networkConnection = NetworkUtil(applicationContext)
        networkConnection.observe(this) { isConnected ->
            applyVisibility(binding.tvConnection, !isConnected)
            applyVisibility(binding.fragmentContainer, isConnected)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}