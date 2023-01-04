package com.dmitry.taxiapp.ui.details

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.dmitry.taxiapp.R
import com.dmitry.taxiapp.databinding.FragmentDetailsBinding
import com.dmitry.taxiapp.utils.Constants.IMAGE_URL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding get() = _binding!!
    private val args: DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailsBinding.bind(view)

        initView()
    }

    private fun initView() {

        requireActivity().actionBar?.title =
            requireContext().getString(R.string.details_toolbar, args.order.id)

        with(binding) {
            tvDate.text = requireContext().getString(R.string.details_date, args.order.orderTime)

            tvFrom.text = requireContext().getString(
                R.string.details_from,
                args.order.startAddress.city,
                args.order.startAddress.address
            )

            tvTo.text = requireContext().getString(
                R.string.details_to,
                args.order.endAddress.city,
                args.order.endAddress.address
            )

            tvAmount.text = requireContext().getString(
                R.string.details_amount,
                args.order.price.amount.div(100).toString(),
                args.order.price.amount.toString().takeLast(2),
                args.order.price.currency
            )

            tvDriverName.text =
                requireContext().getString(R.string.details_driver, args.order.vehicle.driverName)

            tvAuto.text = requireContext().getString(
                R.string.details_auto,
                args.order.vehicle.modelName,
                args.order.vehicle.regNumber
            )

            CoroutineScope(Dispatchers.IO).launch {
                val bitmap = downloadBitmap(IMAGE_URL + args.order.vehicle.photo)
                withContext(Dispatchers.Main) {
                    ivAutoImage.setImageBitmap(bitmap)
                }
            }
        }
    }

    private fun downloadBitmap(imageUrl: String): Bitmap? {
        return try {
            val connection = URL(imageUrl).openConnection()
            connection.connect()
            val inputStream = connection.getInputStream()
            val bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream.close()
            bitmap
        } catch (e: Exception) {
            null //fixme
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}