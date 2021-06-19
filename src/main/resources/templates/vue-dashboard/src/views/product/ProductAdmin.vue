<template>
  <div id="product">
    <div class="row align-items-start">
      <div class="row g-1">
        <div class="col-3">
          <div>
            <div
              style=" float:left; font-size: 24px; padding-right: 10px; border-right: 2px solid #C3BEB6;"
            >
              전체 상품
            </div>

            <div style="margin-bottom: 20px;">
              <form
                action=""
                name="searchForm1"
                method="post"
                style="width: 500px; "
              >
                <div class="input-group w-50" style="padding-left: 20px;">
                  <input
                    type="text"
                    name="searchValue"
                    class="form-control"
                    placeholder="Search"
                    aria-describedby="basic-addon1"
                    style="border-radius:8px; padding-right: 10px;"
                  />
                  <button
                    value=" 검 색 "
                    class="btn2"
                    onclick="sendIt()"
                    style=" width: 40px; border:1px solid #EFEFEF; border-radius:8px; background-color: #C3BEB6;  "
                  >
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      width="16"
                      height="16"
                      style="color: black;"
                      fill="currentColor"
                      class="bi bi-search"
                      viewBox="0 0 16 16"
                    >
                      <path
                        d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"
                      ></path>
                    </svg>
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
        <div class="col-3"></div>
        <div class="col-3"></div>
        <div class="col-3">
          <div align="right" style="margin-right: 30px;">
            <button
              class="btn btn-primary"
              type="submit"
              style="background-color: #332D2F; color: #EFEFEF; border: 0; width: 200px; height: 50px;"
            >
              상품 추가하기
            </button>
          </div>
        </div>
      </div>
      <div class="row g-1">
        <div
          class="col-12"
          style="border-bottom:1px solid #C3BEB6; width: 1000px; padding-bottom: 10px;"
        >
          <div class="dropdown">
            <form action="" name="searchForm" method="post">
              <button
                class="btn btn-secondary dropdown-toggle"
                type="button"
                id="dropdownMenu2"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                10개
              </button>
              <ul
                class="dropdown-menu"
                aria-labelledby="dropdownMenu2"
                name="searchKey"
                onclick="searchData();"
              >
                <li>
                  <button class="dropdown-item" type="button" value="ten">
                    10개
                  </button>
                </li>
                <li>
                  <button class="dropdown-item" type="button" value="thirty">
                    30개
                  </button>
                </li>
                <li>
                  <button class="dropdown-item" type="button" value="one_h">
                    100개
                  </button>
                </li>
                <li>
                  <button class="dropdown-item" type="button" value="five_h">
                    500개
                  </button>
                </li>
                <li>
                  <button class="dropdown-item" type="button" value="one_t">
                    1000개
                  </button>
                </li>
              </ul>
            </form>
          </div>
        </div>
      </div>
      <div
        class="row g-1"
        style="border-bottom:1px solid #C3BEB6; padding-top: 10px;"
      >
        <div class="col-6">
          <form>
            <p>
              <input type="checkbox" name="all" class="check-all" />
              <label style="padding-left:10px;">상품 이름</label>
            </p>
          </form>
        </div>
        <div class="col-1">
          가격
        </div>
        <div class="col-1">
          수량
        </div>
        <div class="col-1">
          상태
        </div>
        <div class="col-3" align="center">
          편집하기
        </div>
      </div>

      <div
        class="row g-1"
        style="border-bottom:1px solid #C3BEB6; padding-bottom: 10px; "
      >
        <c:forEach var="dto" items="${lists}"></c:forEach>
        <div class="col-6" style="padding-top: 20px;">
          <form>
            <p>
              <input type="checkbox" name="cb1" class="ab" />
              <label style="padding-left:10px;">Checkbox 1</label>
            </p>
          </form>
        </div>
        <div class="col-1" style="padding-top: 20px;">
          가격
        </div>
        <div class="col-1" style="padding-top: 20px;">
          수량
        </div>
        <div class="col-1" style="padding-top: 20px;">
          상태
        </div>
        <div class="col-3" align="center" style="padding-top: 10px;">
          <button
            class="btn btn-primary"
            type="submit"
            style="background-color: #EAEAF3; color: #D9557C; border: 0; width: 130px; height: 50px; font-size:13pt;"
          >
            편집하기
          </button>
        </div>
        <!-- </c:forEach> -->
      </div>

      <div id="footer" style="padding-top: 30px;">
        <c:if test="${totalDataCount!=0}">
          ${pageIndexList}
        </c:if>
        <c:if test="${totalDataCount==0}">
          등록된 게시물이 없습니다.
        </c:if>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
#product {
  margin: 0 0 0 230px;
  padding: 100px 50px 0 50px;
}
.align-items-start {
  background-color: white;
  width: 1500px;
  padding: 50px 20px 30px 50px;
  border-radius: 12px;
}
</style>
