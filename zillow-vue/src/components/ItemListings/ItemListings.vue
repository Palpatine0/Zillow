<template>
<div>
    <div v-if="itemList.length">
        <v-row v-if="!isMobile">
            <v-col v-for="(item, index) in itemList" :key="index" cols="12" lg="6" md="6">
                <Item :key="index" :itemData="item"/>
            </v-col>
        </v-row>
        <div v-else>
            <Item v-for="(item, index) in itemList" :key="index" :itemData="item" class="mb-10"/>
        </div>

        <LoadMore v-if="!loading&&hasMore" @getMoreData='getMoreData'/>
    </div>
    <div v-if="loading">
        <div style="padding: 10px;color: gainsboro;" align="center">
            Loading...
        </div>
    </div>
</div>
</template>

<script>
import Item from "@/components/ItemListings/Item/Item.vue";
import LoadMore from "@/components/LoadMore/LoadMore.vue";
import {mapState} from "vuex";

export default {
    name: 'ItemListings',

    components: {
        Item,
        LoadMore,
    },
    props: ['searchContent'],
    data() {
        return {
            itemList: [],
            loading: true,
            hasMore: true,
            page: 0,
        };
    },

    computed: {
        ...mapState([
            'currentCity'
        ])
    },
    mounted() {
        if (this.searchContent !== undefined) {
            this.search();
        } else {
            this.searchByCity();
        }
    },
    watch: {
        'searchContent': function(n, o) {
            if (n === o) {
                return;
            }
            if (n !== undefined) {
                this.search();
            }
        }
    },
    methods: {
        search() {
            this.loading = true;
            if (this.searchContent === undefined) {
                this.searchByCity();
            } else {
                this.searchByKeyWord();
            }
        },
        searchByCity() {
            return this.$api.search.searchByCity({
                city: this.currentCity,
                page: this.page,
                rows: !this.isMobile ? 4 : 3,
            })
            .then((res) => {
                this.itemList = this.itemList.concat(res.data.data)
                this.hasMore = res.data.hasMore;
                console.log(this.hasMore);
                this.loading = false;
                return res;
            })
        },
        searchByKeyWord() {
            this.itemList = [];
            return this.$api.search.searchByKeyWord({
                city: this.currentCity,
                content: this.searchContent,
                page: this.page
            })
            .then((res) => {
                this.itemList = this.itemList.concat(res.data.data)
                this.hasMore = res.data.hasMore;
                this.loading = false;
                return res;
            })
        },
        getMoreData() {
            this.page += 1;
            this.search()
        },
    },
};
</script>
