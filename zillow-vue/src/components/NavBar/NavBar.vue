<template>

<div v-if="dw != null">
    <div class="nav-footer" :style="{ width: dw + 'px' }">
        <div class="clear-fix warp">
            <div class="manu-btn">
                <router-Link exact to="/">
                    <svg height="16" width="16" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512">
                        <path fill="#1470f5"
                              d="M575.8 255.5c0 18-15 32.1-32 32.1l-32 0 .7 160.2c0 2.7-.2 5.4-.5 8.1l0 16.2c0 22.1-17.9 40-40 40l-16 0c-1.1 0-2.2 0-3.3-.1c-1.4 .1-2.8 .1-4.2 .1L416 512l-24 0c-22.1 0-40-17.9-40-40l0-24 0-64c0-17.7-14.3-32-32-32l-64 0c-17.7 0-32 14.3-32 32l0 64 0 24c0 22.1-17.9 40-40 40l-24 0-31.9 0c-1.5 0-3-.1-4.5-.2c-1.2 .1-2.4 .2-3.6 .2l-16 0c-22.1 0-40-17.9-40-40l0-112c0-.9 0-1.9 .1-2.8l0-69.7-32 0c-18 0-32-14-32-32.1c0-9 3-17 10-24L266.4 8c7-7 15-8 22-8s15 2 21 7L564.8 231.5c8 7 12 15 11 24z"/>
                    </svg>
                    <br>
                    Home
                </router-Link>
            </div>
            <div class="manu-btn">
                <router-Link to="/shop">
                    <svg height="16" width="16" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512">
                        <path fill="#1470f5"
                              d="M40 48C26.7 48 16 58.7 16 72v48c0 13.3 10.7 24 24 24H88c13.3 0 24-10.7 24-24V72c0-13.3-10.7-24-24-24H40zM192 64c-17.7 0-32 14.3-32 32s14.3 32 32 32H480c17.7 0 32-14.3 32-32s-14.3-32-32-32H192zm0 160c-17.7 0-32 14.3-32 32s14.3 32 32 32H480c17.7 0 32-14.3 32-32s-14.3-32-32-32H192zm0 160c-17.7 0-32 14.3-32 32s14.3 32 32 32H480c17.7 0 32-14.3 32-32s-14.3-32-32-32H192zM16 232v48c0 13.3 10.7 24 24 24H88c13.3 0 24-10.7 24-24V232c0-13.3-10.7-24-24-24H40c-13.3 0-24 10.7-24 24zM40 368c-13.3 0-24 10.7-24 24v48c0 13.3 10.7 24 24 24H88c13.3 0 24-10.7 24-24V392c0-13.3-10.7-24-24-24H40z"/>
                    </svg>
                    <br>
                    Homes
                </router-Link>
            </div>

            <div class="manu-btn ">
                <router-Link to="/shopcar">
                    <svg height="16" width="16" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512">
                        <path fill="#1470f5" d="M224 256A128 128 0 1 0 224 0a128 128 0 1 0 0 256zm-45.7 48C79.8 304 0 383.8 0 482.3C0 498.7 13.3 512 29.7 512l388.6 0c16.4 0 29.7-13.3 29.7-29.7C448 383.8 368.2 304 269.7 304l-91.4 0z"/>
                    </svg>
                    <br>
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
        dw.value = globalData.deviceWidth
    }, 1000);
});
</script>