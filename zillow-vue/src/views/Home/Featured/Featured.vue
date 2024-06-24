<template>
    <div>
        <FeaturedView v-if="recommendations.length>0" :hotData="recommendations" title="Recommendations"/>
        <div v-else >
            <div style="padding: 10px;color: gainsboro;">
                Loading...
            </div>
        </div>
        <FeaturedView v-if="trendies.length>0" :hotData="trendies" title="Trendies"/>
        <div v-else class="test">
            <div style="padding: 10px;color: gainsboro;">
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
        ...mapState(['cur_city'])
    },
    components: {
        FeaturedView
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