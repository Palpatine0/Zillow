<template>
<v-app style="padding: 4vw">
    <Header v-if="isMobile" title='Mine'/>
    <v-container>
        <v-row style="display: flex;justify-content: space-between" cols="12" lg="4" md="6">
            <v-col v-if="!isMobile" cols="12" lg="4" md="6" style="margin-top: 60px">
                <v-row style="position: fixed;top: 30vh;">
                    <v-col cols="4" lg="4" md="6">
                        <v-avatar size="120" class="d-flex justify-center">
                            <v-img :src="awsS3RequestUrl + user.avatar" aspect-ratio="2"></v-img>
                        </v-avatar>
                    </v-col>
                    <v-col cols="8" lg="8" md="6">
                        <h1 class="mt-4" style="margin-left: 20px"><b>{{ user.username }}</b></h1>
                        <div class="" style="margin-left: 20px;display: flex">
                            <v-icon small color="#9e9e9e">fas fa-map-marker-alt</v-icon>
                            <h3 class="ml-2" style="color: #9e9e9e">{{ currentCity }}</h3>
                        </div>
                    </v-col>
                </v-row>
                <v-row style="position: fixed;top: 48vh;width: 26vw">
                    <v-btn depressed @click="signOut" block>
                        <v-icon>mdi-exit-to-app</v-icon>
                    </v-btn>
                </v-row>
            </v-col>
            <v-col v-else cols="12" lg="4" md="6">
                <v-row>
                    <v-col cols="3" lg="3" md="6">
                        <v-avatar size="80" class="d-flex justify-center mb-6 m-1">
                            <v-img :src="awsS3RequestUrl + user.avatar" aspect-ratio="2"></v-img>
                        </v-avatar>
                    </v-col>
                    <v-col cols="8" lg="8" md="6" style="margin-left: 20px;">
                        <h3 style="margin-top: 2vh;font-weight: bold;">{{ user.username }}</h3>
                        <div class="" style="display: flex">
                            <v-icon small color="#9e9e9e">fas fa-map-marker-alt</v-icon>
                            <h4 class="ml-2" style="color: #9e9e9e">{{ currentCity }}</h4>
                        </div>
                    </v-col>
                </v-row>
                <v-row>
                    <v-btn depressed @click="signOut" block>
                        <v-icon>mdi-exit-to-app</v-icon>
                    </v-btn>
                </v-row>
            </v-col>

            <v-col cols="12" lg="6" md="6">
                <PurchasedItemListings/>
            </v-col>

        </v-row>
    </v-container>
</v-app>
</template>
<script>
import {mapState} from "vuex";
import Header from "../../components/Header/Header";
import PurchasedItemListings from "@/views/Mine/PurchasedItemListings/PurchasedItemListings.vue";
import NavBar from "@/components/NavBar/NavBar.vue";

export default {
    name: "Mine",
    data() {
        return {};
    },
    components: {
        Header,
        PurchasedItemListings,
        NavBar
    },
    mounted() {
        if(!this.user) {
            this.$router.push("/login");
        }
    },
    computed: {
        ...mapState([
            "user",
            "awsS3RequestUrl",
            "currentCity"
        ])
    },
    methods: {
        signOut() {
            this.$store.dispatch('clearUserAction');
            this.$router.push("/login");
        }
    },
};
</script>
<style lang="less" scoped>
.svg {
    display: flex;
    align-items: center;
    gap: 0;
}
</style>