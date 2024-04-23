<template>
  <swiper :options="swiperOption" class="mySwiper">
    <swiper-slide v-for="(slide, index) in swiperSlides" :key="index">
      <img :src="slide" alt/>
    </swiper-slide>
    <div slot="pagination" class="swiper-pagination"></div>
  </swiper>
</template>
<script>
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
  props: ['swiperSlides']
};
</script>
<style lang="less" scoped>
.mySwiper img {
  width: 100%;
  height: 300px;
}
</style>