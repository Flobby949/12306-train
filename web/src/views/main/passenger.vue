<template>
  <p>
    <a-space>
      <!-- 刷新 -->
      <a-button type="primary" @click="handleQuery()">刷新</a-button>
      <a-button type="primary" @click="onAdd">新增</a-button>
    </a-space>
  </p>

  <a-table :columns="columns" :data-source="passengerData" :pagination="pagination" @change="handlePageChange">
    <template #bodyCell="{ column, record }">
      <template v-if="column.dataIndex === 'operation'">
        <a-space>
          <a-space>
            <!-- 删除确认 -->
            <a-popconfirm
              title="删除后不可恢复，是否删除"
              ok-text="确认"
              cancel-text="取消"
              @confirm="onDelete(record)"
            >
              <a style="color: red">删除</a>
            </a-popconfirm>
          </a-space>
          <a @click="onEdit(record)">编辑</a>
        </a-space>
      </template>
      <template v-else-if="column.dataIndex === 'type'">
        <span v-for="item in PASSENGER_TYPE_ARRAY" :key="item.key">
          <span v-if="item.key === record.type">{{ item.label }}</span>
        </span>
      </template>
    </template>
  </a-table>

  <!-- 新增修改对话框 -->
  <a-modal title="乘车人" :visible="visible" @ok="handleOk" @cancel="visible = false" ok-text="确认" cancel-text="取消">
    <a-form :form="form" :label-col="{ span: 4 }" :wrapper-col="{ span: 20 }">
      <a-form-item label="姓名">
        <a-input v-model:value="passenger.name" />
      </a-form-item>
      <a-form-item label="身份证">
        <a-input v-model:value="passenger.idCard" />
      </a-form-item>
      <a-form-item label="乘客类型">
        <a-select v-model:value="passenger.type">
          <a-select-option v-for="item in PASSENGER_TYPE_ARRAY" :key="item.key" :value="item.key">
            {{ item.label }}
          </a-select-option>
        </a-select>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import axios from 'axios'
import { notification } from 'ant-design-vue'

const PASSENGER_TYPE_ARRAY = window.PASSENGER_TYPE_ARRAY

const visible = ref(false)
const onAdd = () => {
  visible.value = true
}

const onEdit = (record) => {
  passenger.value = window.Tool.copy(record)
  visible.value = true
}

const passenger = ref({
  name: '',
  idCard: '',
  type: ''
})

const handleOk = () => {
  axios.post('/member/passenger/save', passenger.value).then((res) => {
    if (res.success) {
      notification.success({
        message: '成功',
        description: '新增成功'
      })
      handleQuery()
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
  },
  {
    title: '操作',
    dataIndex: 'operation'
  }
]

const pagination = ref({
  current: 1,
  pageSize: 10,
  total: 0
})

const handleQuery = (param) => {
  if (!param) {
    param = {
      page: 1,
      size: pagination.value.pageSize
    }
  }
  axios.get('/member/passenger/query', { params: param }).then((res) => {
    if (res.success) {
      passengerData.value = res.data.list
      pagination.value.total = res.data.total
      pagination.value.current = param.page
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

const onDelete = (record) => {
  axios.delete('/member/passenger/delete/' + record.id).then((res) => {
    if (res.success) {
      notification.success({
        message: '成功',
        description: '删除成功'
      })
      handleQuery({
        page: pagination.value.current,
        size: pagination.value.pageSize
      })
    } else {
      notification.error({
        message: '失败',
        description: res.message
      })
    }
  })
}

onMounted(() => {
  handleQuery({
    page: 1,
    size: pagination.value.pageSize
  })
})
</script>

<style scoped></style>
