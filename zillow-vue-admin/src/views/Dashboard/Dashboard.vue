<template>
    <v-app style="padding: 20px">
        <v-subheader class="subheading grey--text" as="h1">Dashboard</v-subheader>

        <v-container>
            <v-row justify="end">
                <v-pagination class="float-right" :length="pagination"   v-model="page" @input="adminSearchByCity"></v-pagination>
            </v-row>
            <v-row>
                <v-col cols="12" md="6" lg="4" v-for="(item, index) in searchListData" :key="index">
                    <v-card class="mx-auto" max-width="400">
                        <v-img class="white--text align-end" height="200px" :src="getFirstImageUrl(item.imgs)">
                            <v-card-title>{{ getHeadAddr(item.title) }}</v-card-title>
                        </v-img>
                        <v-card-subtitle class="pb-0">
                            {{ getSubAddr(item.title) }}
                        </v-card-subtitle>
                        <v-card-text class="text--primary">
                            <b><div>{{item.rentType}}</div></b>
                            <div>{{item.aptType}}</div>
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
export default {
    data() {
        return {
            city: 'Dallas',
            pagination: 0,
            page: 1,
            totalCount_item: 0,
            searchListData: [],
        }
    },
    methods: {
        adminSearchByCity() {
            this.$api.adminSearchByCity({city: this.city, page: this.page - 1})
            .then((data) => {
                console.log(data)
                console.log("-------------------")
                this.searchListData = []
                this.totalCount_item = data.data.data[0].totalCount
                this.pagination = Math.ceil(this.totalCount_item / 6);
                this.searchListData = this.searchListData.concat(data.data.data)
                console.log("this.pagination")
                console.log(this.pagination)
                console.log(this.searchListData)
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
}
</script>
