import ls from 'store'
import router from './index'
import store from '@/store'
import NProgress from 'nprogress' // progress bar
import '@/components/NProgress/nprogress.less'
import { ACCESS_TOKEN } from '@/store/mutation-types' // progress bar custom style
import { notification } from 'ant-design-vue'

NProgress.configure({ showSpinner: false }) // NProgress Configuration

const allowList = ['login', 'register', 'registerResult'] // no redirect whitelist
const loginRoutePath = '/user/login'
const defaultRoutePath = '/dashboard'

router.beforeEach((to, from, next) => {
  NProgress.start() // start progress bar
  const token = ls.get(ACCESS_TOKEN)
  if (token) {
    if (to.path === loginRoutePath) {
      next({ path: defaultRoutePath })
      NProgress.done()
    } else {
      // check login user.roles is null
      if (store.getters.roles.length === 0) {
        store.dispatch('GetInfo').then(res => {
          const roles = res && res.data.role
          // generate dynamic router
          store.dispatch('GenerateRoutes', { roles }).then(() => {
            // 역할 권한을 기반으로 액세스 가능한 라우팅 테이블을 생성하고
            // 액세스 가능한 라우팅 테이블을 동적으로 추가
            router.addRoutes(store.getters.addRouters)
            // 请求带有 redirect 重定向时，登录自动重定向到该地址
            const redirect = decodeURIComponent(from.query.redirect || to.path)
            if (to.path === redirect) {
              // set the replace: true so the navigation will not leave a history record
              next({ ...to, replace: true })
            } else {
              // 목적지 경로로 이동
              next({ path: redirect })
            }
          })
        }).catch(() => {
          notification.error({
            message: '오류',
            description: '사용자 정보를 요청하지 못했습니다. 다시 시도하십시오',
          })
          // 실패시 사용자 정보 획득 실패시 로그 아웃을 호출하여 이력 정보 삭제
          store.dispatch('Logout').then(() => {
            next({ path: loginRoutePath, query: { redirect: to.fullPath } })
          })
        })
      } else {
        next()
      }
    }
  } else {
    // not login
    if (allowList.includes(to.name)) {
      // 로규인이 필요 없는 경로로 이동
      next()
    } else {
      next({ path: loginRoutePath, query: { redirect: to.fullPath } })
      NProgress.done() // if current page is login will not trigger afterEach hook, so manually handle it
    }
  }
  next()
})

router.afterEach(() => {
  NProgress.done() // finish progress bar
})
