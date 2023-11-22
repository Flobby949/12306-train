const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  // 自动打开浏览器
  devServer: {
    open: true
  }
})
