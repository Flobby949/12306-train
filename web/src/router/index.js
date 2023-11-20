import store from '@/store'
import { notification } from 'ant-design-vue/lib'
import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    component: () => import('../views/main.vue'),
    meta: {
      loginRequire: true
    }
  },
  {
    path: '/login',
    component: () => import('../views/login.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

// 路由登录拦截
router.beforeEach((to, from, next) => {
  if (to.matched.some((item) => item.meta.loginRequire)) {
    const member = store.state.member
    if (member.token) {
      next()
    } else {
      notification.error({
        message: '登录失效',
        description: '请重新登录'
      })
      next({
        path: '/login'
      })
    }
  } else {
    next()
  }
})

export default router
