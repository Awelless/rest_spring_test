import Vue from 'vue'
import Vuetify from 'vuetify'
import '@babel/polyfill'
import 'static/js/apiapi/resource'
import router from 'static/js/router/router'
import App from 'static/js/pages/App.vue'
import store  from 'static/js/store/store'
import { connect } from 'static/js/util/ws'
import 'vuetify/dist/vuetify.min.css'

if (profile) {
    connect()
}

Vue.use(Vuetify)

new Vue({
    el: '#app',
    store: store,
    router: router,
    vuetify: new Vuetify(),
    render: a => a(App)
})