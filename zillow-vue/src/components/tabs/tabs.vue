<script>
import Content from "./content";

export default {
  name: "Tabs",
  data() {
    return {
      showContent: []
    };
  },
  props: {
    currentIndex: {
      type: [String, Number],
      default: 1
    }
  },
  methods: {
    updateCurrentIndex(index) {
      this.$emit("changeCurrentIndex", index);
    }
  },
  render() {
    return (
        <div>
          <ul class="tabs-header">{this.$slots.default}</ul>
          <Content showContent={this.showContent}/>
        </div>
    );
  }
};
</script>
<style scoped>
.tabs-header {
  display: flex;
  list-style: none;
  margin: 0;
  padding: 0;
  border-bottom: 2px solid #ededed;
}
</style>
