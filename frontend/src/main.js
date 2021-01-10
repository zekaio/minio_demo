import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'

Vue.config.productionTip = false

Vue.prototype.$goTo = function(path) {
  this.$router.push({
    path
  })
}

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
