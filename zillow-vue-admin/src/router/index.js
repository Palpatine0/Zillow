import Vue from 'vue'
import VueRouter from 'vue-router'
import Dashboard from '../views/Dashboard/Dashboard.vue'
import Item from '../views/Dashboard/Item/Item.vue'
import Users from '../views/Users/Users.vue'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Dashboard',
        component: Dashboard
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
