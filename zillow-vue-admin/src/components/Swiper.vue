<template>
<v-carousel cycle height="550" hide-delimiter-background show-arrows-on-hover>
    <v-carousel-item
        v-for="(slide, index) in slides"
        :key="index"
    >
        <v-sheet height="100%">
            <v-row align="center" class="fill-height" justify="center">
                <div class="text-h2 rounded-lg">
                    <v-img :src="awsS3RequestUrl+slide" aspect-ratio="2" class="centered-image "/>
                </div>
            </v-row>
        </v-sheet>
    </v-carousel-item>
</v-carousel>
</template>

<script>
import {mapState} from "vuex";

export default {
    name: "Swiper",
    data() {
        return {}
    },
    props: ['slides', 'title'],
    computed: {
        ...mapState(['awsS3RequestUrl']),
    }

};
</script>

<style>
.centered-image {
    position: relative;
    object-fit: cover;
    max-width: 100%;
    width: 1200px;
    top: -70px
}


</style>