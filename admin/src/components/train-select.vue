<template>
  <a-select
    v-model:value="trainCode"
    show-search
    allowClear
    :filterOption="filterTrainCodeOption"
    @change="onChange"
    placeholder="请选择车次"
    :style="'width: ' + _width"
  >
    <a-select-option
      v-for="item in trains"
      :key="item.code"
      :value="item.code"
      :label="item.code + item.start + item.end"
    >
      {{ item.code }} {{ item.start }} ~ {{ item.end }}
    </a-select-option>
  </a-select>
</template>

<script setup>
import { ref, onMounted, watch, defineProps, defineEmits } from 'vue'
import axios from 'axios'
import { notification } from 'ant-design-vue'
const emit = defineEmits(['update:modelValue', 'change'])
const trainCode = ref()
const trains = ref([])

const props = defineProps({
  width: {
    type: String,
    default: '100%'
  },
  modelValue: {
    type: String,
    default: ''
  }
})

const _width = ref(props.width)
if (Tool.isEmpty(_width.value)) {
  _width.value = '100%'
}

// 利用watch，动态获取父组件的值，如果放在onMounted或其它方法里，则只有第一次有效
watch(
  () => props.modelValue,
  () => {
    console.log('props.modelValue', props.modelValue)
    trainCode.value = props.modelValue
  },
  { immediate: true }
)

/**
 * 查询所有的车次，用于车次下拉框
 */
const queryAllTrain = () => {
  const list = SessionStorage.get(SESSION_ALL_TRAIN)
  if (Tool.isNotEmpty(list)) {
    console.log('queryAllTrain 读取缓存')
    trains.value = list
  } else {
    axios.get('/business/admin/train/query-all').then((data) => {
      if (data.success) {
        trains.value = data.data
        console.log('queryAllTrain 保存缓存')
        SessionStorage.set(SESSION_ALL_TRAIN, JSON.stringify(trains.value))
      } else {
        notification.error({ description: data.message })
      }
    })
  }
}

/**
 * 车次下拉框筛选
 */
const filterTrainCodeOption = (input, option) => {
  console.log(input, option)
  return option.label.toLowerCase().indexOf(input.toLowerCase()) >= 0
}

/**
 * 将当前组件的值响应给父组件
 * @param value
 */
const onChange = (value) => {
  emit('update:modelValue', value)
  let train = trains.value.filter((item) => item.code === value)[0]
  if (Tool.isEmpty(train)) {
    train = {}
  }
  emit('change', train)
}

onMounted(() => {
  queryAllTrain()
})
</script>
