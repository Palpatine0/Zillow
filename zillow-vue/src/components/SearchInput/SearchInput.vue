<!--SearchInput.vue-->
<template>
    <input v-model="msg" class='search-input' placeholder="Address, neighborhood, city, Zip, etc" type="text" @keyup="search($event)">
</template>

<script>
export default {
    name: 'SearchInput',
    inject: ['reload'],
    data() {
        return {
            msg: ''
        }
    },
    props: ['pk'],
    methods: {
        search(e) {
            if (e.keyCode === 13) {
                this.$router.push('/search/' + this.msg)
                if (this.msg == this.pk) {
                    return
                }
                this.$emit('setSearchMsgAction', {data: this.msg})
            }
        }
    },
    mounted() {
        this.msg = this.pk
        this.$emit('setSearchMsgAction', {data: this.pk})
    },
    watch: {
        'pk': function (n, o) {
            if (n !== this.msg) {
                this.msg = n;
                this.$emit('setSearchMsgAction', {data: n})
            }
        }
    },
}
</script>

<style lang="less" scoped>
.search-input {
    font-size: 22px !important;
    font-weight: normal;
    border: 0;
    width: 82% !important;
    height: 22px;
    color: #333 !important;
    padding-left: 5px;
    position: relative;
    font-family: "Open Sans", "Adjusted Arial", Tahoma, Geneva, sans-serif !important;
}
</style>