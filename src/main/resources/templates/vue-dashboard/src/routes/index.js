import Vue from "vue";
import VueRouter from "vue-router";
//상품
import ProductAdmin from "../views/product/ProductAdmin.vue";
import ProductStock from "../views/product/ProductStock.vue";
//오다
import OrderAll from "../views/order/OrderAll.vue";
import OrderPayS from "../views/order/OrderPayS.vue";
import OrderSuccess from "../views/order/OrderSuccess.vue";
//메인
import MainAdmin from "../views/MainAdmin.vue";
//고객
import UserAdmin from "../views/user/UserAdmin.vue";
import UserCare from "../views/user/UserCare.vue";
//게시판
import Board from "../views/board/Board.vue";
//후기,질문
import Review from "../views/questions/Review.vue";
import Questions from "../views/questions/Questions.vue";

Vue.config.productionTip = false;

Vue.use(VueRouter);

export const router = new VueRouter({
  mode: "history",
  routes: [
    //home
    {
      path: "/",
      //component: url 주소로 갔을 때 표시될 컴포넌트
      redirect: "/dashboard/home",
    },
    {
      path: "/dashboard/home",
      component: MainAdmin,
    },
    //상품
    {
      path: "/dashboard/products",
      component: ProductAdmin,
    },
    {
      path: "/dashboard/products/stock",
      component: ProductStock,
    },
    //주문
    {
      path: "/dashboard/orders",
      component: OrderAll,
    },
    {
      path: "/dashboard/orders/paysuccess",
      component: OrderPayS,
    },
    {
      path: "/dashboard/orders/success",
      component: OrderSuccess,
    },
    //고객
    {
      path: "/dashboard/users",
      component: UserAdmin,
    },
    {
      path: "/dashboard/usercare",
      component: UserCare,
    },
    //게시판
    {
      path: "/dashboard/board",
      component: Board,
    },
    //후기와 질문
    {
      path: "/dashboard/review",
      component: Review,
    },
    {
      path: "/dashboard/questions",
      component: Questions,
    },
  ],
});
