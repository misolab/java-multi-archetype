import exceptionLang from '@/views/exception/locales/koKR'

export default {
  navBar: {
    lang: '언어',
  },
  layouts: {
    usermenu: {
      dialog: {
        title: '로그아웃',
        content: '로그아웃 하시겠습니까?',
      },
    },
  },
  menu: {
    home: '메인화면',
    dashboard: {
      default: '대시보드',
      welcome: 'Welcome',
      workplace: '작업영역',
    },
    form: {
      default: '폼(양식)',
      basicform: '기본 양식',
      stepform: '단계별 양식',
      advancedform: '고급 양식',
    },
    nav1: '네비게이션1',
    nav2: '네비게이션2',
    nav3: '네비게이션3',
  },

  pages: {
    dashboard: {
      welcome: {
        tips: '환영합니다 Ant Design Vue',
        'show-loading': 'Loading 보이기',
        'hide-loading': 'Loading 숨기기',
      },
    },
    form: {
      basicform: {
        headers: {
          btn1: '버튼 1',
          customTitle: '사용자 정의 제목',
        },
        content: '양식 페이지는 사용자에게 정보를 수집하거나 확인하는 데 사용되며 데이터 항목이 적은 시나리오에서는 기본 양식이 일반적입니다.',
        tabs: {
          tab1: '탭1',
          tab2: '탭2',
          tab3: '탭3',
        },
      },
    },
  },

  'navBar.lang': 'Language',

  'app.setting.pagestyle': 'Page style setting',
  'app.setting.pagestyle.light': 'Light style',
  'app.setting.pagestyle.dark': 'Dark style',
  'app.setting.pagestyle.realdark': 'RealDark style',
  'app.setting.themecolor': 'Theme Color',
  'app.setting.navigationmode': 'Navigation Mode',
  'app.setting.content-width': 'Content Width',
  'app.setting.fixedheader': 'Fixed Header',
  'app.setting.fixedsidebar': 'Fixed Sidebar',
  'app.setting.sidemenu': 'Side Menu Layout',
  'app.setting.topmenu': 'Top Menu Layout',
  'app.setting.content-width.fixed': 'Fixed',
  'app.setting.content-width.fluid': 'Fluid',
  'app.setting.othersettings': 'Other Settings',
  'app.setting.weakmode': 'Weak Mode',
  'app.setting.copy': 'Copy Setting',
  'app.setting.loading': 'Loading theme',
  'app.setting.copyinfo': 'copy success，please replace defaultSettings in src/models/setting.js',
  'app.setting.production.hint': 'Setting panel shows in development environment only, please manually modify',

  // page locales
  ...exceptionLang,
}
