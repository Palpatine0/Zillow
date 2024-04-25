<template>
    <div class="hotproduct">
        <h1>{{ title }}</h1>
        <div class="hot-container">
            <ul class="clear-fix">
                <li v-for='(item,index) in hotData' :key='index' class="hot-item">
                    <a :href='item.link'>
                        <div class="image-wrapper">
                            <img class="img-fluid" :alt='item.title' :src='item.img'/>
                        </div>
                        <span>{{ item.title }}</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</template>

<script>
export default {
    name: 'HomeHotView',
    data() {
        return {}
    },
    props: ['title', 'hotData']
}
</script>
<style lang="less" scoped>
.hotproduct {
    * {
        font-family: Arial;
    }

    .center_h {
        justify-content: center;
        align-items: center;
        display: flex;
        margin: 0 auto;
    }

    h1 {
        position: relative;
        left: 3%;
        width: 97%;
        margin-top: 10px;
        font-size: 20px;
        font-weight: bold;
        text-align: left;
        color: #156FF6;
        font-size: 30px !important;
    }

    .hot-container {
        background: #f7f8fa;
        margin: 10px;
        border-radius: 5px;
        //box-shadow: 1px 1px 5px 1px #e1e1e1;

        ul {
            position: relative;
            left: 1%;

            li {
                width: 50%;
                overflow: hidden;
                float: left;

                a {
                    img {
                        border-radius: 5px;
                        width: 90%;
                        height: 90%;
                        bottom: 0;
                    }

                    span {
                        margin: 0 auto;
                        width: 160px;
                        height: 28px;
                        display: block;
                        text-align: center;
                        font-size: 12px;
                    }
                }
            }
        }
    }

    .hot-item {
        width: 50%;
        padding: 5px;
    }

    .image-wrapper {
        display: flex;
        justify-content: center;
        align-items: center;
        overflow: hidden;
        height: 0;
        padding-bottom: 56.25%; /* 16:9 aspect ratio */
        position: relative;
    }

    img.img-fluid {
        max-width: 100%; /* Will scale the image to the width of the container */
        max-height: 100%; /* Will scale the image to the height of the container */
        object-fit: cover; /* Will fill the content box, can be set to 'contain' if you want to see the whole image */
        border-radius: 5px;
        position: absolute; /* Positioning the image absolutely within the relative container */
    }


    * {
        //outline: 1px solid red;
    }
}
</style>