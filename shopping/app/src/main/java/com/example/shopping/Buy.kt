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

        // 구매화면에서 내가 선택한 목록들을 visibility설정을 위한 변수
        val banana : TableRow = findViewById(R.id.banana)
        val apple : TableRow = findViewById(R.id.apple)
        val watermelon : TableRow = findViewById(R.id.watermelon)

        // firestor사용을 위한 변수
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("my").document("cart")

        // 현재 내 데이테베이스 목록들 확인 후 보여주
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    // 데이터 베이스의 key값에서 banana,apple,watermelon을 확인 후 null값이 아니라면 visibility를 visible로 변환 해서 목록 보여주기
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
                    // 현재 내 구매목록들 가격 총합 보여주기
                    textView7.setText("가격: " + document.data?.get("price"))
                }
            }

        // 구매버튼 클릭 시
        finally_buy.setOnClickListener {
            // 주소,연락처 입력창 체크하는 validate호출
            if (validate()){
                Toast.makeText(this,"구매 완료",Toast.LENGTH_LONG).show()

                // 구매 완료시 내 장바구니 데이터베이스 clear시키고 메인화면으로 넘어가기
                myCart.clear()
                db.collection("my").document("cart")
                    .set(myCart)
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else{

            }
        }

        // 홈으로 버튼 클릭 시 메인화면으로 넘어가기
        go_home.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    // 주소,연락처 editText가 비었으면 경고 해주고 true/false반환
    // 둘 다 입력 되어 있으면 true 반환
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