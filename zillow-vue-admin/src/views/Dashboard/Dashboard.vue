<template>
<v-app style="padding: 20px">
    <v-subheader as="h1" class="subheading grey--text">Dashboard</v-subheader>
    <v-container>
        <v-row style="justify-content: space-between">
            <v-col cols="2">
                <v-select v-model="selectedCity" :items="cities" label="Location"></v-select>
            </v-col>
            <div class="mt-5">
                <v-pagination v-model="page" :length="pagination" class="float-right" color="#156ff6" @input="adminGetItemByCity"></v-pagination>
            </div>
        </v-row>

        <v-row>
            <v-col v-for="(item, index) in searchListData" :key="index" cols="12" lg="4" md="6">
                <v-card :href="item.link" class="mx-auto" max-width="400">
                    <v-img :src="awsS3RequestUrl+item.imgs[0]" class="white--text align-end" height="200px">
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

                    <v-card-actions v-if="false">
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

    <DashboardAddItem class="mt-5"></DashboardAddItem>


</v-app>
</template>

<script>
import {mapState} from "vuex";
import DashboardAddItem from "@/views/Dashboard/Dashboard-AddItem/Dashboard-AddItem.vue";

export default {
    components: {
        DashboardAddItem
    },
    data() {
        return {

            // page vars
            page: 1,
            pagination: 0,
            totalCount_item: 0,
            searchListData: [],
        }
    },
    methods: {
        adminGetItemByCity() {
            this.$api.getItemsByCity({
                city: this.selectedCity,
                page: this.page - 1,
                rows: 6
            })
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
            if(!fullAddress) {
                return "No address available";
            }
            const parts = fullAddress.split(',');
            return parts.length > 1 ? parts.slice(1).join(',').trim() : fullAddress;
        },
        getHeadAddr(fullAddress) {
            if(!fullAddress) {
                return "Unknown Location"; // Default title if none provided
            }
            const parts = fullAddress.split(',');
            return parts[0]; // Return the first part before the comma
        },

    },

    mounted() {
        this.adminGetItemByCity();
    },
    computed: {
        ...mapState(['city']),
        ...mapState(['cities']),
        ...mapState(['awsS3RequestUrl']),
        selectedCity: {
            get() {
                console.log("Getting city:", this.$store.state.city);
                return this.$store.state.city;
            },
            set(value) {
                console.log("Setting city to:", value);
                this.$store.commit('setCity', value);
                this.adminGetItemByCity();
            }
        }

    }

}
</script>
