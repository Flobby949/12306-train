<script setup>
	import {
		onLaunch
	} from "@dcloudio/uni-app"
	onLaunch(() => {
		let requestData = {
			platform: '',
			appName: '',
			appVersion: ''
		}
		uni.getSystemInfo().then(res => {
			console.log(res);
			requestData.appName = res.appName
			requestData.appVersion = res.appVersion
			requestData.platform = res.osName
			localStorage.setItem("appName", res.appName)
			localStorage.setItem("appVersion", res.appVersion)

			uni.request({
				url: "https://b153-112-2-253-188.ngrok-free.app/business/admin/app-manage/check-version",
				method: 'POST',
				data: requestData
			}).then(data => {
				let result = data.data
				if (result.success) {
					uni.showToast({
						title: "当前是最新版本"
					})
				} else {
					uni.showToast({
						icon: 'error',
						title: result.message
					})
					uni.navigateTo({
						url: "/pages/download/download?versionId="+ result.data.id
					})
				}
			})
		})
	})
</script>

<style>
	/*每个页面公共css */
</style>