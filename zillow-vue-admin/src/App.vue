<template>
<v-app>
    <NavBar/>
    <v-main class="grey lighten-5">
        <HeadBar/>
        <router-view class="grey lighten-5"/>
    </v-main>
</v-app>
</template>

<script>
import NavBar from './components/NavBar.vue';
import HeadBar from "@/components/HeadBar.vue";

export default {
    name: "App",
    components: {
        HeadBar,
        NavBar,
    },
    provide() {
        return {
            globalData: this.globalData,
            reload: this.reload
        };
    },
    data() {
        return {
            globalData: {
                deviceWidth: 0
            },
            isRouterAlive: true
        };
    },
    methods: {
        reload() {
            this.isRouterAlive = false;
            this.$nextTick(() => (this.isRouterAlive = true));
        }
    },
    mounted() {
        this.globalData.deviceWidth = window.screen.width;
    }
};
</script>

<style lang="less">
* {
    font-family: Arial;
    //outline: 1px solid red;
}
.center-h {
    justify-content: center;
    align-items: center;
    display: flex;
    margin: 0 auto;
}
.center-v {
    display: flex;
    align-items: center;
}
</style>
