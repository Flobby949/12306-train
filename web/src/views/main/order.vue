<template>
  <div>{{ dailyTrainTicket }}</div>
</template>

<script setup>
const dailyTrainTicket = SessionStorage.get('dailyTrainTicket') || {}
console.log(dailyTrainTicket)
</script>

<style scoped></style>
