package com.example.shopping

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var price:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = FirebaseFirestore.getInstance()
        val myCart = hashMapOf(
                "test" to 1
        )

        val buy_btn: Button = findViewById(R.id.button_buy)
        buy_btn.setOnClickListener{
            if(checkBox_banana.isChecked == true){
                myCart.put("banana",1500)
                price += 1500
            }else{
            }
            if(checkBox_apple.isChecked == true){
                myCart.put("apple",1000)
                price += 1000
            }else{
            }
            if(checkBox_wm.isChecked == true){
                myCart.put("watermelon",3000)
                price += 3000
            }else{
            }
            myCart.put("price", price)
            db.collection("my").document("cart")
                .set(myCart)
                .addOnSuccessListener { }
                .addOnFailureListener { }
            var intent = Intent(this, Buy::class.java)

            startActivity(intent)
        }
        button_cart.setOnClickListener {
            var intent = Intent(this, Cart::class.java)

            startActivity(intent)
        }
        add_cart.setOnClickListener {
            if(checkBox_banana.isChecked == true){
                myCart.put("banana",1500)
                price += 1500
            }else{
            }
            if(checkBox_apple.isChecked == true){
                myCart.put("apple",1000)
                price += 1000
            }else{

            }
            if(checkBox_wm.isChecked == true){
                myCart.put("watermelon",3000)
                price += 3000
            }else{
            }
            myCart.put("price", price)
            db.collection("my").document("cart")
                .set(myCart)
                .addOnSuccessListener { }
                .addOnFailureListener { }
            Toast.makeText(this,"ok",Toast.LENGTH_LONG).show()
        }



    }
}