<template>
<v-navigation-drawer :value="drawer" dark style="background: #156ff6;" app mini-variant mini-variant-width="105">

<!--    <v-row class="align-center mt-5">
        <v-col class="mt-5 d-flex flex-column align-center" cols="12">
            <v-avatar size="100">
                <img alt="Palpatine" src="/avatar-1.png">
            </v-avatar>
            <p class="white&#45;&#45;text subheading mt-1">Palpatine</p>
        </v-col>
    </v-row>-->

    <v-list>
        <v-list-item class="mb-12">
            <v-list-item-action>
            </v-list-item-action>
        </v-list-item>
    </v-list>
    <v-list flat>
        <v-list-item v-for="(item, i) in links" :key="i" link @click="navigateTo(item.route)" >
            <v-list-item-content>
                <v-icon class="mb-4" v-text="item.icon" ></v-icon>
                <v-list-item-subtitle v-text="item.text" align="center"></v-list-item-subtitle>
            </v-list-item-content>
        </v-list-item>
    </v-list>
</v-navigation-drawer>
</template>

<script>
import {mapState} from 'vuex'

export default {
    data() {
        return {};
    },

    computed: {
        ...mapState(['drawer']),
        ...mapState(['links'])
    },
    methods: {
        navigateTo(route) {
            this.$router.push(route);
        }
    },


};
</script>
