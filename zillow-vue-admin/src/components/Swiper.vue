<template>
    <v-carousel cycle height="400" show-arrows-on-hover hide-delimiter-background>
        <v-carousel-item v-for="(slide, i) in slides" :key="i">
            <v-sheet height="100%">
                <v-row class="fill-height" align="center" justify="center">
                    <div class="text-h2 rounded-lg">
                        <v-img class="centered-image " :src="slide"/>
                    </div>
                </v-row>
            </v-sheet>
        </v-carousel-item>
        <!--<v-card-title class="bottom-title">{{ title }}</v-card-title>-->
    </v-carousel>
</template>

<script>
export default {
    name: "Swiper",
    data() {
        return {}
    },
    props: ['slides', 'title']

};
</script>

<style>
.centered-image {
    position: relative;
    object-fit: cover;
    max-width: 100%;
    width: 100%;
    height: 100%;
}

.bottom-title {
    font-size: 26px;
    position: absolute;
    bottom: -8px;
    color: white;
    z-index: 10000;
}
</style>