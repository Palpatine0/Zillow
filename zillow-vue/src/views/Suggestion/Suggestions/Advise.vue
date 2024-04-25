<script setup>

</script>

<template>

    <div>
        <div class="advise">
            <div class="advise_warp">
                <h4>What to do next</h4>
                <br>
                <div class="box">
                    <img src="../../../assets/images/advise-a.png">
                    <div class="content">
                        <h5>See how mush you can afford</h5>
                        <p>Estimate a realistic budget using Zillow's affordable calculator</p>
                    </div>
                </div>
                <div class="box">
                    <img src="../../../assets/images/advise-b.png">
                    <div class="content">
                        <h5>Get pre-qualified for a home loan</h5>
                        <p>At Zillow Home Loans, we can pre-qualify you in less than three minutes with no impact on
                            your credit score.</p>
                    </div>
                </div>
                <div class="box">
                    <img src="../../../assets/images/advise-c.png">
                    <div class="content">
                        <h5>About Zillow Home Loans</h5>
                        <p>learn what services and loan types we offer, and hear from happy customers</p>
                    </div>
                </div>

            </div>
        </div>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
    </div>

</template>

<style lang="less" scoped>
.advise {

    .advise_warp {
        width: 92%;
        margin: 0 auto;


        h4 {
            font-size: 16px;
            line-height: 25px;
        }

        .box {
            width: 100%;
            border-radius: 5px;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.2);
            padding: 20px; /* This adds padding on all sides */
            position: relative; /* Ensures .content is positioned inside .box */
            margin-bottom: 20px;

            img {
                position: absolute;
                width: 12%;
            }

            .content {
                width: 80%;
                margin-left: 65px;

                h5 {
                    width: 60%;
                    margin: 0;
                    color: #333;
                    font-size: 13px;
                    font-weight: bold;
                    padding-bottom: 8px;
                }

                p {
                    width: 70%; /* Width of the paragraph is 70% of its container, which is .content */
                    color: #848b8d;
                    font-size: 14px;
                    line-height: 1.2;
                }
            }
        }
    }

}
</style>