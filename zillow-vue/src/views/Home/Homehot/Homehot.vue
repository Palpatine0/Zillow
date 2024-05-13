<template>
    <div>
        <HomeHotView v-if="recommendations.length>0" :hotData="recommendations" title="Recommendations"/>
        <div v-else class="test">Loading Data...</div>
        <HomeHotView v-if="trendies.length>0" :hotData="trendies" title="Trendies"/>
        <div v-else class="test">Loading Data...</div>
    </div>
</template>

<script>
import {mapState} from 'vuex'
import HomeHotView from "../HomeHotView/HomeHotView";

export default {
    name: "Homehot",
    data() {
        return {
            trendies: [],
            recommendations: []
        };
    },
    computed: {
        ...mapState(['cur_city'])
    },
    components: {
        HomeHotView
    },
    mounted() {
        this.$api.getTrendies({
            city: this.cur_city
        })
        .then(data => {
            if (data.data.status) {
                this.trendies = data.data.data
            }
        })

        this.$api.getRecommendations({
            city: this.cur_city
        })
        .then(data => {
            if (data.data.status) {
                this.recommendations = data.data.data
            }
        })
    }
};
</script>

<style>
.test {
    font-family: Arial;
}


</style>