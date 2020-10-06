import Vue from 'vue'
import VueRouter from 'vue-router'
import Cover from '../views/Cover.vue'
import Login from '../components/Login.vue'
import Home from '../views/Home.vue'
import Sharing from '../views/Sharing.vue'
import HomeContents from '../views/HomeContents.vue'
import Reservation from '../views/Reservation.vue'
import FAQ from '../views/FAQ.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Cover',
    component: Cover
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/home',
    // name: 'Home',
    component: Home,
    children:[
      {
        path: '/',
        name: 'HomeContents',
        component: HomeContents
      },
      {
        path: '/sharing',
        name: 'Sharing',
        component: Sharing
      },
      {
        path: '/reservation',
        name: 'Reservation',
        component: Reservation
      },
      {
        path: '/reservation/:house_id',
        name: 'Reservation_detail',
        component: () => import('../views/Reservation_detail.vue')
      },
      {
        path: '/profile',
        name: 'Profile',
        component: () => import('../views/Profile.vue')
      },
      {
        path: '/faq',
        name: 'FAQ',
        component: FAQ
      },
    ]
  },
  ,
  // {
  //   path: '/about',
  //   name: 'About',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  // }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
