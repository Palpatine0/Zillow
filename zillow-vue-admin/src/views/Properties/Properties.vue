<template>
<v-app style="padding: 20px">
    <v-subheader as="h1" class="subheading grey--text">Properties</v-subheader>
    <v-container>
        <v-row style="justify-content: space-between">
            <v-col cols="12" lg="3" md="6">
                <v-select v-model="selectedCity" :items="cities" label="Location"></v-select>
            </v-col>
            <v-col cols="12" lg="9" md="6">
                <v-pagination v-model="page" :length="pagination" class="float-right" color="#156ff6" @input="adminGetItemByCity"></v-pagination>
            </v-col>
        </v-row>
        <v-row>
            <v-col v-for="(item, index) in searchListData" :key="index" cols="12" lg="4" md="6">
                <v-card class="mx-auto" @click="itemRedirect(item.id)" :style="!isMobile?{'width':'40vw','height': '33vh'}:{'width':'90vw','height': '32vh'}">
                    <v-img :src="awsS3RequestUrl+item.imgs[0]" class="white--text align-end" height="24vh">
                        <v-card-title>{{ getHeadAddr(item.title) }}</v-card-title>
                    </v-img>
                    <div style="flex-direction: column;align-items: start">
                        <v-card-subtitle class="pb-0">
                            <div>{{ getSubAddr(item.title) }}</div>
                        </v-card-subtitle>
                        <v-card-text class="text--primary">
                            <div style="font-weight: bold">{{ item.rentType }}</div>
                            <div>{{ item.aptType }}</div>
                        </v-card-text>
                    </div>
                </v-card>
            </v-col>
        </v-row>

    </v-container>

    <AddItem class="mt-5"></AddItem>


</v-app>
</template>

<script>
import {mapState} from "vuex";
import AddItem from "@/views/Properties/AddItem/AddItem.vue";

export default {
    name: "Properties",
    components: {
        AddItem
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
    },
    methods: {
        adminGetItemByCity() {
            this.$api.item.getItemsByCity({
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

        // Redirects
        itemRedirect(id) {
            this.$router.push('/item/' + id);
        }

    },

}
</script>
