<script>
export default {
  name: "Content",
  data() {
    return {};
  },
  props: {
    showContent: {
      type: Array,
      required: true
    }
  },
  render() {
    return (
        <div>
          {this.showContent.map((element, index) => {
            return (
                <div key={index}>
                  {element.active ? element.$slots.default : ""}
                </div>
            );
          })}
        </div>
    );
  }
};
</script>
