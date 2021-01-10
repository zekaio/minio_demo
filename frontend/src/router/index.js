import Vue from 'vue'
import VueRouter from 'vue-router'
import Index from '../views/Index.vue'
import Show from '../views/Show.vue'
import Upload from '../views/Upload.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Index',
    component: Index
  },
  {
    path: '/show',
    name: 'Show',
    component: Show
  },
  {
    path: '/upload',
    name: 'Upload',
    component: Upload
  }
]

const router = new VueRouter({
  routes
})

export default router
