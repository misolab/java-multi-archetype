<template>
  <a-result
    :is-success="true"
    :content="false"
    :title="email"
    :sub-title="description"
  >
    <template #extra>
      <a-button size="large" type="primary">查看邮箱</a-button>
      <a-button size="large" style="margin-left: 8px" @click="goHomeHandle">返回首页</a-button>
    </template>
  </a-result>
</template>

<script>
export default {
  name: 'RegisterResult',
  data () {
    return {
      description: '인증 이메일이 사서함으로 전송되었으며 이메일은 24시간 동안 유효합니다. 시간안에 사서함에 로그인하고 이메일의 링크를 클릭하여 계정을 활성화하십시오.',
      form: {},
    }
  },
  computed: {
    email () {
      const v = this.form && this.form.email || 'xxx'
      return `당신의 계정：${v} 등록 성공`
    },
  },
  created () {
    this.form = this.$route.params
  },
  methods: {
    goHomeHandle () {
      this.$router.push({ name: 'login' })
    },
  },
}
</script>

<style scoped>

</style>
