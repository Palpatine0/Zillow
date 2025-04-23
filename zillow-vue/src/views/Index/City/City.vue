<template>
    <div>
        <div v-if="isMobile">
            <Header title='Select a City'/>
        </div>

        <div class="current-city">
            <h2>Current City: {{ currentCity }}</h2>
        </div>
        <Cities/>
    </div>
</template>
<script>
import Header from '../../../components/Header/Header.vue'
import Cities from './Cities/Cities.vue'
import {mapState} from 'vuex'

export default {
    name: 'City',

    components: {
        Header,
        Cities
    },
    data() {
        return {}
    },

    computed: {
        ...mapState([
            'currentCity'
        ])
    },
}
</script>
<style lang="less" scoped>
.current-city {

    width: 90%;
    height: 120px;
    background-color: #f6f8fe;
    text-align: center;
    padding: 30px 0;
    border-bottom: 1px solid #f1f1f1;
    border-radius: 15px;
    margin: 20px auto;

    h2 {
        position: relative;
        top: 20px;
        font-family: Arial;
        font-size: 20px;
    }
}
</style>