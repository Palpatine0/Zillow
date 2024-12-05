// index.js
import base from "./base"
import qs from "querystring"
import axios from 'axios'


const api = {


    // file
    getBanners() {
        return axios.get(base.baseUrl + base.getBanners)
    },
    uploadFile(file) {
        return axios.post(base.baseUrl + base.uploadFile, file, {
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


    // buyAction
    buyAction(params) {
        return axios.get(base.baseUrl + base.buyAction + "?" + qs.stringify(params));
    },

    // item
    getItemById(params) {
        return axios.get(base.baseUrl + base.getItemById + "?" + qs.stringify(params));
    },


    // comment
    addComment(params) {
        return axios.post(base.baseUrl + base.addComment + "?" + qs.stringify(params));
    },
    getCommentsByItemId(params) {
        return axios.get(base.baseUrl + base.getCommentsByItemId + "?" + qs.stringify(params));
    },


    // user
    login(params) {
        return axios.post(base.baseUrl + base.login, qs.stringify(params));
    },
    register(params) {
        return axios.post(base.baseUrl + base.register, qs.stringify(params));
    },
    getUserByUsername(params) {
        return axios.get(base.baseUrl + base.getUserByUsername + "?" + qs.stringify(params));
    },

    // order
    getOrdersByUserId(params) {
        return axios.get(base.baseUrl + base.getOrdersByUserId + "?" + qs.stringify(params));
    },

    // llm
    chat(params){
        return axios.get(base.baseUrl + base.chat + "?" + qs.stringify(params));
    }

}

export default api;