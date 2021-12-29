package com.dharmapal.expensemanager

import androidx.recyclerview.widget.DiffUtil

data class Category(val ImageUrl:String,val Category:String,val Expense:Int)
object catagorydiff:DiffUtil.ItemCallback<Category>(){
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.Category==newItem.Category
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem==newItem
    }
}