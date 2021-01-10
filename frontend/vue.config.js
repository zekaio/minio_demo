module.exports = {
  publicPath: process.env.NODE_ENV === 'production' ? '/minio_demo/' : '/',
  productionSourceMap: false,
  chainWebpack: config => {
    config.resolve.symlinks(true)
  }
}
