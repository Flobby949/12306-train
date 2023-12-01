<template>
  <a-layout-header class="header">
    <div class="logo" style="float: right; color: white">
      你好:{{ member.mobile }}
      <router-link to="/login" style="color: white">退出登录</router-link>
    </div>
    <a-menu v-model:selectedKeys="selectedKeys" theme="dark" mode="horizontal" :style="{ lineHeight: '64px' }">
      <a-menu-item key="/welcome">
        <CoffeeOutlined />
        <router-link to="/welcome">欢迎</router-link>
      </a-menu-item>
      <a-menu-item key="/passenger">
        <UserOutlined />
        <router-link to="/passenger">乘车人管理</router-link>
      </a-menu-item>
      <a-menu-item key="/ticket">
        <UserOutlined />
        <router-link to="/ticket">余票查询</router-link>
      </a-menu-item>
      <a-menu-item key="/my-ticket">
        <router-link to="/my-ticket">
          <idcard-outlined />
          我的车票
        </router-link>
      </a-menu-item>
      <a-menu-item key="/seat-sell">
        <router-link to="/seat-sell">
          <idcard-outlined />
          座位销售图
        </router-link>
      </a-menu-item>
    </a-menu>
  </a-layout-header>
</template>

<script setup>
import { ref, watch } from 'vue'
import store from '@/store'
import { CoffeeOutlined, UserOutlined } from '@ant-design/icons-vue'
import router from '@/router'

const member = store.state.member
const selectedKeys = ref([])

watch(
  () => router.currentRoute.value.path,
  (newValue) => {
    selectedKeys.value = []
    selectedKeys.value.push(newValue)
  },
  { immediate: true }
)
</script>

<style scoped>
.logo {
  color: #fff;
}
</style>
