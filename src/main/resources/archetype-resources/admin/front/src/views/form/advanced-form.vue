<template>
  <page-container
    :tab-list="tabList"
    :tab-active-key="tabActiveKey"
    :title="false"
    :tab-change="(key) => {
      this.tabActiveKey = key
      console.log('PageHeader::tabChange', key)
    }"
    @back="() => {
      console.log('PageHeader::@back')
    }"
    :back="() => {
      // back 재정의 처리, onBack 이벤트를 재정의하지 않습니다
      console.log('PageHeader::.back')
    }"
  >
    <template v-slot:content>
      <span>{{ $t('pages.form.basicform.content') }}</span>
    </template>
    <!-- 사용자 정의 컨텐츠 확장 정보 -->
    <template v-slot:extraContent>
      <div><a-button>{{ $t('pages.form.basicform.headers.btn1') }}</a-button></div>
    </template>
    <div>
      <h3>Advanced Form</h3>
      <p>헤더 제목 예시 숨기기</p>
    </div>
  </page-container>
</template>

<script>
export default {
  name: 'AdvancedForm',
  data () {
    return {
      console: window.console,
      tabList: [
        { tab: 'pages.form.basicform.tabs.tab1', key: 'tab1' },
        { tab: 'pages.form.basicform.tabs.tab2', key: 'tab2' },
        { tab: 'pages.form.basicform.tabs.tab3', key: 'tab3' },
      ],
      tabActiveKey: 'tab1',
    }
  },
  methods: {
    handleTabChange (key) {
      this.tabActiveKey = key
      console.log('PageHeader::tabChange', key)
    },
  },
}
</script>
