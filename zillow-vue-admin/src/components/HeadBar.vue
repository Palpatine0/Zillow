<template>
    <v-app-bar flat app>
        <v-app-bar-nav-icon @click="toggleDrawer" class="grey--text"/>

        <v-toolbar-title class="text-uppercase grey--text">
            <span class="font-weight-light">Zillow</span>
            <span>Admin</span>
        </v-toolbar-title>

        <v-spacer></v-spacer>

        <!-- dropdown menu -->
        <v-menu offset-y>
            <template v-slot:activator="{ on }">
                <v-btn text v-on="on" color="grey">
                    <v-icon left>mdi-expand-more</v-icon>
                    <span>Menu</span>
                </v-btn>
            </template>
            <v-list>
                <v-list-item v-for="link in links" :key="link.text" :to="link.route">
                    <v-list-item-title>{{ link.text }}</v-list-item-title>
                </v-list-item>
            </v-list>
        </v-menu>

        <v-btn text color="grey">
            <span>Sign Out</span>
            <v-icon right>mdi-exit-to-app</v-icon>
        </v-btn>
    </v-app-bar>
</template>
<script>
import {mapState} from 'vuex'
import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);


export default {

    name: 'HeadBar',
    data() {
        return {}
    },

    computed: {
        ...mapState(['drawer']),
        ...mapState(['links'])
    },

    methods: {
        toggleDrawer() {
            this.$store.commit('toggleDrawer');
        }
    }
}
</script>

