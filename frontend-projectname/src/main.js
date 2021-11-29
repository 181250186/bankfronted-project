import Vue from 'vue';
import Vuex from 'vuex'
import store from './store'
Vue.config.productionTip = false;

import App from './App.vue';
import router from './router/index';

import iView from 'iview';
import 'iview/dist/styles/iview.css';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './js/directives'
Vue.use(ElementUI);

new Vue({
  el: '#app',
  router,
  render: h => h(App) ,
  store,
});
Vue.use(iView);
Vue.use(Vuex);

import Router from 'vue-router';

Vue.use(Router);

new Vue({
  router: router,
  store,
  render: h => h(App)
}).$mount('#app')







