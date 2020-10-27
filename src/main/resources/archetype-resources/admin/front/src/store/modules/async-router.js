/**
 * 백엔드에서 사용자 메뉴를 요청하고 동적으로 라우팅을 생성합니다
 */
import { constantRouterMap } from '@/config/router.config'
import { generatorDynamicRouter } from '@/router/generator-routers'

const permission = {
  state: {
    routers: constantRouterMap,
    addRouters: [],
  },
  mutations: {
    SET_ROUTERS: (state, routers) => {
      state.addRouters = routers
      state.routers = constantRouterMap.concat(routers)
    },
  },
  actions: {
    GenerateRoutes ({ commit }, data) {
      return new Promise(resolve => {
        const { token } = data
        generatorDynamicRouter(token).then(routers => {
          commit('SET_ROUTERS', routers)
          resolve()
        })
      })
    },
  },
}

export default permission
