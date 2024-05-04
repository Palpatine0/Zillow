<template>
    <v-app>
        <v-container class="my-5">
            <v-subheader class="display-1 font-weight-bold mb-3">Property Details: {{ title }}</v-subheader>

            <v-carousel cycle height="400" show-arrows-on-hover>
                <v-carousel-item v-for="(img, index) in detailsInfo.imgs" :key="index">
                    <v-img :src="img" height="400" gradient="to top right, rgba(0,0,0,.1), rgba(0,0,0,.5)">
                        <template v-slot:placeholder>
                            <v-row class="fill-height ma-0" align="center" justify="center">
                                <v-progress-circular indeterminate color="white"></v-progress-circular>
                            </v-row>
                        </template>
                    </v-img>
                </v-carousel-item>
            </v-carousel>

            <v-card shaped tile elevation="12" class="mt-4">
                <v-card-text>
                    <v-container>
                        <v-row>
                            <v-col cols="12" md="6" v-for="item in detailItems" :key="item.title">
                                <v-card class="pa-3" outlined color="#f5f5f5">
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
