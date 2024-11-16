<template>
    <swiper :options="swiperOption" class="mySwiper ">
        <swiper-slide v-for="(slide, index) in swiperSlides" :key="index">
            <img :src="awsS3RequestUrl+slide" alt style="height: 300px"/>
        </swiper-slide>
        <div slot="pagination" class="swiper-pagination"></div>
    </swiper>
</template>
<script>
import {mapState} from "vuex";

export default {
    name: "MySwiper",
    data() {
        return {
            swiperOption: {
                pagination: {
                    el: ".swiper-pagination"
                },
                loop: true,
                autoplay: {
                    delay: 2500,
                    disableOnInteraction: false,
                },
            }
        };
    },
    props: ['swiperSlides'],
    computed: {
        ...mapState(['awsS3RequestUrl']),
    },
    created() {
        if (this.swiperMark == 'home') {

        }
    }
};
</script>
<style lang="less" scoped>
.mySwiper img {
    width: 100%;
}
</style>