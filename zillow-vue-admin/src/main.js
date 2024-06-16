import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import axios from "axios";
import api from './api/index.js'

axios.interceptors.request.use(function (config) {
    return config;
}, function (error) {
    // Do something with request error
    return Promise.reject(error);
});

store.dispatch('initializeStore');
Vue.prototype.axios = axios;
Vue.prototype.$api = api;
Vue.config.productionTip = false

new Vue({
    router, store, vuetify, render: h => h(App)
}).$mount('#app')
