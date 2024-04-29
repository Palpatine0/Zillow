import Vue from 'vue'
import Vuetify from 'vuetify/lib'
import 'vuetify/src/stylus/app.styl'
import 'vuetify/dist/vuetify.min.css'




Vue.use(Vuetify, {
  iconfont: 'md',
  theme: {
    primary: '#156ff6',
    success: '#3cd1c2',
    info: '#ffaa2c',
    error: '#f83e70'
  }
})

