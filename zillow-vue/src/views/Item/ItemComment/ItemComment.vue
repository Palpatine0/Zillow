<template>
<v-app style="padding: 4vw">
    <v-container>
        <div v-if="commentData == ''">
            <div class="center-h" style="color:gainsboro;">There's no comment.</div>
        </div>
        <div v-for='(comment,index) in commentData' :key='index' class="comment-item">
            <v-row>
                <h3>{{ comment.username }}</h3>
            </v-row>
            <v-row>
                <p>{{ comment.comment }}</p>
            </v-row>
        </div>
    </v-container>
</v-app>

</template>
<script>
import LoadMore from '../../../components/LoadMore/LoadMore'
import {mapState} from "vuex";

export default {
    name: 'Comment',

    components: {
        LoadMore
    },
    props: ['itemId'],
    data() {
        return {
            commentData: {},
            page: 0,

            defaultAvatar:'public/image/user/6808979ab054b50e0280128d/profile.jpg'
        }
    },

    computed: {
        ...mapState([
            'awsS3RequestUrl'
        ])
    },
    mounted() {
        this.getCommentsByItemId()
    },
    methods: {
        getCommentsByItemId() {
            return this.$api.comment.getCommentsByItemId({
                itemId: this.itemId,
                page: this.page
            })
            .then(data => {
                this.commentData = data.data.data
            })
        },
        getMoreData() {
            this.page += 1;
            this.getCommentsByItemId()
            .then(data => {
                this.commentData = this.commentData.concat(data.data.data)
            })
        }
    },
}
</script>
<style lang="less" scoped>
.comment-item {
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