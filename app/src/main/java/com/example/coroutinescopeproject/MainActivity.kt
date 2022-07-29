package com.example.coroutinescopeproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.coroutinescopeproject.databinding.ActivityMainBinding
import com.example.mvvmkotlin.viewmodel.MainViewModel
import com.example.mvvmkotlin.viewmodelfactory.MyViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()

    }

    private fun initView() {
        viewModel = ViewModelProvider(this, MyViewModelFactory(this)).get(
            MainViewModel::class.java)

        binding.btn.setOnClickListener {
            viewModel.listUserApi()
        }

        viewModel.loading.observe(this,{
            if(it)
            {
                binding.progressbar.visibility=VISIBLE
            }
            else{
                binding.progressbar.visibility=GONE
            }
        })

        viewModel.errorMessage.observe(this,{
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        })

        viewModel.response.observe(this,{
            Log.e("TAG", "initView: data size "+it.data.size )
        })
    }
}