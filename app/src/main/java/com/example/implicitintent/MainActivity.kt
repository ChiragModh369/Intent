package com.example.implicitintent

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var btnPhone:Button
    lateinit var btnData:Button
    lateinit var edtNumber:EditText
    lateinit var btnSms:Button
    lateinit var btnEmail:Button

    val Log_D = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(Log_D, "Method:onCreate")

        btnPhone=findViewById(R.id.btnPhone)
        btnData=findViewById(R.id.btnData)
        edtNumber=findViewById(R.id.edtNumber)
        btnSms=findViewById(R.id.btnSms)
        btnEmail=findViewById(R.id.btnEmail)
    }

    override fun onStart() {
        super.onStart()

        btnPhone.setOnClickListener{
            var callIntent:Intent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:+916353193177")
            startActivity(Intent.createChooser(callIntent,"Choose DialPad"))

        }
        btnData.setOnClickListener(){
            var dataIntent:Intent = Intent()
            dataIntent.action = Intent.ACTION_SEND
            dataIntent.putExtra(Intent.EXTRA_TEXT,edtNumber.text.toString())
            dataIntent.type = "text/plain"
            startActivity(dataIntent)
        }
        btnSms.setOnClickListener(){
            var smSUri = Uri.parse("tel:+9185698569850")
            val intent = Intent(Intent.ACTION_VIEW,smSUri)
            intent.putExtra("address","Ranveer Kapoor")
            intent.putExtra("sms_body","Hello Friend! How's Going Brahmastra ?")
            intent.type ="vnd.android-dir/mms-sms"

            if(intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
            else{
                Toast.makeText(this,"Intent Has No Action Found",Toast.LENGTH_LONG).show()
            }
            btnEmail.setOnClickListener(){
                var intent = Intent(Intent.ACTION_SEND)
                intent.type = "plain/text"
                intent.putExtra(Intent.EXTRA_EMAIL,arrayOf("anybody@gmail.com"))
                intent.putExtra(Intent.EXTRA_SUBJECT,"subject")
                intent.putExtra(Intent.EXTRA_TEXT,"write your mail body ")
                if(intent.resolveActivity(packageManager) != null) {
                    startActivity(Intent.createChooser(intent,""))
                }
            }
        }



    }


}
