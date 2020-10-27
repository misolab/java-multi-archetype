import storage from 'store'
import { login, getInfo, logout } from '@/api/auth/login'
import { ACCESS_TOKEN } from '@/store/mutation-types'

const user = {
  state: {
    token: '',
    name: '',
    welcome: '',
    avatar: '',
    roles: [],
    info: {},
  },

  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    SET_NAME: (state, { name, welcome }) => {
      state.name = name
      state.welcome = welcome
    },
    SET_AVATAR: (state, avatar) => {
      state.avatar = avatar
    },
    SET_ROLES: (state, roles) => {
      state.roles = roles
    },
    SET_INFO: (state, info) => {
      state.info = info
    },
  },

  actions: {
    // 로그인
    Login ({ commit }, userInfo) {
      return new Promise((resolve, reject) => {
        login(userInfo).then(resBody => {
          console.log('resBody:', resBody)

          const { token } = resBody.data
          storage.set(ACCESS_TOKEN, token)
          commit('SET_TOKEN', token)
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 사용자 정보 얻기
    GetInfo ({ commit }) {
      return new Promise((resolve, reject) => {
        getInfo().then(resBody => {
          if (resBody.data.role && resBody.data.role.permissions.length > 0) {
            const role = resBody.data.role
            role.permissions = resBody.data.role.permissions
            role.permissions.map(per => {
              if (per.actionEntitySet != null && per.actionEntitySet.length > 0) {
                const action = per.actionEntitySet.map(action => { return action.action })
                per.actionList = action
              }
            })
            role.permissionList = role.permissions.map(permission => { return permission.permissionId })
            commit('SET_ROLES', resBody.data.role)
            commit('SET_INFO', resBody.data)
          } else {
            reject(new Error('getInfo: roles must be a non-null array !'))
          }

          commit('SET_NAME', { name: resBody.data.name, welcome: '' })
          commit('SET_AVATAR', resBody.data.avatar)

          resolve(resBody)
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 로그아웃
    Logout ({ commit, state }) {
      return new Promise((resolve) => {
        logout(state.token).then(() => {
          resolve()
        }).catch(() => {
          resolve()
        }).finally(() => {
          commit('SET_TOKEN', '')
          commit('SET_ROLES', [])
          storage.remove(ACCESS_TOKEN)
        })
      })
    },

  },
}

export default user
