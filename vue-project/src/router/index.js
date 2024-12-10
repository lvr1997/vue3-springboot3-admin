import { createRouter, createWebHistory } from 'vue-router'
import Manager from "@/views/Manager.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      redirect: '/manager/home'
    },
    {
      path: '/login',
      name: 'Login',
      component: () => import('@/views/Login.vue')
    },
    {
      path: '/manager',
      name: 'Manager',
      component: Manager,
      children: [
        { path: 'home', name: 'Home', meta: { title: '首页' }, component: () => import('@/views/Home.vue') },
        { path: 'employee', name: 'Employee', meta: { title: '员工管理' }, component: () => import('@/views/Employee.vue') },
        { path: 'data', name: 'Data', meta: { title: '数据统计' }, component: () => import('@/views/Data.vue') },
      ]
    }
  ],
})

export default router
