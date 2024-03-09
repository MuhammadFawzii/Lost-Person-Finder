package com.example.lostpeoplefinder

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.lostpeoplefinder.API.RetrofitClient
import com.github.dhaval2404.imagepicker.ImagePicker
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.File


class DetailsActivity7 : AppCompatActivity() {
    lateinit var constraintLayout7: ConstraintLayout
    lateinit var textView11: TextView
    lateinit var img:ImageView
    private lateinit var file: File
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_template)
        /*val name=intent.getStringExtra("name")
        val age=intent.getStringExtra("age")
        val gender=intent.getStringExtra("gender")
        val date=intent.getStringExtra("date")
        val number=intent.getStringExtra("number")
        val email=intent.getStringExtra("email")
        val note=intent.getStringExtra("note")
        val lang=intent.getStringExtra("lang")
        val lat=intent.getStringExtra("lat")*/
        val name="Abdo"
        val age="20"
        val gender="1"
        val date="2024-11-11"
        val number="01011554554"
        val email="abdo@gmail.com"
        val lang="25"
        val lat="50"
        textView11 = findViewById<TextView>(R.id.textView11)
        val pickImageButton = findViewById<TextView>(R.id.tv_browsePhoto)
        constraintLayout7 = findViewById(R.id.constraintLayout7)
        img=findViewById(R.id.img)

        pickImageButton.setOnClickListener {
            ImagePicker.with(this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }
        val btn_next3 = findViewById<Button>(R.id.btn_next3)
        val btn_previous3 = findViewById<Button>(R.id.btn_previous3)
        btn_next3.setOnClickListener {

            val requestFile = RequestBody.create(MediaType.parse("image/*"), file)
            val imagePart = MultipartBody.Part.createFormData("image", file.name, requestFile)
            val requestBody = RequestBody.create(MediaType.parse("text/plain"), name)
            val call = RetrofitClient.instance.sendPersonData(
                requestBody,
                RequestBody.create(MediaType.parse("text/plain"), age),
                RequestBody.create(MediaType.parse("text/plain"), date),
                RequestBody.create(MediaType.parse("text/plain"), number),
                RequestBody.create(MediaType.parse("text/plain"), email),
                imagePart,
                RequestBody.create(MediaType.parse("text/plain"), lang),
                RequestBody.create(MediaType.parse("text/plain"), lat),
                RequestBody.create(MediaType.parse("text/plain"), gender)
            )

          /*  call.enqueue(object : Callback<Response<List<Person>>> {
                override fun onResponse(call: Call<Response<List<Person>>>, response: Response<Response<List<Person>>>) {
                    if (response.isSuccessful) {
                        val apiResponse = response.body()
                        if (apiResponse != null) {
                            val data = apiResponse.data
                            val message = apiResponse.message

                            if (data != null) {
                                // Handle list of persons
                            } else if (message != null) {
                                // Handle message
                            }
                        }
                    } else {
                        // Handle unsuccessful response
                    }
                }

                override fun onFailure(call: Call<Response<List<Person>>>, t: Throwable) {
                    // Handle failure
                }
            })*/


            call.enqueue(object : Callback<LostPersonResponse> {
                override fun onResponse(call: Call<LostPersonResponse>, response: Response<LostPersonResponse>) {
                    if (response.isSuccessful) {

                        /*val jsonString = response.body().toString()
                        Log.d("00ttt", jsonString)
                        val jsonObject = JSONObject(jsonString)
                        val findPeopleResponse = response.body()
                        val finalResultList = findPeopleResponse?.toString()

                        // Process your finalResultList here
                        // Handle the JSON response here
                        val finalResultArray = jsonObject.getJSONArray("final_result")
                        for (i in 0 until finalResultArray.length()) {
                            val lostPersonJson = finalResultArray.getJSONObject(i)
                            val lostPerson = ResponseReport(
                                person_name = lostPersonJson.getString("person_name"),
                                age = lostPersonJson.getString("age"),
                                date_of_lost = lostPersonJson.getString("date_of_lost"),
                                phone_number = lostPersonJson.getString("phone_number"),
                                email = lostPersonJson.getString("email"),
                                lng = lostPersonJson.getString("lng"),
                                lat = lostPersonJson.getString("lat"),
                                gender = lostPersonJson.getString("gender"),
                                image_url = lostPersonJson.optString("image_url")
                            )
                            finalResults.add(lostPerson)
                            Log.d("final res",finalResultList.toString())
                            startActivity(
                                Intent(
                                    this@DetailsActivity7,
                                    DetailsCompleteActivity8::class.java
                                )
                            )

                        }*/
                        val lostPersonResponse = response.body()
                            // Check if the response is not null
                                if (lostPersonResponse != null) {
                                    Toast.makeText(this@DetailsActivity7, "dfsdssfd!!", Toast.LENGTH_SHORT)
                                        .show()
                                    val finalResult = lostPersonResponse.final_result
                                    val x= lostPersonResponse.massage
                                    Toast.makeText(this@DetailsActivity7, x.toString(), Toast.LENGTH_SHORT).show()
                                    // Check if finalResult is not null and not empty
                                    if (finalResult != null && finalResult.isNotEmpty()) {
                                        if(x.equals("Image does not contain exactly one face or No Face")){
                                            Toast.makeText(this@DetailsActivity7, "Image does not contain exactly one face or No Face", Toast.LENGTH_SHORT)
                                                .show()
                                        }
                                        var intent=Intent(this@DetailsActivity7,ResponseActivity::class.java)
                                        intent.putExtra("final", ArrayList(finalResult))
                                        startActivity(intent)
                                        Log.d("test000",finalResult.toString())
                                        Toast.makeText(this@DetailsActivity7, "155055055!!", Toast.LENGTH_SHORT)
                                            .show()
                                        Log.d("result",finalResult.toString())
                                        // Handle the case where finalResult is not empty
                                        // Update your UI with the retrieved data
                                        // For example, you can iterate through the list and display person details
                                        for (person in finalResult) {
                                            Toast.makeText(this@DetailsActivity7, "2222", Toast.LENGTH_SHORT)
                                                .show()
                                        }
                                    } else {
                                        Toast.makeText(this@DetailsActivity7, "Not found", Toast.LENGTH_SHORT)
                                            .show()
                                    }
                                } else {
                                    Toast.makeText(this@DetailsActivity7, "error", Toast.LENGTH_SHORT)
                                        .show()
                                }
                        }

                        // Now you have finalResults ArrayList with all LostPerson objects
                    else {
                        val jsonString = response.body()?.toString()
                        Log.d("00ttt", jsonString.toString())
                        Toast.makeText(this@DetailsActivity7, "hear!!", Toast.LENGTH_SHORT)
                            .show()
                        // Handle unsuccessful response
                    }
                }

                override fun onFailure(call: Call<LostPersonResponse>, t: Throwable) {
                    Toast.makeText(this@DetailsActivity7, t.toString(), Toast.LENGTH_SHORT).show()
                }
            })
        }
















       /* val finalResults = ArrayList<ResponseReport>()
        pickImageButton.setOnClickListener {
            ImagePicker.with(this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }
        val btn_next3 = findViewById<Button>(R.id.btn_next3)
        val btn_previous3 = findViewById<Button>(R.id.btn_previous3)
        btn_next3.setOnClickListener {
            val lostPerson = ReportPerson(
                    person_name = name,
                    age = age,
                    date_of_found = "2024-7-20",
                    phone_number = number,
                    image = file, // Provide a File object here
                    email = email,
                    lng = lang,
                    lat = lat,
                    gender = gender
                )
            val call =  RetrofitClient.instance.reportLostPerson(name,age, number,email,date,lat,lang,gender,file)
            call.enqueue(object : Callback<ApiResponse> {
                override fun onResponse(
                    call: Call<ApiResponse>,
                    response: Response<ApiResponse>
                ) {
                    if (response.isSuccessful) {
                        val jsonString = response.body()?.toString()
                        Log.d("00ttt",jsonString.toString())
                        val jsonObject = JSONObject(jsonString)
                        // Handle the JSON response here
                        val finalResultArray = jsonObject.getJSONArray("final_result")
                        for (i in 0 until finalResultArray.length()) {
                            val lostPersonJson = finalResultArray.getJSONObject(i)
                            val lostPerson = ResponseReport(
                                person_name = lostPersonJson.getString("person_name"),
                                age = lostPersonJson.getString("age"),
                                date_of_lost = lostPersonJson.getString("date_of_lost"),
                                phone_number = lostPersonJson.getString("phone_number"),
                                email = lostPersonJson.getString("email"),
                                lng = lostPersonJson.getString("lng"),
                                lat = lostPersonJson.getString("lat"),
                                gender = lostPersonJson.getString("gender"),
                                image_url = lostPersonJson.optString("image_url"))
                            finalResults.add(lostPerson)
                            startActivity(Intent(this@DetailsActivity7, DetailsCompleteActivity8::class.java))

                        }

                        // Now you have finalResults ArrayList with all LostPerson objects
                    } else {
                        val jsonString = response.body()?.toString()
                        Log.d("00ttt",jsonString.toString())
                        Toast.makeText(this@DetailsActivity7, "hear!!", Toast.LENGTH_SHORT)
                            .show()
                        // Handle unsuccessful response
                    }
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    Toast.makeText(this@DetailsActivity7, "Failed", Toast.LENGTH_SHORT)
                        .show()
                }
            })

        }*/
        btn_previous3.setOnClickListener {
            startActivity(Intent(this, DetailsActivity6::class.java))
        }

        }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val uri: Uri = data?.data!!
            constraintLayout7.visibility = View.VISIBLE
            textView11.visibility = View.VISIBLE
            // Use Uri object instead of File to avoid storage permissions
            file= File(uri.path)

            //send it to next page

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }
}