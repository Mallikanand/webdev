import Vuex from 'vuex'
import Vue from 'vue'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    authenticated: false,
    user: null,
    orders: []
  },
  actions: {
    loginSuccess ({commit}, userDetails) {
      //xzxccccconsole.log(userDetails)
      if (userDetails) {
        commit('setAuthenticatedAndUserDetails', userDetails)
      }
    },
    logoutSuccess ({commit}){
      commit('unsetAuthentication')
      commit('deleteUserData')
    },
    setOrders ({commit},orders) {
      commit('saveOrders',orders)
    }
  },
  mutations: {
    setAuthenticatedAndUserDetails (state, userDetails) {
      state.authenticated = true
      state.user = userDetails
    },
    unsetAuthentication (state) {
      state.authenticated = false
      state.user = null
    },
    deleteUserData (state) {
      state.orders = []
    },
    saveOrders (state,orders) {
      state.orders = orders
    }
  },
  getters: {

  }
})
