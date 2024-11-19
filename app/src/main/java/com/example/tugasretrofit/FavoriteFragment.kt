package com.example.tugasretrofit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tugasretrofit.databinding.FragmentFavoriteBinding
import com.example.tugasretrofit.model.Vendors

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: DataAdapter

    companion object {
        val favoriteList: MutableList<Vendors> = mutableListOf()
    }
//  membuat tampilan view dari fragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }
//  dipanggil setelah view dari fragment sudah dibuat dan ditambahkan ke dalam hierarki view.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Initialize adapter with onFavoriteClick parameter
        adapter = DataAdapter(favoriteList) { vendor ->
            // Handle favorite item click
            favoriteList.remove(vendor)
            adapter.notifyDataSetChanged()
        }
        binding.recyclerView.adapter = adapter
    }
//  dipanggil untuk menghapus referensi binding
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

