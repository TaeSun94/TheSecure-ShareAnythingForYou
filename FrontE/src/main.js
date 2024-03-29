import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import swal from 'sweetalert'
Vue.config.productionTip = false
Vue.use(require('vue-cookies'))


new Vue({
  swal,
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
