<template>
    <div>
        <div v-if="searchListData.length > 0">
            <Item v-for="(curdata, index) in searchListData" :key="index" :curdata="curdata"/>
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
import Item from "@/components/Item/Item.vue";
import LoadMore from '../../../components/LoadMore/LoadMore';

export default {
    name: 'ItemListings',
    data() {
        return {
            searchListData: [],
            hasMore: false,
            page: 0,
        };
    },
    components: {
        Item,
        LoadMore,
    },
    props: ['currentCity'],
    methods: {
        getMoreData() {
            this.page += 1;
            this.http()
            .then((data) => {
                this.searchListData = this.searchListData.concat(data.data.data);
                this.hasMore = data.data.hasMore;
            });
        },
        http() {
            return this.$api.search.searchByCity({
                city: this.currentCity,
                page: this.page
            })
            .then((data) => {
                this.searchListData = this.searchListData.concat(data.data.data)
                return data;
            })
        }

    },

    created() {
        this.http();
    }


};
</script>
