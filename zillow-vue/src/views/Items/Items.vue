<!-- Items.vue -->
<template>
    <div>
        <ItemListings :currentCity="currentCity"/>
    </div>
</template>

<script>
import NavBar from '../../components/NavBar/NavBar';
import Item from "@/views/Home/Search/SearchItemListings/Item/Item.vue";
import LoadMore from "@/components/LoadMore/LoadMore.vue";
import {mapActions, mapState} from 'vuex';
import ItemListings from "@/views/Items/ItemListings/ItemListings.vue";

export default {
    name: 'Items',
    components: {
        ItemListings,
        LoadMore,
        Item,
        NavBar
    },
    computed: {
        ...mapState(['currentCity', 'page'])
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