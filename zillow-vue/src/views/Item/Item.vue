<template>
    <div>
        <span class="back-icon" style="position: absolute;z-index: 2;top: 10px;left: 10px;color: #156FF6" @click='back'>
            <i class="icon-chevron-left"></i>
        </span>

      <div v-if="swiperSlides.length>0" style="position: relative">
            <MySwiper :swiperSlides="swiperSlides"/>
        </div>
        <div v-else>Loading...</div>

        <tabs :currentIndex="currentIndex" class="tabb" @changeCurrentIndex="changeCurrentIndexHandler">
            <tab class="t" index="1" label="Info">
                <template>
                    <div>
                        <div class="detail-info">
                            <h3><b>{{ detailsInfo.title }}</b></h3>
                            <div class="box_infoA">
                                <div class="box_infoA_A">
                                    <div class="info-list_A">
                                        <p class="p_a">Price: </p>
                                        <span> {{ detailsInfo.price }}/Mouth</span>
                                    </div>
                                    <div class="info-list_A">
                                        <p class="p_a">Type: </p>
                                        <span>{{ detailsInfo.info.type }}</span>
                                    </div>
                                    <div class="info-list_A">
                                        <p class="p_a">Area: </p>
                                        <span>{{ detailsInfo.houseType }}</span>
                                    </div>
                                </div>
                            </div>

                            <div class="box_infoB">
                                <div class="info-list_B">
                                    <p>
                                        <b>Floor:</b>
                                        {{ detailsInfo.info.level }}</p>
                                    <p>
                                        <b>Decoration: </b>
                                        {{ detailsInfo.info.style }}</p>
                                </div>
                                <div class="info-list_B">
                                    <p>
                                        <b>Orientation: </b>
                                        {{ detailsInfo.info.orientation }}</p>
                                    <p>
                                        <b>Complete Date: </b>
                                        {{ detailsInfo.info.years }}</p>
                                </div>

                            </div>
                        </div>
                        <DetailsStore :id="id"/>
                    </div>
                </template>
            </tab>
            <tab class="t" index="0" label="Comments">
                <DetailsComment :id='$route.params.id'/>
            </tab>
        </tabs>

    </div>
</template>

<script>
import Header from "../../components/Header/Header";
import MySwiper from "../../components/MySwiper/MySwiper";
import DetailsComment from './ItemComment/ItemComment.vue'
import Tabs from "../../components/tabs/index.js";
import Vue from "vue";
import DetailsStore from "@/views/Item/ItemOption/ItemOption.vue";


Vue.use(Tabs);
export default {
    name: "Details",
    data() {
        return {
            currentIndex: 1,
            swiperSlides: [],
            detailsInfo: {}
        };
    },
    components: {
        DetailsStore,
        MySwiper,
        Header,
        DetailsComment,
    },
    mounted() {
      this.$api.getItemByID({id: this.$route.params.id}).then(data => {
            this.swiperSlides = data.data.imgs;
            this.detailsInfo = data.data;
        });
    },
    methods: {
        changeCurrentIndexHandler(index) {
            this.currentIndex = index;
        },
      back() {
        window.history.back()
        }
    }
};
</script>
<style>
.t {
    font-family: Arial;
    font-size: 16px;
    margin-right: 0px !important;
}

.detail-info {
    * {
        font-family: Arial;
    }

    h3 {
        font-size: 17px;
        color: #000;
        opacity: 0.8;
        margin-top: 20px;
        margin-left: 20px;
        margin-bottom: 20px;
    }

    .box_infoA {
        width: 90%;
        height: 220px;
        border-radius: 8px;
        margin-left: 20px;

        .box_infoA_A {
            position: relative;
            top: 10px;
            left: 10px;

            .info-list_A {
                margin-bottom: 11px;

                p {
                    text-align: left;
                    font-size: 20px;
                    color: #585858;
                    position: relative;
                //font-weight: bold; //top: 10px;
                }

                span {
                    text-align: center;
                    font-size: 18px;
                    color: #156FF6;
                    position: relative;
                    top: 2px;
                    bottom: 10px;
                }
            }

        }

        box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.1);


    }

    .box_infoB {
        margin: 20px;

        .info-list_B {
            display: flex;

            p {
                flex: 1;
                font-size: 15px;
                margin: 5px;

                b {
                    font-size: 14px;
                }
            }
        }
    }

  /* Ensure your Swiper container does not overlap in z-index */

  .swiper-container {
    z-index: 10; /* Lower than the back icon */
  }


}
</style>
