<template>
    <div>
        <Header title="Details"/>

        <div v-if="swiperSlides.length>0">
            <MySwiper :swiperSlides="swiperSlides"/>
        </div>
        <div v-else>Loading...</div>

        <tabs :currentIndex="currentIndex" class="tabb" @changeCurrentIndex="changeCurrentIndexHandler">

            <tab class="t" index="1" label="Comments">
                <DetailsComment :id='$route.params.id'/>
            </tab>

            <tab class="t" index="0" label="Info">
                <div v-if='detailsInfo.title !== undefined'>
                    <DetailsInfo :id='$route.params.id' :detailsInfo='detailsInfo'/>
                </div>
                <div v-else>Loading...</div>
            </tab>

        </tabs>

    </div>
</template>

<script>
import Header from "../../components/Header/Header";
import MySwiper from "../../components/MySwiper/MySwiper";
import DetailsComment from './DetailsComment/DetailsComment'
import DetailsInfo from './DetailsInfo/DetailsInfo'
import Tabs from "../../components/tabs/index.js";
import Vue from "vue";

Vue.use(Tabs);
export default {
    name: "Details",
    data() {
        return {
            swiperSlides: [],
            currentIndex: 1,
            detailsInfo: {}
        };
    },
    components: {
        MySwiper,
        Header,
        DetailsComment,
        DetailsInfo
    },
    mounted() {
        this.$api.getDetails({id: this.$route.params.id}).then(data => {
            this.swiperSlides = data.data.imgs;
            this.detailsInfo = data.data;
        });
    },
    methods: {
        changeCurrentIndexHandler(index) {
            this.currentIndex = index;
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

</style>
