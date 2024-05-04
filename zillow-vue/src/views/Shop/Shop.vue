<!-- Shop.vue -->
<template>
    <div>
        <SearchListShop :curcity="curcity"/>
        <FootNav/>
    </div>
</template>

<script>
import FootNav from '../../components/FootNav/FootNav';
import Item from "@/views/Search/SearchList/Item/Item.vue";
import LoadMore from "@/components/LoadMore/LoadMore.vue";
import {mapActions, mapState} from 'vuex';
import SearchListShop from "@/views/Shop/SearchListShop/SearchListShop.vue";

export default {
    name: 'Shop',
    components: {
        SearchListShop,
        LoadMore,
        Item,
        FootNav
    },
    computed: {
        ...mapState(['curcity', 'page'])
    },
    methods: {
        back() {
            window.history.back();
        }
    }

}
</script>
<style>
.foot-nav-wrapper {
    position: relative; /* This will ensure z-index is applied */
    z-index: 10; /* Example value, make sure this is higher than other elements */
}
</style>