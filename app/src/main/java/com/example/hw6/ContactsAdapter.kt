package com.example.hw6

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hw6.databinding.ItemRecyclerBinding

class ContactsAdapter(
    private var onItemClick: (id: Int) -> Unit
) : RecyclerView.Adapter<ContactsViewHolder>() {

    private var list = arrayListOf<Contact>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        return ContactsViewHolder(
            ItemRecyclerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size


    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        holder.bind(list[position]) {
        }
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(list[position].id)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: ArrayList<Contact>) {
        this.list = list
        notifyDataSetChanged()
    }

}
