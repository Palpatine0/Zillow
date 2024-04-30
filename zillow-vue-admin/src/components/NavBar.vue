<template>
    <v-navigation-drawer :value="drawer" app dark style="background: #156ff6;" width="300">

    <v-row class="align-center">
            <v-col cols="12" class="mt-5 d-flex flex-column align-center">
                <v-avatar size="100">
                    <img src="/avatar-1.png" alt="Palpatine">
                </v-avatar>
                <p class="white--text subheading mt-1">Palpatine</p>
            </v-col>

        </v-row>

        <v-list shaped>
            <v-list-item-group  color="grey lighten-5">
                <v-list-item v-for="(item, i) in links" :key="i" @click="navigateTo(item.route)">
                    <v-list-item-icon>
                        <v-icon v-text="item.icon" size="16"></v-icon>
                    </v-list-item-icon>
                    <v-list-item-content>
                        <v-list-item-title v-text="item.text"></v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
            </v-list-item-group>
        </v-list>
    </v-navigation-drawer>
</template>

<script>
import {mapState} from 'vuex'
export default {
    data() {
        return {

        };
    },
    methods: {
        navigateTo(route) {
            this.$router.push(route);
        }
    },
    computed: {
        ...mapState(['drawer']),
        ...mapState(['links'])
    },

};
</script>
