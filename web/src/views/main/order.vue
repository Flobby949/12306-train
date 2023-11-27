<template>
  <div class="order-train">
    <span class="order-train-main">{{ dailyTrainTicket.date }}</span
    >&nbsp; <span class="order-train-main">{{ dailyTrainTicket.trainCode }}</span
    >次&nbsp; <span class="order-train-main">{{ dailyTrainTicket.start }}</span
    >站&nbsp; <span class="order-train-main">({{ dailyTrainTicket.startTime }})</span>&nbsp;
    <span class="order-train-main"> —— </span>&nbsp; <span class="order-train-main">{{ dailyTrainTicket.end }}</span
    >站&nbsp; <span class="order-train-main">({{ dailyTrainTicket.endTime }})</span>&nbsp;

    <div class="order-train-ticket">
      <span v-for="item in seatTypes" :key="item.type">
        <span>{{ item.desc }}</span>
        <span class="order-train-ticket-main">{{ item.price }}￥</span>
        <span class="order-train-ticket-main" style="color: green">{{ item.count }}</span
        >张票
      </span>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
const dailyTrainTicket = ref({})
dailyTrainTicket.value = JSON.parse(SessionStorage.get(SESSION_ORDER)) || {}
console.log(dailyTrainTicket.value)

const SEAT_TYPE = window.SEAT_TYPE

const seatTypes = []
for (const KEY in SEAT_TYPE) {
  const key = KEY.toLowerCase()
  if (dailyTrainTicket.value[key] >= 0) {
    seatTypes.push({
      type: KEY,
      code: SEAT_TYPE[KEY].code,
      desc: SEAT_TYPE[KEY].desc,
      count: dailyTrainTicket.value[key],
      price: dailyTrainTicket.value[key + 'Price']
    })
  }
}
</script>

<style scoped>
.order-train .order-train-main {
  font-size: 18px;
  font-weight: bold;
}

.order-train-ticket {
  margin-top: 15px;
}

.order-train-ticket-main {
  color: red;
  font-size: 18px;
}
</style>
