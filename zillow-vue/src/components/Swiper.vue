<template>
<v-carousel v-if="isMobile" :show-arrows="false" cycle hide-delimiter-background style="width: 100vw;height: 350px">
    <v-carousel-item
        v-for="(slide,index) in slides"
        :key="index"
    >
        <v-img :height="350" :src="awsS3RequestUrl+slide" aspect-ratio="2"/>
    </v-carousel-item>
</v-carousel>
<v-carousel v-else :show-arrows="false" cycle hide-delimiter-background style="width: 100vw;height: 450px">
    <v-carousel-item
        v-for="(slide,index) in slides"
        :key="index"
    >
        <v-img :height="450" :src="awsS3RequestUrl+slide" aspect-ratio="2"/>
    </v-carousel-item>
</v-carousel>
</template>

<script>
import {mapState} from "vuex";

export default {
    name: "Swiper",

    props: [
        'slides',
        'title'
    ],
    data() {
        return {}
    },

    computed: {
        ...mapState(['awsS3RequestUrl']),
    }

};
</script>
