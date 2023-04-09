package com.example.hw6

import androidx.recyclerview.widget.RecyclerView
import com.example.hw6.databinding.ItemRecyclerBinding
import com.squareup.picasso.Picasso

class ContactsViewHolder(
    private val binding: ItemRecyclerBinding

) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        item: Contact,
        onItemClick: (id: Int) -> Unit,
        onItemLongClick: (idLong: Int) -> Unit

    ) {
        val imageUri = "https://picsum.photos/300/300?random=" + item.id
        binding.contactId.text = item.id.toString()
        binding.contactFirstName.text = item.firstName
        binding.contactSecondName.text = item.secondName
        binding.contactPhoneNumber.text = item.phoneHumber
        Picasso.get().load(imageUri).into(binding.contactImage)

        itemView.setOnClickListener {
            onItemClick(item.id)
        }
        itemView.setOnLongClickListener {
            onItemLongClick(item.id)
            true
        }

    }



}


