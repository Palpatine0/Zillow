// store/index.js

import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        currentCity: 'Dallas',
        awsS3RequestUrl: "https://percival-s3-zillow.s3.us-east-1.amazonaws.com/",
        awsS3Paths: {
            item: 'public/image/item/',
        },
        searchMsg: '',
        collect: [],
        userId: '',
    },
    mutations: {
        setCity(state, payload) {
            state.currentCity = payload.data
        },
        setSearchMsg(state, payload) {
            state.searchMsg = payload.data
        },
        addCollect(state, payload) {
            state.collect.push(payload.data)
            state.collect = [...state.collect]
        },
        delCollect(state, payload) {
            let index = state.collect.indexOf(payload.data)
            if(index > -1) {
                state.collect.splice(index, 1)
            }
            state.collect = [...state.collect]
        },
        setUserId(state, payload) {
            state.userId = payload.data
        }
    },
    actions: {
        initializeStore({commit}) {
            if(localStorage.getItem('currentCity')) {
                commit('setCity', localStorage.getItem('currentCity'));
            }
        },
        setCity(state, city) {
            state.city = city;
            localStorage.setItem('city', city);
        },
        setCityAciton(context, payload) {
            context.commit('setCity', payload)
        },
        setSearchMsgAction(context, payload) {
            context.commit('setSearchMsg', payload)
        },
        addCollectAction(context, payload) {
            context.commit('addCollect', payload)
        },
        delCollectAction(context, payload) {
            context.commit('delCollect', payload)
        },
        setUserIdAction(context, payload) {
            context.commit('setUserId', payload)
        }
    },
    modules: {}
});
