# kotlin_personal_shop
<h1>간단한 과제 설명</h1>
<p>firebase연동을 통한 간단한 상품 구매 어플 만들기</p>
<hr>
<h1>실행 시 주의점</h1>
<ul>
    <li>emulator: Pixel 2 API 30버전으로 실행해야 firestore연동 가</li>
</ul>
<hr>
<h1>각 activity별 설명</h1>
<h3>MainActivity</h3>
    
<ul>
    <li>원하는 상품 체크 후 구매 버튼 클릭시 firestore의 구매목록 db에 저장 후 구매 activity로 이동</li>
    <li>원하는 상품 체크 후 장바구니 추가 버튼 클릭 시
        firestore의 장바구니 목록db에 저장</li>
    <li>장바구니 버튼 클릭 시 장바구니 activity로 이동</li>
    <li>아무것도 체크하지 않고 구매나 장바구니 추가 버튼 클릭시 아무것도 체크 되지 않았다는
    Toast메시지 출력</li>
</ul>
<h3>CartActivity</h3>
<p>
<ul>
    <li>activity create시 장바구니 db에서 정보를 가져와 화면에 장바구니 목록 출력</li>

    <li>구매를 원하는 상품 체크 후 구매 버튼 클릭 시 체크 여부 판단 후
        구매 목록 db에 최신화 시켜준 뒤 BuyActivity로 이동</li>
    <li>삭제 하고 싶은 상품 체크 후 삭제 버튼 클릭 시 장바구니 목록db에서 해당 품목
    삭제 후 activity새로고침을 통하여 바로 최신화된 목록 보여줌.</li>
    <li>만약 아무것도 체크 하지 않고 구매나 삭제 버튼 클릭 시 아무것도 체크 되지 않았다는 Toast메시지 출력</li>
    <li>홈으로 버튼 클릭 시 MainActivity로 이동</li>
</ul>
</p>
<h3>BuyActivity</h3>
<p>
<ul>
    <li>activity create시 구매목록 db에서 정보를 가져와 화면에 출력</li>

    <li>주소와 연락처 입력 후 구매버튼 클릭 시 구매완료 메시지와 MainActivity로 이동
        </li>
    <li>주소와 연락처 입력하지 않고 구매 버튼 클릭 시 구매불가</li>
    <li>구매 완료 시 만약 구매한 상품들과 장바구니db에 있는 상품들을 비교하여 동일한 상품이
        있을 경우 장바구니 목록에서 해당 데이터는 삭제
        </li>

    <li>홈으로 버튼 클릭 시 MainActivity로 이동</li>
</ul>
</p>
