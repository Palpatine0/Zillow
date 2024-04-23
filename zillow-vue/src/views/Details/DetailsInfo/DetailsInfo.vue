<template>
  <div>
    <div class="detail-info">
      <h3>{{ detailsInfo.title }}</h3>

      <div class="box_infoA">
        <div class="box_infoA_A">
          <div class="info-list_A">
            <p class="p_a">Price: </p>
            <span> {{ detailsInfo.price }}/Mouth</span>
          </div>
          <div class="info-list_A">
            <p class="p_a">Type: </p>
            <span>{{ detailsInfo.info.type }}</span>
          </div>
          <div class="info-list_A">
            <p class="p_a">Area: </p>
            <span>{{ detailsInfo.houseType }}</span>
          </div>
        </div>
      </div>

      <div class="box_infoB">
        <div class="info-list_B">
          <p>
            <b>Floor:</b>
            {{ detailsInfo.info.level }}</p>
          <p>
            <b>Decoration: </b>
            {{ detailsInfo.info.style }}</p>
        </div>
        <div class="info-list_B">
          <p>
            <b>Orientation: </b>
            {{ detailsInfo.info.orientation }}</p>
          <p>
            <b>Complete Date: </b>
            {{ detailsInfo.info.years }}</p>
        </div>

      </div>
    </div>
    <DetailsStore :id="id"/>
  </div>
</template>
<script>
import DetailsStore from "../DetailsStore/DetailsStore";

export default {
  name: "DetailsInfo",
  data() {
    return {};
  },
  components: {
    DetailsStore
  },
  props: ["detailsInfo", "id"]
};
</script>
<style lang="less" scoped>
.detail-info {
  * {
    font-family: Arial;
  }

  h3 {
    font-size: 17px;
    color: #000;
    opacity: 0.8;
    margin-top: 20px;
    margin-left: 20px;
    margin-bottom: 20px;
  }

  .box_infoA {
    width: 90%;
    height: 190px;
    border-radius: 8px;
    margin-left: 20px;

    .box_infoA_A {
      position: relative;
      top: 10px;
      left: 10px;

      .info-list_A {
        margin-bottom: 11px;

        p {
          text-align: left;
          font-size: 20px;
          color: #585858;
          position: relative;
          //font-weight: bold;
          //top: 10px;
        }

        span {
          text-align: center;
          font-size: 18px;
          color: #156FF6;
          position: relative;
          top: 2px;
          bottom: 10px;
        }
      }

    }

    box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.1);


  }

  .box_infoB {
    margin: 20px;

    .info-list_B {
      display: flex;


      p {
        flex: 1;
        font-size: 15px;
        margin: 5px;

        b {
          font-size: 14px;
        }
      }
    }
  }
}
</style>