import Vue from 'vue'
import Router from 'vue-router'
import Dashboard from './views/Dashboard.vue'
import Projects from './views/Projects.vue'
import Users from './views/Users/Users.vue'
import Test from './views/Test/Test.vue'

Vue.use(Router)

export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
        {
            path: '/projects',
            name: 'projects',
            component: Projects
        },
        {
            path: '/',
            name: 'dashboard',
            component: Dashboard
        },
        {
            path: '/users',
            name: 'users',
            component: Users
        },
        {
            path: '/test',
            name: 'test',
            component: Test
        }

    ]
})
