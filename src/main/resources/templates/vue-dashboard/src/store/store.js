import Vue from "vue";
import Vuex from "vuex";
import { fetchNewsList } from "../api/api.js";

Vue.use(Vuex);

export const store = new Vuex.Store({
  state: {
    news: [],
  },
  mutations: {
    SET_NEWS(state, data) {
      state.news = data;
    },
  },
  actions: {
    FETCH_NEWS(context) {
      fetchNewsList()
        .then((reponse) => {
          this.users = reponse.data;
          context.commit("SET_NEWS", reponse.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
});
