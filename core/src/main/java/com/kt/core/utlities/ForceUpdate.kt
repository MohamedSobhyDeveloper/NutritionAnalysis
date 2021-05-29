package com.kt.core.utlities

import android.content.IntentSender
import androidx.appcompat.app.AppCompatActivity
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability

 fun   AppCompatActivity.checkUpdate() {


   var appUpdateManager: AppUpdateManager = AppUpdateManagerFactory.create(this)

    // Returns an intent object that you use to check for an update.
    val appUpdateInfoTask = appUpdateManager.getAppUpdateInfo()

    // Checks that the platform will allow the specified type of update.
    appUpdateInfoTask.addOnSuccessListener({ appUpdateInfo ->
        if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
            // For a flexible update, use AppUpdateType.FLEXIBLE
            && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
        ) {


            updateApp(appUpdateInfo,appUpdateManager)

            // Request the update.
        }
    })
}

private fun  AppCompatActivity.updateApp(appUpdateInfo: AppUpdateInfo,appUpdateManager: AppUpdateManager) {
    //Todo add on activity resul
    try {
        appUpdateManager.startUpdateFlowForResult(
            // Pass the intent that is returned by 'getAppUpdateInfo()'.
            appUpdateInfo,
            // Or 'AppUpdateType.FLEXIBLE' for flexible updates.
            AppUpdateType.IMMEDIATE,
            // The current activity making the update request.
            this,
            // Include a request code to later monitor this update request.
            44
        )
    } catch (e: IntentSender.SendIntentException) {
        e.printStackTrace()
    }

}

//endregion
