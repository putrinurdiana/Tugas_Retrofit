package com.example.tugasretrofit

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tugasretrofit.databinding.FragmentHomeBinding
import com.example.tugasretrofit.model.Result
import com.example.tugasretrofit.model.Vendors
import com.example.tugasretrofit.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: DataAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Fetch data from API
        fetchData()
    }

    private fun fetchData() {
        val apiService = ApiClient.getInstance()

        apiService.getAllResult().enqueue(object : Callback<Result> {
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                if (response.isSuccessful && response.body() != null) {
                    val result = response.body()!!

                    // Convert List<Vendors> to MutableList<Vendors>
                    val mutableDataList = result.data.toMutableList()

                    // Set adapter with data list and handle favorite click
                    adapter = DataAdapter(mutableDataList) { vendor ->
                        addToFavorites(vendor)
                    }
                    binding.recyclerView.adapter = adapter
                } else {
                    Toast.makeText(requireContext(), "Failed to retrieve data", Toast.LENGTH_SHORT)
                        .show()
                    Log.e("HomeFragment", "Response unsuccessful or empty body")
                }
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {
                Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                Log.e("HomeFragment", "API call failed", t)
            }
        })
    }

    private fun addToFavorites(vendor: Vendors) {
        if (!FavoriteFragment.favoriteList.contains(vendor)) {
            FavoriteFragment.favoriteList.add(vendor)
            Toast.makeText(requireContext(), "${vendor.title} added to favorites!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "${vendor.title} is already in favorites!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
