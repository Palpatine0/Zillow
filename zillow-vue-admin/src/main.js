import Vue from 'vue'
import './plugins/vuetify'
import App from './App.vue'
import router from './router'
import qs from "querystring";
import vuetify from './plugins/vuetify'

import axios from "axios";
import api from './api/index.js'
import '@fortawesome/fontawesome-free/css/all.css'



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
Vue.config.productionTip = false

new Vue({
    vuetify,
    router,
    render: h => h(App)
}).$mount('#app')
