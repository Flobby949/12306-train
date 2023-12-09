<template>
	<view class="content">
		<image class="logo" src="/static/logo.png"></image>
		<view class="text-area">
			<text class="title">{{appName}}:</text>
			<text class="title">{{appVersion}}</text>
		</view>
	</view>
</template>

<script setup>
	import {
		ref
	} from "vue";
	import {
		onShow
	} from "@dcloudio/uni-app"
	let title = ref("hello")
	let appVersion = ref("")
	let appName = ref("")
	appName.value = localStorage.getItem("appName")
	appVersion.value = localStorage.getItem("appVersion")
</script>

<style>
	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}

	.logo {
		height: 200rpx;
		width: 200rpx;
		margin-top: 200rpx;
		margin-left: auto;
		margin-right: auto;
		margin-bottom: 50rpx;
	}

	.text-area {
		display: flex;
		justify-content: center;
	}

	.title {
		font-size: 36rpx;
		color: #8f8f94;
	}
</style>