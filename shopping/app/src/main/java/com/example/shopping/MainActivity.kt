package com.example.shopping

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = FirebaseFirestore.getInstance()

        // 구매 버튼 클릭 시
        button_buy.setOnClickListener{

            var count : Int = 0 // 아무것도 체크 안될 걸 검사하기 위한 count변수
            if(!checkBox_banana.isChecked) count += 1
            if(!checkBox_apple.isChecked) count += 1
            if(!checkBox_wm.isChecked) count += 1
            if(count == 3){ // 아무것도 체크 안한 경우
                Toast.makeText(this,"아무것도 선택하지 않았습니다.",Toast.LENGTH_SHORT).show()

            }else{ // 바로 구매를 누르는 경우여서 price변수를 0으로 초기화
                price = 0
                // 구매목록 데이터베이스에 체크 된 항목 추가하기
                if(checkBox_banana.isChecked){
                    buy_list.put("banana",1500)   // 체크 되어있는걸 buy_list에 추가
                    price += 1500
                }
                // 구매목록 데이터베이스에 체크안된 항목 제거하기
                else{
                    buy_list.remove("banana") // buy_list에 있을 수 있기 때문에 체크 안되어 있으면 buy_list에서 제거
                }
                if(checkBox_apple.isChecked){
                    buy_list.put("apple",1000)
                    price += 1000
                }else{
                    buy_list.remove("apple")
                }
                if(checkBox_wm.isChecked){
                    buy_list.put("watermelon",3000)
                    price += 3000
                }else{
                    buy_list.remove("watermelon")
                }
                buy_list.put("price", price)

                // 새롭게 업데이트된 buy_list를 구매목록 데이터베이스에 업데이트
                db.collection("m").document("buy")
                    .set(buy_list)
                var intent = Intent(this, Buy::class.java)
                startActivity(intent)
            }

        }

        // 장바구니 버튼 클릭 시 장바구니액티비티로 이행동
        button_cart.setOnClickListener {
            var intent = Intent(this, Cart::class.java)

            startActivity(intent)
        }

        //장바구니 추가 버튼 클릭 시
        add_cart.setOnClickListener {
            // 아무것도 체크 안 된 경우 검
            var count : Int = 0
            if(!checkBox_banana.isChecked) count += 1
            if(!checkBox_apple.isChecked) count += 1
            if(!checkBox_wm.isChecked) count += 1
            if(count == 3){
                Toast.makeText(this,"아무것도 선택하지 않았습니다.",Toast.LENGTH_SHORT).show()
            }else{

                // 기존에 장바구니에 있는 품목들은 그대로 유지된 상태에서 체크 한 품목을 장바구니에 업데이트 하기 위한 부분
                // 장바구니 목록 가져오기
                val docRef = db.collection("my").document("cart")
                docRef.get()
                    .addOnSuccessListener { document ->
                        if (document != null) {
                            // 기존 장바구니 데이터 베이스에 있거나 체크 되어 있으면 myCart에 추가하기
                            if(document.data?.get("banana").toString() != "null" || checkBox_banana.isChecked){
                                myCart.put("banana",1500)
                                price += 1500
                            }else{ //그게 아니면 제거
                                myCart.remove("banana")
                            }
                            if(document.data?.get("apple").toString() != "null" || checkBox_apple.isChecked){
                                myCart.put("apple",1000)
                                price += 1000
                            }else{
                                myCart.remove("apple")
                            }
                            if(document.data?.get("watermelon").toString() != "null" || checkBox_wm.isChecked){
                                myCart.put("watermelon",3000)
                                price += 3000
                            }else{
                                myCart.remove("watermelon")
                            }
                        }
                        myCart.put("price", price)
                        // 위 코드들로 기존 장바구니에 체크한 품목이 업데이트된 myCart를 데이터베이스에 업데이트
                        db.collection("my").document("cart")
                            .set(myCart)
                            .addOnSuccessListener {
                                Toast.makeText(this,"추가 완료!",Toast.LENGTH_SHORT).show()
                            }

                    }
            }
        }
    }
}