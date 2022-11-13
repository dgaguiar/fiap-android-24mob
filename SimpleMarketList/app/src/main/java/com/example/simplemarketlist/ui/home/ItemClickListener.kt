package com.example.simplemarketlist.ui.home

interface ItemClickListener
{
    fun editTaskItem(taskItem: ItemList)
    fun completeTaskItem(taskItem: ItemList)
}