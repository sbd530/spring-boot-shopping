<template>
  <div class="container">
    <label style="padding:45px 0 20px 0; font-size: 25pt;">MY tree</label>
    <div class="row g-2" style="width:1500px;">
      <div class="col-4">
        <div class="p-3 border bg-light" style=" border-radius: 12px; ">
          <div class="toplogo">
            <i id="high-i1" class="fas fa-shopping-cart"></i>주문
          </div>

          <div class="col">
            <span>주문 현황</span>

            <div>
              <span id="gea"><i class="far fa-list-alt"></i> 입금 대기</span>
              <span
                style="display: inline-block;
            width: 70px; text-align: right;"
                >개</span
              >
            </div>
            <div>
              <span id="gea"><i class="far fa-credit-card"></i> 결제 완료</span>
              <span
                style="display: inline-block;
            width: 70px; text-align: right;"
                >개</span
              >
            </div>
            <div>
              <span id="gea"><i class="fas fa-box-open"></i> 배송 준비</span>
              <span
                style="display: inline-block;
            width: 70px; text-align: right;"
                >개</span
              >
            </div>
            <div>
              <span id="gea"><i class="fas fa-truck"></i> 배송 중 </span>
              <span
                id="su"
                style="display: inline-block;
           width: 70px; text-align: right;"
                >개</span
              >
            </div>
          </div>

          <div class="col">
            <div
              style="border-top: 2px solid #C3BEB6; padding: 5px 0 5px 0;"
            ></div>
            <span>취소/반품 요청</span>

            <div>
              <span id="gea"
                ><i class="far fa-window-close"></i> 취소 요청</span
              >
              <span
                style="display: inline-block;
            width: 70px; text-align: right;"
                >개</span
              >
            </div>
            <div>
              <span id="gea"><i class="fas fa-reply"></i> 반품 요청</span>
              <span
                style="display: inline-block;
            width: 70px; text-align: right;"
                >개</span
              >
            </div>
          </div>

          <div class="col">
            <div
              style="border-top: 2px solid #C3BEB6; padding: 5px 0 5px 0;"
            ></div>

            <div>
              <span id="gea"
                ><i class="fas fa-exclamation-triangle"></i> 환불 요청</span
              >
              <span
                style="display: inline-block;
            width: 70px; text-align: right;"
                >개</span
              >
            </div>
          </div>
        </div>
      </div>
      <div class="col-4">
        <div class="p-3 border bg-light">
          <div class="toplogo"><i id="high-i1" class="fas fa-box"></i>상품</div>

          <div class="col">
            <span>상태</span>

            <div>
              <span id="gea"><i class="fas fa-box"></i> 판매 중</span>
              <span
                style="display: inline-block;
            width: 70px; text-align: right;"
                >개</span
              >
            </div>
            <div>
              <span id="gea"
                ><i class="far fa-minus-square"></i> 판매 중지</span
              >
              <span
                style="display: inline-block;
            width: 70px; text-align: right;"
                >개</span
              >
            </div>
            <div>
              <span id="gea"><i class="fas fa-ban"></i> 품절</span>
              <span
                style="display: inline-block;
            width: 70px; text-align: right;"
                >개</span
              >
            </div>
          </div>
        </div>
      </div>

      <div class="col-4">
        <div class="p-3 border bg-light">
          <div class="toplogo">
            <i id="high-i1" class="fas fa-poll-h"></i>게시글
          </div>
          <div class="col">
            <span>게시판</span>

            <div>
              <span id="gea"><i class="fas fa-box"></i> 새로 올라온 글</span>
              <span
                style="display: inline-block;
            width: 70px; text-align: right;"
                >개</span
              >
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.toplogo {
  font-size: 20pt;
  background-color: #f3d2cd;
  padding-bottom: 10px;
  margin-bottom: 10px;
  border-radius: 8px;
}
.col {
  padding-bottom: 20px;
}
.p-3 {
  border-radius: 12px;
  height: 580px;
}
.row {
  width: 1500px;
  font-size: 15pt;
}

#su {
  display: inline-block;
  width: 70px;
}
#gea {
  display: inline-block;
  width: 320px;
}
.container {
  padding: 50px 0 0 280px;
  margin: 0;
}

#high-i1 {
  width: 40px;
  height: 40px;
  padding: 11px;
  border-radius: 8px;
  margin: 10px;
}

span {
  padding: 10px;
}
</style>
