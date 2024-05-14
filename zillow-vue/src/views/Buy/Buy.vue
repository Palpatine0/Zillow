<template>
    <div>
        <Header title='Reservation Result'/>
        <div class="wrap">
            <p class="p1">Reservation Result</p>
            <br/>
            <p class="p2">{{ msg }}</p>
        </div>
    </div>
</template>

<script>
import Header from '../../components/Header/Header'

export default {
    name: 'Buy',
    data() {
        return {
            msg: ''
        }
    },
    components: {
        Header
    },
    mounted() {
        this.$api.buyAction({
            itemId: this.$route.params.itemId,
            userId: this.$route.params.userId,
            startDate: this.$route.params.startDate,
            endDate: this.$route.params.endDate,
            price: this.$route.params.price
        })
        .then(data => {
            // console.log(data)
            this.msg = data.data.msg
        })
    },

}
</script>

<style scoped>

.wrap {
    margin-top: 40%;
    display: flex;
    flex-direction: column;

    align-items: center;
    justify-content: center;
}

div p {
    font-family: Arial;

}

.p1 {
    font-size: 20px;
    font-weight: bold;
    color: #156FF6;
}

.p2 {
    font-size: 20px;
//font-weight: bold; //color: #156FF6;
}
</style>