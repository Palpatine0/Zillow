<template>
<div>

    <v-container>
        <v-row justify="center">
            <v-dialog v-model="itemPurchase_dialog" max-width="600px" persistent>
                <v-card>
                    <v-card-title>
                        <span class="text-h5">Select Renting Duration</span>
                    </v-card-title>

                    <v-card-text>
                        <v-container>
                            <v-row>
                                <span class="close" @click.stop="toggleDatePicker">&times;</span>
                                <h4></h4>
                                <div class="center-h">
                                    <input type="date" v-model="startDate">
                                    <input type="date" v-model="endDate">
                                </div>
                            </v-row>
                        </v-container>
                    </v-card-text>

                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="blue darken-1" text @click="itemPurchase_dialog = false">
                            Close
                        </v-btn>
                        <v-btn color="blue darken-1" text @click="purchase">
                            Confirm
                        </v-btn>
                    </v-card-actions>

                </v-card>
            </v-dialog>
        </v-row>
    </v-container>

</div>
</template>
<script>
import {mapActions, mapState} from "vuex";


export default {
    name: "Item-purchase",

    props: [
        "itemPurchase_dialog",
        "itemId",
        "price"
    ],
    data() {
        return {
            startDate: new Date().toISOString().substring(0, 10),
            endDate: new Date().toISOString().substring(0, 10),
            price: this.price,
        };
    },

    computed: {
        ...mapState([
            "collect",
            'user'
        ])
    },
    methods: {
        ...mapActions([
            "addCollectAction",
            "delCollectAction"
        ]),

        toggleDatePicker() {
            this.showDatePicker = !this.showDatePicker;
        },

        purchase() {
            let startDate = new Date(this.startDate);
            let endDate = new Date(this.endDate);
            let months = (endDate.getFullYear() - startDate.getFullYear()) * 12;
            months -= startDate.getMonth();
            months += endDate.getMonth();
            let totalPrice = months * this.price;
            if(this.user) {
                this.$router.push(
                    '/buy/' + this.itemId +
                    '/' + this.userId +
                    '/' + this.startDate +
                    '/' + this.endDate +
                    '/' + totalPrice
                );
            } else {
                this.$router.push('/login')
            }
        },


        isStored() {
            let index = this.collect.indexOf(this.itemId);
            if(index > -1) {
                return true;
            } else {
                return false;
            }
        },
    },

};
</script>
<style lang="less" scoped>

.center-h {
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
</style>