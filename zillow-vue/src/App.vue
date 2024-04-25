<template>
    <div id="app">
        <router-view v-if="isRouterAlive"></router-view>
    </div>
</template>

<script>
export default {
    name: "App",
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
        console.log("APP: globalData.deviceWidth")
        console.log(this.globalData.deviceWidth)
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
