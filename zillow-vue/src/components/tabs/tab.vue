<script>
export default {
    name: "Tab",

    props: {
        label: {
            type: String,
            default: "tab"
        },
        index: {
            type: [String, Number],
            default: 1
        }
    },
    data() {
        return {};
    },

    computed: {
        active() {
            return this.$parent.currentIndex == this.index;
        }
    },
    mounted() {
        this.$parent.showContent.push(this);
    },
    render() {
        const classStyle = {
            tab: true,
            active: this.active
        };
        return (
            <li onClick={this.clickTabHandler} class={classStyle}>
                {this.label}
            </li>
        );
    },
    methods: {
        clickTabHandler() {
            this.$parent.updateCurrentIndex(this.index);
        }
    },
};
</script>
<style scoped>
.tab {
    flex: 1;
    list-style: none;
    line-height: 40px;
    margin-right: 30px;
    position: relative;
    text-align: center;
}

.tab.active {
    border-bottom: 2px solid blue;
}
</style>

