package com.dmitry.taxiapp.ui.details

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.dmitry.taxiapp.R
import com.dmitry.taxiapp.databinding.FragmentDetailsBinding
import com.dmitry.taxiapp.utils.*
import java.io.*

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding get() = _binding!!
    private val args: DetailsFragmentArgs by navArgs()
    lateinit var viewModel: DetailsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[DetailsViewModel::class.java]
        _binding = FragmentDetailsBinding.bind(view)
        val fileName = args.order.vehicle.photo

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

            if (!loadImageFromStorage(fileName)) {
                viewModel.getAutoImage(fileName)
                viewModel.autoBitmap.observe(viewLifecycleOwner) {
                    ivAutoImage.setImageBitmap(it)
                    saveToInternalStorage(it, fileName)
                }
            }

            viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
                binding.ivAutoImage.applyVisibility(!isLoading)
                binding.progressCircular.applyVisibility(isLoading)
            }

        }
    }

    private fun saveToInternalStorage(bitmapImage: Bitmap, fileName: String) {
        val directory: File =
            requireContext().applicationContext.getDir(
                Constants.DIRECTORY_NAME,
                Context.MODE_PRIVATE
            )
        val image = File(directory, fileName)
        var fileOutputStream: FileOutputStream? = null
        try {
            fileOutputStream = FileOutputStream(image)
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                fileOutputStream?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun loadImageFromStorage(fileName: String): Boolean {
        return try {
            val image = File(Constants.DIRECTORY_FULL_NAME, fileName)
            val bitmap = BitmapFactory.decodeStream(FileInputStream(image))
            binding.ivAutoImage.setImageBitmap(bitmap)
            binding.progressCircular.applyVisibility(false)
            true
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}