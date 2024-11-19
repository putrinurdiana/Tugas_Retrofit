package com.example.tugasretrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasretrofit.databinding.DataBinding
import com.example.tugasretrofit.model.Vendors

class DataAdapter(
    private val dataList: MutableList<Vendors>, // Gunakan MutableList untuk memperbarui data
    private val onFavoriteClick: (Vendors) -> Unit
) : RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = DataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(dataList[position], onFavoriteClick)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class DataViewHolder(private val binding: DataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Vendors, onFavoriteClick: (Vendors) -> Unit) {
            binding.tvDate.text = data.date
            binding.tvTitle.text = data.title
            binding.tvCategory.text = data.category
            binding.tvDescription.text = data.description

            // Set warna ikon favorit berdasarkan status
            val favoriteColor = if (data.isFavorite)
                android.R.color.holo_red_dark
            else
                android.R.color.darker_gray

            binding.iconFavorite.setColorFilter(
                ContextCompat.getColor(binding.root.context, favoriteColor)
            )

            // Handle klik ikon favorit
            binding.iconFavorite.setOnClickListener {
                data.isFavorite = !data.isFavorite // Ubah status favorit
                onFavoriteClick(data) // Panggil callback
                // Perbarui tampilan
                binding.iconFavorite.setColorFilter(
                    ContextCompat.getColor(binding.root.context,
                        if (data.isFavorite) android.R.color.holo_red_dark
                        else android.R.color.darker_gray
                    )
                )
            }
        }
    }
}
