<template>
	<view style="display: flex; justify-content: center; align-items: center;width: 100%;height: 100%;">
		<button @tap="update">版本更新</button>
	</view>
</template>

<script setup>
	import {
		onLoad
	} from "@dcloudio/uni-app"
	import {
		ref
	} from "vue";
	let appInfo = ref({})
	onLoad(option => {
		console.log(option.versionId);
		uni.request({
			url: "https://b153-112-2-253-188.ngrok-free.app/business/admin/app-manage/query/" + option.versionId,
			header: {
				"ngrok-skip-browser-warning":"any"
			}
		}).then(res => {
			console.log(res.data);
			appInfo.value = res.data.data
		})
	})
	const update = () => {
		window.location.href = appInfo.value.downloadUrl
	}
</script>

<style>

</style>