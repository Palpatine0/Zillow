// store/index.js

import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        currentCity: 'Beverly Hills',
        awsS3RequestUrl: "https://percival-s3-zillow.s3.us-east-1.amazonaws.com/",
        awsS3ImagePaths: {
            item: 'public/image/item/',
            user: 'public/image/user/',
        },
        searchContent: '',
        collect: [],
        userId: '',
        user: '',
    },
    mutations: {
        setCity(state, payload) {
            state.currentCity = payload.data
        },
        setSearchContent(state, payload) {
            state.searchContent = payload.data
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

        setUser(state, payload) {
            state.user = payload; // Update Vuex state
            localStorage.setItem('user', JSON.stringify(payload)); // Store as JSON
        },
        clearUser(state) {
            state.user = null;
            localStorage.removeItem('user');
        }
    },
    actions: {
        initializeStore({commit}) {
            const currentCity = localStorage.getItem('currentCity');
            if(currentCity) {
                commit('setCity', { data: currentCity });
            }
            const user = localStorage.getItem('user');
            if (user) {
                commit('setUser', JSON.parse(user));
            }
        },
        setCity({commit}, city) {
            commit('setCity', { data: city });
            localStorage.setItem('currentCity', city);
        },
        setCityAciton(context, payload) {
            context.commit('setCity', payload)
        },
        setSearchContentAction(context, payload) {
            context.commit('setSearchContent', payload)
        },
        addCollectAction(context, payload) {
            context.commit('addCollect', payload)
        },
        delCollectAction(context, payload) {
            context.commit('delCollect', payload)
        },

        setUserAction(context, payload) {
            context.commit('setUser', payload)
        },
        clearUserAction({commit}) {
            commit('clearUser');
        }
    },
    modules: {}
});
