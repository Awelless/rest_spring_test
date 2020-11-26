import Vue from 'vue'
import VueRouter from 'vue-router'
import MessageList from 'pages/MessageList.vue'
import Auth from 'pages/Auth.vue'
import Profile from 'pages/Profile.vue'

Vue.use(VueRouter)

const routes = [
    { path: '/', component: MessageList },
    { path: '/auth', component: Auth },
    { path: '/profile', component: Profile },
    { path: '*', component: MessageList }
]

export default new VueRouter({
    mode: 'history',
    routes
})