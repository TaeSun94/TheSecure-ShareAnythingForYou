import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

Vue.config.productionTip = false

window.Kakao.init('8ec24047f36c31765ee62cb1766ec26a')

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
