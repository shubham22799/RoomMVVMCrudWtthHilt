package com.example.roommvvmcrudwtthhilt.feature

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roommvvmcrudwtthhilt.R
import com.example.roommvvmcrudwtthhilt.database.Subscriber
import com.example.roommvvmcrudwtthhilt.databinding.ListItemBinding

class MyRecycleViewAdapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<MyViewHolder>() {
    private val subscriberList = ArrayList<Subscriber>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(subscriberList[position], listener)
    }

    override fun getItemCount(): Int {
        return subscriberList.size
    }

    fun setList(subscriber: List<Subscriber>) {
        subscriberList.clear()
        subscriberList.addAll(subscriber)
    }
    interface OnItemClickListener{
        fun onItemClick(subscriber: Subscriber)
    }
}

class MyViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(subscriber: Subscriber, listener: MyRecycleViewAdapter.OnItemClickListener){
        binding.txtName.text = subscriber.name
        binding.txtEmail.text = subscriber.email
        binding.item.setOnClickListener { listener.onItemClick(subscriber)}
    }
}