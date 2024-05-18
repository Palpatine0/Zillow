<template>
    <div>
        <div v-if='order.length'>
            <Item v-for='(orderData, index) in order' :key='index' :orderData='orderData'/>
        </div>
        <div v-else>Loading...</div>
        <br>
        <br>
        <br>
    </div>
</template>

<script>
import Item from '../Item/Item'
import { mapState } from 'vuex'

export default {
    name: 'Order',
    data() {
        return {
            order: []
        }
    },
    components: {
        Item
    },
    computed: {
        ...mapState(['userId'])
    },
    async mounted() {
        try {
            const ordersResponse = await this.$api.getOrdersByUserId({
                userId: this.userId
            });
            const orders = ordersResponse.data.data;

            const orderPromises = orders.map(async (order) => {
                const itemResponse = await this.$api.getItemByID({
                    id: order.itemId
                });
                order.itemDetails = itemResponse.data;
                return order;
            });

            this.order = await Promise.all(orderPromises);

            console.log("this.orderData");
            console.log(this.order);
        } catch (error) {
            console.error("Error fetching order data:", error);
        }
    }
}
</script>
