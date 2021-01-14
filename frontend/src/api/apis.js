import instance from './config'

let apis = {}

apis.presignedPutObject = (bucketName, fileType, subBucketName) => {
  return instance({
    url: '/presigned/put',
    method: 'get',
    params: { bucketName, fileType, subBucketName }
  })
}

export default apis
