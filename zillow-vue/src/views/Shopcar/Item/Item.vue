<!--Item.vue-->
<template>
    <div>
        <div class="clear-fix order-item-container">

            <div class="order-item-img float-left">
                <img :src="idata.img"/>
            </div>

            <div class="order-item-content">
                <span><b>Name:</b> <br>{{ idata.title }}</span>
                <span><b>Type:</b> <br>{{ idata.houseType }}</span>
                <span><b>Price:</b> <br>${{ idata.price }}</span>
            </div>

            <!--comment btn-->
            <div class="order-item-comment float-right">
                <button v-if="idata.commentState===0" class="btn" @click="commentBtnOnclick">Comment</button>
                <!--            <button v-else-if="idata.commentState === 2" class="btn unseleted-btn">Commented</button>-->
                <span v-else></span>
            </div>

            <div v-if="idata.commentState === 1" class="comment-text-container">
                <textarea v-model="msg" :style="{ width: '100%', height: '80px' }" class="comment-text"></textarea>
                <div class="btns">
                    <button class="btn" @click="submit">Submit</button>
                    <button class="btn unseleted-btn" @click="cancelBtnOnclick">Cancel</button>
                </div>
            </div>
        </div>

    </div>

</template>
<script>
export default {
    name: "Item",
    data() {
        return {
            msg: ''
        };
    },
    props: ['idata'],
    methods: {
        commentBtnOnclick() {
            this.idata.commentState = 1
        },
        submit() {

            this.$api.addComment(
                    {
                        commentContent: this.msg,
                        orderId: this.idata.id,
                        phone: this.idata.phone
                    })
            .then(data => {
                console.log(data)
                this.idata.commentState = 0;
                this.msg = ''
            })

        },
        cancelBtnOnclick() {
            this.idata.commentState = 0;
        }
    },
};
</script>
<style lang="less" scoped>
.order-item-container {
    //height: 220px;
    padding: 20px;
    //border-bottom: 1px solid #f1f1f1;

    * {
        font-family: Arial;
    }

    .btn {
        width: 168px;
        text-align: center;
        background-color: #156ff6;
        color: #fff;
        padding: 5px 0;
        border: 0;
        font-size: 16px;
        border-radius: 10px;
        margin: 2px;
    }

    .unseleted-btn {
        background-color: #999;
    }

    .order-item-img {
        position: relative;
        left: 1%;
        margin: 5px;
        display: block;
        height: 140px;
        width: 180px;

        img {
            border-radius: 10px;
            width: 100%;
            height: 120px;
            position: relative;
            top: 10px;
        }

    }

    .order-item-comment {
        display: block;
        position: relative;
        top: 5px;
        left: -62%;
        height: 40px;
        width: 150px;
        text-align: left;

        button {
            margin-top: 25px;
        }
    }

    .order-item-content {
        padding-top: 10px;
        position: relative;
        left: 3%;

        span {
            color: #666;
            display: block;
            text-align: left;
            line-height: 1.5;
            font-size: 12px;
        }
    }

    .comment-text-container {
        margin-top: 10px;
        padding: 10px;

        textarea {
            border-radius: 5px;
        }

        .btns {
            display: flex;
            height: 35px;

            .btn {
                width: 50%;
            }
        }

        .comment-text {
            border: 1px solid #f1f1f1;
        }
    }
}
</style>