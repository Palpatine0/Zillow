<template>
  <div>
    <div class="star-container">
      <i v-for="(item,index) in 5" :key="index" :class="{'icon-star':true,'light':star >= item }"></i>
    </div>
  </div>
</template>


<script>
export default {
  name: "Star",
  data() {
    return {};
  },
  props: ["star"]
};
</script>
<style lang="less" scoped>
.star-container {
  display: inline-block;

  i {
    color: #999;
    margin: 0 1.5px;
  }

  .light {
    color: rgb(250, 176, 39);
  }
}
</style>