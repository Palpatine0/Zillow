// router/index.js
import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home/Home.vue'
import Life from '../views/Life/Life.vue'
import Mine from '../views/Mine/Mine.vue'
import Register from "@/views/Register/Regiser.vue";
import Login from '../views/Login/Login.vue'
import Shop from '../views/Shop/Shop.vue'
import Shopcar from '../views/Shopcar/Shopcar.vue'
import City from '../views/City/City.vue'
import Details from '../views/Details/Details.vue'
import Search from '../views/Search/Search.vue'
import Buy from '../views/Buy/Buy'

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'home',
        component: Home
    },
    {
        path: '/life',
        name: 'life',
        component: Life
    },
    {
        path: '/mine',
        name: 'mine',
        component: Mine
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
        path: '/shop',
        name: 'shop',
        component: Shop
    },
    {
        path: '/shopcar',
        name: 'shopcar',
        component: Shopcar
    },
    {
        path: '/city',
        name: 'city',
        component: City
    },
    {
        path: '/details/:id',
        name: 'details',
        component: Details
    },
    {
        path: '/search/:content',
        name: 'search',
        component: Search
    },
    {
        path: '/buy/:id/:user',
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
