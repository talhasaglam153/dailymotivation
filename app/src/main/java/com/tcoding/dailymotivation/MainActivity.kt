package com.tcoding.dailymotivation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tcoding.dailymotivation.databinding.ActivityMainBinding
import com.tcoding.dailymotivation.viewmodel.MotivationViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initViewModel()


        binding.container.setOnClickListener {
            initViewModel()
        }
        binding.floatingActionButton.setOnClickListener {
         Toast.makeText(this, "Click anywhere if you wanna see new motivation sentences", Toast.LENGTH_SHORT).show()
        }


    }


    fun initViewModel() {
        val viewModel = ViewModelProvider(this).get(MotivationViewModel::class.java)
        viewModel.getLiveData().observe(this, Observer {
            binding.text.text = it.slip.advice
        })
        viewModel.callAPI()
    }
}