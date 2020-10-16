package com.example.shopping

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_cart.*

class Cart : AppCompatActivity() {
    var price : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val banana : LinearLayout = findViewById(R.id.banana)
        val apple : LinearLayout = findViewById(R.id.apple)
        val watermelon : LinearLayout = findViewById(R.id.watermelon)

        val myCart = hashMapOf(
            "banana" to 1000,
            "apple" to 1500,
            "watermelon" to 3000
        )

        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("my").document("cart")

        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    if(document.data?.get("banana").toString() != "null"){
                        banana.visibility = View.VISIBLE
                        banana_line.visibility = View.VISIBLE
                    }else{
                        myCart.remove("banana")
                    }
                    if(document.data?.get("apple").toString() != "null"){
                        apple.visibility = View.VISIBLE
                        apple_line.visibility = View.VISIBLE
                    }
                    else{
                        myCart.remove("apple")
                    }
                    if(document.data?.get("watermelon").toString() != "null"){
                        watermelon.visibility = View.VISIBLE
                        wm_line.visibility = View.VISIBLE
                    }
                    else{
                        myCart.remove("watermelon")
                    }
                    //textView7.setText("가격: " + document.data?.get("price"))
                }
            }
            .addOnFailureListener { exception ->

            }

        cart_to_home.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        cart_to_buy.setOnClickListener {
            if(checkBox_banana.isChecked == true){
                price += 1500
            }else{
                myCart.remove("banana")
            }
            if(checkBox_apple.isChecked == true){
                price += 1000
            }else{
                myCart.remove("apple")
            }
            if(checkBox_wm.isChecked == true){
                price += 3000
            }else{
                myCart.remove("watermelon")
            }
            myCart.put("price", price)
            db.collection("my").document("cart")
                .set(myCart)
                .addOnSuccessListener { }
                .addOnFailureListener { }
            var intent = Intent(this, Buy::class.java)
            startActivity(intent)
        }
        remove_item.setOnClickListener {
            if(checkBox_banana.isChecked == true){
                myCart.remove("banana")
                price -= 1500
            }else{

            }
            if(checkBox_apple.isChecked == true){
                myCart.remove("apple")
                price -= 1000
            }else{

            }
            if(checkBox_wm.isChecked == true){
                myCart.remove("watermelon")
                price -= 3000
            }else{

            }
            myCart.put("price", price)
            db.collection("my").document("cart")
                .set(myCart)
                .addOnSuccessListener { }
                .addOnFailureListener { }

        }
    }
}