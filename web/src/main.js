import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import Antd, { notification } from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css'
import * as Icons from '@ant-design/icons-vue'
import axios from 'axios'

const app = createApp(App)

app.use(Antd).use(store).use(router).mount('#app')

// 全局使用图标
const icons = Icons
for (const i in icons) {
  app.component(i, icons[i])
}

axios.defaults.baseURL = process.env.VUE_APP_BASE_API
console.log('环境：' + process.env.NODE_ENV)
console.log('请求地址：' + process.env.VUE_APP_BASE_API)
// axios 请求拦截
axios.interceptors.request.use(
  function (config) {
    console.log(`请求参数：${config}`)
    const token = store.state.member.token
    if (token) {
      config.headers.token = token
    }
    console.log(JSON.stringify(config))
    return config
  },
  (error) => {
    return Promise.reject(error)
  }
)
// axios 响应拦截
axios.interceptors.response.use(
  (response) => {
    console.log('响应参数：' + response)
    return response
  },
  (error) => {
    const response = error.response
    const status = response.status
    if (status === 401) {
      console.log(response)
      // 判断状态码是否是401,跳转到登录页面
      notification.error({
        message: '登录失效',
        description: '请重新登录'
      })
      store.commit('setMember', {})
      router.push('/login')
    }
    return Promise.reject(error)
  }
)
