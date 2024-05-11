<template>
    <div>
        <div class="buy-store-container clear-fix">
            <!--<div class="item-container float-left" @click="clickStoreHandler">
                <button :class="{'selected':true, 'o':isCollect}">
                    {{ isCollect ? 'Untag' : 'Tag' }}
                </button>
            </div>-->

            <div class="item-container float-right" @click="clickBuyHandler">
                <!--                <button :class="{'jin':buytime}" :disabled='disableclick' @click="purchase">-->
                <button :class="{'jin':buytime}" :disabled='disableclick' @click="toggleDatePicker">
                    Rent
                    <!--<i v-if='buytime'>({{ buytime }})</i>-->
                </button>
                <div v-if="showDatePicker" class="modal">
                    <div class="modal-content mb-4">
                        <span class="close" @click.stop="toggleDatePicker">&times;</span>
                        <h4>Select a date for renting</h4>
                        <div class="center_h">
                            <input type="date" v-model="startDate">
                            <input type="date" v-model="endDate">
                        </div>
                        <button class="mt-2" @click="purchase">Confirm</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import {mapActions, mapState} from "vuex";


export default {
    name: "ItemOption",
    data() {
        return {
            isCollect: false,
            buytime: '',
            disableclick: false,
            timer: null,

            showModal: false,
            showDatePicker: false,

            startDate: new Date().toISOString().substring(0, 10),
            endDate: new Date().toISOString().substring(0, 10),
            price: this.price

        };
    },
    computed: {
        ...mapState(["collect", 'userId'])
    },
    mounted() {
        this.isCollect = this.isStored()
        /*this.$api.buytime({id: this.id})
        .then(data => {
            if (data.status == 200) {
                this.dtimer(data.data.time)
            } else {
                console.log(data.data.msg)
            }
        })*/

    },
    beforeDestroy() {
        this.timer = null;
    },
    props: ["itemId", "price"],
    methods: {
        ...mapActions(["addCollectAciton", "delCollectAction"]),
        toggleDatePicker() {
            this.showDatePicker = !this.showDatePicker; // This should toggle the state between true and false.
            console.log("Toggle DatePicker: ", this.showDatePicker);
        },


        clickBuyHandler() {
            this.showDatePicker = true;  // This assumes you have a `showDatePicker` data property to control the visibility of your date picker modal.
        },
        purchase() {
            let startDate = new Date(this.startDate);
            let endDate = new Date(this.endDate);
            let months = (endDate.getFullYear() - startDate.getFullYear()) * 12;
            months -= startDate.getMonth();
            months += endDate.getMonth();
            let totalPrice = months * this.price;
            console.log(this.startDate)
            console.log(this.endDate)
            console.log(totalPrice)
           /* if (this.userId) {
                this.$router.push(
                        '/buy/' + this.itemId +
                        '/' + this.userId +
                        '/' + this.startDate +
                        '/' + this.endDate +
                        '/' + totalPrice
                );
            } else {
                this.$router.push('/login')
            }*/
        },

        dtimer(wei) {
            let that = this;
            that.timer = setInterval(function () {
                let now = new Date().getTime() + 6000;
                let left = (wei - now) / 1000
                if (left > 0) {
                    that.disableclick = true;
                    that.buytime = that.timeFormat(left)
                } else {
                    that.disableclick = false;
                    that.buytime = '';
                    that.timer = null;
                }
            }, 1000)
        },

        timeFormat(t) {
            let h = parseInt(t / 3600);
            let m = parseInt(t % 3600 / 60);
            let s = parseInt(t % 3600 % 60);
            return this.buling(h) + ":" + this.buling(m) + ':' + this.buling(s);
        },
        buling(t) {
            return t > 9 ? t : '0' + t;
        },

        isStored() {
            let index = this.collect.indexOf(this.itemId);
            if (index > -1) {
                return true;
            } else {
                return false;
            }
        },
        clickStoreHandler() {
            let a = this.isStored();
            // console.log(a)
            if (this.userId) {
                if (a) {
                    this.isCollect = false
                    this.delCollectAction({data: this.itemId})
                } else {
                    this.isCollect = true
                    this.addCollectAciton({data: this.itemId})
                }
            } else {
                this.$router.push('/login')
            }
        },

    },

};
</script>
<style lang="less" scoped>

.center_h {
    justify-content: center;
    align-items: center;
    display: flex;
    margin: 0 auto;
}

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

    .modal {
        position: fixed;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, 0.7); /* Darker background */
        display: flex;
        justify-content: center;
        align-items: center;
        z-index: 10; /* Make sure it's above other content */
    }

    .modal-content {
        position: relative; /* For positioning the close button absolutely within this container */
        background-color: #fff;
        padding: 20px;
        border-radius: 5px;
        width: 360px; /* Adjust width as needed */
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Optional: adds shadow for better visibility */
    }

    .close {
        position: absolute;
        top: 10px; /* Adjust as necessary */
        right: 10px; /* Adjust as necessary */
        cursor: pointer;
        font-size: 24px;
    }

    input[type="date"] {
        width: 90%; /* Adjusts width to fit the modal better */
        padding: 8px; /* More comfortable padding */
        margin-top: 10px; /* Spacing between the date inputs */
        border: 2px solid #ccc; /* Gives a more defined border */
        border-radius: 4px; /* Rounded borders */
        display: block; /* Ensures it takes a full row */
        box-sizing: border-box; /* Includes padding and border in the element's total width */
    }


}
</style>