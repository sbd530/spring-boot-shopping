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
          <div slot="body">
            <div class="row flex-nowrap">
              <div class="col-3 bd-sidebar">
                <ul class="nav">
                  <div class="modal-side">상품 이름</div>
                  <li><input type="text" /></li>
                  <div class="modal-side">상품 가격</div>
                  <li><input type="text" /></li>
                  <div class="modal-side">이미지</div>
                  <li class="image"><input type="text" /></li>
                  <li class="image"><input type="text" /></li>
                  <li class="image"><input type="text" /></li>
                  <li class="image"><input type="text" /></li>
                  <div class="modal-side">상품 수량</div>
                  <li><input type="text" /></li>
                  <div class="modal-side">상태</div>
                  <li><input type="text" /></li>
                </ul>
                <br />
              </div>
              <!-- 파일 업로드 -->
              <main class="col-9 py-md-3 pl-md-5 bd-content" role="main">
                <div class="room-deal-information-container">
                  <div class="room-deal-information-title"></div>
                  <div class="room-picture-notice"></div>
                  <div class="room-file-upload-wrapper">
                    <div
                      v-if="!files.length"
                      class="room-file-upload-example-container"
                    >
                      <div class="room-file-upload-example">
                        <div
                          class="room-file-notice-item room-file-upload-button"
                        >
                          <div class="image-box">
                            <label for="file">일반 사진 등록</label>
                            <input
                              type="file"
                              id="file"
                              ref="files"
                              @change="imageUpload"
                              multiple
                            />
                          </div>
                        </div>
                      </div>
                    </div>
                    <div v-else class="file-preview-content-container">
                      <div class="file-preview-container">
                        <div
                          v-for="(file, index) in files"
                          :key="index"
                          class="file-preview-wrapper"
                        >
                          <div
                            class="file-close-button"
                            @click="fileDeleteButton"
                            :name="file.number"
                          >
                            <!-- X 버튼 아이콘 -->
                            <i class="fas fa-times-circle"></i>
                          </div>
                          <img class="image" :src="file.preview" />
                        </div>
                        <div class="file-preview-wrapper-upload">
                          <div class="image-box">
                            <label for="file">추가 사진 등록</label>
                            <input
                              type="file"
                              id="file"
                              ref="files"
                              @change="imageAddUpload"
                              multiple
                            />
                          </div>
                          <!-- <div class="file-close-button" @click="fileDeleteButton" :name="file.number">x</div> -->
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </main>
            </div>
          </div>
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

      files: [], //업로드용 파일
      filesPreview: [],
      uploadImageIndex: 0, // 이미지 업로드를 위한 변수
    };
  },
  components: {
    Modal,
  },
  methods: {
    imageUpload() {
      console.log(this.$refs.files.files);

      // this.files = [...this.files, this.$refs.files.files];
      //하나의 배열로 넣기
      let num = -1;
      for (let i = 0; i < this.$refs.files.files.length; i++) {
        this.files = [
          ...this.files,
          //이미지 업로드
          {
            //실제 파일
            file: this.$refs.files.files[i],
            //이미지 프리뷰
            preview: URL.createObjectURL(this.$refs.files.files[i]),
            //삭제및 관리를 위한 number
            number: i,
          },
        ];
        num = i;
        //이미지 업로드용 프리뷰
        // this.filesPreview = [
        //   ...this.filesPreview,
        //   { file: URL.createObjectURL(this.$refs.files.files[i]), number: i }
        // ];
      }
      this.uploadImageIndex = num + 1; //이미지 index의 마지막 값 + 1 저장
      console.log(this.files);
      // console.log(this.filesPreview);
    },

    imageAddUpload() {
      console.log(this.$refs.files.files);

      // this.files = [...this.files, this.$refs.files.files];
      //하나의 배열로 넣기c
      let num = -1;
      for (let i = 0; i < this.$refs.files.files.length; i++) {
        console.log(this.uploadImageIndex);
        this.files = [
          ...this.files,
          //이미지 업로드
          {
            //실제 파일
            file: this.$refs.files.files[i],
            //이미지 프리뷰
            preview: URL.createObjectURL(this.$refs.files.files[i]),
            //삭제및 관리를 위한 number
            number: i + this.uploadImageIndex,
          },
        ];
        num = i;
      }
      this.uploadImageIndex = this.uploadImageIndex + num + 1;

      console.log(this.files);
      // console.log(this.filesPreview);
    },
    fileDeleteButton(e) {
      const name = e.target.getAttribute("name");
      this.files = this.files.filter((data) => data.number !== Number(name));
      // console.log(this.files);
    },
  },
};
</script>

<style scoped>
.main-container {
  width: 1200px;
  height: 400px;
  margin: 0 auto;
}

.room-deal-information-container {
  margin-top: 50px;
  color: #222222;
  border: 1px solid #dddddd;
}

.room-deal-information-title {
  text-align: center;
  font-size: 18px;
  line-height: 60px;
  border-bottom: 1px solid #dddddd;
}

.room-deal-information-content-wrapper {
  min-height: 50px;
  display: flex;
}

.room-deal-informtaion-content-title {
  font-size: 15px;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 150px;
  background-color: #f9f9f9;
}

.room-deal-information-content {
  width: 100%;
  font-size: 14px;
}

.room-deal-option-selector {
  display: flex;
  align-items: center;
  padding: 15px;
}

.room-deal-option-item {
  width: 100px;
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 1px solid #cccccc;
  border-radius: 5px;
  cursor: pointer;
}

.room-deal-option-item:last-child {
  margin-left: 10px;
}

.room-deal-option-notice {
  margin-left: auto;
  font-size: 14px;
  color: #888888;
}

.room-deal-option-item-deposit {
  margin-left: 10px;
}

.room-deal-information-wrapper {
  display: flex;
  flex-direction: column;
}

.room-deal-information-option {
  padding: 10px;
  display: flex;
  align-items: center;
}

.room-deal-information-option:last-child {
  border-bottom: 1px solid #dddddd;
}

.room-deal-information-item-type {
  font-size: 13px;
  color: #fff;
  background-color: #61b6e5;
  min-width: 50px;
  height: 26px;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 3px;
}

.room-deal-information-item-wrapper {
  display: flex;
  align-items: center;
  margin-left: 10px;
  height: 46px;
  width: 100%;
}

.room-deal-information-item-wrapper > input {
  border: 1px solid #dddddd;
  width: 140px;
  height: 100%;
  padding: 0 15px;
  font-size: 15px;
}

.room-deal-inforamtion-won {
  margin: 0 10px;
}

.room-deal-information-example {
  color: #888888;
}

.room-deal-information-option:not(:first-child) {
  margin-top: 10px;
}

.room-deal-inforamtion-divide {
  font-size: 22px;
  margin: 0 8px;
  color: #222222;
  font-weight: 100;
}

.room-deal-close-button-wrapper {
  margin-left: auto;
  cursor: pointer;
}

.room-deal-close-button {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 22px;
  height: 22px;
  background-color: #666666;
  color: rgb(220, 220, 220);
}

.room-deal-cliked {
  background-color: rgb(235, 235, 235);
  color: rgb(170, 170, 170);
}

.room-file-upload-example {
  height: 100%;
}

.room-write-content-container {
  border-top: 1px solid #dddddd;
  min-height: 260px;
}

.room-picture-notice {
  margin: 20px;
  padding: 20px 40px;
  border: 1px solid #dddddd;
}

.file-preview-content-container {
  height: 100%;
}

.room-file-upload-wrapper {
  margin: 20px;
  border: 1px solid #dddddd;
  background-color: #f4f4f4;
  min-height: 350px;
  font-size: 15px;
  color: #888888;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
}

.room-file-upload-example-container {
  display: flex;
  align-items: center;
  justify-content: center;
  /* height: 100%;
  width: 100%; */
}

.room-file-image-example-wrapper {
  text-align: center;
}

.room-file-notice-item {
  margin-top: 5px;
  text-align: center;
}

.room-file-notice-item-red {
  color: #ef4351;
}

.image-box {
  margin-top: 30px;
  padding-bottom: 20px;
  text-align: center;
}

.image-box input[type="file"] {
  position: absolute;
  width: 0;
  height: 0;
  padding: 0;
  overflow: hidden;
  border: 0;
}

.image-box label {
  display: inline-block;
  padding: 10px 20px;
  background-color: #232d4a;
  color: #fff;
  vertical-align: middle;
  font-size: 15px;
  cursor: pointer;
  border-radius: 5px;
}

.file-preview-wrapper {
  padding: 10px;
  position: relative;
}

.file-preview-wrapper img {
  position: relative;
  width: 190px;
  z-index: 10;
}

.file-close-button {
  position: absolute;
  /* align-items: center; */
  line-height: 18px;
  z-index: 99;
  font-size: 20px;
  right: 5px;
  top: 10px;
  color: #fff;
  font-weight: bold;
  background-color: #666666;
  width: 20px;
  height: 20px;
  text-align: center;
  cursor: pointer;
}

.file-preview-container {
  height: 100%;
  display: flex;
  flex-wrap: wrap;
}

.file-preview-wrapper-upload {
  margin: 10px;
  padding-top: 20px;
  background-color: #888888;
  width: 190px;
  height: 130px;
}

.room-write-button-wrapper {
  margin-top: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
  color: #222222;
}

.room-write-button-wrapper div {
  width: 160px;
  height: 50px;
  border: 1px solid #dddddd;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 15px;
  cursor: pointer;
}

.room-write-button {
  margin-left: 15px;
  color: #fff;
  background-color: #1564f9;
}

.room-write-button:hover {
  opacity: 0.8;
}
.modal-side {
  padding-top: 20px;
}
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
