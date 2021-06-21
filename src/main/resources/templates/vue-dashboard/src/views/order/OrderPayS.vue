<template>
  <div id="product">
    <div class="row align-items-start">
      <div class="row g-1">
        <div class="col-3">
          <div>
            <div
              style=" float:left; font-size: 24px; padding-right: 10px; border-right: 2px solid #C3BEB6;"
            >
              전체 주문
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

      <div class="row g-1 top">
        <div class="col-5">
          <div style="padding-left:10px;">상품 이름</div>
        </div>
        <div class="col-1">
          가격
        </div>
        <div class="col-1">
          수량
        </div>
        <div class="col-2" align="center">
          상태
        </div>
        <div class="col-3" align="center">
          편집하기
        </div>
      </div>

      <div class="row g-1">
        <div
          v-for="user in this.$store.state.news"
          v-bind:key="user"
          class="listAll"
        >
          <div class="col-5">
            <!-- 리스트 -->

            {{ user.title }}
          </div>
          <div class="col-1">
            가격
          </div>
          <div class="col-1">
            수량
          </div>
          <div class="col-2" align="center">
            상태
          </div>
          <div class="col-3" align="center">
            <button
              class="btn btn-primary"
              type="submit"
              id="show-modal"
              @click="showModal = true"
            >
              편집하기
            </button>
          </div>
        </div>
        <!-- 편집하기 모달 -->
        <modal v-if="showModal" @close="showModal = false">
          <!--
         you can use custom content here to overwrite
         default content
       -->
          <!-- <h3 slot="header">전체 주문 현황</h3> -->
        </modal>
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
import Modal from "../order/OrderModal.vue";

export default {
  created() {
    this.$store.dispatch("FETCH_NEWS");
  },
  data() {
    return {
      newTodoItem: "",
      showModal: false,
    };
  },
  components: {
    Modal,
  },
};
</script>

<style scoped>
.top {
  border-top: 1px solid #c3beb6;
  border-bottom: 1px solid #c3beb6;
  align-items: center;
  display: flex;
  font-size: 15pt;
  height: 70px;
}
.btn-primary {
  background-color: #eaeaf3;
  color: #d9557c;
  border: 0;
  width: 105px;
  height: 45px;
  font-size: 13pt;
}
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
.listAll {
  align-items: center;
  display: flex;
  height: 60px;
  padding: 0;
  margin: 0;
  border-bottom: 1px solid #c3beb6;
}
</style>
