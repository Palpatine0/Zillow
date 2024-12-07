<template>
<div>
    <h1>{{ title }}</h1>
    <v-container v-if="!isMobile" class="featured-container" style="max-width: 90vw;border-radius: 15px;padding: 20px;">
        <v-row>
            <v-col cols="6" lg="6" md="6" @click="itemRedirect(hotData[0].id)">
                <v-img :src='awsS3RequestUrl+hotData[0].imgs[0]' class="white--text align-end rounded-lg" height="320px"/>
                <div class="center-h"><span><b>{{ hotData[0].title }}</b></span></div>
            </v-col>
            <v-col cols="6" lg="6" md="6" @click="itemRedirect(hotData[1].id)">
                <v-img :src='awsS3RequestUrl+hotData[1].imgs[0]' class="white--text align-end rounded-lg" height="320px"/>
                <div class="center-h"><span><b>{{ hotData[1].title }}</b></span></div>
            </v-col>
        </v-row>
        <v-row>
            <v-col cols="6" lg="6" md="6" @click="itemRedirect(hotData[2].id)">
                <v-img :src='awsS3RequestUrl+hotData[2].imgs[0]' class="white--text align-end rounded-lg" height="320px"/>
                <div class="center-h"><span><b>{{ hotData[2].title }}</b></span></div>
            </v-col>
            <v-col cols="6" lg="6" md="6" @click="itemRedirect(hotData[3].id)">
                <v-img :src='awsS3RequestUrl+hotData[3].imgs[0]' class="white--text align-end rounded-lg" height="320px"/>
                <div class="center-h"><span><b>{{ hotData[3].title }}</b></span></div>
            </v-col>
        </v-row>
    </v-container>
    <v-container v-else class="featured-container">
        <v-row>
            <v-col cols="6" lg="6" md="6" @click="itemRedirect(hotData[0].id)">
                <v-img :src='awsS3RequestUrl+hotData[0].imgs[0]' class="white--text align-end rounded-lg" height="100px"/>
                <div class="center-h"><span><b>{{ hotData[0].title }}</b></span></div>
            </v-col>
            <v-col cols="6" lg="6" md="6" @click="itemRedirect(hotData[1].id)">
                <v-img :src='awsS3RequestUrl+hotData[1].imgs[0]' class="white--text align-end rounded-lg" height="100px"/>
                <div class="center-h"><span><b>{{ hotData[1].title }}</b></span></div>
            </v-col>
        </v-row>
        <v-row>
            <v-col cols="6" lg="6" md="6" @click="itemRedirect(hotData[2].id)">
                <v-img :src='awsS3RequestUrl+hotData[2].imgs[0]' class="white--text align-end rounded-lg" height="100px"/>
                <div class="center-h"><span><b>{{ hotData[2].title }}</b></span></div>
            </v-col>
            <v-col cols="6" lg="6" md="6" @click="itemRedirect(hotData[3].id)">
                <v-img :src='awsS3RequestUrl+hotData[3].imgs[0]' class="white--text align-end rounded-lg" height="100px"/>
                <div class="center-h"><span><b>{{ hotData[3].title }}</b></span></div>
            </v-col>
        </v-row>
    </v-container>
</div>
</template>

<script>
import {mapState} from "vuex";

export default {
    name: 'FeaturedView',
    data() {
        return {}
    },
    props: [
        'title',
        'hotData'
    ],
    computed: {
        ...mapState(['awsS3RequestUrl']),
    },
    methods: {
        // Redirects
        itemRedirect(id) {
            this.$router.push('/item/' + id);
        }
    },
}
</script>
<style lang="less" scoped>
h1 {
    position: relative;
    left: 3%;
    width: 97%;
    margin-top: 10px;
    font-weight: bold;
    text-align: left;
    color: #156FF6;
    font-size: 30px !important;
}

.featured-container {
    background: #f7f8fa;
}
</style>