<template>
    <div class="list-item">
        <router-link :to="`/item/${ curdata.id }`">

            <img :src="awsS3RequestUrl+curdata.imgs" alt/>

            <div class="mask">
                <div class="left">
                    <p>{{ curdata.title }}</p>
                    <p>{{ curdata.aptType }}</p>
                </div>
                <div class="right">
                    <div class="btn">{{ curdata.rentType }}</div>
                    <p>
                        <span v-html="curdata.price"></span>$ / Month
                    </p>
                </div>
            </div>
        </router-link>
    </div>
</template>
<script>
import {mapState} from "vuex";

export default {
    name: "Item",

    props: ["curdata"],
    data() {
        return {};
    },

    methods: {
    },
    computed: {
        ...mapState(['awsS3RequestUrl']),
    },
};
</script>

<style lang="less" scoped>
* {
    font-family: Arial;
}

.list-item {


    position: relative;
    margin: 5px 0;

    img {
        width: 100%;
    }

    .mask {
        position: relative;
        width: 100%;
        height: 80px;
        background: rgba(255, 255, 255, 0.8);
        left: 0;
        right: 0;
        bottom: 0px;
        display: flex;
        z-index: 1;

        .left {
            text-align: center;
            padding: 10px 10px;
            flex: 1;

            p:nth-child(1) {
                font-weight: bold;
                font-size: 16px;
            }

            p {
                padding: 5px 10px;
                font-size: 12px;
            }
        }

        .right {
            text-align: center;
            padding: 10px 10px;
            flex: 1;

            p,
            div {
                padding: 5px 10px;
                font-size: 12px;
            }

            .btn {
                border-radius: 5px;
                background: rgba(255, 255, 255, 0.8);
                color: #84a0ae;
            }
        }
    }
}
</style>