// index.js
import base from "./base"
import qs from "querystring"
import axios from 'axios'


const api = {


    // file
    file: {
        getBanners() {
            return axios.get(base.baseUrl + base.file.getBanners)
        },
        uploadFile(file) {
            return axios.post(base.baseUrl + base.file.uploadFile, file, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            })
        },
    },

    // trendy
    trendy: {
        getTrendies(params) {
            return axios.get(base.baseUrl + base.trendy.getTrendies + "?city=" + params.city);
        },
    },

    // recommendation
    recommendation: {
        getRecommendations(params) {
            return axios.get(base.baseUrl + base.recommendation.getRecommendations + "?city=" + params.city);
        },
    },

    // search
    search: {
        searchByKeyWord(params) {
            return axios.get(base.baseUrl + base.search.searchByKeyWord + "?" + qs.stringify(params));
        },
        searchByCity(params) {
            return axios.get(base.baseUrl + base.search.searchByCity + "?" + qs.stringify(params));
        },
    },

    // buyAction
    buyAction: {
        buyAction(params) {
            return axios.get(base.baseUrl + base.buyAction.buyAction + "?" + qs.stringify(params));
        },
    },

    // item
    item: {
        getItemById(params) {
            return axios.get(base.baseUrl + base.item.getItemById + "?" + qs.stringify(params));
        },
    },

    // comment
    comment: {
        addComment(params) {
            return axios.post(base.baseUrl + base.comment.addComment + "?" + qs.stringify(params));
        },
        getCommentsByItemId(params) {
            return axios.get(base.baseUrl + base.comment.getCommentsByItemId + "?" + qs.stringify(params));
        },
    },

    // user
    user: {
        login(params) {
            return axios.post(base.baseUrl + base.user.login, qs.stringify(params));
        },
        register(params) {
            return axios.post(base.baseUrl + base.user.register, qs.stringify(params));
        },
        getUserByUsername(params) {
            return axios.get(base.baseUrl + base.user.getUserByUsername + "?" + qs.stringify(params));
        },
    },


    // order
    order: {
        getOrdersByUserId(params) {
            return axios.get(base.baseUrl + base.order.getOrdersByUserId + "?" + qs.stringify(params));
        },
    },

    // llm
    llm: {
        chat(params) {
            return axios.get(base.baseUrl + base.llm.chat + "?" + qs.stringify(params));
        }
    }
}

export default api;