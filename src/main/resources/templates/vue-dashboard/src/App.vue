<template>
  <div id="app">
    <side-tool-bar></side-tool-bar>
    <router-view></router-view>
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
</style>
