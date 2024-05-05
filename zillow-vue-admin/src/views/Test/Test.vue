<template>
  <v-app>
    <v-container class="my-5">
      <v-subheader class="display-1 font-weight-bold mb-3">Property Details: {{ title }}</v-subheader>

      <v-carousel cycle height="400" show-arrows-on-hover>
        <v-carousel-item v-for="(img, index) in detailsInfo.imgs" :key="index">
          <v-img :src="img" gradient="to top right, rgba(0,0,0,.1), rgba(0,0,0,.5)" height="400">
            <template v-slot:placeholder>
              <v-row align="center" class="fill-height ma-0" justify="center">
                <v-progress-circular color="white" indeterminate></v-progress-circular>
              </v-row>
            </template>
          </v-img>
        </v-carousel-item>
      </v-carousel>

      <v-card class="mt-4" elevation="12" shaped tile>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col v-for="item in detailItems" :key="item.title" cols="12" md="6">
                <v-card class="pa-3" color="#f5f5f5" outlined>
                  <div class="subtitle-1">{{ item.title }}</div>
                  <div class="title">{{ item.value }}</div>
                </v-card>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
      </v-card>
    </v-container>
  </v-app>
</template>
