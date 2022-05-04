package com.example.roommvvmcrudwtthhilt.feature

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roommvvmcrudwtthhilt.R
import com.example.roommvvmcrudwtthhilt.database.Subscriber
import com.example.roommvvmcrudwtthhilt.databinding.ActivityMainBinding
import com.example.roommvvmcrudwtthhilt.viewModel.SubscriberViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val subscriberViewModel by viewModels<SubscriberViewModel>()
    lateinit var bind: ActivityMainBinding

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_main)
        bind.myViewModel = subscriberViewModel
        bind.lifecycleOwner = this
        val myRecycleViewAdapter = MyRecycleViewAdapter(object : MyRecycleViewAdapter.OnItemClickListener{
            override fun onItemClick(subscriber: Subscriber) {
                subscriberViewModel.initUpdate(subscriber)
            }
        })

        bind.rv.layoutManager = LinearLayoutManager(this)
        subscriberViewModel.getListFromFlow().observe(this){
            myRecycleViewAdapter.setList(it)
            myRecycleViewAdapter.notifyDataSetChanged()
        }
        bind.rv.adapter = myRecycleViewAdapter
        subscriberViewModel.message.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }
}