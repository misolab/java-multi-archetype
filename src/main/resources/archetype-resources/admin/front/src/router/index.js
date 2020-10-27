import Vue from 'vue'
import VueRouter from 'vue-router'
import { constantRouterMap } from '@/config/router.config'

// hack router push/replace callback
['push', 'replace'].map(key => {
  return {
    k: key,
    prop: VueRouter.prototype[key],
  }
}).forEach(item => {
  VueRouter.prototype[item.k] = function newCall (location, onResolve, onReject) {
    if (onResolve || onReject) return item.prop.call(this, location, onResolve, onReject)
    return item.prop.call(this, location).catch(err => err)
  }
})

Vue.use(VueRouter)

const router = new VueRouter({
  mode: 'history',
  routes: constantRouterMap,
})

export default router
