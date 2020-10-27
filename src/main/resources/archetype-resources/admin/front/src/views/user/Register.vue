<template>
  <div class="main user-layout-register">
    <h3><span>등록</span></h3>
    <a-form ref="formRegister" :form="form" id="formRegister">
      <a-form-item>
        <a-input
          size="large"
          type="text"
          placeholder="이메일 주소"
          v-decorator="['email', {rules: [{ required: true, type: 'email', message: '이메일 주소를 입력하세요' }], validateTrigger: ['change', 'blur']}]"
        />
      </a-form-item>

      <a-popover
        placement="rightTop"
        :trigger="['focus']"
        :get-popup-container="(trigger) => trigger.parentElement"
        v-model="state.passwordLevelChecked"
      >
        <template slot="content">
          <div :style="{ width: '240px' }">
            <div :class="['user-register', passwordLevelClass]">强度：<span>{{ passwordLevelName }}</span></div>
            <a-progress :percent="state.percent" :show-info="false" :stroke-color=" passwordLevelColor " />
            <div style="margin-top: 10px;">
              <span>6 자 이상 입력하십시오. 추측하기 쉬운 암호를 사용하지 마십시오.</span>
            </div>
          </div>
        </template>
        <a-form-item>
          <a-input-password
            size="large"
            @click="handlePasswordInputClick"
            placeholder="최소 6 자리 비밀번호, 대소 문자 구분"
            v-decorator="['password', {rules: [{ required: true, message: '최소 6 자리 비밀번호, 대소 문자 구분'}, { validator: this.handlePasswordLevel }], validateTrigger: ['change', 'blur']}]"
          />
        </a-form-item>
      </a-popover>

      <a-form-item>
        <a-input-password
          size="large"
          placeholder="비밀번호 확인"
          v-decorator="['password2', {rules: [{ required: true, message: '최소 6 자리 비밀번호, 대소 문자 구분' }, { validator: this.handlePasswordCheck }], validateTrigger: ['change', 'blur']}]"
        />
      </a-form-item>

      <a-form-item>
        <a-input size="large" placeholder="11 전화번호" v-decorator="['mobile', {rules: [{ required: true, message: '유효한 전화 번호를 입력하세요', pattern: /^1[3456789]\d{9}$/ }, { validator: this.handlePhoneCheck } ], validateTrigger: ['change', 'blur'] }]">
          <a-select slot="addonBefore" size="large" default-value="+86">
            <a-select-option value="+86">+86</a-select-option>
            <a-select-option value="+87">+87</a-select-option>
          </a-select>
        </a-input>
      </a-form-item>
      <!--<a-input-group size="large" compact>
            <a-select style="width: 20%" size="large" defaultValue="+86">
              <a-select-option value="+86">+86</a-select-option>
              <a-select-option value="+87">+87</a-select-option>
            </a-select>
            <a-input style="width: 80%" size="large" placeholder="11 位手机号"></a-input>
          </a-input-group>-->

      <a-row :gutter="16">
        <a-col class="gutter-row" :span="16">
          <a-form-item>
            <a-input size="large" type="text" placeholder="확인 코드" v-decorator="['captcha', {rules: [{ required: true, message: '인증 코드를 입력하세요' }], validateTrigger: 'blur'}]">
              <a-icon slot="prefix" type="mail" :style="{ color: 'rgba(0,0,0,.25)' }" />
            </a-input>
          </a-form-item>
        </a-col>
        <a-col class="gutter-row" :span="8">
          <a-button
            class="getCaptcha"
            size="large"
            :disabled="state.smsSendBtn"
            @click.stop.prevent="getCaptcha"
            v-text="!state.smsSendBtn && '인증 코드 받기'||(state.time+' s')"
          />
        </a-col>
      </a-row>

      <a-form-item>
        <a-button
          size="large"
          type="primary"
          html-type="submit"
          class="register-button"
          :loading="registerBtn"
          @click.stop.prevent="handleSubmit"
          :disabled="registerBtn"
        >
          注册
        </a-button>
        <router-link class="login" :to="{ name: 'login' }">기존 계정으로 로그인</router-link>
      </a-form-item>
    </a-form>
  </div>
</template>

<script>
import { getSmsCaptcha } from '@/api/auth/login'
import { deviceMixin } from '@/store/device-mixin'

const levelNames = {
  0: '낮음',
  1: '낮음',
  2: '중간',
  3: '강함',
}
const levelClass = {
  0: 'error',
  1: 'error',
  2: 'warning',
  3: 'success',
}
const levelColor = {
  0: '#ff0000',
  1: '#ff0000',
  2: '#ff7e05',
  3: '#52c41a',
}
export default {
  name: 'Register',
  components: {
  },
  mixins: [deviceMixin],
  data () {
    return {
      form: this.$form.createForm(this),

      state: {
        time: 60,
        smsSendBtn: false,
        passwordLevel: 0,
        passwordLevelChecked: false,
        percent: 10,
        progressColor: '#FF0000',
      },
      registerBtn: false,
    }
  },
  computed: {
    passwordLevelClass () {
      return levelClass[this.state.passwordLevel]
    },
    passwordLevelName () {
      return levelNames[this.state.passwordLevel]
    },
    passwordLevelColor () {
      return levelColor[this.state.passwordLevel]
    },
  },
  methods: {
    handlePasswordLevel (rule, value, callback) {
      let level = 0

      // 이 문자열에 숫자가 있는지 확인
      if (/[0-9]/.test(value)) {
        level++
      }
      // 문자열에 영문자가 있는지 확인
      if (/[a-zA-Z]/.test(value)) {
        level++
      }
      // 문자열에 특수 기호가 있는지 확인
      if (/[^0-9a-zA-Z_]/.test(value)) {
        level++
      }
      this.state.passwordLevel = level
      this.state.percent = level * 30
      if (level >= 2) {
        if (level >= 3) {
          this.state.percent = 100
        }
        callback()
      } else {
        if (level === 0) {
          this.state.percent = 10
        }
        callback(new Error('비밀번호가 충분히 강력하지 않습니다.'))
      }
    },

    handlePasswordCheck (rule, value, callback) {
      const password = this.form.getFieldValue('password')
      console.log('value', value)
      if (value === undefined) {
        callback(new Error('비밀번호를 입력 해주세요'))
      }
      if (value && password && value.trim() !== password.trim()) {
        callback(new Error('두 암호가 일치하지 않습니다.'))
      }
      callback()
    },

    handlePhoneCheck (rule, value, callback) {
      console.log('handlePhoneCheck, rule:', rule)
      console.log('handlePhoneCheck, value', value)
      console.log('handlePhoneCheck, callback', callback)

      callback()
    },

    handlePasswordInputClick () {
      if (!this.isMobile) {
        this.state.passwordLevelChecked = true
        return
      }
      this.state.passwordLevelChecked = false
    },

    handleSubmit () {
      const { form: { validateFields }, state, $router } = this
      validateFields({ force: true }, (err, values) => {
        if (!err) {
          state.passwordLevelChecked = false
          $router.push({ name: 'registerResult', params: { ...values } })
        }
      })
    },

    getCaptcha (e) {
      e.preventDefault()
      const { form: { validateFields }, state, $message, $notification } = this

      validateFields(['mobile'], { force: true },
        (err, values) => {
          if (!err) {
            state.smsSendBtn = true

            const interval = window.setInterval(() => {
              if (state.time-- <= 0) {
                state.time = 60
                state.smsSendBtn = false
                window.clearInterval(interval)
              }
            }, 1000)

            const hide = $message.loading('확인 코드 전송..', 0)

            getSmsCaptcha({ mobile: values.mobile }).then(res => {
              setTimeout(hide, 2500)
              $notification.success({
                message: '확인',
                description: '인증 코드를 성공적으로 획득했습니다. 인증 코드는 다음과 같습니다.' + res.result.captcha,
                duration: 8,
              })
            }).catch(err => {
              setTimeout(hide, 1)
              clearInterval(interval)
              state.time = 60
              state.smsSendBtn = false
              this.requestFailed(err)
            })
          }
        },
      )
    },
    requestFailed (err) {
      this.$notification.error({
        message: '오류',
        description: ((err.response || {}).data || {}).message || '요청에 오류가 있습니다. 나중에 다시 시도하십시오.',
        duration: 4,
      })
      this.registerBtn = false
    },
  },
  watch: {
    'state.passwordLevel' (val) {
      console.log(val)
    },
  },
}
</script>
<style lang="less">
  .user-register {

    &.error {
      color: #ff0000;
    }

    &.warning {
      color: #ff7e05;
    }

    &.success {
      color: #52c41a;
    }

  }

  .user-layout-register {
    .ant-input-group-addon:first-child {
      background-color: #fff;
    }
  }
</style>
<style lang="less" scoped>
  .user-layout-register {

    & > h3 {
      font-size: 16px;
      margin-bottom: 20px;
    }

    .getCaptcha {
      display: block;
      width: 100%;
      height: 40px;
    }

    .register-button {
      width: 50%;
    }

    .login {
      float: right;
      line-height: 40px;
    }
  }
</style>
