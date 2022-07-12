package com.example.mvvmexapmle1.models

class MyData {
    private val list = arrayOf("Kotlin", "Java", "Android")

    suspend fun getData(index:Int):String = list[index]

    suspend fun getSize():Int = list.size
}