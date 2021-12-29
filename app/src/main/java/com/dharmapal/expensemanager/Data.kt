package com.dharmapal.expensemanager

import androidx.recyclerview.widget.DiffUtil
import com.google.firebase.Timestamp
import java.time.LocalDate

data class Data(val e_name:String, val e_category:String, val expense:Int, val DateTime: Timestamp)

object datadiff:DiffUtil.ItemCallback<Data>(){
    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem.e_name==newItem.e_name
    }

    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem==newItem
    }

}