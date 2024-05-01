<template>
    <v-app style="padding: 20px">
        <v-subheader class="subheading grey--text" as="h1">Dashboard</v-subheader>

        <v-container>
            <v-row style="justify-content: space-between">
                <v-col cols="2">
                    <v-select :items="cities" label="Standard" v-model="selectedCity"></v-select>

                </v-col>
                <div class="mt-5">
                    <v-pagination class="float-right" :length="pagination" v-model="page" @input="adminSearchByCity" color="#156ff6"></v-pagination>
                </div>
            </v-row>
            <v-row>
                <v-col cols="12" md="6" lg="4" v-for="(item, index) in searchListData" :key="index">
                    <v-card class="mx-auto" max-width="400" :href="item.link">
                        <v-img class="white--text align-end" height="200px" :src="getFirstImageUrl(item.imgs)">
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
    </v-app>
</template>

<script>
import {mapState} from "vuex";

export default {
    data() {
        return {
            pagination: 0,
            page: 1,
            totalCount_item: 0,
            searchListData: [],
        }
    },
    methods: {
        adminSearchByCity() {
            this.$api.adminSearchByCity({city: this.selectedCity, page: this.page - 1})
            .then((data) => {
                this.searchListData = []
                this.totalCount_item = data.data.data[0].totalCount
                this.pagination = Math.ceil(this.totalCount_item / 6);
                this.searchListData = this.searchListData.concat(data.data.data)
                return data;
            })
        },
        getFirstImageUrl(imgs) {
            const matches = imgs.match(/\[(.*?)\]/);
            if (matches && matches.length > 1) {
                const firstUrlPart = matches[1].split(',')[0].trim();
                const imageUrl = `http://111.231.19.137:8888/${firstUrlPart}`;
                console.log("Generated image URL:", imageUrl);
                return imageUrl;
            } else {
                console.error("No image URLs found in the provided string");
                return "default-image-url"; // Fallback URL
            }
        },
        getSubAddr(fullAddress) {
            console.log("Processing address:", fullAddress); // This will show you what the input is
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
        }
    },
    mounted() {
        this.adminSearchByCity();
    },
    computed: {
        ...mapState(['cities']),
        ...mapState(['city']),
        selectedCity: {
            get() {
                return this.$store.state.city; // Getter to return the current city
            },
            set(value) {
                this.$store.commit('setCity', value); // Setter to update the city in the Vuex store
                this.adminSearchByCity(); // Refresh data when city changes
            }
        }

    },
}
</script>
