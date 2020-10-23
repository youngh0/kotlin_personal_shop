# kotlin_personal_shop
<h1>간단한 과제 설명</h1>
<ul>
    <li>firebase연동을 통한 간단한 상품 구매 어플 만들기</li>
    <li>firestore에서 구매목록,장바구니목록 collection 2개를 이용하여 db관리</li>
        
</ul>

<h1>실행 시 주의점</h1>
<ul>
    <li>emulator: Pixel 2 API 30버전으로 실행해야 firestore연동 가</li>
</ul>

<h1>각 activity별 설명</h1>
<h3>MainActivity</h3>
<ul>
    <li>원하는 상품 체크 후 구매 버튼 클릭시 firestore의 구매목록 db에 저장 후 구매 activity로 이동</li>
    <ul>
        <li>buy_list변수에 체크된 항목 추가 후 품목(key) : 가격(value)형식으로 저장</li>
    </ul>
    <li>원하는 상품 체크 후 장바구니 추가 버튼 클릭 시
        firestore의 장바구니 목록db에 저장</li>
    <ul>
            <li>myCart변수에 마찬가지로 품목 : 가격 형식으로 저장</li>
        </ul>
    <li>장바구니 버튼 클릭 시 장바구니 activity로 이동</li>
    <li>아무것도 체크하지 않고 구매나 장바구니 추가 버튼 클릭시 아무것도 체크 되지 않았다는
    Toast메시지 출력</li>
</ul>
<h3>CartActivity</h3>

<ul>
    <li>activity create시 장바구니 db에서 정보를 가져와 visibilty속성을 활용하여
            화면에 장바구니 목록 출력</li>

   <li>구매를 원하는 상품 체크 후 구매 버튼 클릭 시 체크 여부에 따라 myCart변수 수정 후
            구매 목록 db를 myCart로 최신 시켜준 뒤 BuyActivity로 이동</li>
   
         
   <li>삭제 하고 싶은 상품 체크 후 삭제 버튼 클릭 시 장바구니 목록db에서 해당 품목
    삭제 후 activity새로고침을 통하여 바로 최신화된 목록 보여줌.</li>
   <li>만약 아무것도 체크 하지 않고 구매나 삭제 버튼 클릭 시 아무것도 체크 되지 않았다는 Toast메시지 출력</li>
   <li>홈으로 버튼 클릭 시 MainActivity로 이동</li>
</ul>

<h3>BuyActivity</h3>

<ul>
    <li>activity create시 구매목록 db에서 정보를 가져와 visibility속성을 활용하여 화면에 출력</li>

   <li>주소와 연락처 입력 후 구매버튼 클릭 시 구매완료 메시지와 MainActivity로 이동
        </li>
    <li>주소와 연락처 입력하지 않고 구매 버튼 클릭 시 구매불가</li>
    <li>구매 완료 시 만약 구매한 상품들과 장바구니db에 있는 상품들을 비교하여 동일한 상품이
        있을 경우 장바구니 목록에서 해당 데이터는 삭제
        </li>
   <ul>
            <li>현재 구매목록 db와 장바구니목록db 비교 후 겹치는 품목을 장바구니에서 삭</li>
        </ul>
   <li>홈으로 버튼 클릭 시 MainActivity로 이동</li>
</ul>

<h3>data.kt(각종 데이터 선언)</h3>

<ul>
    <li>buy_list : 구매목록 HashMap변수, 이 변수를 통해 구매목록 db관리</li>
    <li>myCart : 장바구니목록 HashMap변수, 이 변수를 통해 장바구니목록 db관리</li>
    <li>price : 구매화면에서 가격 총합을 나타내주는 변수</li>
    
</ul>

