<template>
<div>
    <div v-if="loading">
        <div style="padding: 10px; color: gainsboro;" align="center">
            Loading...
        </div>
    </div>
    <div v-else-if="purchasedItemList.length">
        <PurchasedItem v-for="(purchasedItem, index) in purchasedItemList" :key="index" :orderData="purchasedItem" class="mb-10"/>
    </div>
    <div v-else>
        <div style="padding: 10px; color: gainsboro;" align="center">
            No more data
        </div>
    </div>
</div>
</template>

<script>
import PurchasedItem from './PurchasedItem/PurchasedItem.vue';
import {mapState} from 'vuex';

export default {
    name: 'PurchasedItemListings',
    data() {
        return {
            purchasedItemList: [],
            loading: true,
        };
    },
    components: {
        PurchasedItem,
    },
    computed: {
        ...mapState(['user']),
    },
    mounted() {
        this.getPurchasedItemsByUserId()
    },
    methods: {
        async getPurchasedItemsByUserId() {
            try {
                const getOrdersByUserId = await this.$api.order.getOrdersByUserId({
                    userId: this.user.id,
                });
                const orderList = getOrdersByUserId.data.data;
                const getItemById = orderList.map(async(order) => {
                    const itemResponse = await this.$api.item.getItemById({
                        id: order.itemId,
                    });
                    order.itemDetails = itemResponse.data;
                    return order;
                });

                this.purchasedItemList = await Promise.all(getItemById);
            } catch(error) {
                console.error('Error fetching order data:', error);
            } finally {
                this.loading = false;
            }
        }
    },

};
</script>
