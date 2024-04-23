<script>
export default {
  name: "Tab",
  data() {
    return {};
  },
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
  mounted() {
    this.$parent.showContent.push(this);
  },
  computed: {
    active() {
      // 不修改props属性
      return this.$parent.currentIndex == this.index;
    }
  },
  methods: {
    clickTabHandler() {
      this.$parent.updateCurrentIndex(this.index);
    }
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
  }
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

