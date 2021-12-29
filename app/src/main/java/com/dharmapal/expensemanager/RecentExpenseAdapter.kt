package com.dharmapal.expensemanager

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dharmapal.expensemanager.databinding.FragmentFirstBinding
import com.dharmapal.expensemanager.databinding.ItemExpenseBinding
import com.google.firebase.Timestamp
import java.time.LocalDate
import java.time.ZoneId

class RecentExpenseAdapter():ListAdapter<Data,RecentExpenseViewHolder>(datadiff){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentExpenseViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val binding=ItemExpenseBinding.inflate(inflater,parent,false)
        return RecentExpenseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecentExpenseViewHolder, position: Int) {
        val data1 = getItem(position)
        when (data1.e_category) {
            "Food" -> {
                holder.binding.image.setImageResource(R.drawable.food)
            }
            "Recharge" -> {
                holder.binding.image.setImageResource(R.drawable.recharge)
            }
            "Shopping" -> {
                holder.binding.image.setImageResource(R.drawable.shopping)
            }
            else -> {
    //            holder.binding.image.setImageResource(R.drawable.Recharge)
            }
        }
//        holder.binding.item.setOnLongClickListener{
//        }

        holder.binding.expenseType.text=data1.e_name
        holder.binding.expenseCategory.text=data1.e_category
        holder.binding.cost.text=data1.expense.toString()
        holder.binding.date.text= data1.DateTime.toLocalDate().toString()

//        fun getposition(): Int {
//            return position
//        }

    }
    fun Timestamp.toLocalDate() : LocalDate = toDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()


}

class RecentExpenseViewHolder(val binding: ItemExpenseBinding):RecyclerView.ViewHolder(binding.root){

}
