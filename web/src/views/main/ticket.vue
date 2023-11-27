<template>
  <p>
    <a-space>
      <train-select v-model:value="params.trainCode" />
      <a-date-picker v-model:value="params.date" valueFormat="YYYY-MM-DD" placeholder="请选择日期" />
      <station-select v-model:value="params.start" />
      <station-select v-model:value="params.end" />
      <a-button type="primary" @click="handleQuery()">查询</a-button>
      <a-button type="primary" @click="onAdd">新增</a-button>
    </a-space>
  </p>
  <a-table
    :dataSource="dailyTrainTickets"
    :columns="columns"
    :pagination="pagination"
    @change="handleTableChange"
    :loading="loading"
  >
    <template #bodyCell="{ column, record }">
      <template v-if="column.dataIndex === 'operation'"> </template>
      <template v-else-if="column.dataIndex === 'station'">
        {{ record.start }}<br />
        {{ record.end }}
      </template>
      <template v-else-if="column.dataIndex === 'time'">
        {{ record.startTime }}<br />
        {{ record.endTime }}
      </template>
      <template v-else-if="column.dataIndex === 'duration'">
        {{ calDuration(record.startTime, record.endTime) }}<br />
        <div v-if="record.startTime.replaceAll(':', '') >= record.endTime.replaceAll(':', '')">次日到达</div>
        <div v-else>当日到达</div>
      </template>
      <template v-else-if="column.dataIndex === 'ydz'">
        <div v-if="record.ydz >= 0">
          {{ record.ydz }}<br />
          {{ record.ydzPrice }}￥
        </div>
        <div v-else>--</div>
      </template>
      <template v-else-if="column.dataIndex === 'edz'">
        <div v-if="record.edz >= 0">
          {{ record.edz }}<br />
          {{ record.edzPrice }}￥
        </div>
        <div v-else>--</div>
      </template>
      <template v-else-if="column.dataIndex === 'rw'">
        <div v-if="record.rw >= 0">
          {{ record.rw }}<br />
          {{ record.rwPrice }}￥
        </div>
        <div v-else>--</div>
      </template>
      <template v-else-if="column.dataIndex === 'yw'">
        <div v-if="record.yw >= 0">
          {{ record.yw }}<br />
          {{ record.ywPrice }}￥
        </div>
        <div v-else>--</div>
      </template>
    </template>
  </a-table>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { notification } from 'ant-design-vue'
import axios from 'axios'
import trainSelect from '@/components/train-select'
import stationSelect from '@/components/station-select'
import dayjs from 'dayjs'

const dailyTrainTickets = ref([])
// 分页的三个属性名是固定的
const pagination = ref({
  total: 0,
  current: 1,
  pageSize: 10
})
const loading = ref(false)
const columns = [
  {
    title: '日期',
    dataIndex: 'date',
    key: 'date'
  },
  {
    title: '车次编号',
    dataIndex: 'trainCode',
    key: 'trainCode'
  },
  {
    title: '车站',
    dataIndex: 'station'
  },
  {
    title: '时间',
    dataIndex: 'time'
  },
  {
    title: '历时',
    dataIndex: 'duration'
  },
  {
    title: '一等座',
    dataIndex: 'ydz',
    key: 'ydz'
  },
  {
    title: '二等座',
    dataIndex: 'edz',
    key: 'edz'
  },
  {
    title: '软卧',
    dataIndex: 'rw',
    key: 'rw'
  },
  {
    title: '硬卧',
    dataIndex: 'yw',
    key: 'yw'
  }
]
const params = ref({
  trainCode: null,
  date: null,
  start: null,
  end: null
})

const handleQuery = (param) => {
  if (!param) {
    param = {
      page: 1,
      size: pagination.value.pageSize
    }
  }
  loading.value = true
  axios
    .get('/business/daily-train-ticket/query-all', {
      params: {
        page: param.page,
        size: param.size,
        trainCode: params.value.trainCode,
        date: params.value.date,
        start: params.value.start,
        end: params.value.end
      }
    })
    .then((data) => {
      loading.value = false
      if (data.success) {
        dailyTrainTickets.value = data.data.list
        // 设置分页控件的值
        pagination.value.current = param.page
        pagination.value.total = data.data.total
      } else {
        notification.error({ description: data.message })
      }
    })
}

const handleTableChange = (pagination) => {
  handleQuery({
    page: pagination.current,
    size: pagination.pageSize
  })
}

const calDuration = (startTime, endTime) => {
  const diff = dayjs(endTime, 'HH:mm:ss').diff(dayjs(startTime, 'HH:mm:ss'), 'seconds')
  return dayjs('00:00:00', 'HH:mm:ss').second(diff).format('HH:mm:ss')
}

onMounted(() => {
  handleQuery({
    page: 1,
    size: pagination.value.pageSize
  })
})
</script>
