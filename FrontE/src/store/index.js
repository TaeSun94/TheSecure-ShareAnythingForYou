import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

import member from '../store/modules/member.js'
import house from '../store/modules/house.js'
export default new Vuex.Store({
  state: {
    formValid : false,
  },
  mutations: {
    setFormValid(state,payload){
      state.formValid = payload
    }
  },
  actions: {
  },
  modules: {
    house,
    member
  }
})
