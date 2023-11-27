<template>
  <a-layout-sider width="200" style="background: #fff">
    <a-menu v-model:selectedKeys="selectedKeys" mode="inline" :style="{ height: '100%', borderRight: 0 }">
      <a-menu-item key="/welcome">
        <CoffeeOutlined />
        <router-link to="/welcome">欢迎</router-link>
      </a-menu-item>
      <a-menu-item key="/ticket">
        <UserOutlined />
        <router-link to="/ticket">余票查询</router-link>
      </a-menu-item>

      <a-menu-item key="/passenger">
        <UserOutlined />
        <router-link to="/passenger">乘车人管理</router-link>
      </a-menu-item>
      <a-menu-item key="/my-ticket">
        <router-link to="/my-ticket">
          <idcard-outlined />
          我的车票
        </router-link>
      </a-menu-item>
    </a-menu>
  </a-layout-sider>
</template>

<script setup>
import { ref, watch } from 'vue'
import router from '@/router'

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

<style scoped></style>
