package com.stickearn.moviefavourite.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stickearn.moviefavourite.databinding.ItemUserBinding
import com.stickearn.moviefavourite.model.usertest.UserTestItem

class MainAdapter(private val users: MutableList<UserTestItem>) : RecyclerView.Adapter<MainAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    class ItemViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(
            item: UserTestItem
        ) {
            binding.lblId.text = item.id.toString()
            binding.lblEmail.text = item.email
            binding.lblName.text = item.nama
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item: UserTestItem = users[position]
        holder.bindItem(item)
    }

    override fun getItemCount(): Int {
        return users.size
    }
}

