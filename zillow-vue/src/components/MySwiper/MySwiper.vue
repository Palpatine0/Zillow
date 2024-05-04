<template>
    <swiper :options="swiperOption" class="mySwiper ">
        <swiper-slide v-for="(slide, index) in swiperSlides" :key="index">
          <img :src="img_prefix+slide" alt/>
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
                autoplay: true
            }
        };
    },
  props: ['swiperSlides'],
  computed: {
    ...mapState(['img_prefix']),
  }
};
</script>
<style lang="less" scoped>
.mySwiper img {
    width: 100%;
}
</style>