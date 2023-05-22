import { createRouter, createWebHashHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import IdentificationView from '../views/IdentificationView.vue'
import ConnectedView from '../views/ConnectedView.vue'

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
  },
  {
    path: '/connected',
    name: 'connected',
    component: ConnectedView
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
