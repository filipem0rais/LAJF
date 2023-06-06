import { createRouter, createWebHashHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import IdentificationView from '../views/IdentificationView.vue'
import ConnectedView from '../views/ConnectedView.vue'
import EncheresView from '@/views/EncheresView.vue'
import LotDetailsView from '@/views/LotDetailsView.vue'
import VenteView from '@/views/VenteView.vue'

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
  },
  {
    path: '/encheres',
    name: 'encheres',
    component: EncheresView
  },
  {
    path: '/lot/:id',
    name: 'lot-details',
    component: LotDetailsView
  },
  {
    path: '/vente',
    name: 'vente',
    component: VenteView
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})
router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('token')
  if (to.name === 'vente' && token === null) {
    next('/identification')
  } else {
    next()
  }
})

export default router
