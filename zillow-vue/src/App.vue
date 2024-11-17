<template>
<div id="app">
    <NavBar/>
    <router-view v-if="isRouterAlive"></router-view>
</div>
</template>

<script>
import NavBar from "@/components/NavBar/NavBar.vue";

export default {
    name: "App",
    components: {NavBar},
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
}

* {
    //outline: 1px solid red;
}
</style>
