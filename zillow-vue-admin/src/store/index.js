// store.js

import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        drawer: true,
        city: 'Los Angeles',
        awsS3RequestUrl: "https://percival-s3-zillow.s3.us-east-1.amazonaws.com/",
        awsS3Paths: {
            item: 'public/image/item/'
        },
        links: [
            {icon: 'fa-lg fas fa-home', text: 'Dashboard', route: '/'},
            {icon: 'fa-lg fa-solid fa-user', text: 'Users', route: '/users'},
            // {icon: 'fa-lg fa-solid fa-flask', text: 'Test', route: '/test'},
        ],
        cities: [
            'Dallas',
            'Cape Coral',
            'Beverly Hills'
        ],
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
    modules: {
        // You can define modules here if needed
    }
});
