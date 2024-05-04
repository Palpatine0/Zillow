<template>
    <div class="city-list-container">
        <h3>Popular Cities</h3>
        <ul class="clear-fix" @click="getClickCity($event)">
            <li>
                <span>Austin</span>
            </li>
            <li>
                <span>Dallas</span>
            </li>
            <li>
                <span>New York</span>

            </li>
            <li>
                <span>Atlanta</span>
            </li>
            <li>
                <span>Cape Coral</span>
            </li>
        </ul>
    </div>
</template>
<script>
import {mapActions} from 'vuex'

export default {
    name: "CityHot",
    data() {
        return {};
    },
    methods: {
        getClickCity(e) {
            // console.log(e.target.innerHTML)
            this.setCityAciton({
                data: e.target.innerHTML
            })
            window.history.back()
        },
        ...mapActions(['setCityAciton'])
    },
};
</script>
<style lang="less" scoped>
.city-list-container {
    background-color: #fff;
    padding: 10px 15px 20px 15px;

    * {
        font-family: Arial;
    }

    h3 {
        font-size: 16px;
    }

    ul {
        display: block;
        width: 100%;
    }

    li {
        list-style: none;
        display: block;
        float: left;
        width: 33.3%;
        text-align: center;

        span {
            display: inline-block;
            border: 1px solid #ccc;
            font-size: 16px;
            line-height: 2;
            margin-top: 15px;
            width: 90%;
        }
    }

    span {
        border-radius: 5px;
    }
}
</style>