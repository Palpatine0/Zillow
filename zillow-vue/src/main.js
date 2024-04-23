// mian.js
import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './assets/css/common.less';
import './assets/css/font.css'
import './assets/css/iconfont.css'
import VueAwesomeSwiper from 'vue-awesome-swiper'
import axios from "axios";
import qs from 'querystring'
import api from './api/index.js'
// require styles
import 'swiper/dist/css/swiper.css'
// font awesome
import {library} from '@fortawesome/fontawesome-svg-core';
import {fas} from '@fortawesome/free-solid-svg-icons';
import {FontAwesomeIcon} from '@fortawesome/vue-fontawesome';

library.add(fas);
Vue.component('font-awesome-icon', FontAwesomeIcon);

axios.interceptors.request.use(function (config) {
    // console.log(config)
    // Do something before request is sent
    if (config.method == 'post') {
        console.log('posted')
        config.data = qs.stringify(config.data)
    }
    return config;
}, function (error) {
    // Do something with request error
    return Promise.reject(error);
});

Vue.prototype.axios = axios;
Vue.prototype.$api = api;

Vue.use(VueAwesomeSwiper)

Vue.config.productionTip = false

new Vue({
    router, store, render: h => h(App)
}).$mount('#app')
