module.exports = {
  // 基本路径
  publicPath: '/dist/',
  // 生产环境是否生成 sourceMap 文件
  productionSourceMap: false,
  // 服务器端口号
  devServer: {
    port: 8083,
    proxy: {
      "/api": {
        target: 'http://localhost:8081',
        changeOrigin: true,
        pathRewrite:{
          '^/api':''
        }
      }
    },
    public:'192.168'
  },

}
