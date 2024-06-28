package com.example.lostpeoplefinder

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.lostpeoplefinder.API.RetrofitClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.Manifest
import android.app.NotificationManager
import android.content.Context
import android.content.DialogInterface
import android.os.Build
import androidx.appcompat.app.AlertDialog
import android.content.IntentSender
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import android.provider.Settings

class RegisterPageActivity : AppCompatActivity() {
    private var token = ""
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    var longitude:Double=0.0
    var latitude:Double=0.0
    var isEnabled:Int=0


    private val LOCATION_PERMISSION_REQUEST_CODE = 1001
    private val NOTIFICATION_PERMISSION_REQUEST_CODE = 1002
    private val LOCATION_SETTINGS_REQUEST_CODE = 1003
    private var locationPermissionGranted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regisiter_page)
        // FirebaseApp.initializeApp(this)
        // Check if the default Firebase app is initialized

        val city: Spinner = findViewById(R.id.citySpinner)
        val cities = arrayOf(
            "Cairo",
            "Giza",
            "Alexandria",
            "Shubra El-Kheima",
            "Port Said",
            "Suez",
            "El Mahalla El Kubra",
            "Luxor",
            "Mansoura",
            "Tanta",
            "Asyut",
            "Ismailia",
            "Faiyum",
            "Zagazig",
            "Aswan",
            "Damietta",
            "Damanhur",
            "Al-Minya",
            "Beni Suef",
            "Qena",
            "Sohag",
            "Hurghada",
            "6th of October City",
            "Shibin El Kom",
            "Banha",
            "Arish",
            "Mallawi",
            "Belbeis",
            "10th of Ramadan City",
            "Qalyub",
            "Kafr el-Sheikh",
            "Minya",
            "Abu Kabir",
            "Kafr el-Dawwar",
            "Desouk",
            "Marsa Matruh",
            "Idfu",
            "El Alamein",
            "Beni Mazar",
            "Basyoun",
            "El-Mahalla El-Kubra",
            "Samalut",
            "Abu Qurqas",
            "Qus",
            "El-Hawamdeya",
            "El-Qantara",
            "Beba",
            "Marsa Alam"
        )

        val adapter = ArrayAdapter(this, R.layout.spinner_item, cities)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        city.adapter = adapter







        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        checkLocationSettings()

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted, request it
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
        } else {
            // Permission is already granted, start getting location
            getCurrentLocation()
        }


        var token = ""
        //Toast.makeText(this, "Firebase is not initialized", Toast.LENGTH_SHORT).show()
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("bkr", "Fetching FCM registration token failed", task.exception)
                return@addOnCompleteListener
            }

            // Get new FCM registration token
            token = task.result
            if (token != null) {
                // Log and toast
                Log.d("bkr", token)
                Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()

                // Create an ArrayAdapter using the string array and a default spinner layout
                val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cities)

                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                city.adapter = adapter


                val btnSignUp: Button = findViewById(R.id.bt_Next_Register)
                var textViewLogin: TextView = findViewById(R.id.go_login)

                val editTextUsername: EditText = findViewById(R.id.et_full_name_register)
                val editTextEmail: EditText = findViewById(R.id.et_email_register)
                val editTextPassword: EditText = findViewById(R.id.et_password_reg)
                val editTextPhoneNumber: EditText = findViewById(R.id.et_phone_register)
                btnSignUp.setOnClickListener {
                    val username = editTextUsername.text.toString()
                    val email = editTextEmail.text.toString()
                    val password = editTextPassword.text.toString()
                    val phoneNumber = editTextPhoneNumber.text.toString()
                    val selectedCity = city.selectedItem.toString()
                    val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
                    val passwordPattern = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}"
                    val phoneNumberPattern = "\\d{11}"
                    val isEmailValid = email.matches(emailPattern.toRegex())
                    val isPasswordValid = password.matches(passwordPattern.toRegex())
                    val isPhoneNumberValid = phoneNumber.matches(phoneNumberPattern.toRegex())

                    if (!isEmailValid) {
                        Toast.makeText(
                            this,
                            "Email not valid \n Should be like example@gmail.com",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    if (!isPasswordValid) {
                        Toast.makeText(
                            this,
                            "Password not valid \n Should contain capital and small letters",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    if (!isPhoneNumberValid) {
                        Toast.makeText(
                            this,
                            "Phone Number not valid \n Should be 11 char",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    if (isEmailValid && isPasswordValid && isPhoneNumberValid && selectedCity != null) {
                        val userData = UserData(
                            username,
                            email,
                            password,
                            phoneNumber,
                            selectedCity,
                            token,
                            isEnabled,
                            longitude,
                            latitude,
                        )

                        val registerRequest=RegisterRequest(email)
                        // Make sign up request
                        val call = RetrofitClient.instance.registerUser(registerRequest)
                        call.enqueue(object : Callback<ApiResponse> {
                            override fun onResponse(
                                call: Call<ApiResponse>,
                                response: Response<ApiResponse>
                            ) {
                                if (response.isSuccessful) {
                                    val message = response.body()?.message
                                    Log.d("00++++", message.toString())
                                    if (message.equals("A verification code has been sent to your email.")) {
                                        val intent = Intent(
                                            this@RegisterPageActivity,
                                            VerifyCodeActivity::class.java
                                        )
                                        val bundle = Bundle()
                                        bundle.putSerializable("userdata", userData)
                                        intent.putExtras(bundle)
                                        startActivity(intent)
                                    }
                                    Toast.makeText(
                                        this@RegisterPageActivity,
                                        message,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    val error = response.body()?.error
                                    Log.d("+++0", error.toString())
                                    Toast.makeText(
                                        this@RegisterPageActivity,
                                        "Something wrong please try again.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                                Log.d("+++", t.message.toString())
                                Toast.makeText(
                                    this@RegisterPageActivity,
                                    t.message,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        })
                    }
                }
                textViewLogin.setOnClickListener {
                    var intent = Intent(this, LoginPageActivity::class.java)
                    startActivity(intent)
                }

            }
        }
    }

    private fun checkLocationSettings() {
        locationRequest = LocationRequest.create().apply {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
        val settingsClient: SettingsClient = LocationServices.getSettingsClient(this)
        val task: Task<LocationSettingsResponse> = settingsClient.checkLocationSettings(builder.build())

        task.addOnSuccessListener { locationSettingsResponse ->
            // All location settings are satisfied, request location permissions
            requestLocationPermission()
        }

        task.addOnFailureListener { exception ->
            if (exception is ResolvableApiException) {
                try {
                    exception.startResolutionForResult(this, LOCATION_SETTINGS_REQUEST_CODE)
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error
                }
            }
        }
    }

    private fun requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)

        } else {
            // Location permission already granted, set flag and request notification permission
            locationPermissionGranted = true
            requestNotificationPermission()
        }
    }

    private fun getCurrentLocation() {
        Log.d("kk","here")
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    if (location != null) {
                        Log.d("kk","sucess")
                         latitude = location.latitude
                         longitude = location.longitude
                        Toast.makeText(this, "Latitude: $latitude, Longitude: $longitude", Toast.LENGTH_SHORT).show()
                        // Use latitude and longitude as needed

                        // Set location permission flag
                        locationPermissionGranted = true

                        // Request notification permission after location permission is granted
                        requestNotificationPermission()
                    } else {
                        // Last known location is null, request a new location
                        requestNewLocationData()
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error getting location: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(this, "Location permission not granted", Toast.LENGTH_SHORT).show()
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
        }
    }

    private fun requestNewLocationData() {
        val locationRequest = LocationRequest.create().apply {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = 0 // Immediate location update
            fastestInterval = 0
            numUpdates = 1 // Only one update
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
        }
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)
            locationResult?.let {
                for (location in it.locations) {
                    val latitude = location.latitude
                    val longitude = location.longitude
                    Toast.makeText(this@RegisterPageActivity, "New Latitude: $latitude, New Longitude: $longitude", Toast.LENGTH_SHORT).show()
                    // Use latitude and longitude as needed

                    // Set location permission flag
                    locationPermissionGranted = true


                    // Request notification permission after location permission is granted
                    requestNotificationPermission()
                }
            }

        }
    }

    private fun requestNotificationPermission() {
        if (locationPermissionGranted && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (!notificationAccessGranted()) {
                showNotificationPermissionDialog()
            } else {
                Toast.makeText(this, "Notification permission already granted", Toast.LENGTH_SHORT).show()
                // Handle notification permission already granted
            }
        } else {
            Toast.makeText(this, "Notification permission not required or location permission not granted", Toast.LENGTH_SHORT).show()
            // Handle cases where notification permission is not needed or location permission is not granted
        }
    }

    private fun showNotificationPermissionDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Enable Push Notifications")
        dialogBuilder.setMessage("To receive push notifications based on your location, enable Notification Access.")
        dialogBuilder.setPositiveButton("Enable") { dialogInterface: DialogInterface, i: Int ->
            val intent = Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)
            //startActivityForResult(intent, NOTIFICATION_PERMISSION_REQUEST_CODE)
            isEnabled=1
        }
        dialogBuilder.setNegativeButton("Cancel") { dialogInterface: DialogInterface, i: Int ->
            // Handle cancel action if needed
        }
        dialogBuilder.setCancelable(false)
        dialogBuilder.show()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun notificationAccessGranted(): Boolean {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        return notificationManager.isNotificationPolicyAccessGranted
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Location permission granted, set flag and request notification permission
                locationPermissionGranted = true
                requestNotificationPermission()
            } else {
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show()
            }
        }

        // Handle other permission requests if needed
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == LOCATION_SETTINGS_REQUEST_CODE) {
            checkLocationSettings()
        } else if (requestCode == NOTIFICATION_PERMISSION_REQUEST_CODE) {
            // Handle result of notification permission request
            if (notificationAccessGranted()) {
                Toast.makeText(this, "Notification permission granted", Toast.LENGTH_SHORT).show()
                // Handle notification permission granted
            } else {
                Toast.makeText(this, "Notification permission denied", Toast.LENGTH_SHORT).show()
                // Handle notification permission denied
            }
        }

        // Handle other activity results if needed
    }

}
