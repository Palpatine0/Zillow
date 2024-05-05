// store.js

import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        drawer: true,
        city: 'Dallas',
        img_prefix: 'http://111.231.19.137:8888/',
        links: [
            {icon: 'fa-lg fas fa-home', text: 'Dashboard', route: '/'},
            {icon: 'fa-lg fa-solid fa-user', text: 'Users', route: '/users'},
            {icon: 'fa-lg fa-solid fa-flask', text: 'Test', route: '/test'},
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
        }
    },
    actions: {
        // You can define actions here if needed
    },
    modules: {
        // You can define modules here if needed
    }
});
