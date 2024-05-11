<template>
    <div>
        <HomeHotView v-if="recommendation.length>0" :hotData="recommendation" title="Recommendations"/>
        <div v-else class="test">Loading Data...</div>
        <HomeHotView v-if="trendy.length>0" :hotData="trendy" title="Trendies"/>
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
            trendy: [],
            recommendation: []
        };
    },
    computed: {
        ...mapState(['cur_city'])
    },
    components: {
        HomeHotView
    },
    mounted() {
        this.$api.hotProduct({
            city: this.cur_city
        })
        .then(data => {
            if (data.data.status) {
                this.trendy = data.data.data
            }
        })

        this.$api.getRecommendation({
            city: this.cur_city
        })
        .then(data => {
            if (data.data.status) {
                this.recommendation = data.data.data
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