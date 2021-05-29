package com.kt.core.utlities

import android.Manifest
import android.app.Activity
import android.content.Context
import android.database.Cursor
import android.graphics.drawable.Drawable
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.github.drjacky.imagepicker.ImagePicker
import com.kt.core.R
import com.mikhaellopez.circularimageview.CircularImageView

import com.squareup.picasso.Picasso
import com.tbruyelle.rxpermissions2.RxPermissions
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog


import okhttp3.RequestBody
import java.io.File

import java.util.*


/**
 * Created by lenovo on 1/15/2019.
 */

public fun loadImage(path: String? = null, img: ImageView, defaultImage: Int) {
    if (path == null || path.equals("")) {
        Picasso.get().load("path").error(defaultImage)
            .into(img)
    } else {
        Picasso.get().load(path).error(defaultImage)
            .into(img)
    }
}

public fun loadImage(path: String? = null, img: CircularImageView, defaultImage: Int) {
    if (path == null || path.equals("")) {
        Picasso.get().load("path").error(defaultImage)
            .into(img)
    } else {
        Picasso.get().load(path).error(defaultImage)
            .into(img)
    }
}

 fun AppCompatActivity.loadImage(path: String? = null, img: ImageView, defaultImage: Int) {
    if (path == null || path.equals("")) {
        Picasso.get().load("path").error(defaultImage)
            .into(img)
    } else {
        Picasso.get().load(path).error(defaultImage)
            .into(img)
    }
}

fun AppCompatActivity.PermissionsCamera(whenPermissionCamera: (isGranted: Boolean) -> Unit) {

    var rxPermissions: RxPermissions = RxPermissions(this)
    rxPermissions
        .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        .subscribe { granted ->

            whenPermissionCamera(granted)

        }

}

fun Fragment.PermissionsCamera(whenPermissionCamera: (isGranted: Boolean) -> Unit) {

    var rxPermissions: RxPermissions = RxPermissions(this)
    rxPermissions
        .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        .subscribe { granted ->

            whenPermissionCamera(granted)

        }

}

//cb: (String) -> Unit
public fun AppCompatActivity.OpenGallary(onSelectedImage: (uriImage: Uri) -> Unit) {
/*
    TedBottomPicker.with(this)
        .setCameraTile(R.drawable.ic_camera)
        .setTitle("")

        .show(object : TedBottomSheetDialogFragment.OnImageSelectedListener {
            override fun onImageSelected(uri: Uri) {
                onSelectedImage(uri)
            }
        })
*/

}

fun AppCompatActivity.askPermssionCamAndOpenCamGallary(onSelectedImage: (uriImage: Uri) -> Unit) {
    PermissionsCamera() {
        if (it) {
            OpenGallary() {
                onSelectedImage(it)
            }
        }
    }
}

fun AppCompatActivity.askPermssionforImagePicker(onSelectedImage: (uriImage: Uri) -> Unit) {
    PermissionsCamera() {
        if (it) {
            DialogChooseCamOrGallery() {
                if (it) {
                    ImagepickerOpenCamera {
                        onSelectedImage(it)
                    }
                } else {
                    ImagepickerOpenCamera {
                        onSelectedImage(it)
                    }

                }
            }
        }
    }
}

fun AppCompatActivity.ImagepickerOpenGallery(onSelectedImage: (uriImage: Uri) -> Unit) {

    ImagePicker.with(this)
        .compress(1024)
        //Final image size will be less than 1 MB(Optional)
        .galleryOnly()
        .maxResultSize(1080, 1080)  //Final image resolution will be less than 1080 x 1080(Optional)
        .start { resultCode, data ->
            if (resultCode == Activity.RESULT_OK) {
                //Image Uri will not be null for RESULT_OK
                val fileUri: Uri? = data?.data
                fileUri?.let { onSelectedImage(it) }
            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }


}

fun AppCompatActivity.ImagepickerOpenCamera(onSelectedImage: (uriImage: Uri) -> Unit) {

    ImagePicker.with(this)
        .compress(1024)
        //Final image size will be less than 1 MB(Optional)
        .cameraOnly()
        .maxResultSize(1080, 1080)  //Final image resolution will be less than 1080 x 1080(Optional)
        .start { resultCode, data ->
            if (resultCode == Activity.RESULT_OK) {
                //Image Uri will not be null for RESULT_OK
                val fileUri: Uri? = data?.data
                fileUri?.let { onSelectedImage(it) }
            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }


}


fun AppCompatActivity.setGallaryResultInActivity(uri: Uri) {
    val act: AppCompatActivity = this


}

//fun AppCompatActivity.CompressImageAndprepareFilePart(
//    partName: String,
//    imageuri: Uri
//): MultipartBody.Part {
//    val compressedImageFile: File = Compressor(this).compressToFile(File(imageuri.getPath()))
//    return prepareFilePart(partName, compressedImageFile)
//}


/*fun AppCompatActivity.CompressImageAndprepareFilePart(
    partName: String,
    imageuri: Uri
): MultipartBody.Part {
    if(imageuri.toString().contains("png")||imageuri.toString().contains("jpg")){
        val compressedImageFile: File = Compressor(this).compressToFile(File(imageuri.getPath()))
        return prepareFilePart(partName, compressedImageFile)
    }
    else{
        val file = File(imageuri.path)
        return prepareFilePart(partName, file)
    }

}
*/

/*fun prepareFilePart(partName: String, file: File): MultipartBody.Part {

    // create RequestBody instance from file
    val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)
    // MultipartBody.Part is used to send also the actual file name
    var fileName = URLEncoder.encode(file.name, "utf-8")

    if (fileName.length > 50) {
        if(fileName.contains(".")){
            val  xx:Int=  fileName.lastIndexOf(".")
            val randomNumber = ('a'..'z').random()
            fileName=fileName.substring(xx)
            fileName=randomNumber.plus(fileName)
        }


       //// var x = fileName.length - 10
     //  fileName= fileName.subSequence(0, x).toString()
    }
    Log.e("imagename", fileName)
    return MultipartBody.Part.createFormData(partName, fileName, requestFile)
}*/

fun createPartFromString(description: String): RequestBody {
    return RequestBody.create(okhttp3.MultipartBody.FORM, description)
}

fun AppCompatActivity.showCalendar(
    formate: String = "yyyy-MM-dd",
    onSelectedDate: (date: String) -> Unit
) {
    val locale = Locale("en")
    var calendar = Calendar.getInstance()
    calendar?.let {
        val dpd = com.wdullaer.materialdatetimepicker.date.DatePickerDialog

            .newInstance(
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    calendar?.set(year, monthOfYear, dayOfMonth)

                    val simpleDateFormat = java.text.SimpleDateFormat(
                        "yyyy-MM-dd",
                        Locale.ENGLISH
                    )
                    onSelectedDate(simpleDateFormat.format(calendar?.getTime()))
                    Log.e("dateis", simpleDateFormat.format(calendar?.getTime()))
                },
                calendar!!.get(Calendar.YEAR),
                calendar!!.get(Calendar.MONTH),
                calendar!!.get(Calendar.DAY_OF_MONTH)
            )
        //  dpd.setThemeDark(true);
        dpd.locale = Locale("en")
        dpd.setMaxDate(calendar)
        dpd.vibrate(true)
        dpd.dismissOnPause(true)
        // dpd.showYearPickerFirst(true);
        dpd.version = com.wdullaer.materialdatetimepicker.date.DatePickerDialog.Version.VERSION_1
        dpd.setAccentColor("#f00000")

        // dpd.setVersion(showVersion2.isChecked() ? DatePickerDialog.Version.VERSION_2 : DatePickerDialog.Version.VERSION_1);
        dpd.show(supportFragmentManager, "Datepickerdialog")
        // this.fragmentManager?.let { it1 -> dpd.show(it1, "Datepickerdialog") }

    }
}

fun AppCompatActivity.showCalendarByYear(onSelectedDate: (date: String) -> Unit) {
    val locale = Locale("en")
    var calendar = Calendar.getInstance()
    calendar?.let {
        val dpd = com.wdullaer.materialdatetimepicker.date.DatePickerDialog

            .newInstance(
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    calendar?.set(year, monthOfYear, dayOfMonth)

                    val simpleDateFormat = java.text.SimpleDateFormat(
                        "yyyy",
                        Locale.ENGLISH
                    )
                    onSelectedDate(simpleDateFormat.format(calendar?.getTime()))
                    Log.e("dateis", simpleDateFormat.format(calendar?.getTime()))
                },
                calendar!!.get(Calendar.YEAR),
                calendar!!.get(Calendar.MONTH),
                calendar!!.get(Calendar.DAY_OF_MONTH)
            )
        //  dpd.setThemeDark(true);
        dpd.locale = Locale("en")
        dpd.setMaxDate(calendar)
        dpd.vibrate(true)
        dpd.dismissOnPause(true)
        // dpd.showYearPickerFirst(true);
        dpd.version = com.wdullaer.materialdatetimepicker.date.DatePickerDialog.Version.VERSION_1
        dpd.setAccentColor("#f00000")
        dpd.showYearPickerFirst(true)
        // dpd.set
        // dpd.setVersion(showVersion2.isChecked() ? DatePickerDialog.Version.VERSION_2 : DatePickerDialog.Version.VERSION_1);
        dpd.show(supportFragmentManager, "Datepickerdialog")
        // this.fragmentManager?.let { it1 -> dpd.show(it1, "Datepickerdialog") }

    }
}

fun Fragment.showCalendar(onSelectedDate: (date: String) -> Unit) {
    val locale = Locale("en")
    var calendar = Calendar.getInstance()
    calendar?.let {
        val dpd = com.wdullaer.materialdatetimepicker.date.DatePickerDialog

            .newInstance(
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    calendar?.set(year, monthOfYear, dayOfMonth)

                    val simpleDateFormat = java.text.SimpleDateFormat(
                        "yyyy",
                        Locale.ENGLISH
                    )
                    onSelectedDate(simpleDateFormat.format(calendar?.getTime()))
                    Log.e("dateis", simpleDateFormat.format(calendar?.getTime()))
                },
                calendar!!.get(Calendar.YEAR),
                calendar!!.get(Calendar.MONTH),
                calendar!!.get(Calendar.DAY_OF_MONTH)
            )
        //  dpd.setThemeDark(true);

        // dpd.setMaxDate(calendar);

        dpd.vibrate(true)
        dpd.dismissOnPause(true)
        // dpd.showYearPickerFirst(true);
        dpd.version = com.wdullaer.materialdatetimepicker.date.DatePickerDialog.Version.VERSION_2
        dpd.setAccentColor("#f00000")
        dpd.locale = locale
        // dpd.setVersion(showVersion2.isChecked() ? DatePickerDialog.Version.VERSION_2 : DatePickerDialog.Version.VERSION_1);
        //dpd.show(supportFragmentManager, "Datepickerdialog")
        this.fragmentManager?.let { it1 -> dpd.show(it1, "Datepickerdialog") }

    }
}

fun Context.getDrawablefromUri(uri: Uri): Drawable? {
    val f = File(getRealPathFromURI(uri))
    val d: Drawable? = Drawable.createFromPath(f.getAbsolutePath())
    return d
}

private fun Context.getRealPathFromURI(contentURI: Uri): String? {
    val cursor: Cursor? = getContentResolver()?.query(contentURI, null, null, null, null)
    return if (cursor == null) { // Source is Dropbox or other similar local file path
        contentURI.path
    } else {
        cursor.moveToFirst()
        val idx: Int = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
        cursor.getString(idx)
    }
}