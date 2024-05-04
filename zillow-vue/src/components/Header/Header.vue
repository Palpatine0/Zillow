<template>
    <div id="common-header">
    <span class="back-icon" @click='back'>
      <i class="icon-chevron-left"></i>
    </span>
        <h1>{{ title }}</h1>
    </div>
</template>
<script>
export default {
    name: 'Header',
    data() {
        return {}
    },
    props: ['title'],
    methods: {
        back() {
            window.history.back()
        }
    },
}
</script>
<style lang="less" scoped>
#common-header {
    * {
        font-family: Arial;
        color: #156FF6;
    }

    background-color: #fff;
    color: #fff;
    padding: 16px 10px;
    text-align: center;
    position: relative;

    h1 {
        display: inline;
        font-size: 16px;
        line-height: 1;
    }

    .back-icon {
        width: 16px;
        height: 16px;
        position: absolute;
        left: 10px;
        top: 19px;
    }
}
</style>