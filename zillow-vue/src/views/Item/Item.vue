<template>
<v-app>
    <span class="back-icon" style="position: absolute;z-index: 2;top: 10px;left: 10px;color: #156FF6" @click='back'>
        <i class="icon-chevron-left"></i>
    </span>

    <div v-if="swiperSlides.length>0" style="position: relative">
        <Swiper :slides="swiperSlides"/>
    </div>

    <div v-else>
        <div style="padding: 10px;color: gainsboro;" align="center">
            Loading...
        </div>
    </div>

    <tabs :currentIndex="currentIndex" class="tabb" @changeCurrentIndex="changeCurrentIndexHandler">
        <tab class="t" index="1" label="Info">

            <v-container style="max-width: 98vw;">
                <v-row cols="12" lg="12" md="12" class="detail-info">
                    <v-col cols="12" lg="8" md="6">
                        <v-row><h1 class="mt-4">${{ commasNumber(itemInfo.price) }} / mo</h1></v-row>
                        <v-row>
                            <div class="box_infoA mt-3">
                                <span class="basic-info">
                                    <svg xmlns="http://www.w3.org/2000/svg" height="14" width="17.5" viewBox="0 0 640 512">
                                        <path fill="#c4c6d1"
                                              d="M64 160C64 89.3 121.3 32 192 32H448c70.7 0 128 57.3 128 128v33.6c-36.5 7.4-64 39.7-64 78.4v48H128V272c0-38.7-27.5-71-64-78.4V160zM544 272c0-20.9 13.4-38.7 32-45.3c5-1.8 10.4-2.7 16-2.7c26.5 0 48 21.5 48 48V448c0 17.7-14.3 32-32 32H576c-17.7 0-32-14.3-32-32H96c0 17.7-14.3 32-32 32H32c-17.7 0-32-14.3-32-32V272c0-26.5 21.5-48 48-48c5.6 0 11 1 16 2.7c18.6 6.6 32 24.4 32 45.3v48 32h32H512h32V320 272z"/>
                                    </svg>
                                    <b>{{ itemInfo.info.beds }}</b> Beds
                                </span>
                                <span class="basic-info">
                                    <svg xmlns="http://www.w3.org/2000/svg" height="14" width="14" viewBox="0 0 512 512">
                                        <path fill="#c4c6d1"
                                              d="M96 77.3c0-7.3 5.9-13.3 13.3-13.3c3.5 0 6.9 1.4 9.4 3.9l14.9 14.9C130 91.8 128 101.7 128 112c0 19.9 7.2 38 19.2 52c-5.3 9.2-4 21.1 3.8 29c9.4 9.4 24.6 9.4 33.9 0L289 89c9.4-9.4 9.4-24.6 0-33.9c-7.9-7.9-19.8-9.1-29-3.8C246 39.2 227.9 32 208 32c-10.3 0-20.2 2-29.2 5.5L163.9 22.6C149.4 8.1 129.7 0 109.3 0C66.6 0 32 34.6 32 77.3V256c-17.7 0-32 14.3-32 32s14.3 32 32 32H480c17.7 0 32-14.3 32-32s-14.3-32-32-32H96V77.3zM32 352v16c0 28.4 12.4 54 32 71.6V480c0 17.7 14.3 32 32 32s32-14.3 32-32V464H384v16c0 17.7 14.3 32 32 32s32-14.3 32-32V439.6c19.6-17.6 32-43.1 32-71.6V352H32z"/>
                                    </svg>
                                    <b>{{ itemInfo.info.baths }}</b> Baths
                                </span>
                                <span class="basic-info">
                                    <svg xmlns="http://www.w3.org/2000/svg" height="14" width="14" viewBox="0 0 512 512">
                                        <path fill="#c4c6d1"
                                              d="M.2 468.9C2.7 493.1 23.1 512 48 512l96 0 320 0c26.5 0 48-21.5 48-48l0-96c0-26.5-21.5-48-48-48l-48 0 0 80c0 8.8-7.2 16-16 16s-16-7.2-16-16l0-80-64 0 0 80c0 8.8-7.2 16-16 16s-16-7.2-16-16l0-80-64 0 0 80c0 8.8-7.2 16-16 16s-16-7.2-16-16l0-80-80 0c-8.8 0-16-7.2-16-16s7.2-16 16-16l80 0 0-64-80 0c-8.8 0-16-7.2-16-16s7.2-16 16-16l80 0 0-64-80 0c-8.8 0-16-7.2-16-16s7.2-16 16-16l80 0 0-48c0-26.5-21.5-48-48-48L48 0C21.5 0 0 21.5 0 48L0 368l0 96c0 1.7 .1 3.3 .2 4.9z"/>
                                    </svg>
                                    <b>{{ itemInfo.info.area }}</b> sqft
                                </span>
                            </div>
                        </v-row>
                        <v-row>
                            <div class="box_infoA mt-1">
                                {{ itemInfo.title }}
                            </div>
                        </v-row>
                        <v-row>
                            <v-col class="box_infoB">
                                <v-row>
                                    <v-col>
                                        <svg xmlns="http://www.w3.org/2000/svg" height="20" width="25" viewBox="0 0 640 512">
                                            <path
                                                d="M480 48c0-26.5-21.5-48-48-48H336c-26.5 0-48 21.5-48 48V96H224V24c0-13.3-10.7-24-24-24s-24 10.7-24 24V96H112V24c0-13.3-10.7-24-24-24S64 10.7 64 24V96H48C21.5 96 0 117.5 0 144v96V464c0 26.5 21.5 48 48 48H304h32 96H592c26.5 0 48-21.5 48-48V240c0-26.5-21.5-48-48-48H480V48zm96 320v32c0 8.8-7.2 16-16 16H528c-8.8 0-16-7.2-16-16V368c0-8.8 7.2-16 16-16h32c8.8 0 16 7.2 16 16zM240 416H208c-8.8 0-16-7.2-16-16V368c0-8.8 7.2-16 16-16h32c8.8 0 16 7.2 16 16v32c0 8.8-7.2 16-16 16zM128 400c0 8.8-7.2 16-16 16H80c-8.8 0-16-7.2-16-16V368c0-8.8 7.2-16 16-16h32c8.8 0 16 7.2 16 16v32zM560 256c8.8 0 16 7.2 16 16v32c0 8.8-7.2 16-16 16H528c-8.8 0-16-7.2-16-16V272c0-8.8 7.2-16 16-16h32zM256 176v32c0 8.8-7.2 16-16 16H208c-8.8 0-16-7.2-16-16V176c0-8.8 7.2-16 16-16h32c8.8 0 16 7.2 16 16zM112 160c8.8 0 16 7.2 16 16v32c0 8.8-7.2 16-16 16H80c-8.8 0-16-7.2-16-16V176c0-8.8 7.2-16 16-16h32zM256 304c0 8.8-7.2 16-16 16H208c-8.8 0-16-7.2-16-16V272c0-8.8 7.2-16 16-16h32c8.8 0 16 7.2 16 16v32zM112 320H80c-8.8 0-16-7.2-16-16V272c0-8.8 7.2-16 16-16h32c8.8 0 16 7.2 16 16v32c0 8.8-7.2 16-16 16zm304-48v32c0 8.8-7.2 16-16 16H368c-8.8 0-16-7.2-16-16V272c0-8.8 7.2-16 16-16h32c8.8 0 16 7.2 16 16zM400 64c8.8 0 16 7.2 16 16v32c0 8.8-7.2 16-16 16H368c-8.8 0-16-7.2-16-16V80c0-8.8 7.2-16 16-16h32zm16 112v32c0 8.8-7.2 16-16 16H368c-8.8 0-16-7.2-16-16V176c0-8.8 7.2-16 16-16h32c8.8 0 16 7.2 16 16z"/>
                                        </svg>
                                        <span>{{ itemInfo.info.type }}</span>
                                    </v-col>
                                    <div class="col">
                                        <svg xmlns="http://www.w3.org/2000/svg" height="20" width="20" viewBox="0 0 512 512">
                                            <path
                                                d="M343.9 213.4L245.3 312l65.4 65.4c7.9 7.9 11.1 19.4 8.4 30.3s-10.8 19.6-21.5 22.9l-256 80c-11.4 3.5-23.8 .5-32.2-7.9S-2.1 481.8 1.5 470.5l80-256c3.3-10.7 12-18.9 22.9-21.5s22.4 .5 30.3 8.4L200 266.7l98.6-98.6c-14.3-14.6-14.2-38 .3-52.5l95.4-95.4c26.9-26.9 70.5-26.9 97.5 0s26.9 70.5 0 97.5l-95.4 95.4c-14.5 14.5-37.9 14.6-52.5 .3z"/>
                                        </svg>
                                        Build in <span>{{ itemInfo.info.years }}</span>
                                    </div>
                                </v-row>
                                <v-row>
                                    <v-col>
                                        <svg xmlns="http://www.w3.org/2000/svg" height="20" width="17.5" viewBox="0 0 448 512">
                                            <path
                                                d="M0 448c0 17.7 14.3 32 32 32s32-14.3 32-32l0-336c0-8.8 7.2-16 16-16l336 0c17.7 0 32-14.3 32-32s-14.3-32-32-32L80 32C35.8 32 0 67.8 0 112L0 448zm160 0a32 32 0 1 0 -64 0 32 32 0 1 0 64 0zm192 0a32 32 0 1 0 -64 0 32 32 0 1 0 64 0zm-96 0a32 32 0 1 0 -64 0 32 32 0 1 0 64 0zm192 0a32 32 0 1 0 -64 0 32 32 0 1 0 64 0zM416 288a32 32 0 1 0 0-64 32 32 0 1 0 0 64zm0 32a32 32 0 1 0 0 64 32 32 0 1 0 0-64zm0-128a32 32 0 1 0 0-64 32 32 0 1 0 0 64z"/>
                                        </svg>
                                        <span>{{ itemInfo.info.style }}</span>
                                    </v-col>
                                    <v-col>
                                        <svg xmlns="http://www.w3.org/2000/svg" height="20" width="20" viewBox="0 0 512 512">
                                            <path
                                                d="M464 256A208 208 0 1 0 48 256a208 208 0 1 0 416 0zM0 256a256 256 0 1 1 512 0A256 256 0 1 1 0 256zm306.7 69.1L162.4 380.6c-19.4 7.5-38.5-11.6-31-31l55.5-144.3c3.3-8.5 9.9-15.1 18.4-18.4l144.3-55.5c19.4-7.5 38.5 11.6 31 31L325.1 306.7c-3.2 8.5-9.9 15.1-18.4 18.4zM288 256a32 32 0 1 0 -64 0 32 32 0 1 0 64 0z"/>
                                        </svg>
                                        <span>{{ itemInfo.info.orientation }}</span>
                                    </v-col>
                                </v-row>
                                <v-row>
                                    <v-col>
                                        <svg xmlns="http://www.w3.org/2000/svg" height="20" width="25" viewBox="0 0 640 512">
                                            <path
                                                d="M335.5 4l288 160c15.4 8.6 21 28.1 12.4 43.5s-28.1 21-43.5 12.4L320 68.6 47.5 220c-15.4 8.6-34.9 3-43.5-12.4s-3-34.9 12.4-43.5L304.5 4c9.7-5.4 21.4-5.4 31.1 0zM320 160a40 40 0 1 1 0 80 40 40 0 1 1 0-80zM144 256a40 40 0 1 1 0 80 40 40 0 1 1 0-80zm312 40a40 40 0 1 1 80 0 40 40 0 1 1 -80 0zM226.9 491.4L200 441.5V480c0 17.7-14.3 32-32 32H120c-17.7 0-32-14.3-32-32V441.5L61.1 491.4c-6.3 11.7-20.8 16-32.5 9.8s-16-20.8-9.8-32.5l37.9-70.3c15.3-28.5 45.1-46.3 77.5-46.3h19.5c16.3 0 31.9 4.5 45.4 12.6l33.6-62.3c15.3-28.5 45.1-46.3 77.5-46.3h19.5c32.4 0 62.1 17.8 77.5 46.3l33.6 62.3c13.5-8.1 29.1-12.6 45.4-12.6h19.5c32.4 0 62.1 17.8 77.5 46.3l37.9 70.3c6.3 11.7 1.9 26.2-9.8 32.5s-26.2 1.9-32.5-9.8L552 441.5V480c0 17.7-14.3 32-32 32H472c-17.7 0-32-14.3-32-32V441.5l-26.9 49.9c-6.3 11.7-20.8 16-32.5 9.8s-16-20.8-9.8-32.5l36.3-67.5c-1.7-1.7-3.2-3.6-4.3-5.8L376 345.5V400c0 17.7-14.3 32-32 32H296c-17.7 0-32-14.3-32-32V345.5l-26.9 49.9c-1.2 2.2-2.6 4.1-4.3 5.8l36.3 67.5c6.3 11.7 1.9 26.2-9.8 32.5s-26.2 1.9-32.5-9.8z"/>
                                        </svg>
                                        <span>{{ itemInfo.rentType }}</span>
                                    </v-col>
                                    <v-col>
                                        <svg xmlns="http://www.w3.org/2000/svg" height="20" width="22.5" viewBox="0 0 576 512">
                                            <path
                                                d="M384 64c0-17.7 14.3-32 32-32H544c17.7 0 32 14.3 32 32s-14.3 32-32 32H448v96c0 17.7-14.3 32-32 32H320v96c0 17.7-14.3 32-32 32H192v96c0 17.7-14.3 32-32 32H32c-17.7 0-32-14.3-32-32s14.3-32 32-32h96V320c0-17.7 14.3-32 32-32h96V192c0-17.7 14.3-32 32-32h96V64z"/>
                                        </svg>
                                        <span>{{ itemInfo.info.level }}</span>
                                    </v-col>
                                </v-row>
                            </v-col>

                        </v-row>
                    </v-col>
                    <v-col cols="12" lg="4" md="6" class="mb-10">


                        <v-card class="mx-auto " max-width="344" outlined style="border-radius: 14px;padding: 20px">
                            <v-btn block color="primary" @click="itemPurchase_dialog=!itemPurchase_dialog">
                                Rent
                            </v-btn>
                        </v-card>

                        <div class="center-h mt-10">
                            <div class="box">
                                <img src="../../assets/images/advise-c.png">
                                <div class="content">
                                    <h5>About Zillow Home Loans</h5>
                                    <p>learn what services and loan types we offer, and hear from happy customers</p>
                                </div>
                            </div>
                        </div>
                    </v-col>
                </v-row>
            </v-container>
        </tab>

        <tab class="t" index="0" label="Comments">
            <DetailsComment :itemId='itemId'/>
        </tab>
    </tabs>

    <ItemPurchase :itemPurchase_dialog="itemPurchase_dialog" :itemId="itemId" :price="itemInfo.price"/>
</v-app>
</template>

<script>
import Vue from "vue";
import Header from "../../components/Header/Header";
import Swiper from "@/components/Swiper.vue";
import Tabs from "../../components/tabs/index.js";
import DetailsComment from './ItemComment/ItemComment.vue'
import ItemPurchase from "@/views/Item/Item-purchase/Item-purchase.vue";

Vue.use(Tabs);
export default {
    name: "Details",
    data() {
        return {
            currentIndex: 1,
            swiperSlides: [],
            itemInfo: {},
            itemId: this.$route.params.id,

            itemPurchase_dialog: false
        };
    },
    components: {
        Swiper,
        Header,
        ItemPurchase,
        DetailsComment,
    },
    mounted() {
        this.getItemById()
    },

    methods: {
        changeCurrentIndexHandler(index) {
            this.currentIndex = index;
        },
        commasNumber(x) {
            return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        },
        back() {
            window.history.back()
        },

        getItemById(){
            this.$api.getItemById({
                id: this.itemId
            }).then(data => {
                this.swiperSlides = data.data.imgs;
                this.itemInfo = data.data;
            });
        }
    }
};
</script>
<style>

.center-h {
    justify-content: center;
    align-items: center;
    display: flex;
    margin: 0 auto;
}

.t {
    font-size: 16px;
    margin-right: 0px !important;
}

.detail-info {
    h1 {
        font-weight: 700;
        font-size: 32px;
        line-height: 32px;

        color: #000;
        opacity: 0.8;
        margin: 0 5vw;
    }

    .box_infoA {
        display: flex;
        height: 25px;
        margin: 0 5vw;

        .basic-info {
            margin-right: 10px;

            svg {
                margin-right: 4px;
            }
        }
    }

    .box_infoB {
        margin: 4vh 5vw;

        .row {
            display: flex;
            justify-content: space-between;

            .col {
                background-color: #efeff5;
                border-radius: 4px;
                width: 40%;
                height: 60px;
                margin: 4px;
                display: flex; /* This will align children (SVG and text) in a row */
                align-items: center; /* This will center the items vertically */
                justify-content: start; /* Aligns items to the start of the flex container */
                gap: 10px;
            }

            svg {
                height: 20px; /* Adjust based on your preference */
                width: auto;
            }
        }
    }
}

.box {
    width: 90%;
    border-radius: 5px;
    box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.2);
    padding: 20px; /* This adds padding on all sides */
    position: relative; /* Ensures .content is positioned inside .box */
    margin-bottom: 20px;

    img {
        position: absolute;
        width: 50px;
    }

    .content {
        width: 80%;
        margin-left: 65px;

        h5 {
            width: 60%;
            margin: 0;
            color: #333;
            font-size: 13px;
            font-weight: bold;
            padding-bottom: 8px;
        }

        p {
            width: 70%; /* Width of the paragraph is 70% of its container, which is .content */
            color: #848b8d;
            font-size: 14px;
            line-height: 1.2;
        }
    }
}


</style>
