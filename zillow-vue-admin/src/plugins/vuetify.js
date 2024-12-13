import '@fortawesome/fontawesome-free/css/all.css'
import 'material-design-icons-iconfont/dist/material-design-icons.css'

import Vue from 'vue';
import Vuetify from 'vuetify/lib/framework';
import colors from "vuetify/lib/util/colors";

Vue.use(Vuetify);

export default new Vuetify({
    icons: {
        iconfont: 'fa' || 'md',
    },
    theme: {
        themes: {
            light: {
                primary: '#156FF6',
                secondary: '#001751',
                accent: '#FED035',
                info: '#dee3e6',
                danger:'#fb6060',
                background: '#f5f5f5',
            },
            dark: {
                background: colors.shades.white,
            },
        },
    },
});
