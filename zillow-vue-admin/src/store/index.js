// store.js

import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        drawer: true,
        links: [
            {icon: 'fa-lg fas fa-home', text: 'Dashboard', route: '/'},
            {icon: 'fa-lg fa-solid fa-user', text: 'Users', route: '/users'},
            {icon: 'fa-lg fa-solid fa-flask', text: 'Test', route: '/test'},
        ],
    },
    mutations: {
        toggleDrawer(state) {
            // Toggle the value of 'drawer'
            state.drawer = !state.drawer;
        }
    },
    actions: {
        // You can define actions here if needed
    },
    modules: {
        // You can define modules here if needed
    }
});
