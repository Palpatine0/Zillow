<template>
<div>
    <router-link :to="`/item/${ itemData.id }`">
        <v-row v-if="!isMobile" style="margin: 20px">
            <v-row>
                <v-img :src="awsS3RequestUrl+itemData.imgs" width="25vw" height="35vh" style="border-radius: 15px"></v-img>
            </v-row>
            <v-row>
                <v-col cols="6" lg="6" md="6" class="item-info">
                    <p class="name">{{ itemData.title }}</p>
                </v-col>
                <v-col cols="6" lg="6" md="6" class="item-info" style="font-size: 14px">
                    <div class="rent-type">{{ itemData.rentType }}</div>
                    <div>{{ itemData.price }}$ / Month</div>
                </v-col>
            </v-row>
        </v-row>
        <v-row v-else style="margin: -20px">
            <v-row>
                <v-img :src="awsS3RequestUrl+itemData.imgs" style="width: 100px"></v-img>
            </v-row>
            <v-row>
                <v-col cols="6" lg="6" md="6" class="item-info">
                    <p class="name">{{ itemData.title }}</p>
                </v-col>
                <v-col cols="6" lg="6" md="6" class="item-info" style="font-size: 14px">
                    <div class="rent-type">{{ itemData.rentType }}</div>
                    <div>{{ itemData.price }}$ / Month</div>
                </v-col>
            </v-row>
        </v-row>
    </router-link>
</div>
</template>
<script>
import {mapState} from "vuex";

export default {
    name: "Item",

    computed: {
        ...mapState(['awsS3RequestUrl']),
    },
    props: {
        itemData: {
            type: Object,
            required: true
        }
    },
    data() {
        return {};
    },

    methods: {},

};
</script>

<style lang="less" scoped>
* {
    font-family: Arial;
}

.item-info {
    text-align: center;

    .name {
        color: #156FF6;
        font-weight: bold;
    }

    .rent-type {
        text-align: center;
        border-radius: 5px;
        background: rgba(255, 255, 255, 0.8);
        color: #84a0ae;
    }
}
</style>