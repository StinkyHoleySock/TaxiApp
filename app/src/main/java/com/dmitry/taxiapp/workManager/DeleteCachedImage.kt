package com.dmitry.taxiapp.workManager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.dmitry.taxiapp.utils.Constants
import com.dmitry.taxiapp.utils.Constants.DIRECTORY_FULL_NAME
import java.io.File

class DeleteCachedImage(
    context: Context, workerParams: WorkerParameters
) :
    Worker(context, workerParams) {
    override fun doWork(): Result {
        deletePhotoFromInternalStorage()
        return Result.success()
    }


    private fun deletePhotoFromInternalStorage() {
        val fileName = inputData.getString(Constants.REQUEST_ID)!!
        File(DIRECTORY_FULL_NAME, fileName).delete()
    }

}