import Vue from 'vue'
import store from '@/store'

/**
 * Action 허가 지시
 * 지시 사용법：
 *  - 다음과 같이 작업 수준 권한을 제어해야하는 구성 요소에 v-action:[method]을 사용해주세요
 *    <a-button v-action:add >사용자 추가</a-button>
 *    <a-button v-action:delete>사용자 삭제</a-button>
 *    <a v-action:edit @click="edit(record)">수정</a>
 *
 *  - 현재 사용자에게 권한이없는 경우 명령을 사용하면 구성 요소가 숨겨집니다
 *  - 백그라운드 권한이 pro에서 제공하는 모드와 다른 경우 여기에서 권한 필터링 만 수정하면됩니다.
 *
 *  @see https://github.com/vueComponent/ant-design-vue-pro/pull/53
 */
const action = Vue.directive('action', {
  inserted: function (el, binding, vnode) {
    const actionName = binding.arg
    const roles = store.getters.roles
    const elVal = vnode.context.$route.meta.permission
    const permissionId = elVal instanceof String && [elVal] || elVal
    roles.permissions.forEach(p => {
      if (!permissionId.includes(p.permissionId)) {
        return
      }
      if (p.actionList && !p.actionList.includes(actionName)) {
        el.parentNode && el.parentNode.removeChild(el) || (el.style.display = 'none')
      }
    })
  },
})

export default action
