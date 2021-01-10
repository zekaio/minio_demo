<template>
  <div class="show">
    <div>
      <el-button class="btn" type="primary" @click="$goTo('/')">
        返回
      </el-button>
    </div>
    <el-input class="input" v-model="bucketName" placeholder="请输入桶名">
      <template slot="prepend">桶名</template>
    </el-input>
    <el-input class="input" v-model="objectName" placeholder="请输入对象名">
      <template slot="prepend">对象名</template>
    </el-input>
    <el-select
      class="input"
      v-model="p"
      placeholder="预设图片处理参数"
      :clearable="true"
    >
      <el-option
        v-for="item in options"
        :key="item.value"
        :label="item.label"
        :value="item.value"
      >
      </el-option>
    </el-select>
    <el-input class="input" v-model="dh" placeholder="请输入高(dh)">
      <template slot="prepend">高</template>
    </el-input>
    <el-input class="input" v-model="dw" placeholder="请输入宽(dw)">
      <template slot="prepend">宽</template>
    </el-input>
    <el-input class="input" v-model="angle" placeholder="请输入旋转角度(angle)">
      <template slot="prepend">旋转角度</template>
    </el-input>
    <el-input class="input" v-model="q" placeholder="请输入图片质量(q)">
      <template slot="prepend">jpeg图片质量</template>
    </el-input>

    <el-button class="btn" type="primary" @click="showImage">
      查看图片
    </el-button>

    <el-link
      style="font-size: 1.2rem; margin-top: 2vh;"
      type="primary"
      :href="url"
      target="_blank"
      >{{ url }}</el-link
    >

    <div class="image">
      <el-image
        style="height: 40vh; width: auto;"
        :src="url"
        fit="contain"
      ></el-image>
    </div>
  </div>
</template>

<script>
import { Notification } from 'element-ui'

import { minioServerURL } from '../config'
export default {
  name: 'Show',
  data() {
    return {
      bucketName: 'zekaio',
      objectName: 'mario.jpg',
      options: [
        { value: 'msize', label: 'p=msize: dw=500,dh=500,q=95' },
        { value: 'ssize', label: 'p=ssize: dw=120,dh=120,q=95' }
      ],

      url: `${minioServerURL}/zekaio/mario.jpg`,

      p: '',
      dh: '',
      dw: '',
      angle: '',
      q: ''
    }
  },
  methods: {
    showImage() {
      const query = new URLSearchParams()
      const params = {
        dh: this.dh,
        dw: this.dw,
        angle: this.angle,
        q: this.q
      }

      if (this.p !== '') {
        query.append('p', this.p)
      } else {
        Object.entries(params).forEach(([k, v]) => {
          if (v !== '') {
            query.append(k, v)
          }
        })
      }

      this.url = `${minioServerURL}/${this.bucketName}/${this.objectName}${
        query.toString() === '' ? '' : `?${query.toString()}`
      }`
    }
  },
  async mounted() {
    Notification.info({
      title: '提示',
      dangerouslyUseHTMLString: true,
      message:
        '可前往 <a href="https://minio.zekaio.cn" target="_blank">minio.zekaio.cn</a> 查看已有文件<br/>账号密码均为minioadmin'
    })
  }
}
</script>

<style scoped>
.show {
  display: flex;
  display: -webkit-flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}
.input {
  width: 40vw;
  margin-top: 2vh;
}
.image {
  margin-top: 2vh;
}
.btn {
  margin-top: 2vh;
  width: 10vw;
}
</style>
