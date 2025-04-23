<template>
    <div>
        <FeaturedView v-if="recommendations.length>0" :itemData="recommendations" title="Recommendations"/>
        <div v-else >
            <div style="padding: 10px;color: gainsboro;" align="center">
                Loading...
            </div>
        </div>
        <FeaturedView v-if="trendies.length>0" :itemData="trendies" title="Trendies"/>
        <div v-else class="test">
            <div style="padding: 10px;color: gainsboro;" align="center">
                Loading...
            </div>
        </div>
    </div>
</template>

<script>
import {mapState} from 'vuex'
import FeaturedView from "./FeaturedView/FeaturedView.vue";

export default {
    name: "Featured",
    data() {
        return {
            trendies: [],
            recommendations: []
        };
    },
    computed: {
        ...mapState(['currentCity'])
    },
    components: {
        FeaturedView
    },
    mounted() {
        this.$api.trendy.getTrendies({
            city: this.currentCity
        })
        .then(data => {
            if (data.data.status) {
                this.trendies = data.data.data
            }
        })

        this.$api.recommendation.getRecommendations({
            city: this.currentCity
        })
        .then(data => {
            if (data.data.status) {
                this.recommendations = data.data.data
            }
        })
    }
};
</script>