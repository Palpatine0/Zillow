<template>
    <div>
        <div v-if='searchListData.length>0'>
            <Item v-for='(curdata,index) in searchListData' :key='index' :curdata='curdata'/>
        </div>
        <div v-else>
            <div style="padding: 10px;color: gainsboro;" align="center">
                Loading...
            </div>
        </div>
        <LoadMore @getMoreData='getMoreData'/>
    </div>
</template>

<script>
import Item from "@/components/Item/Item.vue";
import LoadMore from '../../../../components/LoadMore/LoadMore.vue'

export default {
    name: 'SearchItemListings',
    data() {
        return {
            searchListData: [],
            hasMore: false,
            page: 0
        }
    },
    components: {
        Item,
        LoadMore
    },
    props: ['kw', 'currentCity'],

    methods: {
        getMoreData() {
            this.page = this.page += 1
            this.http(this.kw, this.currentCity)
            .then(data => {
                this.searchListData = this.searchListData.concat(data.data.data)
                this.hasMore = data.data.hasMore
            })
        },
        http(keyword, city) {
            return this.$api.search.searchByKeyWord({
                city: city,
                content: keyword,
                page: this.page
            })
        }
    },
    watch: {
        'kw': function (n, o) {
            if (n === o) {
                return;
            }
            this.http(this.kw, this.currentCity)
            .then(data => {
                this.searchListData = data.data.data
                this.hasMore = data.data.hasMore
            })
        }
    },
}
</script>
