package com.example.guestlist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guestlist.databinding.FragmentHomeBinding
import com.example.guestlist.view.adapter.GuestsAdapter
import com.example.guestlist.view.listener.OnGuestListener

class AllGuestsFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: AllGuestsViewModel
    private val adapter = GuestsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        homeViewModel = ViewModelProvider(this).get(AllGuestsViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        //layout
        binding.recyclerAllGuests.layoutManager = LinearLayoutManager(context)

        //adapter
        binding.recyclerAllGuests.adapter = adapter
        val listener = object : OnGuestListener {
            override fun onClick(id: Int) {
                Toast.makeText(context, "fui clicado $id", Toast.LENGTH_SHORT).show()

            }

            override fun onDelete() {
                TODO("Not yet implemented")
            }

        }
        adapter.attachListener(listener)

        homeViewModel.getAll()
        observe()

        homeViewModel.guests.observe(viewLifecycleOwner) {

        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe() {
        homeViewModel.guests.observe(viewLifecycleOwner) {
            adapter.updateGuests(it)
        }

    }
}