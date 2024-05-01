// index.js
import base from "./base"
import qs from "querystring"
import axios from 'axios'


const api = {

    // index
    getBanner() {
        return axios.get(base.baseUrl + base.getBanner)
    },
    getRecommendation(params) {
        return axios.get(base.baseUrl + base.getRecommendation + "?city=" + params.city);
    },
    hotProduct(params) {
        return axios.get(base.baseUrl + base.getTrendy + "?city=" + params.city);
    },

    // search
    searchByKeyWord(params) {
        return axios.get(base.baseUrl + base.searchByKeyWord + "?" + qs.stringify(params));
    },
    searchByCity(params) {
        return axios.get(base.baseUrl + base.searchByCity + "?" + qs.stringify(params));
    },
    adminSearchByCity(params) {
        return axios.get(base.baseUrl + base.adminSearchByCity + "?" + qs.stringify(params));
    },

    // detail
    buytime(params) {
        return axios.get(base.baseUrl + base.getBuyTime + '?id=' + params.id);
    },
    buyaction(params) {
        return axios.get(base.baseUrl + base.buyAction + "?" + qs.stringify(params));
    },
    getDetails(params) {
        return axios.get(base.baseUrl + base.getDetail + "?" + qs.stringify(params));
    },

    // comment
    addComment(params) {
        console.log(params)
        return axios.post(base.baseUrl + base.feelback, params);
    },
    getComment(params) {
        return axios.get(base.baseUrl + base.comment + "?" + qs.stringify(params));
    },


    // user
    sendVerificationCode(params) {
        return axios.post(base.baseUrl + base.sendVerificationCode, params)
    },
    login(params) {
        return axios.post(base.baseUrl + base.login, params);
    },
    register(params) {
        return axios.post(base.baseUrl + base.register, params);
    },
    deleteUser(params) {
        return axios.post(base.baseUrl + base.deleteUser, params);
    },
    getUser(params) {
        return axios.get(base.baseUrl + base.getUser, params);
    },

    // order
    getOrder(params) {
        return axios.get(base.baseUrl + base.order + "?" + qs.stringify(params));
    },


}

export default api;