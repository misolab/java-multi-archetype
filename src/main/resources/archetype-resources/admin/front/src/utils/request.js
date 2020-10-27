import axios from 'axios'
import storage from 'store'
import notification from 'ant-design-vue/es/notification'
import { VueAxios } from './axios'
import { ACCESS_TOKEN } from '@/store/mutation-types'

// axios 인스턴스 만들기
const request = axios.create({
  // API 기본 경로
  baseURL: process.env.VUE_APP_API_BASE_URL,
  timeout: 6000, // 요청 시간 초과
})

// 예외 처리 핸들러
const errorHandler = (error) => {
  if (error.response) {
    const resBody = error.response.data
    // localstorage에서 토큰 가져 오기
    if (error.response.status === 403) {
      notification.error({
        message: 'Forbidden',
        description: resBody.error.message,
      })
    }
    if (error.response.status === 401) {
    // if (error.response.status === 401 && !(data.result && data.result.isLogin)) {
        notification.error({
        message: 'Unauthorized',
        description: 'Authorization verification failed',
      })
    }
  }
  return Promise.reject(error)
}

// request interceptor
request.interceptors.request.use(config => {
  const token = storage.get(ACCESS_TOKEN)
  // 토큰이있는 경우
  // 각 요청에 사용자 지정 토큰을 전달하고 실제 상황에 따라 수정하십시오
  if (token) {
    config.headers['Access-Token'] = token
  }
  return config
}, errorHandler)

// response interceptor
request.interceptors.response.use((response) => {
  const resBody = response.data
  return resBody
}, errorHandler)

const installer = {
  vm: {},
  install (Vue) {
    Vue.use(VueAxios, request)
  },
}

export default request

export {
  installer as VueAxios,
  request as axios,
}
