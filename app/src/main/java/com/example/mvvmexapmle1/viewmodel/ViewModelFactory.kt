package com.example.mvvmexapmle1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmexapmle1.repository.MyDataRepository

class ViewModelFactory(
    private val myDataRepository: MyDataRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyViewmodel::class.java)) {
            return MyViewmodel(myDataRepository) as T
        }
        throw IllegalArgumentException("Error")
    }
}