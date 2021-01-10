<template>
  <div class="upload">
    <el-button type="primary" @click="$goTo('/')">
      返回
    </el-button>
    <el-tabs v-model="activeName">
      <!-- 原生上传 -->
      <el-tab-pane label="原生" name="native">
        <input type="file" id="file" multiple accept="image/*" />
        <button id="btn" @click="nativeUpload">上传</button>
      </el-tab-pane>
      <!-- element-ui uploader组件上传 -->
      <el-tab-pane label="element ui+进度条" name="element">
        <el-upload
          action="#"
          ref="upload"
          list-type="picture-card"
          :file-list="fileList"
          :on-preview="handlePictureCardPreview"
          :auto-upload="false"
        >
          <i class="el-icon-plus"></i>
        </el-upload>
        <el-dialog :visible.sync="dialogVisible">
          <img width="100%" :src="dialogImageUrl" alt="" />
        </el-dialog>
        <el-button style="margin: 10px;" type="primary" @click="elementUpload"
          >上传到服务器</el-button
        >
      </el-tab-pane>
    </el-tabs>
    <!-- 进度条 -->
    <div v-for="i in fileCount" :key="i">
      <el-progress
        style="margin-top:2vh"
        :text-inside="true"
        :stroke-width="26"
        :percentage="percentages[i - 1]"
        :status="percentages[i - 1] === 100 ? 'success' : ''"
      ></el-progress>
    </div>
  </div>
</template>

<script>
import { v4 as uuidv4 } from 'uuid'
import mime from 'mime-types'
import { Message } from 'element-ui'
import axios from 'axios'

import apis from '../api/apis'
import { bucketName } from '../config'

export default {
  name: 'Upload',
  data() {
    return {
      activeName: 'element',

      dialogImageUrl: '',
      dialogVisible: false,
      fileList: [],

      fileCount: 0,
      percentages: []
    }
  },
  methods: {
    nativeUpload() {
      const files = document.querySelector('#file').files
      files.forEach(file => {
        const objectName = `${uuidv4()}.${mime.extension(file.type)}`

        apis
          .presignedPutObject(bucketName, objectName)
          .then(res => {
            axios
              .put(res.data.data, file, {
                headers: {
                  'Content-Type': file.type // 要设置content-type，否则无法预览
                }
              })
              .then(() => {
                Message.success({
                  message: `文件${file.name}上传成功，文件名为${objectName}`
                })
              })
              .catch(err => {
                Message.error({
                  message: `${err.response.status}: ${err.response.data.message}`
                })
              })
          })
          .catch(err => {
            Message.error({
              message: `${err.response.status}: ${err.response.data.message}`
            })
          })
      })
    },

    elementUpload() {
      const uploadFiles = this.$refs.upload.uploadFiles
      this.fileCount = uploadFiles.length
      this.percentages = new Array(this.fileCount)
      this.percentages.fill(0)

      uploadFiles.forEach((uploadFile, index) => {
        const file = uploadFile.raw
        const objectName = `${uuidv4()}.${mime.extension(file.type)}`

        apis
          .presignedPutObject(bucketName, objectName)
          .then(res => {
            axios
              .put(res.data.data, file, {
                headers: {
                  'Content-Type': file.type // 要设置content-type，否则无法预览
                },
                // 跟踪上传进度
                onUploadProgress: e => {
                  // 使用set方法更新数组，见https://cn.vuejs.org/v2/guide/reactivity.html#%E5%AF%B9%E4%BA%8E%E6%95%B0%E7%BB%84
                  this.$set(
                    this.percentages,
                    index,
                    parseInt((e.loaded / e.total) * 100)
                  )
                }
              })
              .then(() => {
                Message.success({
                  message: `文件${file.name}上传成功，文件名为${objectName}`
                })
              })
              .catch(err => {
                Message.error({
                  message: `${err.response.status}: ${err.response.data.message}`
                })
              })
          })
          .catch(err => {
            Message.error({
              message: `${err.response.status}: ${err.response.data.message}`
            })
          })
      })
    },

    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url
      this.dialogVisible = true
    }
  },
  async mounted() {}
}
</script>

<style scoped></style>
