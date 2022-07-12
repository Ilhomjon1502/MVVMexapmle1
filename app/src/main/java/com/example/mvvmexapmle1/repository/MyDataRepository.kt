package com.example.mvvmexapmle1.repository

import com.example.mvvmexapmle1.models.MyData

class MyDataRepository(val myData: MyData) {

    suspend fun getData(index:Int) = myData.getData(index)

    suspend fun getSize() = myData.getSize()
}