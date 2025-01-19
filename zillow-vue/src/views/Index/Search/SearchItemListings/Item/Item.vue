<!--PurchasedItem.vue-->
<template>
    <div class="list-item">
        <router-link :to="`/item/${ curdata.id }`">

            <img :src="getFirstImageUrl(curdata.imgs)" alt/>

            <div class="mask">
                <div class="left">
                    <b><p>{{ curdata.title }}</p></b>
                    <p>{{ curdata.houseType }}</p>
                </div>
                <div class="right">
                    <div class="btn">{{ curdata.rentType }}</div>
                    <p>
                        ${{ curdata.price }} / Month
                    </p>
                </div>
            </div>
        </router-link>
    </div>
</template>
<script>
export default {
    name: "Item",


    data() {
        return {};
    },
    props: ["curdata"],

    methods: {
        getFirstImageUrl(imgs) {
            let isInBrackets = false;
            let imagePath = '';

            // Iterate through each character in the string
            for (let i = 0; i < imgs.length; i++) {
                const char = imgs[i];

                if (char === '[') {
                    // Start recording characters when "[" is encountered
                    isInBrackets = true;
                } else if (char === ',' && isInBrackets) {
                    // Stop recording when "," is encountered
                    break;
                } else if (isInBrackets) {
                    // Add the character to the image path
                    imagePath += char;
                }
            }

            // Trim any leading or trailing whitespace
            imagePath = imagePath.trim();

            if (imagePath) {
                const imageUrl = `http://percival.services:8888/${imagePath}`;
                // console.log("Generated image URL:", imageUrl);
                return imageUrl;
            } else {
                // console.error("Failed to extract image path:", imgs);
                return "default-image-url";
            }
        },
    },
};
</script>

<style lang="less" scoped>
* {
    font-family: Arial;
}

.list-item {


    position: relative;
    margin: 5px 0;

    img {
        width: 100%;
    }

    .mask {
        position: relative;
        width: 100%;
        height: 80px;
        background: rgba(255, 255, 255, 0.8);
        left: 0;
        right: 0;
        bottom: 0px;
        display: flex;
        z-index: 1;

        .left {
            text-align: center;
            padding: 10px 10px;
            flex: 1;

            p {
                padding: 5px 10px;
                font-size: 12px;
            }
        }

        .right {
            text-align: center;
            padding: 10px 10px;
            flex: 1;

            p,
            div {
                padding: 5px 10px;
                font-size: 12px;
            }

            .btn {
                border-radius: 5px;
                background: rgba(255, 255, 255, 0.8);
                color: #84a0ae;
            }
        }
    }
}
</style>