<template>
<v-app>
    <NavBar/>
    <v-main>
        <router-view v-if="isRouterAlive"></router-view>
        <ChatBot/>
    </v-main>
</v-app>
</template>
<script>
import NavBar from "@/components/NavBar/NavBar.vue";
import ChatBot from "@/components/ChatBot/ChatBot.vue";

export default {
    name: "App",

    components: {
        NavBar,
        ChatBot
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
</style>
