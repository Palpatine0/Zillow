<template>
    <div class="comment-list">
        <div v-if="commentData == ''">
            <div class="center-h" style="color:gainsboro;">There's no comment.</div>
        </div>
        <div v-for='(item,index) in commentData' :key='index' class="comment-item">
            <h3>
                {{ item.username }}
            </h3>
            <!--<Star :star='item.star'/>-->
            <p>{{ item.comment }}</p>
        </div>
    </div>
</template>
<script>
import LoadMore from '../../../components/LoadMore/LoadMore'
import Star from '../Star/Star'

export default {
    name: 'DetailsComment',
    data() {
        return {
            commentData: {},
            page: 0
        }
    },
    props: ['itemId'],
    components: {
        LoadMore,
        Star
    },
    methods: {
        http() {
            return this.$api.getCommentsByItemId({
                        itemId: this.itemId, page: this.page
                    }
            )
        },
        getMoreData() {
            this.page += 1;
            this.http()
            .then(data => {
                this.commentData = this.commentData.concat(data.data.data)
            })
        }
    },
    mounted() {
        this.http()
        .then(data => {
            console.log("comment")
            console.log(data.data.data)
            console.log("/comment")
            this.commentData = data.data.data
        })
    },
}
</script>
<style lang="less" scoped>
.comment-item {
    * {
        font-family: Arial;
    }

    border-bottom: 1px solid #f1f1f1;
    padding-bottom: 10px;
    margin-bottom: 10px;

    h3 {
        font-size: 18px;
        line-height: 1.5;
        color: #666;
    }

    p {
        color: #999;
        line-height: 1.5;
    }
}

.comment-list {
    padding: 20px;
}

.center-h {
    justify-content: center;
    align-items: center;
    display: flex;
    margin: 0 auto;
}
</style>