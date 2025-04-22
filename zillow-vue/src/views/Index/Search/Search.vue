<!--Search.vue-->
<template>
    <v-app style="padding: 4vw">
        <div id="search-header" class="clear-fix">
            <span class="back-icon float-left" @click="back">
                <i class="icon-chevron-left"></i>
            </span>
            <div class="input-container">
                <SearchInput :pk='$route.params.content' @setSearchContentAction='setSearchContentAction'/>
                <svg height="20" viewBox="0 0 512 512" width="20" xmlns="http://www.w3.org/2000/svg">
                    <path d="M416 208c0 45.9-14.9 88.3-40 122.7L502.6 457.4c12.5 12.5 12.5 32.8 0 45.3s-32.8 12.5-45.3 0L330.7 376c-34.4 25.2-76.8 40-122.7 40C93.1 416 0 322.9 0 208S93.1 0 208 0S416 93.1 416 208zM208 352a144 144 0 1 0 0-288 144 144 0 1 0 0 288z" fill="#006aff"/>
                </svg>
            </div>
        </div>
        <v-container>
            <v-row style="display: flex;justify-content: space-between" cols="12" lg="4" md="6">
                <v-col cols="12" lg="12" md="12">
                    <ItemListings :searchContent='searchContent'/>
                </v-col>
            </v-row>
        </v-container>
    </v-app>
</template>

<script>
import SearchInput from '../../../components/SearchInput/SearchInput.vue'
import {mapActions, mapState} from 'vuex'
import ItemListings from "@/components/ItemListings/ItemListings.vue";

export default {
    name: 'Search',

    components: {
        ItemListings,
        SearchInput,
    },
    computed: {
        ...mapState(['searchContent', 'currentCity', 'page'])
    },
    data() {
        return {}
    },

    methods: {
        ...mapActions(['setSearchContentAction']),
        back() {
            window.history.back();
        }
    }
}
</script>

<style lang="less" scoped>
#search-header {
    * {
        font-family: Arial;
    }

    border-bottom: 1px solid gainsboro;
    background-color: #ffffff;
    color: #fff;
    padding: 32px;
    text-align: center;
    position: relative;

    .input-container {
        position: relative;
        top: 20px;
        border: 1px solid gainsboro;
        background-color: #fff;
        border-radius: 2px;
        overflow: hidden;
        padding: 6px;
        text-align: left;

        margin-left: 30px;
        margin-right: 10px;

        svg {
            position: relative;
            top: 2px;
            right: 10px;
            float: right;
            z-index: 10;
        }

        i {
            color: #ccc;
        }
    }

    .back-icon {
        color: #156FF6;
        width: 26px;
        height: 26px;
        position: relative;
        top: 30px;
        left: -10px;
    }
}
</style>
