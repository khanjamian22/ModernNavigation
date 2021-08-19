package com.uveous.taximohdriver

import android.Manifest
import android.annotation.SuppressLint
import android.app.ActivityManager
import android.app.AlertDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.Window
import android.widget.*
import androidx.annotation.NonNull
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import belka.us.androidtoggleswitch.widgets.BaseToggleSwitch.OnToggleSwitchChangeListener
import belka.us.androidtoggleswitch.widgets.ToggleSwitch
import com.bumptech.glide.Glide
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.mindorks.example.ubercaranimation.util.AnimationUtils
import com.mindorks.example.ubercaranimation.util.MapUtils
import com.uveous.loopfoonpay.Api.ApiClient
import com.uveous.loopfoonpay.Api.ApiService
import com.uveous.loopfoonpay.Api.Model.RequestAccept
import com.uveous.loopfoonpay.Api.Model.RideResponse
import com.uveous.loopfoonpay.Api.Model.backgroundcheck
import com.uveous.loopfoonpay.Api.Model.todayearning
import com.uveous.loopfoonpay.LoginActivity
import com.uveous.loopfoonpay.ProfileDetail
import com.uveous.loopfoonpay.Trip
import com.uveous.taximohdriver.Api.Model.profiledetail
import com.uveous.taximohdriver.Api.SessionManager
import de.hdodenhof.circleimageview.CircleImageView
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class TravelDashboard : AppCompatActivity() , OnMapReadyCallback {
    var navigationPosition: Int = 0
    var value: Int = 0
    lateinit var drawerLayout : DrawerLayout;
    lateinit var toolbar : Toolbar
    lateinit var navigationView: NavigationView
    private var mMap: GoogleMap? = null
    var destLatLng: LatLng? = null
    var startLatlng: LatLng? = null
    var driverLatlng: LatLng? = null
    var apitoken:String?=""
    var availabilty:String?=""
    var price:String?=""
    var totaldistance:String?=""
    var userid:Int?=0
    var showpopup:Int?=0
    var clientclick:Int?=0
    var a:Int?=0
    var userId:Int?=0
    var showpopup1:Boolean?=false
    lateinit var offline:TextView
    lateinit var textPickUp:TextView
    lateinit var textDropOff:TextView
    lateinit var name:TextView
    lateinit var phone:TextView
    lateinit var name1:TextView
    lateinit var earning:TextView
    lateinit var simpleRatingBar: RatingBar
    lateinit var lo1: RideResponse
    lateinit var submitreview:TextView
    lateinit var et_post:EditText
    lateinit var phone1:TextView
    lateinit var requestRide:AppCompatButton
    lateinit var rejectRide:AppCompatButton
    lateinit var navigate:AppCompatButton
    lateinit var client:AppCompatButton
    lateinit var viewpathlinear:LinearLayout
    lateinit var acceptreject:LinearLayout
    lateinit var dropup:LinearLayout
    lateinit var pickup:LinearLayout
    lateinit var showratings:LinearLayout
    lateinit var paymentbox:LinearLayout
    lateinit var phonecall:LinearLayout
    lateinit var paymentRide:AppCompatButton
    lateinit var sheet_request_trip:ConstraintLayout
    lateinit var currency:TextView
    lateinit var namelinear:LinearLayout
    private var previousLocation: Location? = null
    private var grayPolyline: Polyline? = null
    private var blackPolyline: Polyline? = null
    private var movingCabMarker: Marker? = null
    private var previousLatLng: LatLng? = null
    private var previousLatLng1: LatLng? = null
    private var currentLatLng: LatLng? = null
    lateinit var online:TextView
    lateinit var cardoffline:CardView
    lateinit var cardonline:CardView
    lateinit var on_off:Switch
    lateinit var currentlocation:ImageView
    lateinit var toggleswitch: ToggleSwitch
    private lateinit var sessionManager: SessionManager
    var handler = Handler()
    var runnable: Runnable? = null
    var delay = 5000
    private var originMarker: Marker? = null
    private var destinationMarker: Marker? = null
    var distance = 0.0
    var distance1 = 0.0
    val DISTANCE = 20.0
    private val UPDATE_INTERVAL_IN_MILLISECONDS: Long = 10000
    private val FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS: Long = 5000

    private val REQUEST_CHECK_SETTINGS = 300
    private var mFusedLocationClient: FusedLocationProviderClient? = null
    private var mSettingsClient: SettingsClient? = null
    private var mLocationRequest: LocationRequest? = null
    private var mLocationSettingsRequest: LocationSettingsRequest? = null
    private var mLocationCallback: LocationCallback? = null
    private var mCurrentLocation: Location? = null
    private var mRequestingLocationUpdates: Boolean? = null

    lateinit var lo: RequestAccept
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashbaord)

        startService(Intent(baseContext, MyService::class.java))

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mSettingsClient = LocationServices.getSettingsClient(this)

        mLocationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                // location is received
                mCurrentLocation = locationResult.lastLocation
                updateLocationUI()
            }
        }
        mRequestingLocationUpdates = false;

        mLocationRequest = LocationRequest()
        mLocationRequest!!.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS)
        mLocationRequest!!.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS)
        mLocationRequest!!.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)

        val builder: LocationSettingsRequest.Builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(mLocationRequest!!)
        mLocationSettingsRequest = builder.build()

        sessionManager = SessionManager(this)
        Dexter.withActivity(this)
            .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                    mRequestingLocationUpdates = true
                    startLocationUpdates()
                }

                override   fun onPermissionDenied(response: PermissionDeniedResponse) {
                    if (response.isPermanentlyDenied()) {
                        // open device settings when the permission is
                        // denied permanently
                        openSettings()
                    }
                }

                override  fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest?,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }).check()

        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)


        apitoken=intent.getStringExtra("token")
        userid=intent.getIntExtra("userid",0)

        initView()

    }
/*
    fun statusCheck() {
        val manager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps()
        }
    }*/

    private fun changeNavigationHeaderInfo() {
        val headerView = navigationView.getHeaderView(0)
        val name = headerView.findViewById(R.id.txtName) as TextView
        val txtEmail = headerView.findViewById(R.id.txtEmail) as TextView
        val itemimage = headerView.findViewById(R.id.itemimage) as CircleImageView
       try{
        var mAPIService: ApiService? = null
        mAPIService = ApiClient.apiService
        sessionManager.fetchuserid()?.let {
            mAPIService!!.getprofile(
                    "Bearer "+ sessionManager.fetchAuthToken(),
                    it
            ).enqueue(object : Callback<profiledetail> {
                override fun onResponse(call: Call<profiledetail>, response: Response<profiledetail>) {
                    Log.i("", "post submitted to API." + response.body()!!)
                    if (response.isSuccessful()) {
                        Log.v("vvv", response.body().toString()!!)
                        var lo: profiledetail = response.body()!!
                        if (lo.status == 200) {
                            txtEmail.setText(lo.email)
                            name.setText(lo.first_name)

                            Glide.with(this@TravelDashboard).load(lo.profile_photo).into(itemimage)

                        } else {

                            //  Toast.makeText(this@ProfileDetail, "not submiited", Toast.LENGTH_SHORT).show()
                        }

                    }
                }

                override fun onFailure(call: Call<profiledetail>, t: Throwable) {
                    Toast.makeText(this@TravelDashboard, t.message, Toast.LENGTH_SHORT).show()
                }
            })
        }
       }catch (e:java.lang.Exception){

       }

        headerView.setOnClickListener(View.OnClickListener {
            val i=Intent(this, ProfileDetail::class.java)
            startActivity(i)
        })
        //  headerView.textEmail.text = "lokeshdesai@android4dev.com"
    }

    private fun buildAlertMessageNoGps() {
        val builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(this)
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
            .setCancelable(false)
            .setPositiveButton("Yes",
                DialogInterface.OnClickListener { dialog, id -> startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
             })
            .setNegativeButton("No",
                DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
        val alert: android.app.AlertDialog? = builder.create()
        alert!!.show()
    }

  /*  private fun fn_permission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) !== PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
            } else {
                ActivityCompat.requestPermissions(
                    this, arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ),
                    REQUEST_PERMISSIONS
                )
            }
        } else {
            boolean_permission = true
            fn_getlocation()
        }
    }*/
/*

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_PERMISSIONS -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    boolean_permission = true
                    fn_getlocation()
                    statusCheck()
                } else {
                    statusCheck()
                    //Toast.makeText(HomeActivity.this, "Please allow the permission", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
*/


    var isGPSEnable = false
    var isNetworkEnable = false
    var latitude : Double= 0.0
    var longitude:Double = 0.0
    var locationManager: LocationManager? = null
    var location: Location? = null
    private val REQUEST_PERMISSIONS = 100

    private val MY_REQUEST = 1001
    var boolean_permission = false

  /*  private fun fn_getlocation() {
        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        isGPSEnable = locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)
        isNetworkEnable = locationManager!!.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        if (!isGPSEnable && !isNetworkEnable) {
        } else {
            if (isNetworkEnable) {
                location = null
                //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,);

                if (locationManager != null) {
                    if (ActivityCompat.checkSelfPermission(
                            this,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                            this,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return
                    }
                    location =
                        locationManager!!.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                    if (location != null) {
                        Log.e("latitude", location!!.latitude.toString() + "")
                        Log.e("longitude", location!!.longitude.toString() + "")
                        latitude = location!!.latitude
                        longitude = location!!.longitude
                        driverLatlng = LatLng(latitude, longitude)
                        originMarker = addOriginDestinationMarkerAndGet(driverLatlng!!)
                        originMarker?.setAnchor(0.5f, 0.5f)
                        updateCarLocation(driverLatlng!!)

                    }
                }
            } else if (isGPSEnable) {
                location = null
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) !== PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) !== PackageManager.PERMISSION_GRANTED
                ) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return
                }
                locationManager!!.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0f, this)
                if (locationManager != null) {
                    location = locationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                    if (location != null) {
                        Log.e("latitude", location!!.latitude.toString() + "")
                        Log.e("longitude", location!!.longitude.toString() + "")
                        latitude = location!!.latitude
                        longitude = location!!.longitude
                        driverLatlng = LatLng(latitude, longitude)
                        originMarker = addOriginDestinationMarkerAndGet(driverLatlng!!)
                        originMarker?.setAnchor(0.5f, 0.5f)
                        updateCarLocation(driverLatlng!!)

                    }
                }
            }
        }
    }
*/
    private fun showDialog() {
        val dialog = Dialog(this@TravelDashboard)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.place_layout)
        val yesBtn = dialog.findViewById(R.id.tv_ok_thanks) as TextView
        val tv_no = dialog.findViewById(R.id.tv_no) as TextView
        yesBtn.setOnClickListener {
            dialog.dismiss()
            availabilty="Online"
            sessionManager.saveAvailabity(availabilty!!)
            cardonline.setCardBackgroundColor(resources.getColor(R.color.white))
            cardoffline.setCardBackgroundColor(resources.getColor(R.color.black1))
            online.setTextColor(resources.getColor(R.color.black))
            offline.setTextColor(resources.getColor(R.color.black))
            getRide()
            onlineapi()
            toggleswitch.setCheckedTogglePosition(0)

            on_off.isChecked=true

        }
        tv_no.setOnClickListener {
            dialog.dismiss()
            availabilty="Offline"
            sessionManager.saveAvailabity(availabilty!!)
            cardonline.setCardBackgroundColor(resources.getColor(R.color.black1))
            cardoffline.setCardBackgroundColor(resources.getColor(R.color.white))
            offline.setTextColor(resources.getColor(R.color.black))
            online.setTextColor(resources.getColor(R.color.black))
            offlineapi()
            toggleswitch.setCheckedTogglePosition(1)

            on_off.isChecked=false
        }

        dialog.show()

    }

    private fun initView(){
        toolbar=findViewById(R.id.toolbar)
        namelinear=findViewById(R.id.namelinear)
        offline=findViewById(R.id.offline)
        textPickUp=findViewById(R.id.textPickUp)
        textDropOff=findViewById(R.id.textDropOff)
        name=findViewById(R.id.name)
        phone=findViewById(R.id.phone)
        name1=findViewById(R.id.name1)
        earning=findViewById(R.id.earning)
        simpleRatingBar=findViewById(R.id.simpleRatingBar)

        try{
            var mAPIService: ApiService? = null
            mAPIService = ApiClient.apiService
            sessionManager.fetchuserid()?.let {
                mAPIService!!.gettodayearning(
                        "Bearer "+ sessionManager.fetchAuthToken(),
                        it
                ).enqueue(object : Callback<todayearning> {
                    override fun onResponse(call: Call<todayearning>, response: Response<todayearning>) {
                        Log.i("", "post submitted to API." + response.body()!!)
                        if (response.isSuccessful()) {
                            Log.v("vvv", response.body().toString()!!)
                            var lo: todayearning = response.body()!!
                            if (lo.status == 200) {
                                earning.text=lo.currency+lo.today_earning
                            } else {

                                //  Toast.makeText(this@ProfileDetail, "not submiited", Toast.LENGTH_SHORT).show()
                            }

                        }
                    }

                    override fun onFailure(call: Call<todayearning>, t: Throwable) {
                        Toast.makeText(this@TravelDashboard, t.message, Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }catch (e:java.lang.Exception){

        }



        phone1=findViewById(R.id.phone1)
        submitreview=findViewById(R.id.submit1)
        et_post=findViewById(R.id.et_post)
   /*     distanceCovered=findViewById(R.id.distanceCovered)
        costDisp=findViewById(R.id.costDisp)*/
        requestRide=findViewById(R.id.requestRide)
        rejectRide=findViewById(R.id.rejectRide)

        navigate=findViewById(R.id.navigate)
        client=findViewById(R.id.client)
        acceptreject=findViewById(R.id.acceptreject)
        viewpathlinear=findViewById(R.id.viewpathlinear)
        currentlocation=findViewById(R.id.currentlocation)
        toggleswitch=findViewById(R.id.toggleswitch)
        toggleswitch.setCheckedTogglePosition(1)


        dropup=findViewById(R.id.dropup)
        pickup=findViewById(R.id.pickup)
        paymentbox=findViewById(R.id.paymentbox)
        phonecall=findViewById(R.id.phonecall)
        paymentRide=findViewById(R.id.paymentRide)
        sheet_request_trip=findViewById(R.id.sheet_request_trip)
        showratings=findViewById(R.id.showratings)
       // currency=findViewById(R.id.currency)
        online=findViewById(R.id.online)
        cardonline=findViewById(R.id.cardonline)
        on_off=findViewById(R.id.on_off)
        cardoffline=findViewById(R.id.cardoffline)
        drawerLayout=findViewById(R.id.drawerLayout)
        navigationView=findViewById(R.id.navigationView1)
        toolbar.title = getString(R.string.app_name)
        setSupportActionBar(toolbar)
        setUpDrawerLayout()

        phonecall.setOnClickListener(View.OnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + phone.text.toString())
            startActivity(dialIntent)

        })

        currentlocation.setOnClickListener(View.OnClickListener {
          updateLocationUI()
        })

        client.setOnClickListener(View.OnClickListener {
            if(clientclick==0){
            navigate.visibility= VISIBLE
            //viewpath.visibility= VISIBLE
            val dialog = Dialog(this@TravelDashboard)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.wantgoclient)
            val yesBtn = dialog.findViewById(R.id.tv_ok_thanks) as TextView
            val tv_no = dialog.findViewById(R.id.tv_no) as TextView
            yesBtn.setOnClickListener {
                dialog.dismiss()
                val dialog1 = Dialog(this@TravelDashboard)
                dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE)
                dialog1.setCancelable(false)
                dialog1.setContentView(R.layout.enterotp)
                val otp = dialog1.findViewById(R.id.otp) as EditText
                val submit = dialog1.findViewById(R.id.submit) as AppCompatButton

                submit.setOnClickListener(View.OnClickListener {
                    if(otp.text.toString().equals(lo.valid_otp.toString())){
                        setMarkerOnMap1()
                        Toast.makeText(this,"Successfully",Toast.LENGTH_LONG).show()
                        client.text="End Trip"
                        clientclick =1
                        dropup.visibility= VISIBLE
                    }else{
                        Toast.makeText(this,"Invalid Otp.",Toast.LENGTH_LONG).show()
                    }
                    dialog1.dismiss()
                })
                dialog1.show()

            }
            tv_no.setOnClickListener {
                dialog.dismiss()
            }

            dialog.show()

            }else{
                paymentbox.visibility= VISIBLE
                sheet_request_trip.visibility= GONE
                val submit = findViewById(R.id.submitpay) as TextView
                val fare = findViewById(R.id.fare) as TextView
                val distance = findViewById(R.id.distance) as TextView
                fare.text=price
                distance.text=totaldistance
                submit.setOnClickListener {
                    try {
                        val progressDialog = ProgressDialog(this@TravelDashboard)
                        // progressDialog.setTitle("Kotlin Progress Bar")
                        progressDialog.setMessage("Please wait")
                        progressDialog.show()
                        progressDialog.setCanceledOnTouchOutside(false)
                        var mAPIService: ApiService? = null
                        mAPIService = ApiClient.apiService
                        mAPIService!!.ridecomplete("Bearer " + sessionManager.fetchAuthToken(), sessionManager.fetchuserid()!!, sessionManager.fetchrequestid()!!).enqueue(object :
                                Callback<backgroundcheck> {
                            override fun onResponse(call: Call<backgroundcheck>, response: Response<backgroundcheck>) {
                                Log.i("", "post submitted to API." + response.body()!!)
                                Log.v("post" , response.body().toString()!!)
                                if (response.isSuccessful()) {
                                    progressDialog.dismiss()
                                    showratings.visibility= VISIBLE
                                    paymentbox.visibility= GONE

                                }
                            }

                            override fun onFailure(call: Call<backgroundcheck>, t: Throwable) {
                                Toast.makeText(this@TravelDashboard, t.message, Toast.LENGTH_SHORT).show()
                            }
                        })
                    }catch (e:java.lang.Exception){

                    }


                }
            }


        })
             submitreview.setOnClickListener(View.OnClickListener {

                 try {
                     val progressDialog = ProgressDialog(this@TravelDashboard)
                     // progressDialog.setTitle("Kotlin Progress Bar")
                     progressDialog.setMessage("Please wait")
                     progressDialog.show()
                     progressDialog.setCanceledOnTouchOutside(false)
                     var mAPIService: ApiService? = null
                     mAPIService = ApiClient.apiService
                     mAPIService!!.review("Bearer " + sessionManager.fetchAuthToken(), sessionManager.fetchrequestid()!!, sessionManager.fetchuserid()!!, simpleRatingBar.getRating().toString(), et_post.text.toString()).enqueue(object :
                             Callback<backgroundcheck> {
                         override fun onResponse(call: Call<backgroundcheck>, response: Response<backgroundcheck>) {
                             Log.i("", "post submitted to API." + response.body()!!)
                             Log.v("post" , response.body().toString()!!)
                             if (response.isSuccessful()) {
                                 var lo: backgroundcheck = response.body()!!
                                 if (lo.status == 200) {
                                     progressDialog.dismiss()
                                     showratings.visibility = GONE
                                     sessionManager.logoutUser1()
                                     showpopup1=false
                                      startActivity(Intent(this@TravelDashboard,TravelDashboard::class.java))
                                     Toast.makeText(this@TravelDashboard, "Your Rating is submitted", Toast.LENGTH_SHORT).show()

                                 } else {
                                     progressDialog.dismiss()
                                     showratings.visibility = GONE


                                     Toast.makeText(this@TravelDashboard, "Your Rating is not submitted", Toast.LENGTH_SHORT).show()
                                 }

                             }
                         }

                         override fun onFailure(call: Call<backgroundcheck>, t: Throwable) {
                             Toast.makeText(this@TravelDashboard, t.message, Toast.LENGTH_SHORT).show()
                         }
                     })
                 }catch (e:java.lang.Exception){

                 }
        })

          navigate.setOnClickListener(View.OnClickListener {
              if(client.text.equals("End Trip")){
                  val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?saddr=${startLatlng!!.latitude},${startLatlng!!.longitude}&daddr=${destLatLng!!.latitude},${destLatLng!!.longitude}"))
                  intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity")
                  startActivity(intent)
                /*  val navigation = Uri.parse("google.navigation:q=" + destLatLng!!.latitude + "," + destLatLng!!.longitude + "")
                  val navigationIntent = Intent(Intent.ACTION_VIEW, navigation)
                  navigationIntent.setPackage("com.google.android.apps.maps")
                  startActivity(navigationIntent)*/
              }else {
                  val navigation = Uri.parse("google.navigation:q=" + startLatlng!!.latitude + "," + startLatlng!!.longitude + "")
                  val navigationIntent = Intent(Intent.ACTION_VIEW, navigation)
                  navigationIntent.setPackage("com.google.android.apps.maps")
                  startActivity(navigationIntent)
              }


        })

        toggleswitch.setOnToggleSwitchChangeListener(OnToggleSwitchChangeListener { position, isChecked ->
            if (isChecked&&position==0) {
                onlineapi()
            } else {
                offlineapi()
            }
        })

        on_off.setOnCheckedChangeListener(
                object : CompoundButton.OnCheckedChangeListener {
                    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                        if (isChecked) {
                            onlineapi()
                        } else {
                            offlineapi()
                        }
                    }
                })

        cardonline.setOnClickListener(View.OnClickListener {
            cardonline.setCardBackgroundColor(resources.getColor(R.color.white))
            cardoffline.setCardBackgroundColor(resources.getColor(R.color.black1))
            online.setTextColor(resources.getColor(R.color.black))
            offline.setTextColor(resources.getColor(R.color.black))
            onlineapi()
        })

        cardoffline.setOnClickListener(View.OnClickListener {
            cardonline.setCardBackgroundColor(resources.getColor(R.color.black1))
            cardoffline.setCardBackgroundColor(resources.getColor(R.color.white))
            offline.setTextColor(resources.getColor(R.color.black))
            online.setTextColor(resources.getColor(R.color.black))

            offlineapi()
        })

        if(sessionManager.fetchAvailabilty().equals("")){
            showDialog()
        }else{
            if(sessionManager.fetchAvailabilty().equals("Online")){
                on_off.isChecked=true
                toggleswitch.setCheckedTogglePosition(0)

                cardonline.setCardBackgroundColor(resources.getColor(R.color.white))
                cardoffline.setCardBackgroundColor(resources.getColor(R.color.black1))
                online.setTextColor(resources.getColor(R.color.black))
                offline.setTextColor(resources.getColor(R.color.black))
                onlineapi()
            }else{
                on_off.isChecked=false
                toggleswitch.setCheckedTogglePosition(1)

                cardonline.setCardBackgroundColor(resources.getColor(R.color.black1))
                cardoffline.setCardBackgroundColor(resources.getColor(R.color.white))
                offline.setTextColor(resources.getColor(R.color.black))
                online.setTextColor(resources.getColor(R.color.black))
                offlineapi()
            }
        }

       /* if(sessionManager.fetchuserrequest().equals("accept")){
            paymentRide.visibility= VISIBLE
            rejectRide.visibility= GONE
            requestRide.visibility= GONE
            cardonline.isEnabled=false
            cardoffline.isEnabled=false
        }else{
            paymentRide.visibility= GONE
            rejectRide.visibility= VISIBLE
            requestRide.visibility= VISIBLE
            cardonline.isEnabled=true
            cardoffline.isEnabled=true
        }
*/
        requestRide.setOnClickListener(View.OnClickListener {
            try {

                var mAPIService: ApiService? = null
                mAPIService = ApiClient.apiService
                sessionManager.fetchuserid()?.let {
                    mAPIService!!.accept(
                            "Bearer " + sessionManager.fetchAuthToken(), it, sessionManager.fetchrequestid()!!, "accept", "").enqueue(object : Callback<RequestAccept> {
                        override fun onResponse(call: Call<RequestAccept>, response: Response<RequestAccept>) {
                            Log.i("", "post submitted to API." + response.body()!!)
                            if (response.isSuccessful()) {
                                lo = response.body()!!
                                if (lo.status == 200) {
                                    namelinear.visibility= VISIBLE
                                    cardonline.isEnabled = false
                                    cardoffline.isEnabled = false
                                    viewpathlinear.visibility = VISIBLE
                                    rejectRide.visibility = GONE
                                    requestRide.visibility = GONE
                                    acceptreject.visibility = GONE

                                    name.text = lo.name
                                    phone.text = lo.mobile
                                    name1.text = lo.name
                                    phone1.text = lo.mobile
                                    userId=lo.user_id
                                    a=1
                                    sessionManager.saverequestAccept("accept")
                                    Toast.makeText(this@TravelDashboard, "Accept", Toast.LENGTH_SHORT).show()
                                    //     sheet_request_trip.visibility=VISIBLE
                                    setMarkerOnMap()
                                } else if (lo.status == 400) {

                                    showDialog("This ride is cancel by user.")
                                    sheet_request_trip.visibility = GONE
                                    //   Toast.
                                    //   \
                                    //   makeText(this@TravelDashboard,lo.msg, Toast.LENGTH_SHORT).show()
                                }

                            }
                        }

                        override fun onFailure(call: Call<RequestAccept>, t: Throwable) {
                            Toast.makeText(this@TravelDashboard, t.message, Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            }catch (e:java.lang.Exception){

            }
        })

        rejectRide.setOnClickListener(View.OnClickListener {

            rejectshowDialog()

        })
        //Load Inbox fragment first
        navigationPosition = R.id.dashboard
        navigationView.setCheckedItem(navigationPosition)


        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.trip -> {
                    val i=Intent(this, Trip::class.java)
                    startActivity(i)
                }
                R.id.dashboard -> {
                    if (drawerLayout.isDrawerOpen(Gravity.START)) {
                        drawerLayout.closeDrawer(Gravity.START)
                    }
                }
                R.id.logout -> {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Alert").setMessage("Are you sure you want to Logout from app")
                    builder.setNegativeButton("No") { dialog, which -> dialog.dismiss() }.
                    setPositiveButton("Yes") { dialog, which -> // Constants.ACTIVITY_NAME=Constants.HOME_ACTIVITY;
                        logout()

                    }

                    val dialog = builder.create()
                    dialog.show()

                }
                R.id.about -> {
                    startActivity(Intent(this@TravelDashboard, About::class.java))
                }
                R.id.support -> {
                    startActivity(Intent(this@TravelDashboard, Support::class.java))
                }
                R.id.wallet -> {
                    startActivity(Intent(this@TravelDashboard, Mywallet::class.java))
                }
                R.id.mywallet -> {
                    startActivity(Intent(this@TravelDashboard, Driverwallet::class.java))
                }
            }
            // set item as selected to persist highlight
            menuItem.isChecked = true
            // close drawer when item is tapped
            drawerLayout.closeDrawers()
            true
        }

        //Change navigation header information
        changeNavigationHeaderInfo()



    }

    private fun paymentdialog() {
        val dialog = Dialog(this@TravelDashboard)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.payment_layout)
        val submit = dialog.findViewById(R.id.submitpay) as TextView
        val fare = dialog.findViewById(R.id.fare) as TextView
        val distance = dialog.findViewById(R.id.distance) as TextView
        fare.text=lo1.currency+lo1.price
        distance.text=lo1.distance+"km"
        submit.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun rejectshowDialog() {
        val dialog = Dialog(this@TravelDashboard)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.reject_dialog)
            val et_post = dialog.findViewById(R.id.et_post) as EditText
            val bt_cancel = dialog.findViewById(R.id.bt_cancel) as AppCompatButton
            val bt_submit = dialog.findViewById(R.id.bt_submit) as AppCompatButton
             bt_cancel.setOnClickListener {
                dialog.dismiss()
            }
            bt_submit.setOnClickListener(View.OnClickListener {
                if(et_post.text.toString().isEmpty()){
                    Toast.makeText(this@TravelDashboard,"Enter reason for rejection",Toast.LENGTH_LONG).show()
                }else {
                    try {
                        val progressDialog = ProgressDialog(this@TravelDashboard)
                        // progressDialog.setTitle("Kotlin Progress Bar")
                        progressDialog.setMessage("Please wait")
                        progressDialog.show()
                        var mAPIService: ApiService? = null
                        mAPIService = ApiClient.apiService
                        sessionManager.fetchuserid()?.let {
                            mAPIService!!.accept(
                                    "Bearer " + sessionManager.fetchAuthToken(), it, sessionManager.fetchrequestid()!!, "reject", et_post.text.toString()).enqueue(object : Callback<RequestAccept> {
                                override fun onResponse(call: Call<RequestAccept>, response: Response<RequestAccept>) {
                                    Log.i("", "post submitted to API." + response.body()!!)
                                    if (response.isSuccessful()) {
                                        var lo: RequestAccept = response.body()!!
                                        if (lo.status == 200) {
                                            dialog.dismiss()
                                            showpopup1=false
                                            progressDialog.dismiss()
                                            Toast.makeText(this@TravelDashboard, "Reject", Toast.LENGTH_SHORT).show()
                                            sheet_request_trip.visibility = GONE
                                        } else {
                                            progressDialog.dismiss()
                                            sheet_request_trip.visibility = VISIBLE
                                            //   Toast.makeText(this@TravelDashboard,lo.msg, Toast.LENGTH_SHORT).show()
                                        }


                                    }
                                }

                                override fun onFailure(call: Call<RequestAccept>, t: Throwable) {
                                    Toast.makeText(this@TravelDashboard, t.message, Toast.LENGTH_SHORT).show()
                                }
                            })
                        }
                    }catch (e:java.lang.Exception){

                    }
                }
            })

            dialog.show()


    }


    private fun onlineapi() {
        try {
            val progressDialog = ProgressDialog(this@TravelDashboard)
            // progressDialog.setTitle("Kotlin Progress Bar")
            progressDialog.setMessage("Please wait")
            progressDialog.show()
            var mAPIService: ApiService? = null
            mAPIService = ApiClient.apiService
            sessionManager.fetchuserid()?.let {
                mAPIService!!.online(
                        "Bearer " + sessionManager.fetchAuthToken(), it, mCurrentLocation!!.latitude.toString(), mCurrentLocation!!.longitude.toString()).enqueue(object : Callback<backgroundcheck> {
                    override fun onResponse(call: Call<backgroundcheck>, response: Response<backgroundcheck>) {
                        Log.i("", "post submitted to API." + response.body()!!)
                        if (response.isSuccessful()) {
                            var lo: backgroundcheck = response.body()!!
                            if (lo.status == 200) {
                                availabilty = "Online"
                                sessionManager.saveAvailabity(availabilty!!)
                                getRide()
                                progressDialog.dismiss()
                                Toast.makeText(this@TravelDashboard, "Online", Toast.LENGTH_SHORT).show()
                            } else {
                                progressDialog.dismiss()
                                //   Toast.makeText(this@TravelDashboard,lo.msg, Toast.LENGTH_SHORT).show()
                            }

                            /*    val Intent = Intent(applicationContext, TravelDashboard::class.java)
                        startActivity(Intent)
*/
                        }
                    }

                    override fun onFailure(call: Call<backgroundcheck>, t: Throwable) {
                        progressDialog.dismiss()
                        Toast.makeText(this@TravelDashboard, t.message, Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }catch (e:java.lang.Exception){

        }
    }

    override fun onResume() {
        super.onResume()
        if (mRequestingLocationUpdates!! && checkPermissions()) {
            updateLocationUI()
        }
        if(showpopup1==false){
             handler.postDelayed(Runnable {
             getRide()
            handler.postDelayed(runnable!!, delay.toLong())
        }.also { runnable = it }, delay.toLong())
        }
    }

    private fun checkPermissions(): Boolean {
        val permissionState = ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        return permissionState == PackageManager.PERMISSION_GRANTED
    }

    override fun onPause() {
        super.onPause()
        if(showpopup1!!){
            sessionManager.saveAvailabity1("On-site")
        }
        handler.removeCallbacks(runnable!!)
    }


    private fun getRide() {
   /*     val progressDialog = ProgressDialog(this@TravelDashboard)
        // progressDialog.setTitle("Kotlin Progress Bar")
        progressDialog.setMessage("Please wait")
        progressDialog.show()*/
        try{
        var mAPIService: ApiService? = null
        mAPIService = ApiClient.apiService
        sessionManager.fetchuserid()?.let {
            mAPIService!!.getRide(
                    "Bearer "+sessionManager.fetchAuthToken(), it).enqueue(object : Callback<RideResponse> {
                override fun onResponse(call: Call<RideResponse>, response: Response<RideResponse>) {
                    Log.i("", "post submitted to API." + response.body()!!)
                    if (response.isSuccessful()) {
                        Log.v("response",response.toString())
                        lo1= response.body()!!
                        if(lo1.status==200){

                            shownotification()
                            availabilty="Online"
                            price=lo1.currency+lo1.price
                            totaldistance=lo1.distance
                         //   progressDialog.dismiss()
                            sheet_request_trip.visibility=VISIBLE
                            //currency.setText(lo.currency)
                            textPickUp.setText(lo1.origin_address)
                          /*  costDisp.setText(lo.price)
                            distanceCovered.setText(lo.distance)*/
                            textDropOff.setText(lo1.destination_address)
                            showpopup=1

                            startLatlng = LatLng(lo1.origin_latitude!!.toDouble(), lo1.origin_longitude!!.toDouble())
                            destLatLng = LatLng(lo1.destination_latitude!!.toDouble(), lo1.destination_longitude!!.toDouble())

                            lo1.request_id?.let { it1 -> sessionManager.saverequestid(it1) }
                        }

                        /*    val Intent = Intent(applicationContext, TravelDashboard::class.java)
                            startActivity(Intent)
    */
                    }
                }

                override fun onFailure(call: Call<RideResponse>, t: Throwable) {
                    Toast.makeText(this@TravelDashboard, t.message, Toast.LENGTH_SHORT).show()
                }
            })
        }
        }catch (e:java.lang.Exception){

        }
    }

    private fun shownotification(){
        val defaultRingtoneUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val mediaPlayer = MediaPlayer()

        if(showpopup1==false) {

            try {
                mediaPlayer.setDataSource(this@TravelDashboard, defaultRingtoneUri)
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_NOTIFICATION)
                mediaPlayer.prepare()
                mediaPlayer.setOnCompletionListener(object : MediaPlayer.OnCompletionListener {
                    override fun onCompletion(mp: MediaPlayer) {
                        mp.release()
                        showpopup1=true
                    }
                })
                mediaPlayer.start()
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            } catch (e: SecurityException) {
                e.printStackTrace()
            } catch (e: IllegalStateException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun showDialog(text:String) {
        val dialog = Dialog(this@TravelDashboard)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.place_layout1)
        val yesBtn = dialog.findViewById(R.id.tv_ok_thanks) as TextView
        val tv_message_thanks = dialog.findViewById(R.id.tv_message_thanks) as TextView
        tv_message_thanks.setText(text)
        yesBtn.setOnClickListener {
            dialog.dismiss()

        }

        dialog.show()

    }
    fun setMarkerOnMap() {
        try {
            Log.d("Latitude", "28.6263905$latitude")
            Log.d("Longitude", "77.3722361$longitude")
            originMarker = addOriginDestinationMarkerAndGet(driverLatlng!!)
            originMarker?.setAnchor(0.5f, 0.5f)
            destinationMarker = addOriginDestinationMarkerAndGet(startLatlng!!)
            destinationMarker?.setAnchor(0.5f, 0.5f)
            val loc1 = Location("one")
            loc1.setLatitude(startLatlng!!.latitude)
            loc1.setLongitude(startLatlng!!.longitude)
            val loc2 = Location("two")
            loc2.setLatitude(driverLatlng!!.latitude)
            loc2.setLongitude(driverLatlng!!.longitude)
            distance= loc1.distanceTo(loc2).toDouble()
            moveCamera(driverLatlng!!)
           //animateCamera(driverLatlng!!)
            updateCarLocation(driverLatlng!!)
            // Getting URL to the Google Directions API
            val url: String? = getUrl(driverLatlng!!, startLatlng!!)
            Log.d("onMapClick", url.toString())
            val FetchUrl: FetchUrl = FetchUrl()

                FetchUrl.execute(url)
                //mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(driverLatlng, 16f))
            /*} else {
                marker!!.setPosition(driverLatlng!!)
            }
*/

        } catch (e: Exception) {
            Log.d("MapException", e.toString())
        }
    }

    private fun getUrl(origin: LatLng, dest: LatLng): String? {
        val str_origin = "origin=" + origin.latitude + "," + origin.longitude
        val str_dest = "destination=" + dest.latitude + "," + dest.longitude
        val sensor = "sensor=false"
        val parameters = "$str_origin&$str_dest&$sensor"
        val output = "json"
        // Building the url to the web service
        return ("https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters
                + "&key=" + "AIzaSyD8UzxFsaAYwR0ZenE3v_zAg10ZuPov72w")
    }

    private inner class FetchUrl : AsyncTask<String?, Void?, String>() {
        override fun onPostExecute(result: String) {
            super.onPostExecute(result)
            val parserTask:ParserTask = ParserTask()

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result)
        }

        override fun doInBackground(vararg url: String?): String {
            var data = ""
            try {
                // Fetching the data from web service
                data = url[0]?.let { downloadUrl(it) }!!
                Log.e("Background Task data", data)
            } catch (e: java.lang.Exception) {
                Log.d("Background Task", e.toString())
            }
            return data
        }

    }
    @Throws(IOException::class)
    private  fun downloadUrl(strUrl: String): String {
        var data = ""
        var iStream: InputStream? = null
        var urlConnection: HttpURLConnection? = null
        try {
            val url = URL(strUrl)

            // Creating an http connection to communicate with url
            urlConnection = url.openConnection() as HttpURLConnection

            // Connecting to url
            urlConnection.connect()

            // Reading data from url
            iStream = urlConnection!!.inputStream
            val br =
                BufferedReader(InputStreamReader(iStream))
            val sb = StringBuffer()
            var line: String? = ""
            while (br.readLine().also { line = it } != null) {
                sb.append(line)
            }
            data = sb.toString()
            Log.d("downloadUrl", data)
            br.close()
        } catch (e: java.lang.Exception) {
            Log.d("Exception", e.toString())
        } finally {
            iStream!!.close()
            urlConnection!!.disconnect()
        }
        return data
    }

    private inner class ParserTask : AsyncTask<String?, Int?, List<List<HashMap<String, String>>>?>() {
        var lineOptions: PolylineOptions? = null
        var startTrack = false
        var points: ArrayList<LatLng>? = null
        override fun onPostExecute(result: List<List<HashMap<String, String>>>?) {

            lineOptions = null

            // Traversing through all the routes
            for (i in result!!.indices) {
                points = java.util.ArrayList<LatLng>()
                lineOptions = PolylineOptions()

                // Fetching i-th route
                val path =
                    result[i]

                // Fetching all the points in i-th route
                for (j in path.indices) {
                    val point = path[j]
                    val lat = point["lat"]!!.toDouble()
                    val lng = point["lng"]!!.toDouble()
                    val position = LatLng(lat, lng)
                    points!!.add(position)
                }

                // Adding all the points in the route to LineOptions
                lineOptions!!.addAll(points)
                lineOptions!!.width(10f)
                lineOptions!!.color(Color.BLACK)
                Log.d("onPostExecute", "onPostExecute lineoptions decoded")
            }

            // Drawing polyline in the Google Map for the i-th route
            if (lineOptions != null) {
                mMap!!.addPolyline(lineOptions)
            } else {
                Log.d("onPostExecute", "without Polylines drawn")
            }
        }

        override fun doInBackground(vararg jsonData: String?): List<List<HashMap<String, String>>>? {
            val jObject: JSONObject
            var routes: List<List<HashMap<String, String>>>? =
                null
            try {
                jObject = JSONObject(jsonData[0])
                Log.d("ParserTask", jsonData[0].toString())
                val parser = DirectionsJSONParser()
                Log.d("ParserTask", parser.toString())

                // Starts parsing data
                routes = parser.parse(jObject)
                Log.d("ParserTask", "Executing routes")
                Log.d("ParserTask", routes.toString())
            } catch (e: java.lang.Exception) {
                Log.d("ParserTask", e.toString())
                e.printStackTrace()
            }
            return routes
        }
    }


    private fun offlineapi() {
        try {
       val progressDialog = ProgressDialog(this@TravelDashboard)
        // progressDialog.setTitle("Kotlin Progress Bar")
        progressDialog.setMessage("Please wait")
        progressDialog.show()
        var mAPIService: ApiService? = null
        mAPIService = ApiClient.apiService
        sessionManager.fetchuserid()?.let {
            mAPIService!!.offline(
                    "Bearer "+sessionManager.fetchAuthToken(), it).enqueue(object : Callback<backgroundcheck> {
            override fun onResponse(call: Call<backgroundcheck>, response: Response<backgroundcheck>) {
                Log.i("", "post submitted to API." + response.body()!!)
                if (response.isSuccessful()) {
                    var lo: backgroundcheck = response.body()!!
                    if(lo.status==200){
                        availabilty="Offline"
                        sessionManager.saveAvailabity(availabilty!!)
                        sheet_request_trip.visibility= GONE
                        progressDialog.dismiss()
                        Toast.makeText(this@TravelDashboard,"Offline", Toast.LENGTH_SHORT).show()
                    }else{
                        progressDialog.dismiss()
                        //   Toast.makeText(this@TravelDashboard,lo.msg, Toast.LENGTH_SHORT).show()
                    }

                    /*    val Intent = Intent(applicationContext, TravelDashboard::class.java)
                        startActivity(Intent)
*/
                }
            }

            override fun onFailure(call: Call<backgroundcheck>, t: Throwable) {
                Toast.makeText(this@TravelDashboard, t.message, Toast.LENGTH_SHORT).show()
            }
        })
        }
        }catch (e:java.lang.Exception){

        }

    }   private fun offlineapi1() {
        try {

        var mAPIService: ApiService? = null
        mAPIService = ApiClient.apiService
        sessionManager.fetchuserid()?.let {
            mAPIService!!.offline(
                    "Bearer "+sessionManager.fetchAuthToken(), it).enqueue(object : Callback<backgroundcheck> {
            override fun onResponse(call: Call<backgroundcheck>, response: Response<backgroundcheck>) {
                Log.i("", "post submitted to API." + response.body()!!)
                if (response.isSuccessful()) {
                    var lo: backgroundcheck = response.body()!!
                    if(lo.status==200){
                        availabilty="Offline"
                        sheet_request_trip.visibility= GONE
                        Toast.makeText(this@TravelDashboard,"Offline", Toast.LENGTH_SHORT).show()
                    }else{
                        //   Toast.makeText(this@TravelDashboard,lo.msg, Toast.LENGTH_SHORT).show()
                    }

                    /*    val Intent = Intent(applicationContext, TravelDashboard::class.java)
                        startActivity(Intent)
*/
                }
            }

            override fun onFailure(call: Call<backgroundcheck>, t: Throwable) {
                Toast.makeText(this@TravelDashboard, t.message, Toast.LENGTH_SHORT).show()
            }
        })
        }
        }catch (e:java.lang.Exception){

        }

    }
     private fun logout() {
        val progressDialog = ProgressDialog(this@TravelDashboard)
        // progressDialog.setTitle("Kotlin Progress Bar")
        progressDialog.setMessage("Please wait")
        progressDialog.show()
        var mAPIService: ApiService? = null
        mAPIService = ApiClient.apiService
        sessionManager.fetchuserid()?.let {
            mAPIService!!.logout(
                    "Bearer "+sessionManager.fetchAuthToken(), it).enqueue(object : Callback<backgroundcheck> {
            override fun onResponse(call: Call<backgroundcheck>, response: Response<backgroundcheck>) {
                Log.i("", "post submitted to API." + response.body()!!)
                if (response.isSuccessful()) {
                    var lo: backgroundcheck = response.body()!!
                    if(lo.status==200){
                        val mSharedPreferences = applicationContext.getSharedPreferences("TASK_ID", 0)
                        mSharedPreferences?.edit()?.remove("userid")?.commit()
                        sessionManager.logoutUser()
                        startActivity(Intent(this@TravelDashboard, LoginActivity::class.java)) //.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        finishAffinity()
                        progressDialog.dismiss()
                     //   Toast.makeText(this@TravelDashboard,"Offline", Toast.LENGTH_SHORT).show()
                    }else{
                        progressDialog.dismiss()
                        //   Toast.makeText(this@TravelDashboard,lo.msg, Toast.LENGTH_SHORT).show()
                    }

                    /*    val Intent = Intent(applicationContext, TravelDashboard::class.java)
                        startActivity(Intent)
*/
                }
            }

            override fun onFailure(call: Call<backgroundcheck>, t: Throwable) {
                Toast.makeText(this@TravelDashboard, t.message, Toast.LENGTH_SHORT).show()
            }
        })
        }
    }



    private fun setUpDrawerLayout() {
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.drawerOpen, R.string.drawerClose)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun navigateToFragment(fragmentToNavigate: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        //fragmentTransaction.replace(R.id.frameLayout, fragmentToNavigate)
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    @SuppressLint("WrongConstant")
    override fun onBackPressed() {

        if (drawerLayout.isDrawerOpen(Gravity.START)) {
            drawerLayout.closeDrawer(Gravity.START)
        }

        if (navigationPosition == R.id.trip) {
            finish()
        } else {
            //Navigate to Inbox Fragment
            navigationPosition = R.id.trip
         //   navigateToFragment(InboxFragment.newInstance())
        //    navigationView.setCheckedItem(navigationPosition)
            toolbar.title = "Travel"
        }
    }


    private fun updateCarLocation(latLng: LatLng) {
        if (movingCabMarker == null) {
            movingCabMarker = addCarMarkerAndGet(latLng)
        }
        if (previousLatLng == null) {
            currentLatLng = latLng
            previousLatLng = currentLatLng
            movingCabMarker?.position = currentLatLng
            movingCabMarker?.setAnchor(0.5f, 0.5f)
          //  animateCamera(currentLatLng!!)
        } else {
            previousLatLng = currentLatLng
            currentLatLng = latLng
            val valueAnimator = AnimationUtils.carAnimator()
            valueAnimator.addUpdateListener { va ->
                if (currentLatLng != null && previousLatLng != null) {
                    val multiplier = va.animatedFraction
                    val nextLocation = LatLng(
                            multiplier * currentLatLng!!.latitude + (1 - multiplier) * previousLatLng!!.latitude,
                            multiplier * currentLatLng!!.longitude + (1 - multiplier) * previousLatLng!!.longitude
                    )
                    movingCabMarker?.position = nextLocation
                    val rotation = MapUtils.getRotation(previousLatLng!!, nextLocation)
                    if (!rotation.isNaN()) {
                        movingCabMarker?.rotation = rotation
                    }
                    movingCabMarker?.setAnchor(0.5f, 0.5f)
                   // animateCamera(nextLocation)
                }
            }
            valueAnimator.start()
        }
    }

    private fun addCarMarkerAndGet(latLng: LatLng): Marker {
        mMap!!.clear()
        val bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(MapUtils.getCarBitmap(this))
        return mMap!!.addMarker(
                MarkerOptions().position(latLng).flat(true).icon(bitmapDescriptor)
        )
    }


    private fun setMarkerOnMap1() {
        try {
            Log.d("Latitude", "28.6263905$latitude")
            Log.d("Longitude", "77.3722361$longitude")

            val loc3 = Location("one")
            loc3.setLatitude(destLatLng!!.latitude)
            loc3.setLongitude(destLatLng!!.longitude)
            val loc4 = Location("two")
            loc4.setLatitude(driverLatlng!!.latitude)
            loc4.setLongitude(driverLatlng!!.longitude)
            distance1= loc3.distanceTo(loc4).toDouble()

                originMarker = addOriginDestinationMarkerAndGet(startLatlng!!)
                originMarker?.setAnchor(0.5f, 0.5f)
                destinationMarker = addOriginDestinationMarkerAndGet(destLatLng!!)
                destinationMarker?.setAnchor(0.5f, 0.5f)

                moveCamera(startLatlng!!)
                animateCamera(startLatlng!!)
                updateCarLocation(startLatlng!!)

                // Getting URL to the Google Directions API
                val url: String? = getUrl(startLatlng!!, destLatLng!!)
                Log.d("onMapClick", url.toString())
                val FetchUrl: FetchUrl = FetchUrl()

                FetchUrl.execute(url)

        } catch (e: Exception) {
            Log.d("MapException", e.toString())
        }
    }


    private fun moveCamera(latLng: LatLng) {
        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(latLng))
    }

    private fun animateCamera(latLng: LatLng) {
        val cameraPosition = CameraPosition.Builder().target(latLng).zoom(15.5f).build()
        mMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    override fun onMapReady(p0: GoogleMap?) {
        mMap = p0
        mMap!!.getUiSettings().setZoomControlsEnabled(true)

    /*    Handler().postDelayed(Runnable {
            showPath(MapUtils.getListOfLocations())
            showMovingCab(MapUtils.getListOfLocations())
        }, 3000)*/

    }

    private fun startLocationUpdates() {
        mSettingsClient!!.checkLocationSettings(mLocationSettingsRequest)
            .addOnSuccessListener(this, object : OnSuccessListener<LocationSettingsResponse?> {
                @SuppressLint("MissingPermission")
                override fun onSuccess(locationSettingsResponse: LocationSettingsResponse?) {
                    //   Log.i(FragmentActivity.TAG, "All location settings are satisfied.")
                    /*   Toast.makeText(
                           applicationContext,
                           "Started location updates!",
                           Toast.LENGTH_SHORT
                       ).show()*/
                    mFusedLocationClient!!.requestLocationUpdates(
                        mLocationRequest,
                        mLocationCallback, Looper.myLooper()
                    )
                    updateLocationUI()
                }
            })
            .addOnFailureListener(this, object : OnFailureListener {
                override fun onFailure(@NonNull e: java.lang.Exception) {
                    val statusCode: Int = (e as ApiException).getStatusCode()
                    when (statusCode) {
                        LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {

                            try {
                                // Show the dialog by calling startResolutionForResult(), and check the
                                // result in onActivityResult().
                                val rae: ResolvableApiException = e as ResolvableApiException
                                rae.startResolutionForResult(
                                    this@TravelDashboard,
                                    REQUEST_CHECK_SETTINGS
                                )
                            } catch (sie: IntentSender.SendIntentException) {

                            }
                        }
                        LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                            val errorMessage =
                                "Location settings are inadequate, and cannot be " +
                                        "fixed here. Fix in Settings."

                        }
                    }
                    updateLocationUI()
                }
            })
    }
    private var place1: MarkerOptions? = null
    private  var place2:MarkerOptions? = null
    private var currentPolyline: Polyline? = null
    var marker: Marker? = null
    private var mFirebaseDatabase: DatabaseReference?=null

    private fun updateLocationUI() {
        try {

            if (mCurrentLocation != null) {
                val geocoder: Geocoder
                val yourAddresses: List<Address>
                geocoder = Geocoder(this, Locale.getDefault())
                yourAddresses = geocoder.getFromLocation(mCurrentLocation!!.latitude, mCurrentLocation!!.longitude, 1)

                Log.v("lo", mCurrentLocation!!.latitude.toString())
                Log.v("lo1", mCurrentLocation!!.longitude.toString())
                driverLatlng = LatLng(mCurrentLocation!!.latitude, mCurrentLocation!!.longitude)

                /*  if(a==1) {
                val ha = Handler()
                ha.postDelayed({
                    mFirebaseDatabase = FirebaseDatabase.getInstance()
                        .getReference("location/$userId-${sessionManager.fetchrequestid()}-${sessionManager.fetchuserid()}")
                    val map: MutableMap<String, Double> = HashMap()
                    map["latitude"] = mCurrentLocation!!.latitude
                    map["longitude"] = mCurrentLocation!!.longitude
                    mFirebaseDatabase!!.push().setValue(map)
                    previousLatLng1 = driverLatlng

                }, 10000)

            }*/

                if (marker == null) {
                    place1 = MarkerOptions().position(driverLatlng!!).title("Origin") //new LatLng(27.658143, 85.3199503)
                    //new LatLng(27.667491, 85.3208583)
                    marker = mMap!!.addMarker(place1)
                    marker!!.showInfoWindow()

                    mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(driverLatlng, 16f))

                } else {
                    marker!!.setPosition(driverLatlng!!)
                }
                /*      originMarker = addOriginDestinationMarkerAndGet(driverLatlng!!)

            originMarker?.setAnchor(0.5f, 0.5f)
            updateCarLocation(driverLatlng!!)
*/
            }
        }catch (e:Exception){

        }
    }

    private fun openSettings() {
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        val uri: Uri = Uri.fromParts(
            "package",
            BuildConfig.APPLICATION_ID, null
        )
        intent.data = uri
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
    private fun showPath(latLngList: ArrayList<LatLng>) {
        val builder = LatLngBounds.Builder()
        for (latLng in latLngList) {
            builder.include(latLng)
        }
        val bounds = builder.build()
        mMap!!.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 2))

        val polylineOptions = PolylineOptions()
        polylineOptions.color(Color.GRAY)
        polylineOptions.width(5f)
        polylineOptions.addAll(latLngList)
        grayPolyline =mMap!!.addPolyline(polylineOptions)

        val blackPolylineOptions = PolylineOptions()
        blackPolylineOptions.color(Color.BLACK)
        blackPolylineOptions.width(5f)
        blackPolyline = mMap!!.addPolyline(blackPolylineOptions)

        originMarker = addOriginDestinationMarkerAndGet(latLngList[0])
        originMarker?.setAnchor(0.5f, 0.5f)
        destinationMarker = addOriginDestinationMarkerAndGet(latLngList[latLngList.size - 1])
        destinationMarker?.setAnchor(0.5f, 0.5f)

        val polylineAnimator = AnimationUtils.polylineAnimator()
        polylineAnimator.addUpdateListener { valueAnimator ->
            val percentValue = (valueAnimator.animatedValue as Int)
            val index = (grayPolyline?.points!!.size) * (percentValue / 100.0f).toInt()
            blackPolyline?.points = grayPolyline?.points!!.subList(0, index)
        }
        polylineAnimator.start()
    }

    private fun addOriginDestinationMarkerAndGet(latLng: LatLng): Marker {
        val bitmapDescriptor =
                BitmapDescriptorFactory.fromBitmap(MapUtils.getOriginDestinationMarkerBitmap())
        return mMap!!.addMarker(
                MarkerOptions().position(latLng).flat(true).icon(bitmapDescriptor)
        )
    }

    private fun showMovingCab(cabLatLngList: ArrayList<LatLng>) {
        handler = Handler()
        var index = 0
        runnable = Runnable {
            run {
                if (index < 10) {
                    updateCarLocation(cabLatLngList[index])
                    handler.postDelayed(runnable!!, 3000)
                    ++index
                } else {
                    handler.removeCallbacks(runnable!!)
                    Toast.makeText(this@TravelDashboard, "Trip Ends", Toast.LENGTH_LONG).show()
                }
            }
        }
        handler.postDelayed(runnable!!, 5000)
    }


    override fun onRestart() {
      /*  if(sessionManager.fetchAvailabilty().equals("Online")){
            onlineapi()
        }*/
        super.onRestart()
    }
    override fun onStop() {
     //   offlineapi1()
        super.onStop()
    }

    private fun isApplicationBroughtToBackground(): Boolean {
        val am =
            this.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val tasks = am.getRunningTasks(1)
        if (!tasks.isEmpty()) {
            val topActivity = tasks[0].topActivity
            if (topActivity!!.packageName != this.packageName) {
                return true
            }
        }
        return false
    }



    override fun onDestroy() {
        offlineapi1()
        super.onDestroy()
    }
}
