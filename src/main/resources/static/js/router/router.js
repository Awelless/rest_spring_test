import Vue from 'vue'
import VueRouter from 'vue-router'
import MessageList from 'static/js/pages/MessageList.vue'
import Auth from 'static/js/pages/Auth.vue'
import Profile from 'static/js/pages/Profile.vue'
import Subscriptions from 'static/js/pages/Subscriptions.vue'

Vue.use(VueRouter)

const routes = [
    { path: '/', component: MessageList },
    { path: '/auth', component: Auth },
    { path: '/user/:id?', component: Profile },
    { path: '/subscriptions/:id', component: Subscriptions },
    { path: '*', component: MessageList }
]

export default new VueRouter({
    mode: 'history',
    routes
})