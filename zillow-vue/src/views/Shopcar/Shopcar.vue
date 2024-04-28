<template>
    <div>
        <Header title="Mine"/>

        <div class="userinfo-container">
            <p>
                <svg height="20" viewBox="0 0 512 512" width="20" xmlns="http://www.w3.org/2000/svg">
                    <path d="M399 384.2C376.9 345.8 335.4 320 288 320H224c-47.4 0-88.9 25.8-111 64.2c35.2 39.2 86.2 63.8 143 63.8s107.8-24.7 143-63.8zM0 256a256 256 0 1 1 512 0A256 256 0 1 1 0 256zm256 16a72 72 0 1 0 0-144 72 72 0 1 0 0 144z"
                          fill="#1470f5"/>
                </svg>
                <br>
                <h3>{{ user }}</h3>
            </p>
            <p>
                <svg height="16" viewBox="0 0 384 512" width="12" xmlns="http://www.w3.org/2000/svg">
                    <path d="M384 192c0 87.4-117 243-168.3 307.2c-12.3 15.3-35.1 15.3-47.4 0C117 435 0 279.4 0 192C0 86 86 0 192 0S384 86 384 192z"
                          fill="#1470f5"/>
                </svg>
                <br>
                <h3>{{ curcity }}</h3>
            </p>
        </div>

        <ShopCarOrder/>
        <FootNav/>
    </div>
</template>
<script>
import {mapState, mapActions} from "vuex";
import Header from "../../components/Header/Header";
import ShopCarOrder from './ShopCarOrder/ShopCarOrder'
import FootNav from "@/components/FootNav/FootNav.vue";

export default {
    name: "Shopcar",
    data() {
        return {};
    },
    components: {
        FootNav,
        Header,
        ShopCarOrder
    },
    computed: {
        ...mapState(["user", "curcity"])
    },
    mounted() {
        if (!this.user) {
            this.$router.push("/login");
        }
    }
};
</script>
<style lang="less" scoped>
* {
    font-family: Arial;
}


.svg {
    // kill svg gap!!
    display: flex;
    align-items: center;
    gap: 0;
}

.userinfo-container {
    background-color: #fff;
    padding: 10px;

    position: relative;
    left: 5%;

    p {
        svg {
            height: 26px !important;
            width: 26px !important;
        }

        h3 {
            font-size: 32px !important;
        }

        line-height: 1.5;
        color: #666;
        margin-bottom: 10px;


    }
}

</style>