package com.example.guestlist.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.guestlist.databinding.RowGuestBinding
import com.example.guestlist.service.model.GuestModel
import com.example.guestlist.view.listener.OnGuestListener

class GuestViewHolder(private val bind: RowGuestBinding, private val listener: OnGuestListener) :
    RecyclerView.ViewHolder(bind.root) {

    fun bind(guest: GuestModel) {
        bind.textGuestName.text = guest.name
        bind.textGuestName.setOnClickListener {
            listener.onClick(guest.id)
        }
    }
}