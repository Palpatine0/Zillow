<template>
    <v-app style="padding: 20px">
        <v-subheader class="subheading grey--text" as="h1">Details</v-subheader>
        <!--<v-breadcrumbs :items="breadcrumbs"  divider="/"></v-breadcrumbs>-->
    </v-app>
</template>

<script setup>
import {ref} from 'vue';

// eslint-disable-next-line no-unused-vars
const breadcrumbs = ref([
    {text: 'Dashboard', href: '/'},
    {text: 'Details'}
]);
</script>