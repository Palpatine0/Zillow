<template>
<div class="service-options">
    <v-container>
        <v-row justify="center" align="center" dense>
            <v-col
                v-for="(option, index) in options"
                :key="index"
                cols="12"
                md="4"
                sm="6"
                class="service-card"
            >
                <v-card outlined class="pa-4" style="margin: 20px;">
                    <div align="center">
                        <v-img :src="awsS3RequestUrl+option.image" class="mb-4" width="160" aspect-ratio="1"></v-img>
                    </div>
                    <v-card-title class="text-h6 font-weight-bold text-center">
                        {{ option.title }}
                    </v-card-title>
                    <v-card-subtitle class="text-center mb-4">
                        {{ option.description }}
                    </v-card-subtitle>
                    <v-card-actions class="center-h">
                        <v-btn color="primary" outlined rounded  @click="homesRedirect">
                            {{ option.buttonText }}
                        </v-btn>
                    </v-card-actions>
                </v-card>
            </v-col>
        </v-row>
    </v-container>
</div>
</template>

<script>
import {mapState} from "vuex";

export default {
    name: "ServiceOptions",
    computed: {
        ...mapState(['awsS3RequestUrl'])
    },
    data() {
        return {
            options: [
                {
                    title: "Buy a home",
                    description: "Find your place with an immersive photo experience and the most listings, including things you won’t find anywhere else.",
                    buttonText: "Browse homes",
                    image: 'public/image/common/views/index/service-options/homepage-spot-agent-lg-1.webp',
                },
                {
                    title: "Rent a home",
                    description: "We’re creating a seamless online experience – from shopping on the largest rental network, to applying, to paying rent.",
                    buttonText: "Find rentals",
                    image: 'public/image/common/views/index/service-options/homepage-spot-rent-lg-1.webp',
                },
            ],
        };
    },
    created() {
        window.xx = this
    },
    methods: {
        // Redirects
        homesRedirect(){
            this.$router.push('/homes')
        }
    },
};
</script>

<style scoped lang="less">
.service-options {
    background: rgb(247, 247, 247);
    padding: 24px 0;

    .service-card {
        display: flex;
        justify-content: center;
    }

    .v-card {
        border-radius: 16px;
        box-shadow: 0px 2px 6px rgba(0, 0, 0, 0.1);
        transition: box-shadow 0.3s ease;

        &:hover {
            box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.15);
        }
    }

    .v-img {
        border-radius: 12px;
    }

    .v-btn {
        text-transform: none;
        font-size: 16px;
        font-weight: bold;
    }
}
</style>
