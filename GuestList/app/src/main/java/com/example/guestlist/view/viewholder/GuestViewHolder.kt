package com.example.guestlist.view.viewholder

import android.app.AlertDialog
import android.content.DialogInterface
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

        bind.textGuestName.setOnLongClickListener{

            AlertDialog.Builder(itemView.context)
                .setTitle("Removing guest")
                .setMessage("Do you realy want remove the guest?")
                .setPositiveButton("Yes"){dialog, which ->
                    listener.onDelete(guest.id)
                }.setNegativeButton("No", null)
                .create()
                .show()
            true
        }
    }
}