<template>
    <v-app>
        <NavBar/>
        <v-main class="grey lighten-5">
            <HeadBar/>
            <router-view class="grey lighten-5"/>
        </v-main>
    </v-app>
</template>

<script>
import NavBar from './components/NavBar.vue';
import HeadBar from "@/components/HeadBar.vue";

export default {
    name: 'App',
    components: {
        HeadBar,
        NavBar,
    },
    data: () => ({
        //
    }),
};

</script>
