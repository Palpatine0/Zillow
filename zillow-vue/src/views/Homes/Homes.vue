<template>
<v-app style="padding: 4vw">
    <v-container>
        <v-row style="display: flex;justify-content: space-between" cols="12" lg="4" md="6">
            <v-col cols="12" lg="12" md="12">
                <ItemListings/>
            </v-col>
        </v-row>
    </v-container>
</v-app>
</template>

<script>
import NavBar from '../../components/NavBar/NavBar';
import LoadMore from "@/components/LoadMore/LoadMore.vue";
import ItemListings from "@/components/ItemListings/ItemListings.vue";

export default {
    name: 'Homes',

    components: {
        ItemListings,
        LoadMore,
        NavBar
    },

    methods: {
        back() {
            window.history.back();
        }
    }
}
</script>