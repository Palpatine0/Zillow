// store/index.js

import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        curcity: 'Dallas',
        searchMsg: '',
        collect: [],
        user: '',
        img_prefix: 'http://111.231.19.137:8888/',
    },
    mutations: {
        setCity(state, payload) {
            state.curcity = payload.data
        }, setSearchMsg(state, payload) {
            state.searchMsg = payload.data
        }, addCollect(state, payload) {
            state.collect.push(payload.data)
            state.collect = [...state.collect]
        }, delCollect(state, payload) {
            let index = state.collect.indexOf(payload.data)
            if (index > -1) {
                state.collect.splice(index, 1)
            }
            state.collect = [...state.collect]
        }, setUser(state, payload) {
            state.user = payload.data
        }
    },
    actions: {
        setCityAciton(context, payload) {
            context.commit('setCity', payload)
        }, setSearchMsgAciton(context, payload) {
            context.commit('setSearchMsg', payload)
        }, addCollectAciton(context, payload) {
            context.commit('addCollect', payload)
        }, delCollectAction(context, payload) {
            context.commit('delCollect', payload)
        }, setUserAction(context, payload) {
            context.commit('setUser', payload)
        }
    }, modules: {}
})
