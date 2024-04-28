<template>
    <div ref="load">Pending for load more...</div>
</template>
<script>
export default {
    name: 'LoadMore',
    data() {
        return {}
    },
    mounted() {
        let timer = null;
        const winHeight = document.documentElement.clientHeight;
        let loadDiv = this.$refs.load
        window.onscroll = () => {
            // console.log(winHeight,document.documentElement.scrollTop,loadDiv.offsetTop,loadDiv.getBoundingClientRect().top)
            if (timer) {
                clearTimeout(timer)
            }
            timer = setTimeout(() => {
                if (loadDiv) {
                    if (winHeight > loadDiv.getBoundingClientRect().top) {
                        this.$emit('getMoreData')
                    }
                }
            }, 300)
        }
    },
}
</script>
<style lang="less" scoped>
div {
    color: #d4d8d9;
}
</style>