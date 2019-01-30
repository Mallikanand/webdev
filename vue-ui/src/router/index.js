import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/pages/Login'
import Logout from '@/pages/Logout'

Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/login',
            name: 'Login',
            component: Login
        },{
            path: '/logout',
            name: 'Logout',
            component: Logout
        }
    ],
    mode: 'history'
})