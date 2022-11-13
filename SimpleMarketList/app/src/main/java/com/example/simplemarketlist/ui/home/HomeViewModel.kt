package com.example.simplemarketlist.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class HomeViewModel: ViewModel() {
    var items = MutableLiveData<MutableList<ItemList>>()

    init {
        items.value = mutableListOf()
    }

    fun addTaskItem(newTask: ItemList) {
        val list = items.value
        list!!.add(newTask)
        items.postValue(list!!)
    }

    fun updateTaskItem(id: UUID, name: String, value: Double) {
        val list = items.value
        val task = list!!.find { it.id == id }!!
        task.name = name
        task.value = value
        items.postValue(list!!)
    }

    fun setCompleted(taskItem: ItemList) {
        val list = items.value
        val task = list!!.find { it.id == taskItem.id }!!
        items.postValue(list!!)
    }
}