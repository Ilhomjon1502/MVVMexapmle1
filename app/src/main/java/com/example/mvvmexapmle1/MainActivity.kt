package com.example.mvvmexapmle1

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmexapmle1.databinding.ActivityMainBinding
import com.example.mvvmexapmle1.models.MyData
import com.example.mvvmexapmle1.repository.MyDataRepository
import com.example.mvvmexapmle1.utils.Status
import com.example.mvvmexapmle1.viewmodel.MyViewmodel
import com.example.mvvmexapmle1.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myViewmodel: MyViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myData = MyData()
        val myDataRepository = MyDataRepository(myData)
        myViewmodel = ViewModelProvider(
            this,
            ViewModelFactory(myDataRepository)
        )[MyViewmodel::class.java]

        loadTv()
        loadEdt()
    }

    private fun loadEdt() {
        binding.edtIndex.addTextChangedListener {
            myViewmodel.randomString(it.toString())
        }
    }

    private fun loadTv() {

        myViewmodel.getLiveData().observe(this) {
            when (it.status) {
                Status.LOADING->{
                    binding.progressBar.visibility = View.VISIBLE
                    binding.tvInfo.visibility = View.GONE
                }
                Status.SUCCESS ->{
                    binding.progressBar.visibility = View.GONE
                    binding.tvInfo.visibility = View.VISIBLE
                    binding.tvInfo.text = it.data
                    binding.tvInfo.setTextColor(Color.BLACK)
                }
                Status.ERROR->{
                    binding.progressBar.visibility = View.GONE
                    binding.tvInfo.visibility = View.VISIBLE
                    binding.tvInfo.text = it.message
                    binding.tvInfo.setTextColor(Color.RED)
                }
            }
        }
    }
}