// index.js
import base from "./base"
import qs from "querystring"
import axios from 'axios'


const api = {

    // index
    getBanner() {
        return axios.get(base.baseUrl + base.banner)
    },
    recommendation(params) { // { city:'beijing' }
        return axios.get(base.baseUrl + base.recommendation + "?city=" + params.city);
    },
    hotProduct(params) { // { city:'beijing' }
        return axios.get(base.baseUrl + base.trendy + "?city=" + params.city);
    },

    // search
    getSearch(params) {
        return axios.get(base.baseUrl + base.search + "?" + qs.stringify(params));
    },
    getSearchAll(params) {
        return axios.get(base.baseUrl + base.searchAll + "?" + qs.stringify(params));
    },

    // detail
    buytime(params) {
        return axios.get(base.baseUrl + base.buytime + '?id=' + params.id);
    },
    buyaction(params) {
        return axios.get(base.baseUrl + base.buyAction + "?" + qs.stringify(params));
    },
    getDetails(params) {
        return axios.get(base.baseUrl + base.details + "?" + qs.stringify(params));
    },

    // comment
    addComment(params) {
        console.log(params)
        return axios.post(base.baseUrl + base.feelback, params);
    },
    getComment(params) {
        return axios.get(base.baseUrl + base.comment + "?" + qs.stringify(params));
    },


    // login
    sendyzm(params) {
        return axios.post(base.baseUrl + base.sendVerificationCode, params)
    },
    login(params) {
        return axios.post(base.baseUrl + base.login, params);
    },
    register(params) {
        return axios.post(base.baseUrl + base.register, params);
    },

    // order
    getOrder(params) {
        return axios.get(base.baseUrl + base.order + "?" + qs.stringify(params));
    },


}

export default api;