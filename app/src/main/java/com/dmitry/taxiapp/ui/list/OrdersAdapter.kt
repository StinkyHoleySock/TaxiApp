package com.dmitry.taxiapp.ui.list

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dmitry.taxiapp.databinding.ItemOrderBinding
import com.dmitry.taxiapp.model.Order
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class OrdersAdapter(
    private val orderClickListener: (order: Order) -> Unit
) : RecyclerView.Adapter<OrdersAdapter.ViewHolder>() {

    private var list: MutableList<Order> = mutableListOf()

    fun setData(data: List<Order>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class ViewHolder(private val binding: ItemOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(response: Order) {
            with(binding) {
                val date: String = ""
                tvDate.text = date
                tvAmount.text = response.price.amount.toString()
                tvStartAddress.text = ("${response.startAddress.city}, ${response.startAddress.address}")
                tvEndAddress.text = ("${response.endAddress.city}, ${response.endAddress.address}")

                clContainer.setOnClickListener {
                    orderClickListener(response)
                }
            }
        }
    }

    override fun getItemCount() = list.size
}