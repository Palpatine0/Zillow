<template>
<div>
    <div v-if="!isMobile" class="nav clear-fix">
        <div class="manu-sector">
            <router-Link exact to="/">
                &nbsp;
            </router-Link>
        </div>

        <div class="manu-sector center-h">
            <v-img src="https://www.zillowstatic.com/s3/pfs/static/z-logo-default-visual-refresh.svg" style="max-width: fit-content;" @click="homeRedirect"/>
        </div>

        <div class="manu-sector">
            <div style="float: right;padding: 26px">
                <div v-if="$common.isEmpty(user)" @click="mineRedirect">
                    <v-icon color="primary">fas fa-user</v-icon>
                </div>
                <v-avatar v-else size="32" class="d-flex justify-center mb-6" @click="mineRedirect">
                    <v-img :src="awsS3RequestUrl + user.avatar" aspect-ratio="2"></v-img>
                </v-avatar>
            </div>
        </div>
    </div>
    <v-bottom-navigation v-else class="nav-mobile">
        <div class="manu-sector" @click="homeRedirect">
            <v-icon size="20" color="primary">fas fa-home</v-icon>
        </div>
        <div class="manu-sector" @click="shopRedirect">
            <v-icon size="18" color="primary">fas fa-list</v-icon>
        </div>
        <div class="manu-sector" @click="mineRedirect">
            <v-icon size="16" color="primary">fas fa-user</v-icon>
        </div>
    </v-bottom-navigation>
</div>
</template>

<script>
import {mapState} from "vuex";

export default {
    name: "NavBar",
    data() {
        return {};
    },
    computed: {
        ...mapState(["user", "awsS3RequestUrl"])
    },
    methods: {
        // Redirects
        homeRedirect() {
            this.$router.push("/");
        },
        mineRedirect() {
            this.$router.push("/mine");
        },
        shopRedirect() {
            this.$router.push("/homes");
        }
    },
};
</script>
<style lang="less" scoped>
.active {
    color: #156FF6;
}

.nav {
    z-index: 10;
    background: #fff;

    height: 80px;
    width: 100vw;

    .manu-sector {
        float: left;
        text-align: center;
        width: 33.3%;
        height: 84px;

        i {
            display: block;
            font-size: 20px;
        }

        span {
            font-size: 12px;
        }
    }

    .svg {
        display: flex;
        align-items: center;
        gap: 0;
    }
}

.nav-mobile {

    border-right: 1px solid #FFFFFF;

    z-index: 10;
    background: #fff;
    position: fixed;
    left: 0;
    right: 0;
    bottom: 0;
    box-shadow: 0px 0px 0px 1px #e1e1e1;

    height: 50px;
    padding-top: 6.5px;

    .manu-sector {
        float: left;
        text-align: center;
        width: 33.3%;
    }
}
</style>