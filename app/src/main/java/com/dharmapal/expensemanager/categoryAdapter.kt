package com.dharmapal.expensemanager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dharmapal.expensemanager.databinding.ItemCategoryBinding

class categoryAdapter: ListAdapter<Category, categoryViewHolder>(catagorydiff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): categoryViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val binding= ItemCategoryBinding.inflate(inflater,parent,false)
        return categoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: categoryViewHolder, position: Int) {
        val category1:Category=getItem(position)
//        holder.binding.image=data1.e_name
        if (category1.Category=="Food"){
            holder.binding.image.setImageResource(R.drawable.food)
        }
        else if(category1.Category=="Recharge"){
            holder.binding.image.setImageResource(R.drawable.recharge)
        }
        else if(category1.Category=="Shopping"){
            holder.binding.image.setImageResource(R.drawable.shopping)
        }
        else{
//            holder.binding.image.setImageResource(R.drawable.Recharge)
        }
        holder.binding.category.text=category1.Category
        holder.binding.expenseTotal.text=category1.Expense.toString()

    }
}

class categoryViewHolder(val binding: ItemCategoryBinding): RecyclerView.ViewHolder(binding.root)
