// index.js
import base from "./base"
import qs from "querystring"
import axios from 'axios'


const api = {

    // file
    getBanner() {
        return axios.get(base.baseUrl + base.getBanner)
    },
    uploadImageNoPrefix(file) {
        const formData = new FormData();
        formData.append('api-file', file);

        axios.post(base.baseUrl + base.uploadImageNoPrefix, formData)
            .then(response => {
                console.log('File uploaded successfully');
                console.log(response);
            })
            .catch(error => {
                console.error('Upload error:', error);
            });
    },

    //
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

    //
    buytime(params) {
        return axios.get(base.baseUrl + base.getBuyTime + '?id=' + params.id);
    },
    buyaction(params) {
        return axios.get(base.baseUrl + base.buyAction + "?" + qs.stringify(params));
    },

    // item
    getItem(params) {
        return axios.get(base.baseUrl + base.getItem + "?" + qs.stringify(params));
    },
    addItem(params) {
        return axios.post(base.baseUrl + base.addItem, params);
    },
    adminGetItemByCity(params) {
        return axios.get(base.baseUrl + base.getItem + "?" + qs.stringify(params));
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