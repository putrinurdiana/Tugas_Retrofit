package com.example.tugasretrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasretrofit.databinding.DataBinding
import com.example.tugasretrofit.model.Vendors

class DataAdapter(private val dataList: List<Vendors>) :
    RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

//  memanggil recyclerview ketika membutuhkan viewholder baru untuk ditampilkan
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = DataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

//  menampilkan data untuk item tertentu
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

//  mengembalikan jumlah total item yang ingin ditampilkan
    override fun getItemCount(): Int {
        return dataList.size
    }

    class DataViewHolder(private val binding: DataBinding) : RecyclerView.ViewHolder(binding.root) {
//  mengatur data pada view
        fun bind(data: Vendors) {
            binding.tvDate.text = data.date
            binding.tvTitle.text = data.title
            binding.tvCategory.text = data.category
            binding.tvDescription.text = data.description
        }
    }
}
