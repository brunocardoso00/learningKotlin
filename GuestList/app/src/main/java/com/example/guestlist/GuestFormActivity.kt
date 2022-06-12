package com.example.guestlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.guestlist.databinding.ActivityGuestFormBinding

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var mViewModel: GuestFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)

        setContentView(binding.root)

        mViewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        setListiners()
    }

    override fun onClick(v: View) {
        val id = v.id

        if (id == binding.buttonSave.id) {
            val name = binding.editName.text.toString()
            val presence = binding.radioPresent.isChecked

            mViewModel.save(name, presence)
        }
    }

    private fun setListiners() {
        binding.buttonSave.setOnClickListener(this)

    }

    private fun observe() {
        mViewModel.saveGuest.observe(this, Observer
        {
            if (it) {
                Toast.makeText(applicationContext, "Sucesso", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Falha", Toast.LENGTH_SHORT).show()
            }
        })
    }
}