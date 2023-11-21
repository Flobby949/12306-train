<template>
  <p>
    <a-space>
      <!-- 刷新 -->
      <a-button type="primary" @click="handleQuery()">刷新</a-button>
      <a-button type="priamry" @click="showModal">新增</a-button>
    </a-space>
  </p>

  <a-table :columns="columns" :data-source="passengerData" :pagination="pagination" @change="handlePageChange" />

  <!-- 新增修改对话框 -->
  <a-modal title="乘车人" :visible="visible" @ok="handleOk" ok-text="确认" cancel-text="取消">
    <a-form :form="form" :label-col="{ span: 4 }" :wrapper-col="{ span: 20 }">
      <a-form-item label="姓名">
        <a-input v-model:value="passenger.name" />
      </a-form-item>
      <a-form-item label="身份证">
        <a-input v-model:value="passenger.idCard" />
      </a-form-item>
      <a-form-item label="乘客类型">
        <a-select v-model:value="passenger.type">
          <a-select-option value="1">成人</a-select-option>
          <a-select-option value="2">儿童</a-select-option>
          <a-select-option value="3">学生</a-select-option>
        </a-select>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script setup>
import { onMounted, reactive, ref } from 'vue'
import axios from 'axios'
import { notification } from 'ant-design-vue'

const visible = ref(false)
const showModal = () => {
  visible.value = true
}

const passenger = reactive({
  name: '',
  idCard: '',
  type: ''
})

const handleOk = (e) => {
  axios.post('/member/passenger/save', passenger).then((res) => {
    if (res.success) {
      notification.success({
        message: '成功',
        description: '新增成功'
      })
      visible.value = false
    } else {
      notification.error({
        message: '失败',
        description: res.message
      })
    }
  })
}

const passengerData = ref([])

const columns = [
  {
    title: '姓名',
    dataIndex: 'name',
    key: 'name'
  },
  {
    title: '身份证',
    dataIndex: 'idCard',
    key: 'idCard'
  },
  {
    title: '乘客类型',
    dataIndex: 'type',
    key: 'type'
  }
]

const pagination = reactive({
  current: 1,
  pageSize: 1,
  total: 0
})

const handleQuery = (param) => {
  if (!param) {
    param = {
      page: 1,
      size: pagination.pageSize
    }
  }
  axios.get('/member/passenger/query', { params: param }).then((res) => {
    if (res.success) {
      passengerData.value = res.data.list
      pagination.total = res.data.total
      pagination.current = param.page
    } else {
      notification.error({
        message: '失败',
        description: res.message
      })
    }
  })
}

const handlePageChange = (page) => {
  handleQuery({
    page: page.current,
    size: page.pageSize
  })
}

onMounted(() => {
  handleQuery({
    page: 1,
    size: pagination.pageSize
  })
})
</script>

<style scoped></style>
