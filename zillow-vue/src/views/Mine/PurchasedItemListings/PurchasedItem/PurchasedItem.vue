<!--PurchasedItem.vue-->
<template>
<div>
    <v-row>
        <v-col>
            <v-img :src="awsS3RequestUrl+orderData.itemDetails.img" class="white--text align-end" :height="!isMobile?'300px':'200px'" style="border-radius: 15px"></v-img>
        </v-col>
        <v-col>
            <v-row>
                <v-col cols="1" lg="2" md="6"><b>Item:</b></v-col>
                <v-col style="text-align: right">{{ orderData.itemDetails.title }}</v-col>
            </v-row>
            <v-row>
                <v-col cols="1" lg="2" md="6"><b>Duration:</b></v-col>
                <v-col style="text-align: right">{{ orderData.startDate }} - {{ orderData.endDate }}</v-col>
            </v-row>
            <v-row>
                <v-col cols="1" lg="2" md="6"><b>Price:</b></v-col>
                <v-col style="text-align: right">{{ orderData.price }}USD</v-col>
            </v-row>
        </v-col>
    </v-row>
    <v-row>
        <v-col>
            <v-btn v-if="!commentToggle" depressed color="primary" @click="cancel" block>
                Comment
            </v-btn>

            <div v-if="commentToggle">
                <v-textarea outlined v-model="commentContent" label="Your feedback"></v-textarea>
                <v-btn color="primary" text @click="submit">Submit</v-btn>
                <v-btn color="primary" text @click="cancel">Cancel</v-btn>
            </div>
        </v-col>
    </v-row>
</div>

</template>

<script>
import {mapState} from 'vuex'

export default {
    name: "PurchasedItem",
    data() {
        return {
            commentContent: '',
            commentToggle: false,
        };
    },
    props: {
        orderData: {
            type: Object,
            required: true
        }
    },
    methods: {
        submit() {
            this.$api.addComment({
                orderId: this.orderData.id,
                commentContent: this.commentContent,
            })
            .then(data => {
                console.log(data)
                this.orderData.commentState = 0;
                this.commentContent = ''
            })

        },
        cancel() {
            this.commentToggle = !this.commentToggle
        },
    },
    computed: {
        ...mapState(['awsS3RequestUrl']),
    },
};
</script>

<style lang="less" scoped>
.order-item-container {
    padding: 20px 0;
}
</style>