// index.js
import base from "./base"
import qs from "querystring"
import axios from 'axios'


const api = {


    // file
    getBanners() {
        return axios.get(base.baseUrl + base.getBanners)
    },
    uploadImageNoPrefix(file) {
        return axios.post(base.baseUrl + base.uploadImageNoPrefix, file, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        })
    },

    //
    getRecommendations(params) {
        return axios.get(base.baseUrl + base.getRecommendations + "?city=" + params.city);
    },
    getTrendies(params) {
        return axios.get(base.baseUrl + base.getTrendies + "?city=" + params.city);
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

    //
    buytime(params) {
        return axios.get(base.baseUrl + base.getBuyTime + '?id=' + params.id);
    },

    // buyAction
    buyAction(params) {
        return axios.get(base.baseUrl + base.buyAction + "?" + qs.stringify(params));
    },

    // item
    getItemByID(params) {
        return axios.get(base.baseUrl + base.getItemByID + "?" + qs.stringify(params));
    },


    // comment
    addComment(params) {
        return axios.post(base.baseUrl + base.addComment, params);
    },
    getCommentsByItemId(params) {
        return axios.get(base.baseUrl + base.getCommentsByItemId + "?" + qs.stringify(params));
    },


    // user
    sendVerificationCode(params) {
        return axios.post(base.baseUrl + base.sendVerificationCode, params)
    },
    login(params) {
        return axios.post(base.baseUrl + base.login, qs.stringify(params));
    },
    register(params) {
        return axios.post(base.baseUrl + base.register, qs.stringify(params));
    },
    getUsers(params) {
        return axios.get(base.baseUrl + base.getUsers, params);
    },
    getUserByUsername(params) {
        return axios.get(base.baseUrl + base.getUserByUsername + "?" + qs.stringify(params));
    },
    getUserById(params) {
        return axios.get(base.baseUrl + base.getUserById + "?" + qs.stringify(params));
    },

    // order
    getOrdersByUserId(params) {
        return axios.get(base.baseUrl + base.getOrdersByUserId + "?" + qs.stringify(params));
    },


}

export default api;