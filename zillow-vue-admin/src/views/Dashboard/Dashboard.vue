<template>
    <v-app style="padding: 20px">
        <v-subheader class="subheading grey--text" as="h1">Dashboard</v-subheader>
        <v-container>
            <v-row style="justify-content: space-between">
                <v-col cols="2">
                    <v-select :items="cities" label="Standard" v-model="selectedCity"></v-select>

                </v-col>
                <div class="mt-5">
                    <v-pagination class="float-right" :length="pagination" v-model="page" @input="adminGetItemByCity" color="#156ff6"></v-pagination>
                </div>
            </v-row>
            <v-row>
                <v-col cols="12" md="6" lg="4" v-for="(item, index) in searchListData" :key="index">
                    <v-card class="mx-auto" max-width="400" :href="item.link">
                        <v-img class="white--text align-end" height="200px" :src="img_prefix+item.img">
                            <v-card-title>{{ getHeadAddr(item.title) }}</v-card-title>
                        </v-img>
                        <v-card-subtitle class="pb-0">
                            {{ getSubAddr(item.title) }}
                        </v-card-subtitle>
                        <v-card-text class="text--primary">
                            <b>
                                <div>{{ item.rentType }}</div>
                            </b>
                            <div>{{ item.aptType }}</div>
                        </v-card-text>
                        <v-card-actions>
                            <v-btn color="orange" text>
                                Share
                            </v-btn>
                            <v-btn color="orange" text>
                                Explore
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                </v-col>
            </v-row>

        </v-container>

        <DashboardAddItem></DashboardAddItem>




    </v-app>
</template>

<script>
import {mapState} from "vuex";
import DashboardAddItem from "@/views/Dashboard/Dashboard-AddItem/Dashboard-AddItem.vue";
export default {
    data() {
        return {
            // l1
            title: '',
            sales: 300,
            recommendation: false,
            weight: 0,
            price: '',
            city: this.$store.state.city,
            rentType: '',
            houseType: '',
            buytime: '',
            isRented: false,
            link: '',
            img: '',
            houseType4Search: '',
            // info
            orientation: '',
            level: '',
            style: '',
            type: '',
            years: '',


            //
            page: 1,

            pagination: 0,
            totalCount_item: 0,
            searchListData: [],
        }
    },
    methods: {
        adminGetItemByCity() {
            this.$api.adminGetItemByCity({city: this.selectedCity, page: this.page - 1})
            .then((data) => {
                this.searchListData = []
                this.totalCount_item = data.data.cnt
                this.pagination = Math.ceil(this.totalCount_item / 6);
                this.searchListData = this.searchListData.concat(data.data.data)
                console.log("this.searchListData")
                console.log(this.searchListData)
            })
        },

        getSubAddr(fullAddress) {
            if (!fullAddress) {
                return "No address available";
            }
            const parts = fullAddress.split(',');
            return parts.length > 1 ? parts.slice(1).join(',').trim() : fullAddress;
        },
        getHeadAddr(fullAddress) {
            if (!fullAddress) {
                return "Unknown Location"; // Default title if none provided
            }
            const parts = fullAddress.split(',');
            return parts[0]; // Return the first part before the comma
        },

    },
    components: {
        DashboardAddItem
    },
    mounted() {
        this.adminGetItemByCity();
    },
    computed: {
        ...mapState(['cities']),
        ...mapState(['city']),
        ...mapState(['img_prefix']),
        selectedCity: {
            get() {
                return this.$store.state.city; // Getter to return the current city
            },
            set(value) {
                this.$store.commit('setCity', value); // Setter to update the city in the Vuex store
                this.adminGetItemByCity(); // Refresh data when city changes
            }
        },
    }

}
</script>
