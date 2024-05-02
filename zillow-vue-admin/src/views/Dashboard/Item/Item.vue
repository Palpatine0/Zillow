<template>
    <v-app style="padding: 20px">
        <v-subheader class="subheading grey--text" as="h1">Dashboard > Item</v-subheader>
        <div class="center_h mb-5">
            <v-img width="200" src="https://assets.leetcode.com/users/images/7da063f0-ed38-4153-9c88-372feaad79d6_1714562261.1995816.jpeg"/>
        </div>
        <Swiper :slides="swiperSlides" :title="title"></Swiper>

        <v-container style="float: right;display: flex">
          <div v-if="itemInfo.isRented!=true">
                <v-chip class="ma-2">
                    For rent
                </v-chip>
            </div>
          <div v-else-if="itemInfo.isRented==true">
                <v-chip class="ma-2" color="#156ff6" text-color="white">
                    <v-icon size="16">mdi-checkbox-marked-circle</v-icon>
                    &nbsp;Renting
                </v-chip>
            </div>
          <div v-if="itemInfo.recommendation!=true">
                <v-chip class="ma-2">
                    Set Recommend
                </v-chip>
            </div>
          <div v-else-if="itemInfo.recommendation==true">
                <v-chip class="ma-2" color="#156ff6" text-color="white" close-icon="mdi-delete">
                    <v-icon size="16">mdi-checkbox-marked-circle</v-icon>
                    &nbsp;Recommending
                </v-chip>
            </div>

        </v-container>
        <v-container>
            <div>
                <p style="font-weight: 700;font-size: 32px;line-height: 32px;">
                  ${{ itemInfo.price }} / mo
                </p>
            </div>
        </v-container>


    </v-app>
</template>

<script>
import Swiper from "@/components/Swiper.vue";

export default {
    name: "Details",
    components: {
        Swiper
    },
    data() {
        return {
            swiperSlides: [],
          itemInfo: {},
            title: ''
        };
    },
    mounted() {
      console.log("data")
      this.$api.getItem({id: this.$route.params.id})
        .then(data => {

          console.log(data)
            this.swiperSlides = data.data.imgs;
          this.itemInfo = data.data;
            this.title = data.data.title;
        });
    }

}
</script>
<style>
.center_h {
    justify-content: center;
    align-items: center;
    display: flex;
    margin: 0 auto;
}

* {
    //outline: 1px solid red;
}
</style>