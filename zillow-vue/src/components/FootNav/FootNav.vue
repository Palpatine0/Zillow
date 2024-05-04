<template>

    <div v-if="dw != null">
        <div class="nav-footer" :style="{ width: dw + 'px' }">

            <!--    <div class="nav-footer" >-->
            <div class="clear-fix warp">
                <div class="manu-btn">
                    <router-Link exact to="/">
                        <i class="iconfont icon-home"></i>
                        Home
                    </router-Link>
                </div>
                <div class="manu-btn">
                    <router-Link to="/shop">
                        <svg height="16" viewBox="0 0 512 512" width="16" xmlns="http://www.w3.org/2000/svg">
                            <path d="M40 48C26.7 48 16 58.7 16 72v48c0 13.3 10.7 24 24 24H88c13.3 0 24-10.7 24-24V72c0-13.3-10.7-24-24-24H40zM192 64c-17.7 0-32 14.3-32 32s14.3 32 32 32H480c17.7 0 32-14.3 32-32s-14.3-32-32-32H192zm0 160c-17.7 0-32 14.3-32 32s14.3 32 32 32H480c17.7 0 32-14.3 32-32s-14.3-32-32-32H192zm0 160c-17.7 0-32 14.3-32 32s14.3 32 32 32H480c17.7 0 32-14.3 32-32s-14.3-32-32-32H192zM16 232v48c0 13.3 10.7 24 24 24H88c13.3 0 24-10.7 24-24V232c0-13.3-10.7-24-24-24H40c-13.3 0-24 10.7-24 24zM40 368c-13.3 0-24 10.7-24 24v48c0 13.3 10.7 24 24 24H88c13.3 0 24-10.7 24-24V392c0-13.3-10.7-24-24-24H40z"
                                  fill="#1470f5"/>
                        </svg>
                        <br>
                        Items
                    </router-Link>
                </div>

                <div class="manu-btn ">
                    <router-Link to="/shopcar">
                        <i class="iconfont icon-team"></i>
                        Your Zillow
                    </router-Link>
                </div>
            </div>
        </div>
    </div>
</template>

<style lang="less" scoped>
.active {
    color: #156FF6;
}

.nav-footer {

    border-right: 1px solid #FFFFFF;

    z-index: 10;
    background: #fff;
    position: fixed;
    left: 0;
    right: 0;
    bottom: 0;
    box-shadow: 0px 0px 0px 1px #e1e1e1;

    * {
        font-family: Arial;
    }

    .svg {
        display: flex;
        align-items: center;
        gap: 0;
    }

    .warp {
        height: 50px;
        padding-top: 6.5px;

        .manu-btn {
            float: left;
            text-align: center;
            width: 33.3%;

            i {
                display: block;
                font-size: 20px;
            }

            span {
                font-size: 12px;
            }
        }
    }

    * {
        //outline: 1px solid red;
    }
}
</style>


<script setup>
import {ref, onMounted, inject} from 'vue';

let globalData = inject('globalData');
let dw = ref(null); // Define deviceWidth in the data option

onMounted(() => {
    setTimeout(() => {
        console.log("globalData-deviceWidth")
        console.log(globalData.deviceWidth)
        dw.value = globalData.deviceWidth
    }, 1000);
});
</script>