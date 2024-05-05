<template>
  <v-app style="padding: 20px">
    <v-subheader as="h1" class="subheading grey--text">Dashboard > Item</v-subheader>
    <div class="center_h mb-5">
      <v-img src="https://assets.leetcode.com/users/images/7da063f0-ed38-4153-9c88-372feaad79d6_1714562261.1995816.jpeg"
             width="200"/>
    </div>
    <Swiper :slides="swiperSlides" :title="title"></Swiper>
    <v-container class="mt-5">
      <v-row style="height: 90px">
        <v-col style="height: 100%">
          <p class="info-head">
            ${{ commasNumber(itemInfo.price) }} / mo

            <!--${{ itemInfo.price }} / mo-->
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
              {{ commasNumber(itemInfo.info.area) }}
              <!--                          {{ itemInfo.info.area }}-->
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
        <v-chip class="ma-2" close-icon="mdi-delete" color="#156ff6" text-color="white">
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
            <v-icon>mdi-compass</v-icon>
            <span>{{ itemInfo.info.orientation }}</span>
          </v-col>
        </v-row>
        <v-row>
          <v-col class="info-cell" cols="6" md="4" sm="6">
            <v-icon>mdi-tools</v-icon>
            <span>Built in {{ itemInfo.info.years }}</span>
          </v-col>
          <v-col class="info-cell" cols="6" md="4" sm="6">
            <v-icon>mdi-home-roof</v-icon>
            <span>{{ itemInfo.rentType }}</span>
          </v-col>
          <v-col class="info-cell" cols="6" md="3" sm="6">
            <v-icon>mdi-stairs</v-icon>
            <span>Floor {{ itemInfo.info.level }}</span>
          </v-col>
        </v-row>
      </v-col>

      <v-col cols="12" md="3" sm="6" style="margin-top: -80px">
        <v-card class="mx-auto center_h" max-width="344" outlined
                style="border-radius: 14px;flex-direction: column">
          <v-btn class="edit-btn mt-5 mb-4" color="#156ff6" dark style="height: 50px!important;"
                 @click="statusUpdate_dialog=!statusUpdate_dialog">
            Edit Status
          </v-btn>
          <v-btn class="edit-btn mb-2" color="#156ff6" dark outlined
                 @click="infoUpdate_dialog=!infoUpdate_dialog">
            Edit Info
          </v-btn>
          <v-btn class="edit-btn mb-5" color="#156ff6" dark outlined
                 @click="showcasesUpdate_dialog=!showcasesUpdate_dialog">
            Edit Showcases
          </v-btn>
        </v-card>
        <v-card class="mx-auto center_h mt-3 mb-3" max-width="344" outlined
                style="border-radius: 14px;flex-direction: column;border-color: #ffa9ac">
          <v-btn class="edit-btn mt-3 mb-3" color="red" dark outlined
                 @click="itemDelete_dialog=!itemDelete_dialog">
            <b>DELETE</b>
          </v-btn>
        </v-card>
      </v-col>
    </v-container>

    <ItemEditStatus :item-info="itemInfo" :statusUpdate_dialog="statusUpdate_dialog"/>
    <ItemEditInfo :infoUpdate_dialog="infoUpdate_dialog" :item-info="itemInfo"/>
    <ItemEditShowcases :item-info="itemInfo" :showcasesUpdate_dialog="showcasesUpdate_dialog"/>

    <v-dialog v-model="itemDelete_dialog" max-width="320" persistent>
      <v-card>
        <v-card-title class="text-h5">
          CONFIRM YOUR OPTION
        </v-card-title>
        <v-card-text>You can't revert this action once deleted.</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="red darken-1" text @click="itemDelete_dialog = false">
            Cancel
          </v-btn>
          <v-btn color="red darken-1" text @click="deleteItemByID(),itemDelete_dialog = false">
            Confirm
          </v-btn>
        </v-card-actions>
      </v-card>

    </v-dialog>

  </v-app>
</template>

<script>
import Swiper from "@/components/Swiper.vue";
import ItemEditStatus from "@/views/Dashboard/Item/Item-editStatus/Item-editStatus.vue";
import ItemEditInfo from "@/views/Dashboard/Item/Item-editInfo/Item-editInfo.vue";
import ItemEditShowcases from "@/views/Dashboard/Item/Item-editShowcases/Item-editShowcases.vue";
import {mapState} from "vuex";

export default {
  name: "Item",
  components: {
    Swiper,
    ItemEditStatus,
    ItemEditInfo,
    ItemEditShowcases
  },
  data() {
    return {
      // page vars
      swiperSlides: [],
      itemInfo: {},
      title: '',

      statusUpdate_dialog: false,
      infoUpdate_dialog: false,
      showcasesUpdate_dialog: false,
      itemDelete_dialog: false
    };
  },
  methods: {
    commasNumber(x) {
      return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
    deleteItemByID() {
      this.$api.deleteItemByID({id: this.$route.params.id})
          .then(data => {
            if (data.data.status == 200) {
              window.history.back();
            }
          });
    }
  },
  mounted() {
    this.$api.getItemByID({id: this.$route.params.id})
        .then(data => {
          this.swiperSlides = data.data.imgs;
          this.itemInfo = data.data;
          this.title = data.data.title;
        });
  },
  computed: {
    ...mapState(['city']),
  }

}
</script>
<style scoped>
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
  font-family: "Roboto Thin", "Helvetica Neue UltraLight", Tahoma, Geneva, sans-serif !important;
  font-size: 20px;
  line-height: 32px;
  font-weight: 100;
  position: relative;
  bottom: 10px;
}

.info-body {
  color: rgb(0, 0, 0);
  text-transform: none;
  font-family: "Roboto Thin", "Helvetica Neue UltraLight", Tahoma, Geneva, sans-serif !important;
  font-size: 24px;
  line-height: 32px;
  font-weight: 100;
  position: relative;
  bottom: 20px;
  color: #848b8d;
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