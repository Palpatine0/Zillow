<template>
<div>
    <v-row v-if="!isMobile" style="margin: 20px"  @click="itemRedirect(itemData.id)">
        <v-row>
            <v-img :src="awsS3RequestUrl+itemData.imgs" width="25vw" height="35vh" style="border-radius: 15px"></v-img>
        </v-row>
        <v-row>
            <v-col cols="6" lg="6" md="6" class="item-info">
                <p class="name">{{ itemData.title }}</p>
            </v-col>
            <v-col cols="6" lg="6" md="6" class="item-info" style="font-size: 14px">
                <div class="rent-type">{{ itemData.rentType }}</div>
                <div class="price">{{ itemData.price }}$ / Month</div>
            </v-col>
        </v-row>
    </v-row>
    <v-row v-else style="margin: -20px" @click="itemRedirect(itemData.id)">
        <v-row>
            <v-img :src="awsS3RequestUrl+itemData.imgs" style="width: 100px"></v-img>
        </v-row>
        <v-row>
            <v-col cols="6" lg="6" md="6" class="item-info">
                <p class="name">{{ itemData.title }}</p>
            </v-col>
            <v-col cols="6" lg="6" md="6" class="item-info" style="font-size: 14px">
                <div class="rent-type">{{ itemData.rentType }}</div>
                <div class="price">{{ itemData.price }}$ / Month</div>
            </v-col>
        </v-row>
    </v-row>
</div>
</template>
<script>
import {mapState} from "vuex";

export default {
    name: "Item",

    props: {
        itemData: {
            type: Object,
            required: true
        }
    },
    data() {
        return {};
    },

    computed: {
        ...mapState([
            'awsS3RequestUrl'
        ]),
    },
    methods: {
        itemRedirect(id) {
            this.$router.push('/item/' + id);
        },
    },

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
        font-size: 20px;
    }

    .rent-type {
        font-size: 18px;
        font-weight: bold;
        text-align: center;
        border-radius: 5px;
        background: rgba(255, 255, 255, 0.8);
        color: #84a0ae;
    }

    .price{
        font-size: 16px;
    }

    @media screen and (max-width: 768px) {
        .name {
            font-size: 14px;
        }

        .rent-type {
            font-size: 14px;
        }

        .price{
            font-size: 13px;
        }
    }
}
</style>