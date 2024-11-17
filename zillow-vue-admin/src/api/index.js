// index.js
import base from "./base"
import qs from "querystring"
import axios from 'axios'


const api = {

    // file
    getBanner() {
        return axios.get(base.baseUrl + base.getBanner)
    },
    uploadFile(file) {
        return axios.post(base.baseUrl + base.uploadFile, file, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        })
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

    // item
    getItemById(params) {
        return axios.get(base.baseUrl + base.getItemById + "?" + qs.stringify(params));
    },
    addItem(params) {
        return axios.post(base.baseUrl + base.addItem, qs.stringify(params));
    },
    deleteItemById(params) {
        return axios.delete(base.baseUrl + base.deleteItemById + "?" + qs.stringify(params));
    },
    getItemsByCity(params) {
        return axios.get(base.baseUrl + base.getItemsByCity + "?" + qs.stringify(params));
    },
    updateItemStatusById(params) {
        return axios.post(base.baseUrl + base.updateItemStatusById + "?" + qs.stringify(params));
    },
    updateItemInfoById(params) {
        return axios.post(base.baseUrl + base.updateItemInfoById + "?" + qs.stringify(params));
    },
    updateItemShowcasesById(params) {
        return axios.post(base.baseUrl + base.updateItemShowcasesById + "?" + qs.stringify(params));
    },


    // comment
    addComment(params) {
        return axios.post(base.baseUrl + base.feedback, qs.stringify(params));
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
    deleteUserById(params) {
        return axios.post(base.baseUrl + base.deleteUserById, qs.stringify(params));
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