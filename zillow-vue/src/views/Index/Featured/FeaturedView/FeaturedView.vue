<template>
<div>
    <v-container class="featured-title">
        <h1>{{ title }}</h1>
        <div v-if="!isMobile" @click="listingRedirect">
            Search All Homes
            <v-icon size="20" color="primary">fas fa-angle-double-right</v-icon>
        </div>
    </v-container>
    <v-container v-if="!isMobile" class="featured-container" style="max-width: 78vw;border-radius: 15px;padding: 20px;">
        <v-row>
            <v-col cols="6" lg="6" md="6" @click="itemRedirect(itemData[0].id)">
                <v-img :src='awsS3RequestUrl+itemData[0].imgs[0]' class="white--text align-end rounded-lg" height="38vh"/>
                <div class="center-h"><span>{{ itemData[0].title }}</span></div>
            </v-col>
            <v-col cols="6" lg="6" md="6" @click="itemRedirect(itemData[1].id)">
                <v-img :src='awsS3RequestUrl+itemData[1].imgs[0]' class="white--text align-end rounded-lg" height="38vh"/>
                <div class="center-h"><span>{{ itemData[1].title }}</span></div>
            </v-col>
        </v-row>
        <v-row>
            <v-col cols="6" lg="6" md="6" @click="itemRedirect(itemData[2].id)">
                <v-img :src='awsS3RequestUrl+itemData[2].imgs[0]' class="white--text align-end rounded-lg" height="38vh"/>
                <div class="center-h"><span>{{ itemData[2].title }}</span></div>
            </v-col>
            <v-col cols="6" lg="6" md="6" @click="itemRedirect(itemData[3].id)">
                <v-img :src='awsS3RequestUrl+itemData[3].imgs[0]' class="white--text align-end rounded-lg" height="38vh"/>
                <div class="center-h"><span>{{ itemData[3].title }}</span></div>
            </v-col>
        </v-row>
    </v-container>
    <v-container v-else class="featured-container">
        <v-row>
            <v-col cols="6" lg="6" md="6" @click="itemRedirect(itemData[0].id)">
                <v-img :src='awsS3RequestUrl+itemData[0].imgs[0]' class="white--text align-end rounded-lg" height="100px"/>
                <div class="center-h"><span>{{ itemData[0].title }}</span></div>
            </v-col>
            <v-col cols="6" lg="6" md="6" @click="itemRedirect(itemData[1].id)">
                <v-img :src='awsS3RequestUrl+itemData[1].imgs[0]' class="white--text align-end rounded-lg" height="100px"/>
                <div class="center-h"><span>{{ itemData[1].title }}</span></div>
            </v-col>
        </v-row>
        <v-row>
            <v-col cols="6" lg="6" md="6" @click="itemRedirect(itemData[2].id)">
                <v-img :src='awsS3RequestUrl+itemData[2].imgs[0]' class="white--text align-end rounded-lg" height="100px"/>
                <div class="center-h"><span>{{ itemData[2].title }}</span></div>
            </v-col>
            <v-col cols="6" lg="6" md="6" @click="itemRedirect(itemData[3].id)">
                <v-img :src='awsS3RequestUrl+itemData[3].imgs[0]' class="white--text align-end rounded-lg" height="100px"/>
                <div class="center-h"><span>{{ itemData[3].title }}</span></div>
            </v-col>
        </v-row>
    </v-container>
</div>
</template>

<script>
import {mapState} from "vuex";

export default {
    name: 'FeaturedView',

    props: [
        'title',
        'itemData'
    ],
    data() {
        return {}
    },

    computed: {
        ...mapState([
            'awsS3RequestUrl'
        ]),
    },
    methods: {
        // Redirects
        itemRedirect(id) {
            this.$router.push('/item/' + id);
        },
        listingRedirect() {
            this.$router.push("/homes");
        },
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

.featured-title {
    max-width: 84vw;
    justify-content: space-between;
    display: flex;
}

.featured-title div{
    width: 200px;
    font-weight: bold;
    position: relative;
    top: 20px;
    right: 1vw;
}

.featured-container {
    background: #f7f8fa;
}

.featured-container span {
    font-weight: bold;
}

@media screen and (max-width: 768px) {
    .featured-title {
        max-width: 100vw;
        padding: 0px;
    }

    .featured-container span {
        font-size: 12px;
    }
}
</style>