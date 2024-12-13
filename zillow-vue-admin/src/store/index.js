// store/index.js

import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        drawer: true,
        city: 'Los Angeles',
        awsS3RequestUrl: "https://percival-s3-zillow.s3.us-east-1.amazonaws.com/",
        awsS3ImagePaths: {
            item: 'public/image/item/'
        },
        cities: [
            'Dallas',
            'Cape Coral',
            'Beverly Hills'
        ],
        links: [
            {icon: 'fa-solid fa-layer-group', text: 'Dashboard', route: '/'},
            {icon: 'fas fa-laptop-house', text: 'Properties', route: '/properties'},
            {icon: 'fa-lg fa-solid fa-user', text: 'Users', route: '/users'},
        ]
    },
    mutations: {
        toggleDrawer(state) {
            state.drawer = !state.drawer;
        },
        setCity(state, city) {
            state.city = city;
            localStorage.setItem('city', city); // Save to local storage
        }
    },
    actions: {
        initializeStore({commit}) {
            if(localStorage.getItem('city')) {
                commit('setCity', localStorage.getItem('city'));
            }
        }
    },
    modules: {}
});
