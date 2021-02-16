  package com.sn.cleannotes.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.sn.cleannotes.R
import com.sn.cleannotes.databinding.ActivityMainBinding

  class MainActivity : AppCompatActivity() {
      private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = findNavController(R.id.navContiner)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }


      override fun onSupportNavigateUp(): Boolean {
          val navController = this.findNavController(R.id.navContiner)
          return navController.navigateUp()
      }
}