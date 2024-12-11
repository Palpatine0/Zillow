import Vue from 'vue'
import VueRouter from 'vue-router'

import Properties from '../views/Properties/Properties.vue'
import Item from '../views/Properties/Item/Item.vue'
import Users from '../views/Users/Users.vue'

Vue.use(VueRouter)

const routes = [
    {
        path: '/properties',
        name: 'Properties',
        component: Properties
    },
    {
        path: '/item/:id',
        name: 'Details',
        component: Item
    },


    {
        path: '/users',
        name: 'Users',
        component: Users
    }


]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
