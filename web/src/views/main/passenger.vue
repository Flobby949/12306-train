<template>
  <a-button type="priamry" @click="showModal">新增</a-button>
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
import { reactive, ref } from 'vue'
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
</script>

<style scoped></style>
