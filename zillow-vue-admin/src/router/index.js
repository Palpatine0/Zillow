import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Dashboard/Dashboard.vue'
import Users from '../views/Users/Users.vue'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home
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
