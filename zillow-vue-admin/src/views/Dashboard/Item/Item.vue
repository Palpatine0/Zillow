<template>
    <v-app style="padding: 20px">
        <v-subheader class="subheading grey--text" as="h1">Dashboard > Item</v-subheader>
        <div class="center_h mb-5">
            <v-img width="200" src="https://assets.leetcode.com/users/images/7da063f0-ed38-4153-9c88-372feaad79d6_1714562261.1995816.jpeg"/>
        </div>
        <Swiper :slides="swiperSlides" :title="title"></Swiper>


        <v-container>
            <v-row style="height: 90px">
                <v-col style="height: 100%">
                    <p class="info-head">
                        ${{ itemInfo.price }} / mo
                    </p>
                    <p class="title">{{ itemInfo.title }}</p>
                </v-col>
                <div style="display: flex;width: 30%;height: 100%">
                    <v-col cols="6" md="4" sm="6">
                        <p class="info-head">
                            {{ itemInfo.info.beds }}
                        </p>
                        <p class="info-body">Beds</p>
                    </v-col>
                    <v-col cols="6" md="4" sm="6">
                        <p class="info-head">
                            {{ itemInfo.info.baths }}
                        </p>
                        <p class="info-body">Baths</p>
                    </v-col>
                    <v-col cols="6" md="4" sm="6">
                        <p class="info-head">
                            {{ itemInfo.info.area }}
                        </p>
                        <p class="info-body">sqft</p>
                    </v-col>
                </div>
            </v-row>
        </v-container>

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

      <v-container style="display: flex">
        <v-col cols="12" md="9" sm="6">
          <v-row>
            <v-col class="info-cell" cols="6" md="4" sm="6">
              <v-icon>fa-city</v-icon>
              <span>{{ itemInfo.info.type }}</span>
            </v-col>
            <v-col class="info-cell" cols="6" md="4" sm="6">
              <v-icon>mdi-border-style</v-icon>
              <span>{{ itemInfo.info.style }}</span>
            </v-col>
            <v-col class="info-cell" cols="6" md="3" sm="6">
              <v-icon>fa-hammer</v-icon>
              <span>{{ itemInfo.info.years }}</span>
            </v-col>
          </v-row>
          <v-row>
            <v-col class="info-cell" cols="6" md="4" sm="6">
              <v-icon>mdi-compass</v-icon>
              <span>{{ itemInfo.info.orientation }}</span>
            </v-col>
            <v-col class="info-cell" cols="6" md="4" sm="6">
              <v-icon>mdi-home-roof</v-icon>
              <span>{{ itemInfo.rentType }}</span>
            </v-col>
            <v-col class="info-cell" cols="6" md="3" sm="6">
              <v-icon>mdi-stairs</v-icon>
              <span>{{ itemInfo.info.level }}</span>
            </v-col>
          </v-row>
        </v-col>


        <v-col cols="12" md="3" sm="6" style="margin-top: -50px">
          <v-card class="mx-auto center_h" max-width="344" outlined style="border-radius: 14px;flex-direction: column">
            <v-btn class="edit-btn mt-5 mb-4" color="#156ff6" dark style="height: 50px!important;"
                   @click="updateStatus_dialog=!updateStatus_dialog">
              Edit Status
            </v-btn>
            <v-btn class="edit-btn mb-5" color="#156ff6" dark outlined>
              Edit Info
            </v-btn>
          </v-card>
        </v-col>
        </v-container>
      <ItemEditStatus :item-info="itemInfo" :update-status_dialog="updateStatus_dialog"/>


    </v-app>
</template>

<script>
import Swiper from "@/components/Swiper.vue";
import ItemEditStatus from "@/views/Dashboard/Item/Item-editStatus/Item-editStatus.vue";

export default {
    name: "Details",
    components: {
      Swiper,
      ItemEditStatus
    },
    data() {
        return {

          // page vars
            swiperSlides: [],
            itemInfo: {},
          title: '',
          updateStatus_dialog: false
        };
    },
    mounted() {
        console.log("data")
        this.$api.getItem({id: this.$route.params.id})
        .then(data => {
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

.info-head {
    font-weight: 700;
    font-size: 32px;
    line-height: 32px;
}

.title {
    color: rgb(108, 108, 112);
    text-transform: none;
    font-family: "Roboto Thin", "Helvetica Neue UltraLight", Tahoma, Geneva, sans-serif;
    font-size: 20px;
    line-height: 32px;
    font-weight: 100;
    position: relative;
    bottom: 10px;
}

.info-body {
    color: rgb(0, 0, 0);
    text-transform: none;
    font-family: "Roboto Thin", "Helvetica Neue UltraLight", Tahoma, Geneva, sans-serif;
    font-size: 20px;
    line-height: 32px;
    font-weight: 100;
    position: relative;
    bottom: 10px;
}

.info-cell {
    background-color: #efeff5;
  margin: 4px;
    border-radius: 10px;
}

.info-cell span {
    margin-left: 6px;
    position: relative;
    top: 2px;
}

.edit-btn {
  display: block;
  width: 90%;
  height: 40px !important;
}

* {
  //outline: 1px solid red;
}
</style>