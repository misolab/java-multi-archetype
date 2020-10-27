IntelliJ IDEAPhpStormWebStorm
<template>
  <page-container
    :tab-list="tabList"
    :tab-active-key="tabActiveKey"
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
    <template v-slot:title>
      <span>{{ $t('pages.form.basicform.headers.customTitle') }}</span>
    </template>
    <template v-slot:tags>
      <a-tag color="pink">라벨 1</a-tag>
      <a-tag>라벨 2</a-tag>
    </template>
    <template v-slot:content>
      <span>{{ $t('pages.form.basicform.content') }}</span>
    </template>
    <!-- 사용자 지정 확장 정보 -->
    <template v-slot:extra>
      <div><a-button>{{ $t('pages.form.basicform.headers.btn1') }}</a-button></div>
    </template>
    <div>
      <h3>Basic Form</h3>
      <p>일반 헤더</p>
    </div>
  </page-container>
</template>

<script>
export default {
  name: 'BasicForm',
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
