/**
 * 프로젝트 기본 구성 항목
 * primaryColor - 기본테마색상, 수정된 색상이 효과가 없을 경우 localStorage을 정리해주세요
 * navTheme - sidebar theme ['dark', 'light'] 두가지테마
 * colorWeak - 색맹모드
 * layout - 전체레이아웃 ['sidemenu', 'topmenu'] 두가지 레이아웃
 * fixedHeader - 고정 Header : boolean
 * fixSiderbar - 왼쪽메뉴바 고정 ： boolean
 * contentWidth - 콘텐츠 영역 레이아웃： 유식 |  고정
 */

export default {
  navTheme: 'dark', // theme for nav menu
  primaryColor: '#52C41A', // primary color of ant design
  layout: 'sidemenu', // nav menu position: `sidemenu` or `topmenu`
  contentWidth: 'Fluid', // layout of content: `Fluid` or `Fixed`, only works when layout is topmenu
  fixedHeader: false, // sticky header
  fixSiderbar: false, // sticky siderbar
  colorWeak: false,
  menu: {
    locale: true,
  },
  title: 'Ant Design Pro',
  pwa: false,
  iconfontUrl: '',
  production: process.env.NODE_ENV === 'production' && process.env.VUE_APP_PREVIEW !== 'true',
}
