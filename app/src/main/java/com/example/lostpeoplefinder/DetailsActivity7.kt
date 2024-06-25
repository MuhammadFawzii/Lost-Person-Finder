package com.example.lostpeoplefinder

import android.app.Activity
import android.content.Context
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
    lateinit var fileName: TextView
    lateinit var img:ImageView
    lateinit var delete:ImageView
    lateinit var call:Call<FindResponse>
    private var file: File? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_template)
        fileName=findViewById(R.id.filename)
        delete=findViewById(R.id.icon_delete)
        val name=intent.getStringExtra("name").toString()
        val age=intent.getStringExtra("age").toString()
        val gender=intent.getStringExtra("gender").toString()
        val date=intent.getStringExtra("date").toString()
        val number=intent.getStringExtra("number").toString()
        val email=intent.getStringExtra("email").toString()
        val note=intent.getStringExtra("note").toString()
        val lang="5.0"//intent.getStringExtra("lang").toString()
        val lat="3.66"//intent.getStringExtra("lat").toString()
        val sharedPreferences = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getString("USER_ID", null)
        Toast.makeText(this@DetailsActivity7, name+age+gender+date, Toast.LENGTH_SHORT)
            .show()

        Toast.makeText(this@DetailsActivity7, number+email+lang+lat, Toast.LENGTH_SHORT)
            .show()



        /*val name="test"
        val age="50"
        val gender="1"
        val date="2020-05-20"
        val number="0101212312"
        val email="test@gmail.com"
        val note="ff"
        val lang="-50"
        val lat="533.54545"
        val report_name ="1"*/
        val report_name =intent.getStringExtra("report").toString()

        val pickImageButton = findViewById<TextView>(R.id.tv_browsePhoto)
        constraintLayout7 = findViewById(R.id.constraintLayout7)
        img=findViewById(R.id.img)

        val btn_next3 = findViewById<Button>(R.id.btn_next3)
        val btn_previous3 = findViewById<Button>(R.id.btn_previous3)

        pickImageButton.setOnClickListener {
            ImagePicker.with(this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }
        Toast.makeText(this@DetailsActivity7, report_name.toString(), Toast.LENGTH_SHORT)
        .show()



        btn_next3.setOnClickListener {
            if(file==null){
                Toast.makeText(this, "an error occure please try upload phote", Toast.LENGTH_SHORT).show()
            }else {
                val requestFile = RequestBody.create(MediaType.parse("image/*"), file)
                val imagePart = MultipartBody.Part.createFormData("image", file?.name, requestFile)
                val requestBody = RequestBody.create(MediaType.parse("text/plain"), name)

                if (report_name == "2") {
                    Toast.makeText(this@DetailsActivity7, "first!!", Toast.LENGTH_SHORT)
                        .show()
                    call = RetrofitClient.instance.sendPersonData(
                        requestBody,
                        RequestBody.create(MediaType.parse("text/plain"), age),
                        RequestBody.create(MediaType.parse("text/plain"), date),
                        RequestBody.create(MediaType.parse("text/plain"), number),
                        RequestBody.create(MediaType.parse("text/plain"), email),
                        imagePart,
                        RequestBody.create(MediaType.parse("text/plain"), lang),
                        RequestBody.create(MediaType.parse("text/plain"), lat),
                        RequestBody.create(MediaType.parse("text/plain"), gender),
                        RequestBody.create(MediaType.parse("text/plain"), userId)
                    )


                } else {
                    Toast.makeText(this@DetailsActivity7, "else!!", Toast.LENGTH_SHORT)
                        .show()
                    call = RetrofitClient.instance.send_find(
                        requestBody,
                        RequestBody.create(MediaType.parse("text/plain"), age),
                        RequestBody.create(MediaType.parse("text/plain"), date),
                        RequestBody.create(MediaType.parse("text/plain"), number),
                        RequestBody.create(MediaType.parse("text/plain"), email),
                        imagePart,
                        RequestBody.create(MediaType.parse("text/plain"), lang),
                        RequestBody.create(MediaType.parse("text/plain"), lat),
                        RequestBody.create(MediaType.parse("text/plain"), gender),
                        RequestBody.create(MediaType.parse("text/plain"), userId)
                    )
                }

                call.enqueue(object : Callback<FindResponse> {
                    override fun onResponse(
                        call: Call<FindResponse>,
                        response: Response<FindResponse>
                    ) {
                        if (response.isSuccessful) {
                            val findResponse = response.body()
                            if (findResponse != null) {
                                if (findResponse.final_result != null) {
                                    val finalResult = findResponse.final_result
                                    // Final result available, do something with it
                                    var intent =
                                        Intent(this@DetailsActivity7, ResponseActivity::class.java)
                                    intent.putExtra("final", ArrayList(finalResult))
                                    startActivity(intent)
                                    Log.d("test000", finalResult.toString())
                                    Toast.makeText(
                                        this@DetailsActivity7,
                                        finalResult.toString(),
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                } else if (findResponse.message != null) {
                                    // No person found, handle the message
                                    Toast.makeText(
                                        this@DetailsActivity7,
                                        " Person not found",
                                        Toast.LENGTH_LONG
                                    )
                                        .show()
                                    startActivity(
                                        Intent(
                                            this@DetailsActivity7,
                                            DetailsCompleteActivity8::class.java
                                        )
                                    )
                                } else if (findResponse.error != null) {
                                    // Handle error
                                    Toast.makeText(
                                        this@DetailsActivity7,
                                        "Image does not contain exactly one Face or No Face",
                                        Toast.LENGTH_LONG
                                    )
                                        .show()
                                }
                            } else {
                                // Handle null response
                                Toast.makeText(
                                    this@DetailsActivity7,
                                    "Some thing wrong please try again",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }

                            /*val lostPersonResponse = response.body()
                        // Check if the response is not null
                        if (lostPersonResponse != null) {
                            Toast.makeText(this@DetailsActivity7, "dfsdssfd!!", Toast.LENGTH_SHORT)
                                .show()
                            val finalResult = lostPersonResponse.final_result

                            // Check if finalResult is not null and not empty
                            if (finalResult != null && finalResult.isNotEmpty()) {
                                var intent =
                                    Intent(this@DetailsActivity7, ResponseActivity::class.java)
                                intent.putExtra("final", ArrayList(finalResult))
                                startActivity(intent)
                                Log.d("test000", finalResult.toString())
                                Toast.makeText(
                                    this@DetailsActivity7,
                                    "155055055!!",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                                Log.d("result", finalResult.toString())

                                for (person in finalResult) {
                                    Toast.makeText(
                                        this@DetailsActivity7,
                                        "2222",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }
                            } else {
                                Toast.makeText(
                                    this@DetailsActivity7,
                                    "Not found",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                        } else {
                            Toast.makeText(this@DetailsActivity7, "error", Toast.LENGTH_SHORT)
                                .show()
                        }
                    } else {
                        val jsonString = response.body()?.toString()
                        Log.d("00ttt", jsonString.toString())
                        Toast.makeText(this@DetailsActivity7, "hear!!", Toast.LENGTH_SHORT)
                            .show()

                    }
                }*/
                        }
                    }

                    override fun onFailure(call: Call<FindResponse>, t: Throwable) {
                        Toast.makeText(
                            this@DetailsActivity7,
                            t.toString() + "505",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                })
            }
        }
        btn_previous3.setOnClickListener {
            startActivity(Intent(this, DetailsActivity6::class.java))
        }
        delete.setOnClickListener {
            file=null
            constraintLayout7.visibility = View.GONE
        }

        }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val uri: Uri = data?.data!!
            constraintLayout7.visibility = View.VISIBLE
            // Use Uri object instead of File to avoid storage permissions
            file= File(uri.path)
            fileName.text=file?.name



            //send it to next page

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

//    val sharedPreferences = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
//    val editor = sharedPreferences.edit()
//    editor.remove("USER_ID")
//    editor.apply()


}