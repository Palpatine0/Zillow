<!-- SearchListShop.vue -->
<template>
    <div>
        <div v-if="searchListData.length > 0">
            <Item v-for="(curdata, index) in searchListData" :key="index" :curdata="curdata"/>
        </div>
        <div v-else>Loading....</div>
        <LoadMore @getMoreData='getMoreData'/>
        <br>
        <br>
        <br>
        <!--        <LoadMore @getMoreData="getMoreData"/>-->
    </div>
</template>

<script>
import Item from './Item/Item';
import LoadMore from '../../../components/LoadMore/LoadMore';

export default {
    name: 'SearchListShop',
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
    props: ['curcity'],
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
            return this.$api.searchByCity({city: this.curcity, page: this.page})
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
