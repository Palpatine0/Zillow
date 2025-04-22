<template>
<div>
    <div v-if="itemList.length > 0">
        <v-row v-if="!isMobile">
            <v-col v-for="(item, index) in itemList" :key="index" cols="12" lg="6" md="6">
                <Item :key="index" :itemData="item"/>
            </v-col>
        </v-row>
        <div v-else>
            <Item v-for="(item, index) in itemList" :key="index" :itemData="item" class="mb-10"/>
        </div>
    </div>
    <div v-else>
        <div style="padding: 10px;color: gainsboro;" align="center">
            Loading...
        </div>
    </div>
    <LoadMore @getMoreData='getMoreData'/>
    <br>
    <br>
    <br>
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
    computed: {
        ...mapState(['currentCity'])
    },
    data() {
        return {
            itemList: [],
            hasMore: false,
            page: 0,
        };
    },

    mounted() {
        if (this.searchContent !== undefined) {
            this.search();
        } else {
            this.searchByCity();
        }
    },
    watch: {
        'searchContent': function (n, o) {
            if (n === o) {
                return;
            }
            if (n !== undefined) {
                this.search();
            }
        }
    },
    methods: {
        search(){
            if (this.searchContent === undefined){
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
