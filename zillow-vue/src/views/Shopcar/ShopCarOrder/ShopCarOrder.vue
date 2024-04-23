<template>
  <div>
    <div v-if='orderData'>
      <Item v-for='(ite,index) in orderData' :key='index' :idata='ite'/>
    </div>
    <div v-else>Loading...</div>
    <br>
    <br>
    <br>
  </div>
</template>
<script>
import Item from '../Item/Item'
import {mapState} from 'vuex'

export default {
  name: 'ShopCarOrder',
  data() {
    return {
      orderData: ''
    }
  },
  components: {
    Item
  },
  computed: {
    ...mapState(['user'])
  },
  mounted() {
    this.$api.getOrder({user: this.user})
        .then(data => {
          // console.log(data)
          this.orderData = data.data
        })
  },
}
</script>