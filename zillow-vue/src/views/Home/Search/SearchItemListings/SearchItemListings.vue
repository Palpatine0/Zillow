<!--ItemListings.vue-->
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
        <FootNav/>
    </div>
</template>

<script>
import Item from './Item/Item.vue'
import LoadMore from '../../../../components/LoadMore/LoadMore.vue'

export default {
    name: 'SearchList',
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
    props: ['kw', 'cur_city'],

    methods: {
        getMoreData() {
            this.page = this.page += 1
            this.http(this.kw, this.cur_city)
            .then(data => {
                this.searchListData = this.searchListData.concat(data.data.data)
                this.hasMore = data.data.hasMore
            })
        },
        http(keyword, city) {
            return this.$api.searchByKeyWord({
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
            // console.log(n,o)
            this.http(this.kw, this.cur_city)
            .then(data => {
                this.searchListData = data.data.data
                this.hasMore = data.data.hasMore
            })
        }
    },
}
</script>
