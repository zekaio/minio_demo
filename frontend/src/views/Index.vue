<template>
  <div class="index">
    <h1>Minio示例</h1>
    <div class="btn_wrapper">
      <el-button type="primary" @click="$goTo('/show')">查看图片</el-button>
      <el-button type="primary" @click="$goTo('/upload')">上传图片</el-button>
    </div>
    <el-link
      style="font-size: 1.2rem; margin-top: 2vh;"
      type="primary"
      href="https://github.com/zekaio/minio_demo"
      target="_blank"
      >项目地址</el-link
    >
    <el-link
      style="font-size: 1.2rem; margin-top: 2vh;"
      type="primary"
      href="https://blog.zekaio.cn/2021/01/11/Minio"
      target="_blank"
      >文档</el-link
    >
  </div>
</template>

<script>
export default {
  name: 'Index',
  data() {
    return {}
  },
  methods: {},
  async mounted() {}
}
</script>

<style scoped>
.index {
  height: 100vh;
  display: flex;
  display: -webkit-flex;
  align-items: center;
  justify-content: center;
  width: 100vw;
  flex-direction: column;
  flex-wrap: wrap;
}
.btn_wrapper {
  display: flex;
  display: -webkit-flex;
  align-items: center;
  justify-content: center;
  width: 100vw;
  flex-direction: row;
  flex-wrap: wrap;

  margin-top: 15vh;
}
</style>
