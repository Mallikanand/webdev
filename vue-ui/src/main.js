import Vue from 'vue'
import App from './App.vue'
import store from '@/store/index'
import router from './router'
import VueResource from 'vue-resource'

{/* <impo type="text/javascript" src="/webjars/jquery/jquery.min.js"></script>
<script type="text/javascript" src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/webjars/js-cookie/js.cookie.js"></script> */}

Vue.use(VueResource)
Vue.config.productionTip = false

export const eventBus = new Vue();
// $.ajaxSetup({
//   beforeSend : function(xhr, settings) {
//     if (settings.type == 'POST' || settings.type == 'PUT'
//         || settings.type == 'DELETE') {
//       if (!(/^http:.*/.test(settings.url) || /^https:.*/
//           .test(settings.url))) {
//         // Only send the token to relative URLs i.e. locally.
//         xhr.setRequestHeader("X-XSRF-TOKEN",
//             Cookies.get('XSRF-TOKEN'));
//       }
//     }
//   }
//   });


new Vue({
  store,
  router,
  render: h => h(App)
}).$mount('#app')
