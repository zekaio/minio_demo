import axios from 'axios'
import { Message } from 'element-ui'

import { baseURL } from '../config'

const instance = axios.create({
  baseURL
})

instance.interceptors.response.use(
  res => {
    return Promise.resolve(res)
  },
  err => {
    if (!err.response) {
      Message.error({
        message: '服务器无法响应'
      })
    } else {
      console.log(err.response)
      switch (err.response.status) {
        case 500:
          Message.error({
            message: '服务器错误'
          })
          break
        default:
          return Promise.reject(err)
      }
    }
    return new Promise(() => {})
  }
)

instance.interceptors.request.use(
  config => {
    if (/get/i.test(config.method)) {
      config.params = config.params || {}
      config.params.timestamp = new Date().getTime()
    }
    return config
  },
  err => {
    return Promise.reject(err)
  }
)

export default instance
