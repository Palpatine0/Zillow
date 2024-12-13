// index.js
import base from "./base"
import qs from "querystring"
import axios from 'axios'


const api = {

    // file
    file: {
        uploadFile(file) {
            return axios.post(base.baseUrl + base.file.uploadFile, file, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            })
        },
    },

    // item
    item: {
        getItemById(params) {
            return axios.get(base.baseUrl + base.item.getItemById + "?" + qs.stringify(params));
        },
        addItem(params) {
            return axios.post(base.baseUrl + base.item.addItem, qs.stringify(params));
        },
        deleteItemById(params) {
            return axios.delete(base.baseUrl + base.item.deleteItemById + "?" + qs.stringify(params));
        },
        getItemsByCity(params) {
            return axios.get(base.baseUrl + base.item.getItemsByCity + "?" + qs.stringify(params));
        },
        updateItemStatusById(params) {
            return axios.post(base.baseUrl + base.item.updateItemStatusById + "?" + qs.stringify(params));
        },
        updateItemInfoById(params) {
            return axios.post(base.baseUrl + base.item.updateItemInfoById + "?" + qs.stringify(params));
        },
        updateItemShowcasesById(params) {
            return axios.post(base.baseUrl + base.item.updateItemShowcasesById + "?" + qs.stringify(params));
        },
    },


    // user
    user: {
        login(params) {
            return axios.post(base.baseUrl + base.user.login, params);
        },
        register(params) {
            return axios.post(base.baseUrl + base.user.register, params);
        },
        getUser(params) {
            return axios.get(base.baseUrl + base.user.getUser, params);
        },
        deleteUserById(params) {
            return axios.post(base.baseUrl + base.user.deleteUserById, qs.stringify(params));
        },
    },

    // order
    order: {
        getOrder(params) {
            return axios.get(base.baseUrl + base.order.getOrders + "?" + qs.stringify(params));
        },
    },

    // llm
    llm: {
        getConstructionStatValues(params) {
            return axios.get(base.baseUrl + base.llm.getConstructionStatValues + "?" + qs.stringify(params));
        }
    }

}

export default api;