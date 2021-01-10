import instance from './config'

let apis = {}

apis.presignedPutObject = (bucketName, objectName) => {
  return instance({
    url: '/presigned/put',
    method: 'get',
    params: { bucketName, objectName }
  })
}

export default apis
