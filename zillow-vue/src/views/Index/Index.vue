<template>
<div @touchend="handleTouchEnd" @touchmove="handleTouchMove" @touchstart="handleTouchStart">
    <!--header-->
    <div id="home-header" class="clear-fix">
        <div class="container-fluid ">
            <div v-if="isMobile" class="col-sm-12 col-md-4">
                <div class="top-row">
                    <div class="city-selector">
                        <router-link to="/city">
                            <span>{{ currentCity ? currentCity : 'Dallas' }}</span>
                            <i class="icon-angle-down"></i>
                        </router-link>
                    </div>
                    <div class="logo">
                        <img alt="Zillow logo" class="" decoding="auto" focusable="false" height="25" loading="eager" src="https://s.zillowstatic.com/pfs/static/z-logo-white.svg" type="image/svg+xml" width="120">
                    </div>
                </div>
            </div>
            <div class="col-sm-12 col-md-4 ">
                <div :class="isMobile?'search-bar-mobile':'search-bar'">
                    <div class="center-h">
                        <h1 class="">Agents. Tours.<br> Loans. Homes.</h1>
                    </div>
                    <div class="search-container center-h">
                        <SearchInput/>
                        <svg height="20" viewBox="0 0 512 512" width="20" xmlns="http://www.w3.org/2000/svg">
                            <path d="M416 208c0 45.9-14.9 88.3-40 122.7L502.6 457.4c12.5 12.5 12.5 32.8 0 45.3s-32.8 12.5-45.3 0L330.7 376c-34.4 25.2-76.8 40-122.7 40C93.1 416 0 322.9 0 208S93.1 0 208 0S416 93.1 416 208zM208 352a144 144 0 1 0 0-288 144 144 0 1 0 0 288z" fill="#006aff"/>
                        </svg>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--/header-->


    <!-- swiper -->
    <Swiper :slides="swiperSlides"/>
    <!-- /swiper -->
    <Featured class="mt-4"/>

    <!-- trendy -->
    <Suggestion/>
    <!-- /trendy -->

</div>
</template>

<script>
import Featured from './Featured/Featured.vue'
import SearchInput from '../../components/SearchInput/SearchInput'
import Swiper from "@/components/Swiper.vue";
import NavBar from "@/components/NavBar/NavBar.vue";
import {mapState} from 'vuex'
import Suggestion from "@/views/Index/Suggestion/Suggestion.vue";
import {inject, provide} from 'vue';

export default {
    name: "Index",
    components: {
        Suggestion,
        Featured,
        SearchInput,
        NavBar,
        Swiper
    },
    data() {
        return {
            msg: 'Default value',

            swiperSlides: [],
            startY: 0,
            isPullingDown: false,

            items: [
                {
                    src: 'https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg',
                },
                {
                    src: 'https://cdn.vuetifyjs.com/images/carousel/sky.jpg',
                },
                {
                    src: 'https://cdn.vuetifyjs.com/images/carousel/bird.jpg',
                },
                {
                    src: 'https://cdn.vuetifyjs.com/images/carousel/planet.jpg',
                },
            ],
        }
    },

    methods: {
        handleTouchStart(event) {
            this.startY = event.touches[0].clientY;
            this.isPullingDown = false;
        },
        handleTouchMove(event) {
            const currentY = event.touches[0].clientY;
            if(currentY > this.startY) { // Detecting downward drag
                this.isPullingDown = true;
            }
        },
        handleTouchEnd() {
            if(this.isPullingDown) {
                // Logic to refresh data or reload page
                // Example: this.fetchData();
                location.reload(); // Or use Vue methods to reload data instead of hard refresh
            }
        }
    },
    created() {
        this.$api.getBanners()
        .then(data => {
            if(data.data.status == 200) {
                this.swiperSlides = data.data.results;
            } else {
                alert('Banner unavailable')
            }
        })
    },

    setup() {
        const reload = inject('reload');
        provide('reload', reload);
    },
    computed: {
        ...mapState(['currentCity'])
    },
};

</script>


<style lang='less' scoped>
.mySwiper {
    z-index: -1;
}


#home-header {
    height: 90px;
    width: 100%;
    position: absolute;
    z-index: 10000;


    * {
        color: #ffffff;
        font-family: Arial;

        line-height: 1;
        text-decoration: none;
    }


    .top-row {
        display: flex;
        width: 100%;
        position: absolute;
        top: 15px;
        text-align: left;
        font-size: 28px;
        font-weight: bold;
        left: 0;
        justify-content: space-between; /* Add this line */

        .city-selector {
            margin-left: 20px;
        }

        .logo {
            margin-right: 20px;
        }
    }

    .search-bar-mobile {
        position: absolute;
        top: 120%;
        width: 100%;
        left: 0px;
        height: 150px;

        h1 {
            color: rgb(255, 255, 255);
            -webkit-font-smoothing: antialiased;
            text-transform: none;
            font-family: "Object Sans", "Adjusted Arial", Tahoma, Geneva, sans-serif;
            margin: 0px;
            font-weight: 900;
            font-size: 36px;
            line-height: 40px;
        }

        .search-container {
            width: 320px;
            height: 58px;
            background-color: #fff;
            border-radius: 10px;
            border: 1px solid gainsboro;

            position: relative;
            top: 20px;

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
                float: right;
                z-index: 10;
            }
        }
    }

    .search-bar-mobile {
        position: absolute;
        top: 120%;
        width: 100%;
        left: 0px;
        height: 150px;

        h1 {
            color: rgb(255, 255, 255);
            -webkit-font-smoothing: antialiased;
            text-transform: none;
            font-family: "Object Sans", "Adjusted Arial", Tahoma, Geneva, sans-serif;
            margin: 0px;
            font-weight: 900;
            font-size: 36px;
            line-height: 40px;
        }

        .search-container {
            width: 320px;
            height: 58px;
            background-color: #fff;
            border-radius: 10px;
            border: 1px solid gainsboro;

            position: relative;
            top: 20px;

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
                float: right;
                z-index: 10;
            }
        }
    }

    .search-bar {
        position: absolute;
        top: 120%;
        width: 800px;
        left: 0;
        height: 150px;

        h1 {
            color: rgb(255, 255, 255);
            -webkit-font-smoothing: antialiased;
            text-transform: none;
            font-family: "Object Sans", "Adjusted Arial", Tahoma, Geneva, sans-serif;
            margin: 0px;
            font-weight: 900;
            font-size: 60px;
            line-height: 60px;
        }

        .search-container {
            width: 450px;
            height: 68px;
            background-color: #fff;
            border-radius: 10px;
            border: 1px solid gainsboro;
            margin-top: 30px;
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
                float: right;
                z-index: 10;
            }
        }
    }

    * {
        //outline: 1px solid red;
    }

}


</style>
