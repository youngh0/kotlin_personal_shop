package com.example.shopping

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TableRow
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_buy.*

class Buy : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy)

        val banana : TableRow = findViewById(R.id.banana)
        val apple : TableRow = findViewById(R.id.apple)
        val watermelon : TableRow = findViewById(R.id.watermelon)

        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("my").document("cart")

        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    if(document.data?.get("banana").toString() != "null"){
                        banana.visibility = View.VISIBLE
                        banana_line.visibility = View.VISIBLE
                    }
                    if(document.data?.get("apple").toString() != "null"){
                        apple.visibility = View.VISIBLE
                        apple_line.visibility = View.VISIBLE
                    }
                    if(document.data?.get("watermelon").toString() != "null"){
                        watermelon.visibility = View.VISIBLE
                        wm_line.visibility = View.VISIBLE
                    }
                    textView7.setText("가격: " + document.data?.get("price"))
                }
            }
            .addOnFailureListener { exception ->

            }

        finally_buy.setOnClickListener {
            if (validate()){
                Toast.makeText(this,"구매 완료",Toast.LENGTH_LONG).show()
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else{

            }
        }
        go_home.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validate() : Boolean{
        if(address.text.toString().isEmpty()){
            address.error = "address should not be blank!"
            return false
        }
        if(phone.text.toString().isEmpty()){
            phone.error = "phone should not be black!"
            return false
        }
        return true
    }
}