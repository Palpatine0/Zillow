<template>
    <div>
        <Header title="Mine"/>

        <div class="userinfo-container mt-3">
            <div style="display: flex">
                <img src="../../../src/assets/images/user.png" style="width: 40px;height: 40px;">
                <h3 class="mt-2" style="margin-left: 10px">{{ username }}</h3>
            </div>
            <div class="mt-2" style="display: flex" >
                <img src="../../../src/assets/images/location.png" style="width: 40px;height: 40px;">
                <h3 class="mt-2" style="margin-left: 10px">{{ currentCity }}</h3>
            </div>
        </div>

        <Order/>


        <NavBar/>
    </div>
</template>
<script>
import {mapState, mapActions} from "vuex";
import Header from "../../components/Header/Header";
import Order from './Orders/Orders.vue'
import NavBar from "@/components/NavBar/NavBar.vue";

export default {
    name: "Mine",
    data() {
        return {
            username: ''
        };
    },
    methods: {
        getUserById() {
            this.$api.getUserById({
                id: this.userId
            })
            .then(data => {
                this.username=data.data.data.username
            })
        }
    },
    components: {
        Header,
        Order,
        NavBar
    },
    computed: {
        ...mapState(["userId", "currentCity"])
    },
    mounted() {
        if (!this.userId) {
            this.$router.push("/login");
        }
        this.getUserById()
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
    width: 80%;
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