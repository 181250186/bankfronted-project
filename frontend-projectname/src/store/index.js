import Vue from 'vue'
import Vuex from 'vuex'
import chart from './modules/chart'
import node from './modules/node'
import edge from './modules/edge'
import getters from './getters'
Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    chart,
    edge,
    node,
  },
  state: {
  },
  mutations: {
  },
  actions: {
  },
  getters
})
