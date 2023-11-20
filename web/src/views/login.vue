<template>
  <a-row class="login">
    <a-col :span="8" :offset="8" class="login-main">
      <h1 style="text-align: center">
        <rocket-two-tone />
        12306售票系统
      </h1>
      <a-form :model="loginForm" name="basic" autocomplete="off" @finish="onFinish" @finishFailed="onFinishFailed">
        <a-form-item label="手机号" name="mobile" :rules="[{ required: true, message: '请输入手机号' }]">
          <a-input v-model:value="loginForm.mobile" placeholder="请输入手机号" />
        </a-form-item>
        <a-form-item label="验证码" name="code" :rules="[{ required: true, message: '请输入验证码' }]">
          <a-input v-model:value="loginForm.code" placeholder="请输入验证码">
            <template #addonAfter>
              <a-button type="primary" @click="sendCode">获取验证码</a-button>
            </template>
          </a-input>
        </a-form-item>
        <a-form-item>
          <a-button type="primary" block html-type="submit" @click="login"> 登录 </a-button>
        </a-form-item>
      </a-form>
    </a-col>
  </a-row>
</template>

<script setup>
import { reactive } from 'vue'
import axios from 'axios'
import { notification } from 'ant-design-vue'
import { useRouter } from 'vue-router'
import store from '@/store'
const router = useRouter()

const loginForm = reactive({
  mobile: '18962521753',
  code: '123456'
})

const onFinish = (values) => {
  console.log('Success:', values)
}

const onFinishFailed = (errorInfo) => {
  console.log('Failed:', errorInfo)
}

const sendCode = () => {
  axios
    .post('/member/member/code', {
      mobile: loginForm.mobile
    })
    .then((res) => {
      const data = res.data
      if (data.success) {
        notification.success({
          message: '发送成功',
          description: '验证码发送成功，请注意查收'
        })
        loginForm.code = '8888'
      } else {
        notification.error({
          message: '发送失败',
          description: data.message
        })
      }
    })
}

const login = () => {
  axios
    .post('/member/member/login', {
      mobile: loginForm.mobile,
      code: loginForm.code
    })
    .then((res) => {
      const data = res.data
      if (data.success) {
        notification.success({
          description: '登录成功'
        })
        router.push('/')
        console.log(data.data)
        store.commit('setMember', data.data)
      } else {
        notification.error({
          description: data.message
        })
      }
    })
}
</script>

<style scoped>
.login-main h1 {
  font-size: 25px;
  font-weight: bold;
}

.login-main {
  margin-top: 100px;
  padding: 30px 30px 20px;
  border: 2px solid grey;
  border-radius: 10px;
  background-color: #fcfcfc;
}
</style>
