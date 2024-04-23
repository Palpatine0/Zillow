<template>
  <div class="hotproduct">
    <h2>{{ title }}</h2>
    <div class="hot-container">
      <ul class="clear-fix">
        <li v-for='(item,index) in hotData' :key='index'>
          <a :href='item.link'>
            <img :alt='item.title' :src='item.img'/>
            <span>{{ item.title }}</span>
          </a>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HomeHotView',
  data() {
    return {}
  },
  props: ['title', 'hotData']
}
</script>
<style lang="less" scoped>
.hotproduct {
  * {
    font-family: Arial;
  }

  h2 {
    position: relative;
    left: 3%;
    margin-top: 10px;
    font-size: 20px;
    font-weight: bold;
    text-align: left;
    font-family: Arial;
    color: #156FF6;
  }

  .hot-container {
    padding: 15px;
    background: #f7f8fa;
    margin: 10px;
    border-radius: 5px;
    //box-shadow: 1px 1px 5px 1px #e1e1e1;

    ul {
      position: relative;
      left: 1%;

      li {
        width: 50%;
        overflow: hidden;
        float: left;

        a {
          img {
            border-radius: 5px;
            width: 200px;
            height: 100px;
          }

          span {
            margin: 0 auto;
            width: 160px;
            height: 28px;
            display: block;
            text-align: center;
            font-size: 12px;
          }
        }
      }
    }
  }
}
</style>