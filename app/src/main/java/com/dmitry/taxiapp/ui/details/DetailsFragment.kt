package com.dmitry.taxiapp.ui.details

import android.content.Context.MODE_PRIVATE
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.dmitry.taxiapp.R
import com.dmitry.taxiapp.databinding.FragmentDetailsBinding
import com.dmitry.taxiapp.utils.formatDate
import com.dmitry.taxiapp.utils.formatTime
import com.dmitry.taxiapp.utils.getCurrencySymbol
import java.io.IOException

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding get() = _binding!!
    private val args: DetailsFragmentArgs by navArgs()
    lateinit var viewModel: DetailsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[DetailsViewModel::class.java]
        _binding = FragmentDetailsBinding.bind(view)

        initView()
        subscribeUi(args.order.vehicle.photo, args.order.id)
    }

    private fun initView() {

        with(binding) {
            tvDate.text = requireContext().getString(
                R.string.details_date,
                args.order.orderTime.formatDate(),
                args.order.orderTime.formatTime()
            )

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
                (args.order.price.amount % 100).toString(),
                args.order.price.currency.getCurrencySymbol()
            )

            tvDriverName.text =
                requireContext().getString(R.string.details_driver, args.order.vehicle.driverName)

            tvAuto.text = requireContext().getString(
                R.string.details_auto,
                args.order.vehicle.modelName,
                args.order.vehicle.regNumber
            )

        }
    }

    private fun subscribeUi(imageLink: String, orderId: Int) {
        viewModel.getAutoImage(imageLink, orderId)
        viewModel.autoBitmap.value?.let { bitmap ->
            saveImage(
                image = bitmap,
                fileName = System.currentTimeMillis().toString()
            )
        }

        viewModel.autoBitmap.observe(viewLifecycleOwner) {
            binding.ivAutoImage.setImageBitmap(it)
        }
    }

    private fun saveImage(image: Bitmap, fileName: String) {
        try {
            requireContext().openFileOutput("$fileName", MODE_PRIVATE).use { stream ->
                if (!image.compress(Bitmap.CompressFormat.JPEG, 95, stream)) {
                    throw IOException("")
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}