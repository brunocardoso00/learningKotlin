package com.example.guestlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.guestlist.databinding.ActivityGuestFormBinding
import com.example.guestlist.service.constants.DataBaseConstants
import com.example.guestlist.service.model.GuestModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var mViewModel: GuestFormViewModel
    private var guestId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mViewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        binding.buttonSave.setOnClickListener(this)
        binding.radioPresent.isChecked = true


        observe(true)
        loadData()

    }

    override fun onClick(v: View) {
        val id = v.id

        if (id == binding.buttonSave.id) {
            val name = binding.editName.text.toString()
            val presence = binding.radioPresent.isChecked

            var guest = GuestModel(guestId,name, presence)

            mViewModel.save(guest)
            finish()

        }
    }

    private fun observe(success: Boolean) {
        mViewModel.guest.observe(this, Observer
        {
            binding.editName.setText(it.name)
            if(it.presence)
            {
                binding.radioPresent.isChecked = true
            }else
            {
                binding.radioAbsent.isChecked = true

            }
        })

    }

    private fun loadData(){

        val bundle = intent.extras
        if (bundle != null)
        {
            guestId = bundle.getInt(DataBaseConstants.GUEST.ID)
            mViewModel.get(guestId)

        }
    }
}