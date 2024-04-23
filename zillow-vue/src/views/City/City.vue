<template>
  <div>
    <CityHeader title='Select a City'/>

    <div class="current-city">
      <h2>Current City: {{ curcity }}</h2>
    </div>
    <CityHot/>
  </div>
</template>
<script>
import CityHeader from '../../components/Header/Header'
import CityHot from './CityHot/CityHot'
import {mapState} from 'vuex'

export default {
  name: 'City',
  data() {
    return {}
  },
  components: {
    CityHeader,
    CityHot
  },
  computed: {
    ...mapState(['curcity'])
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