<template>
  <div class="index">
    <el-button type="primary" @click="$goTo('/show')">查看图片</el-button>
    <el-button type="primary" @click="$goTo('/upload')">上传图片</el-button>
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
  display: flex;
  display: -webkit-flex;
  align-items: center;
  justify-content: center;
  height: 100vh;
  width: 100vw;
  flex-direction: row;
}
</style>
