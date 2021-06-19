<template>
  <div id="page-wrapper">
    <!-- 사이드바 -->
    <div id="sidebar-wrapper">
      <div id="logo">MY <i class="fab fa-pagelines"></i></div>
      <ul class="sidebar-nav">
        <div style="padding:0 0 30px 10px;">
          <router-link to="/admin/user">
            <i class="fas fa-eye"></i>내 상점 바로 가기
          </router-link>
        </div>

        <label style="padding-left: 20px; color:#c3beb6">상점 관리하기</label>
        <li class="sidebar-brand"></li>
        <li class="mb-1">
          <button
            class="btn d-inline-flex align-items-center rounded collapsed"
            data-bs-toggle="collapse"
            data-bs-target="#components-collapse"
            aria-expanded="false"
            aria-current="true"
          >
            <router-link to="/dashboard/home">
              <span
                ><i class="fas fa-caret-right"></i>
                <i class="fas fa-home"></i>홈</span
              >
            </router-link>
          </button>
        </li>

        <li class="mb-1">
          <button
            class="btn d-inline-flex align-items-center rounded collapsed"
            data-bs-toggle="collapse"
            data-bs-target="#getting-started-collapse"
            aria-expanded="false"
          >
            <router-link to="/dashboard/orders">
              <span>
                <i class="fas fa-caret-down"></i>
                <i class="fas fa-shopping-cart"></i> 주문</span
              >
            </router-link>
          </button>

          <div class="collapse" id="getting-started-collapse">
            <ul class="list-unstyled fw-normal pb-1 small">
              <router-link to="/dashboard/orders/paysuccess">
                <li id="ca">
                  결제완료
                </li>
              </router-link>

              <router-link to="/dashboard/orders/success">
                <li id="ca">
                  배송완료
                </li>
              </router-link>
            </ul>
          </div>
        </li>

        <li class="mb-1">
          <button
            class="btn d-inline-flex align-items-center rounded collapsed"
            data-bs-toggle="collapse"
            data-bs-target="#customize-collapse"
            aria-expanded="false"
          >
            <router-link to="/dashboard/products">
              <span>
                <i class="fas fa-caret-down"></i>
                <i class="fas fa-box"></i>상품</span
              >
            </router-link>
          </button>

          <div class="collapse" id="customize-collapse">
            <ul class="list-unstyled fw-normal pb-1 small">
              <router-link to="/dashboard/products/stock">
                <li id="ca">
                  재고관리
                </li>
              </router-link>
            </ul>
          </div>
        </li>

        <li class="mb-1">
          <button
            class="btn d-inline-flex align-items-center rounded collapsed"
            data-bs-toggle="collapse"
            data-bs-target="#layout-collapse"
            aria-expanded="false"
          >
            <router-link to="/dashboard/users">
              <span
                ><i class="fas fa-caret-down"></i>
                <i class="fas fa-user-friends"></i>고객</span
              >
            </router-link>
          </button>

          <div class="collapse" id="layout-collapse">
            <ul class="list-unstyled fw-normal pb-1 small">
              <router-link to="/dashboard/usercare">
                <li id="ca">
                  가입관리
                </li>
              </router-link>
            </ul>
          </div>
        </li>

        <li class="mb-1">
          <button
            class="btn d-inline-flex align-items-center rounded collapsed"
            data-bs-toggle="collapse"
            data-bs-target="#content-collapse"
            aria-expanded="false"
          >
            <span
              ><i class="fas fa-caret-down"></i>
              <i class="fas fa-th-list"></i>게시판</span
            >
          </button>

          <div class="collapse" id="content-collapse">
            <ul class="list-unstyled fw-normal pb-1 small">
              <router-link to="/dashboard/board">
                <li id="ca">
                  관리
                </li>
              </router-link>
            </ul>
          </div>
        </li>

        <li class="mb-1">
          <button
            class="btn d-inline-flex align-items-center rounded collapsed"
            data-bs-toggle="collapse"
            data-bs-target="#forms-collapse"
            aria-expanded="false"
          >
            <span
              ><i class="fas fa-caret-down"></i>
              <i class="fas fa-comments"></i>후기와 질문</span
            >
          </button>

          <div class="collapse" id="forms-collapse">
            <ul class="list-unstyled fw-normal pb-1 small">
              <router-link to="/dashboard/review">
                <li id="ca">
                  REVIEW
                </li>
              </router-link>
              <router-link to="/dashboard/questions">
                <li id="ca">
                  Q&A
                </li>
              </router-link>
            </ul>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
#ca {
  padding-left: 37px;
}
#logo {
  color: #d9557c;
  padding: 0 0 30px 20px;
  font-size: 40pt;
}
body {
  background: #181a1b;
  color: #c3beb6;
}

#page-wrapper {
  padding-left: 250px;
}

#sidebar-wrapper {
  position: fixed;
  width: 200px;
  height: 100%;
  margin-left: -250px;
  background: #332d2f;
  overflow-x: hidden;
  overflow-y: auto;
  padding-top: 20px;
}

#page-content-wrapper {
  width: 100%;
  padding: 20px;
}

/* 사이드바 스타일 */

.sidebar-nav {
  width: auto;
  margin: 0;
  padding: 0;
  list-style: none;
}

.sidebar-nav li {
  text-indent: 1.5em;
  line-height: 2.8em;
}

.sidebar-nav li a {
  display: block;
  text-decoration: none;
  color: #c3beb6;
}

.sidebar-nav li a:hover {
  color: #fff;
  background: rgba(255, 255, 255, 0.2);
}

.sidebar-nav > .sidebar-brand {
  font-size: 1.3em;
  line-height: 3em;
}

i {
  padding-right: 10px;
}
span {
  color: #c3beb6;
}

a {
  width: auto;
  padding-right: 20px;
}
</style>
