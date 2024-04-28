<template>
    <div>
        <div class="buy-store-container clear-fix">
            <div class="item-container float-left" @click="clickStoreHandler">
                <button :class="{'selected':true, 'o':isCollect}">
                    {{ isCollect ? 'Untag' : 'Tag' }}
                </button>
            </div>

            <div class="item-container float-right" @click="clickBuyHandler">
                <button :class="{'jin':buytime}" :disabled='disableclick' @click="buy">
                    Reserve
                    <i v-if='buytime'>({{ buytime }})</i>
                </button>
            </div>
        </div>
    </div>
</template>
<script>
import {mapState, mapActions} from "vuex";

export default {
    name: "DetailsStore",
    data() {
        return {
            isCollect: false,
            buytime: '',
            disableclick: false,
            timer: null
        };
    },
    computed: {
        ...mapState(["collect", 'user'])
    },
    mounted() {
        this.isCollect = this.isStore()
        this.$api.buytime({id: this.id})
        .then(data => {
            // console.log(data)
            if (data.status == 200) {
                this.dtimer(data.data.time)
            } else {
                console.log(data.data.msg)
            }
        })
    },
    beforeDestroy() {
        this.timer = null;
    },
    props: ["id"],
    methods: {
        ...mapActions(["addCollectAciton", "delCollectAction"]),
        buy() {
            console.log('购买', this.id, this.user)
            if (this.user) {
                this.$router.push('/buy/' + this.id + '/' + this.user)
            } else {
                this.$router.push('/login')
            }
        },

        dtimer(wei) {
            let that = this;
            that.timer = setInterval(function () {
                let now = new Date().getTime() + 6000;
                let left = (wei - now) / 1000
                if (left > 0) {
                    that.disableclick = true;
                    that.buytime = that.timefommate(left)
                } else {
                    that.disableclick = false;
                    that.buytime = '';
                    that.timer = null;
                }
            }, 1000)
        },

        timefommate(t) {
            let h = parseInt(t / 3600);
            let m = parseInt(t % 3600 / 60);
            let s = parseInt(t % 3600 % 60);
            return this.buling(h) + ":" + this.buling(m) + ':' + this.buling(s);
        },

        buling(t) {
            return t > 9 ? t : '0' + t;
        },

        isStore() {
            // 判断是否收藏
            let index = this.collect.indexOf(this.id);
            if (index > -1) {
                return true;
            } else {
                return false;
            }
        },
        clickStoreHandler() {
            let a = this.isStore();
            // console.log(a)
            if (this.user) {
                if (a) {
                    this.isCollect = false
                    this.delCollectAction({data: this.id})
                } else {
                    this.isCollect = true
                    this.addCollectAciton({data: this.id})
                }
            } else {
                this.$router.push('/login')
            }
        },
        clickBuyHandler() {
        }
    }
};
</script>
<style lang="less" scoped>
.clear-fix {
    clear: both;
    overflow: hidden;
    border-left: #ffffff;
}

.float-left {
    float: left;
}

.float-right {
    float: right;
}

.buy-store-container {
    //border-top: 1px solid #f1f1f1;
    background-color: #fff;
    display: flex;
    padding: 20px;

    .item-container {
        flex: 1;
        text-align: center;

        button {
            width: 100%;
            font-size: 16px;
            background-color: #156FF6;
            color: #fff;
            padding: 10px 0;
            border-radius: 10px;
            border: 1px solid #fff;
        }

        button.o {
            //background: #ff7f32;
        }

        button.selected {
            color: #fff;
        }

        button.jin {
            background: grey;
        }
    }
}
</style>