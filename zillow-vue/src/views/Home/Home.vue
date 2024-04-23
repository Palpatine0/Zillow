<template>
  <div @touchend="handleTouchEnd" @touchmove="handleTouchMove" @touchstart="handleTouchStart">

    <!--header-->
    <div id="home-header" class="clear-fix">
      <div class="home-header-left">
        <router-link to="/city">
          <span>{{ curcity ? curcity : 'Dallas' }}</span>
          <i class="icon-angle-down"></i>
        </router-link>
      </div>


      <div class="home-header-middle">
        <h1>Agents. Tours. Loans. Homes.</h1>
        <div class="search-container">
          <SearchInput/>
          <svg height="20" viewBox="0 0 512 512" width="20" xmlns="http://www.w3.org/2000/svg">
            <path d="M416 208c0 45.9-14.9 88.3-40 122.7L502.6 457.4c12.5 12.5 12.5 32.8 0 45.3s-32.8 12.5-45.3 0L330.7 376c-34.4 25.2-76.8 40-122.7 40C93.1 416 0 322.9 0 208S93.1 0 208 0S416 93.1 416 208zM208 352a144 144 0 1 0 0-288 144 144 0 1 0 0 288z"
                  fill="#006aff"/>
          </svg>
        </div>
      </div>

      <div class="home-header-right">
        <img alt="Zillow logo" class="pfs__j60ma-3 jGdMiP" decoding="auto"
             focusable="false" height="25" loading="eager" src="https://s.zillowstatic.com/pfs/static/z-logo-white.svg" type="image/svg+xml"
             width="120">
      </div>
    </div>
    <!--/header-->
    <!--/header-->


    <!-- swiper -->
    <MySwiper :swiperSlides="swiperSlides" class="mySwiper"/>
    <!-- /swiper -->
    <br>
    <br>

    <!-- trendy -->
    <Homehot/>
    <!-- /trendy -->
    <br>
    <br>
    <Suggestions/>

    <!-- foot nav -->
    <FootNav/>
    <!-- /foot nav -->


  </div>
</template>

<script>
import Homehot from './Homehot/Homehot'
import SearchInput from '../../components/SearchInput/SearchInput'
import MySwiper from '../../components/MySwiper/MySwiper'
import FootNav from '../../components/FootNav/FootNav'
import {mapState} from 'vuex'
import Suggestions from "../Suggestion/Suggestion.vue";

export default {
  name: "Home",
  data() {
    return {
      msg: 'Default value',

      swiperSlides: [],
      startY: 0,
      isPullingDown: false,
    }
  },
  methods: {
    handleTouchStart(event) {
      this.startY = event.touches[0].clientY;
      this.isPullingDown = false;
    },
    handleTouchMove(event) {
      const currentY = event.touches[0].clientY;
      if (currentY > this.startY) { // Detecting downward drag
        this.isPullingDown = true;
      }
    },
    handleTouchEnd() {
      if (this.isPullingDown) {
        // Logic to refresh data or reload page
        // Example: this.fetchData();
        location.reload(); // Or use Vue methods to reload data instead of hard refresh
      }
    }
  },
  mounted() {
    this.$api.getBanner()
        .then(data => {
          // console.log(data)
          if (data.data.status == 200) {
            this.swiperSlides = data.data.results;
          } else {
            alert('Banner unavailable')
          }
        })
  },
  components: {
    Suggestions,
    Homehot,
    SearchInput,
    MySwiper,
    FootNav
  },
  computed: {
    ...mapState(['curcity'])
  },
};
</script>


<style lang='less' scoped>
.mySwiper {

  z-index: -1;
}

#home-header {
  //background-color: #ff5555;
  height: 100px;

  padding: 25px 20px;
  position: absolute;

  * {
    color: #ffffff;
    font-family: Arial;

    line-height: 1;
    text-decoration: none;
  }


}

.home-header-left {
  position: absolute;
  top: 5px;
  left: 18px;
  text-align: left;
  margin: 5px 0;
  float: left;
  width: 600px;
  font-size: 28px;
  font-weight: bold;
}

.home-header-middle {
  position: absolute;
  top: 120%;
  width: auto;
  margin: 0 1.5rem 0 3rem;
  left: 0px;

  h1 {
    font-size: 26px;
    font-family: "Ivar Headline", "Ivar Headline Subset", "Adjusted Times", "Adjusted Times New Roman", "Times New Roman", serif !important;
    position: relative;
    left: 5%
  }

  .search-container {
    width: 440px;
    height: 58px;
    background-color: #fff;
    border-radius: 3px;
    border: 1px solid gainsboro;
    overflow: hidden;

    position: relative;
    top: 20px;
    left: -35px;
    padding: 5px;

    i {
      color: #ccc !important;
    }

    input {
      width: 90%;
      color: #000000 !important;
      font-size: 14px !important;
      font-weight: normal;
      border: none;
      overflow: hidden;
    }

    svg {
      position: relative;
      top: 14px;
      right: 10px;
      float: right;
      z-index: 10;
    }
  }
}

.home-header-right {
  position: absolute;
  top: 10px;
  left: 340px;
}

</style>
