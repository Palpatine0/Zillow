// store/index.js

import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        cur_city: 'Dallas',
        searchMsg: '',
        collect: [],
        userId: '',
        img_prefix: 'http://111.231.19.137:8888/',
    },
    mutations: {
        setCity(state, payload) {
            state.cur_city = payload.data
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
            if (index > -1) {
                state.collect.splice(index, 1)
            }
            state.collect = [...state.collect]
        },
        setUserId(state, payload) {
            state.userId = payload.data
        }
    },
    actions: {
        setCityAciton(context, payload) {
            context.commit('setCity', payload)
        },
        setSearchMsgAciton(context, payload) {
            context.commit('setSearchMsg', payload)
        },
        addCollectAciton(context, payload) {
            context.commit('addCollect', payload)
        },
        delCollectAction(context, payload) {
            context.commit('delCollect', payload)
        },
        setUserIdAction(context, payload) {
            context.commit('setUid', payload)
        }
    },
    modules: {}
})
