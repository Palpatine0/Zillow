<template>
<v-app>
    <NavBar/>
    <v-main>
        <router-view v-if="isRouterAlive"></router-view>
        <ChatBox/>
    </v-main>
</v-app>
</template>
<script>
import NavBar from "@/components/NavBar/NavBar.vue";
import ChatBox from "@/components/ChatBox/ChatBox.vue";

export default {
    name: "App",
    components: {
        NavBar,
        ChatBox
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
}

* {
    //outline: 1px solid red;
}
</style>
