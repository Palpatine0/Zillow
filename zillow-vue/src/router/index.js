// router/index.js
import Vue from 'vue'
import VueRouter from 'vue-router'

import Index from '../views/Index/Index.vue'
import Register from "@/views/Register/Regiser.vue";
import Login from '../views/Login/Login.vue'
import Homes from '../views/Homes/Homes.vue'
import Mine from '../views/Mine/Mine.vue'
import City from '../views/Index/City/City.vue'
import Item from '../views/Item/Item.vue'
import Search from '../views/Index/Search/Search.vue'
import Buy from '../views/Buy/Buy'

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'index',
        component: Index
    },
    {
        path: '/login',
        name: 'login',
        component: Login
    },
    {
        path: '/Register',
        name: 'register',
        component: Register
    },
    {
        path: '/homes',
        name: 'homes',
        component: Homes
    },
    {
        path: '/mine',
        name: 'mine',
        component: Mine
    },
    {
        path: '/city',
        name: 'city',
        component: City
    },
    {
        path: '/item/:id',
        name: 'item',
        component: Item
    },
    {
        path: '/search/:content',
        name: 'search',
        component: Search
    },
    {
        path: '/buy/:itemId/:userId/:startDate/:endDate/:price',
        name: 'buy',
        component: Buy
    }
]

const router = new VueRouter({
    mode: 'history',
    linkActiveClass: 'active',
    routes
})

export default router
