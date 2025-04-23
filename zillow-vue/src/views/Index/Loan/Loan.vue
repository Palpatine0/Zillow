<template>
<div class="loan">
    <v-container class="loan-container">
        <div class="center-h" :style="{width: !isMobile?'500px':'300px'}">
            <v-img src="../../../assets/images/S.jpg"/>
        </div>
        <div class="mt-8 mb-8">
            <h3 class="mb-2">Zillow Home Loans is here to help you get your next home</h3>
            <p>Start with us to see what you can afford, with no impact to your credit score.</p>
        </div>
        <v-btn depressed color="primary">Get started</v-btn>
    </v-container>
</div>
</template>

<script setup>

</script>

<style scoped>
.loan {
    background-color: #f3f8fe;

    .loan-container {
        margin: 0 auto;
        padding: 60px 5vw 42px 5vw;

        h3 {
            font-size: 22px;
            line-height: 25px;
        }

        p {
            font-size: 15px;
        }

        button {
            font-weight: bold;
            display: block;
            padding: 10px;
            background-color: #156FF6;
            color: #FFFFFF;
            border: transparent;
            border-radius: 3px;
        }
    }
}
</style>