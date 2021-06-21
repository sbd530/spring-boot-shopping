<template>
  <div id="app">
    <side-tool-bar></side-tool-bar>
    <transition name="page">
      <router-view></router-view>
    </transition>
  </div>
</template>

<script>
import SideToolBar from "./components/SideToolBar.vue";

export default {
  name: "App",
  components: {
    SideToolBar,
  },
};
</script>

<style>
body {
  background-color: #dedddb;
}
/* Router Transition */
.page-enter-active,
.page-leave-active {
  transition: opacity 0.5s;
}
page .page-enter,
.page-leave-to {
  opacity: 0;
}
</style>
