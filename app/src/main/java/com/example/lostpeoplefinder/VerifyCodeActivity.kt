package com.example.lostpeoplefinder
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.lostpeoplefinder.API.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.text.TextWatcher
import android.text.Editable

class VerifyCodeActivity : AppCompatActivity() {
    private lateinit var vCode1: EditText
    private lateinit var vCode2: EditText
    private lateinit var vCode3: EditText
    private lateinit var vCode4: EditText
    private lateinit var submit: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_code)
        initViews()
        moveToNext()
        val bundle = intent.extras
        val userData = bundle?.getSerializable("userdata") as? UserData
        submit.setOnClickListener {
            val verificationCode = buildVerificationCode()//Get VCode 4
            // **Show the toast with the verification code**
            val toast = Toast.makeText(this, "Verification Code: $verificationCode", Toast.LENGTH_SHORT)
            toast.show()

            val call=RetrofitClient.instance.verifyCode(userData!!.username,userData.password,userData.phone_number,userData.email,verificationCode,userData.city,userData.token,userData.isEnabledNofification)
            call.enqueue(object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    if (response.isSuccessful) {
                        val message = response.body()?.message
                        if(message.equals("User registered successfully")) {
                            Log.d("00", message.toString())
                            startActivity(
                                Intent(
                                    this@VerifyCodeActivity,
                                    LoginPageActivity::class.java
                                )
                            )
                        }
                        Toast.makeText(this@VerifyCodeActivity, message, Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        val error = response.body()?.error
                        Log.d("+++0",error.toString())
                        Toast.makeText(this@VerifyCodeActivity, error, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    Log.d("+++",t.message.toString())
                    Toast.makeText(this@VerifyCodeActivity, t.message, Toast.LENGTH_SHORT).show()
                }
            })
        }

    }
    private fun initViews() {
        vCode1 = findViewById<EditText>(R.id.verification_code_1)
        vCode2 = findViewById<EditText>(R.id.verification_code_2)
        vCode3 = findViewById<EditText>(R.id.verification_code_3)
        vCode4 = findViewById<EditText>(R.id.verification_code_4)
        submit=findViewById<Button>(R.id.bt_VerifyCode)

    }
    private fun moveToNext(){
        vCode1.requestFocus() // Set initial focus on the first EditText
        vCode1.afterTextChanged { text ->
            if (text.toString().length == 1) {
                vCode2.requestFocus()
            }
        }
        vCode2.afterTextChanged { text ->
            if (text.toString().length == 1) {
                vCode3.requestFocus()
            }
        }
        vCode3.afterTextChanged { text ->
            if (text.toString().length == 1) {
                vCode4.requestFocus()
            }
        }
    }
    private fun buildVerificationCode(): String {
        val codeBuilder = StringBuilder()
        codeBuilder.append(vCode1.text.toString())
        codeBuilder.append(vCode2.text.toString())
        codeBuilder.append(vCode3.text.toString())
        codeBuilder.append(vCode4.text.toString())
        return codeBuilder.toString()
    }
    private fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                afterTextChanged(s.toString())
            }
        })
    }

}
