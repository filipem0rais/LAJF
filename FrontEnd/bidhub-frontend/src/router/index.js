import { createRouter, createWebHashHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import IdentificationView from '../views/IdentificationView.vue'

const routes = [
  {
    path: '/',
    name: 'accueil',
    component: HomeView
  },
  {
    path: '/identification',
    name: 'identification',
    component: IdentificationView
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
