<template>
  <a-layout id="components-layout-demo-top-side-2">
    <the-header-view></the-header-view>
    <a-layout>
      <the-sider-view></the-sider-view>
      <a-layout style="padding: 0 24px 24px">
        <a-breadcrumb style="margin: 16px 0">
          <a-breadcrumb-item>Home</a-breadcrumb-item>
          <a-breadcrumb-item>List</a-breadcrumb-item>
          <a-breadcrumb-item>App</a-breadcrumb-item>
        </a-breadcrumb>
        <a-layout-content
          :style="{
            background: '#fff',
            padding: '24px',
            margin: 0,
            minHeight: '280px'
          }"
        >
          <a-select v-model="choose" style="width: 120px" @change="print" placeholder="请选择">
            <a-select-option v-for="item in enums" :key="item.type" :value="item.type">{{ item.desc }}</a-select-option>
          </a-select>
          {{ choose }}
        </a-layout-content>
      </a-layout>
    </a-layout>
  </a-layout>
</template>

<script setup>
import TheHeaderView from '@/components/the-header.vue'
import TheSiderView from '@/components/the-sider.vue'
import { ref } from 'vue'
import axios from 'axios'
const count = ref(0)

const getCount = () => {
  axios.get('/member/member/count').then((data) => {
    if (data.success) {
      count.value = data.data
    }
  })
}

// getCount()
const choose = ref(1)
const enums = [
  {
    type: 1,
    desc: '计算机类'
  },
  {
    type: 2,
    desc: '文学类'
  },
  {
    type: 3,
    desc: '历史类'
  }
]
const print = (e) => {
  console.log(e)
  choose.value = e
}
</script>

<style scoped>
#components-layout-demo-top-side-2 .logo {
  float: left;
  width: 120px;
  height: 31px;
  margin: 16px 24px 16px 0;
  background: rgba(255, 255, 255, 0.3);
}

.ant-row-rtl #components-layout-demo-top-side-2 .logo {
  float: right;
  margin: 16px 0 16px 24px;
}

.site-layout-background {
  background: #fff;
}
</style>
