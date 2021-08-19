package com.uveous.taximohdriver

import android.Manifest
import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.uveous.loopfoonpay.Api.ApiClient
import com.uveous.loopfoonpay.Api.ApiService
import com.uveous.loopfoonpay.Api.Model.backgroundcheck
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.*

class VehicleRegistration :AppCompatActivity() {
    var apitoken: String? = ""
    var userid: Int? = 0
    lateinit var submit: TextView
    lateinit var next: TextView
    private val REQUEST_PERMISSIONS = 100
    private val PICK_IMAGE_REQUEST = 1
    private var bitmap: Bitmap? = null
    private var filePath: String? = null
    var choice: Int? = 0
    lateinit var destination: File
    lateinit var filePart: MultipartBody.Part
    lateinit var requestBody: RequestBody
    lateinit var filePart1: MultipartBody.Part
    lateinit var profile_image: ImageView
    private val MY_CAMERA_PERMISSION_CODE = 100
    protected val CAMERA_REQUEST = 0
    protected val GALLERY_PICTURE = 1
    private val pictureActionIntent: Intent? = null
    lateinit var toolbar: Toolbar

    var selectedImagePath: String? = null
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vehicleregistration)
        next = findViewById(R.id.next)
        submit = findViewById(R.id.submit)
        profile_image = findViewById(R.id.profile_image)
        toolbar=findViewById(R.id.toolbar)
        toolbar.setNavigationOnClickListener(View.OnClickListener {
            startActivity(Intent(this, DriverAcccountSetupSteps::class.java))
        })
        apitoken = intent.getStringExtra("token")
        userid = intent.getIntExtra("userid", 0)

        if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this@VehicleRegistration,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(this@VehicleRegistration,
                            Manifest.permission.READ_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this@VehicleRegistration, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE),
                        REQUEST_PERMISSIONS)
            }
        }


        next.setOnClickListener(View.OnClickListener {
            if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                            Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this@VehicleRegistration,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(this@VehicleRegistration,
                                Manifest.permission.READ_EXTERNAL_STORAGE)) {
                } else {
                    ActivityCompat.requestPermissions(this@VehicleRegistration, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE),
                            REQUEST_PERMISSIONS)
                }
            } else {
                Log.e("Else", "Else")
                startDialog()
            }

            next.setText("Change Photo")

        })

        submit.setOnClickListener(View.OnClickListener {
            submitfun()
        })


    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun startDialog() {
        val myAlertDialog = AlertDialog.Builder(
                this)
        myAlertDialog.setTitle("Upload Pictures Option")
        myAlertDialog.setMessage("How do you want to set your picture?")
        myAlertDialog.setPositiveButton("Gallery"
        ) { arg0, arg1 ->
            var pictureActionIntent: Intent? = null
            pictureActionIntent = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(
                    pictureActionIntent,
                    GALLERY_PICTURE)

            choice=2
        }
        myAlertDialog.setNegativeButton("Camera"
        ) { arg0, arg1 ->
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.CAMERA), MY_CAMERA_PERMISSION_CODE)
            } else {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, CAMERA_REQUEST)
            }
            choice=1
        }
        myAlertDialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        bitmap = null
        selectedImagePath = null
        if (resultCode == Activity.RESULT_OK && requestCode == CAMERA_REQUEST) {
            bitmap = data!!.extras!!["data"] as Bitmap?
            profile_image.setImageBitmap(bitmap)
            val tempUri: Uri? = bitmap?.let { getImageUri(this, it) }
            selectedImagePath = getRealPathFromURI(tempUri)
            val finalFile = File(getRealPathFromURI(tempUri))
            submit.setVisibility(View.VISIBLE)
        } else if (resultCode == Activity.RESULT_OK && requestCode == GALLERY_PICTURE) {
            if (data != null) {
                val selectedImage = data.data
                val filePath = arrayOf(MediaStore.Images.Media.DATA)
                val c: Cursor? = contentResolver.query(selectedImage!!, filePath,
                        null, null, null)
                c!!.moveToFirst()
                val columnIndex: Int = c.getColumnIndex(filePath[0])
                selectedImagePath = c.getString(columnIndex)
                c!!.close()
                if (selectedImagePath != null) {
                    //  txt_image_path.setText(selectedImagePath)
                }
                bitmap = BitmapFactory.decodeFile(selectedImagePath) // load
                // preview image
                bitmap = Bitmap.createScaledBitmap(bitmap!!, 400, 400, false)
                profile_image.setImageBitmap(bitmap)
                submit.setVisibility(View.VISIBLE)
            } else {
                Toast.makeText(applicationContext, "Cancelled",
                        Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getImageUri(inContext: Context, inImage: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path: String = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null)
        return Uri.parse(path)
    }

    fun getRealPathFromURI(uri: Uri?): String? {
        var path = ""
        if (contentResolver != null) {
            val cursor = contentResolver.query(uri!!, null, null, null, null)
            if (cursor != null) {
                cursor.moveToFirst()
                val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
                path = cursor.getString(idx)
                cursor.close()
            }
        }
        return path
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions!!, grantResults)
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show()
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, CAMERA_REQUEST)
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun submitfun() {

        val progressDialog = ProgressDialog(this@VehicleRegistration)
        // progressDialog.setTitle("Kotlin Progress Bar")
        progressDialog.setMessage("Please wait")
        progressDialog.show()
        Log.v("tok", apitoken.toString());
        Log.v("tok", userid.toString());


            val file = File(selectedImagePath)
            Log.v("tok", filePath.toString());
            filePart1 = MultipartBody.Part.createFormData("user_id", userid.toString())
            requestBody = RequestBody.create(MediaType.parse("image/*"), file)
            filePart = MultipartBody.Part.createFormData("vehicle_registration", file.name, requestBody)

        try{
        var mAPIService: ApiService? = null
        mAPIService = ApiClient.apiService
        mAPIService!!.vehicleregistration("Bearer " + apitoken, filePart1, filePart).enqueue(object :
                Callback<backgroundcheck> {
            override fun onResponse(call: Call<backgroundcheck>, response: Response<backgroundcheck>) {
                Log.i("", "post submitted to API." + response.body()!!)
                if (response.isSuccessful()) {
                    var lo: backgroundcheck = response.body()!!
                    if (lo.status == 200) {
                        progressDialog.dismiss()
                        val i= Intent(this@VehicleRegistration,DriverAcccountSetupSteps::class.java)
                        i.putExtra("token",apitoken)
                        i.putExtra("userid", userid!!)
                        startActivity(i)
                        Toast.makeText(this@VehicleRegistration, "submit", Toast.LENGTH_SHORT).show()
                        Log.v("dd", "post registration to API" + response.body()!!.toString())
                    } else {
                        progressDialog.dismiss()
                        Toast.makeText(this@VehicleRegistration, "error", Toast.LENGTH_SHORT).show()
                    }
                    /*   val Intent = Intent(applicationContext, TravelDashboard::class.java)
                       startActivity(Intent)*/
                }
            }

            override fun onFailure(call: Call<backgroundcheck>, t: Throwable) {
                Toast.makeText(this@VehicleRegistration, t.message, Toast.LENGTH_SHORT).show()
            }
        })

        }catch (e:java.lang.Exception){

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val i= Intent(this,DriverAcccountSetupSteps::class.java)
        i.putExtra("token",apitoken)
        i.putExtra("userid", userid!!)
        startActivity(i)
    }
}
