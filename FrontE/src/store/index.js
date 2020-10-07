import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

import member from '../store/modules/member.js'
import house from '../store/modules/house.js'
import reservation from '../store/modules/reservation.js'
import transaction from '../store/modules/transaction.js'

export default new Vuex.Store({
  state: {
    formValid : false,
    pwdDialog : false,
  },
  mutations: {
    setFormValid(state,payload){
      state.formValid = payload
    },
    setpwdDialog(state,payload){
      state.pwdDialog = payload
    }
  },
  actions: {
  },
  modules: {
    house,
    member,
    reservation,
    transaction
  }
})
