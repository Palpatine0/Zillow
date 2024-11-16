// mian.js
import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import axios from "axios";
import api from './api/index.js'
import common from "./utils/common";
import qs from 'querystring'
import './assets/css/common.less';
import './assets/css/font.css'
import './assets/css/iconfont.css'
import VueAwesomeSwiper from 'vue-awesome-swiper'
import 'swiper/dist/css/swiper.css'

axios.interceptors.request.use(
    function(config) {
        return config;
    }, function(error) {
        return Promise.reject(error);
    });

store.dispatch('initializeStore');

Vue.prototype.axios = axios;
Vue.prototype.$api = api;
Vue.prototype.$common = common;
Vue.use(VueAwesomeSwiper)

Vue.config.productionTip = false

new Vue({
    router,
    store,
    vuetify,
    render: h => h(App)
}).$mount('#app')
