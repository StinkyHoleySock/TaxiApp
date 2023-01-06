package com.dmitry.taxiapp.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dmitry.taxiapp.R
import com.dmitry.taxiapp.databinding.ItemOrderBinding
import com.dmitry.taxiapp.model.Order
import com.dmitry.taxiapp.utils.formatDate
import com.dmitry.taxiapp.utils.getCurrencySymbol

class OrdersAdapter(
    private val orderClickListener: (order: Order) -> Unit,
) : RecyclerView.Adapter<OrdersAdapter.ViewHolder>() {

    private var list: MutableList<Order> = mutableListOf()

    fun setData(data: List<Order>) {
        list.clear()
        list.addAll(data)
        list.sortByDescending { it.orderTime }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], holder.itemView.context)
    }

    inner class ViewHolder(private val binding: ItemOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(response: Order, context: Context) {
            with(binding) {

                tvDate.text = response.orderTime.formatDate()

                tvStartAddress.text = context.getString(
                    R.string.address,
                    response.startAddress.city,
                    response.startAddress.address
                )

                tvEndAddress.text = context.getString(
                    R.string.address,
                    response.endAddress.city,
                    response.endAddress.address
                )

                tvAmount.text = context.getString(
                    R.string.amount,
                    response.price.amount.div(100).toString(),
                    (response.price.amount % 100).toString(),
                    response.price.currency.getCurrencySymbol()
                )

                clContainer.setOnClickListener {
                    orderClickListener(response)
                }
            }
        }
    }

    override fun getItemCount() = list.size
}