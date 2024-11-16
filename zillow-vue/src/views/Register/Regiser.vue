<template>
    <div id="login-container">
        <router-Link to="/login">
            <div class="sign_in">
                Sign In
            </div>
        </router-Link>

        <div class="sign_up">
            Sign Up
        </div>

        <span class="back-icon" @click='back'>
            <i class="icon-chevron-left"></i>
        </span>
        <v-text-field label="Username" placeholder="Enter Username" v-model="username" outlined class="mt-5"></v-text-field>
        <v-text-field label="Password" placeholder="Password" v-model="password" outlined type="password"></v-text-field>
        <v-btn  style="background-color: #156FF6" color="primary" x-large dark width="100%" @click="register">
            Sign In
        </v-btn>
        <NavBar/>
    </div>
</template>


<script>
import {mapActions} from 'vuex'
import NavBar from "@/components/NavBar/NavBar.vue";

export default {
    name: 'Register',
    components: {NavBar},
    data() {
        return {
            username: '',
            password: '',
        }
    },
    methods: {
        back() {
            this.$router.push('/');
        },
        ...mapActions(['setUserIdAction']),
        /*sendYzm() {
            if (this.timer) {
                clearInterval(this.timer)
            }
            this.$api.sendyzm({phone: this.msg})
                .then(data => {
                    console.log(data)
                })
            this.dtimer()
        },*/

        register() {
            this.$api.register({
                username: this.username,
                password: this.password
            })
            .then(data => {
                if (data.data.status == 200) {
                    this.setUserIdAction({data: this.username})
                    window.history.back();
                } else {
                    alert(data.data.msg)
                }
            })
        },
    },
}
</script>


<style lang="less" scoped>
#login-container {
    width: 300px;
    margin: 160px auto 0 auto;

    * {
        font-family: Arial;
    }

    .sign_in {
        position: absolute;
        top: 5px;
        right: 5px;
        float: right;
        color: #156FF6;
    }

    .sign_up {
        position: relative;
        width: 134px;
        height: 40px;
        margin: 0 auto;
        margin-bottom: 10px;
        font-size: 35px;
        font-weight: bold;
        display: block;
        color: #156FF6;
    }

    .input-container {
        background-color: #fff;
        border: 1px solid #f1f1f1;
        padding: 5px 10px;
        border-radius: 5px;
        overflow: hidden;

        // kill svg gap!!
        display: flex;
        align-items: center;
        gap: 0; /* Adjust this as needed */

        input {
            font-size: 16px;
            display: block;
            border: none;
        }

        i {
            color: #156FF6;
            width: 16px;
            float: left;
            margin-top: 3px;
            font-size: 17px;
        }
    }

    .phone-container {
        input {
            margin-left: 20px;
        }
    }

    .password-container {
        margin-top: 10px;

        input {
            margin-left: 20px;
            margin-right: 80px;
        }

        button {
            width: 80px;
            height: 100%;
            font-size: 16px;
            color: #156FF6;
            border: 0;
            background-color: #fff;
            text-align: center;
            float: right;
        }
    }

    .btn-login {
        width: 100%;
        height: 40px;
        text-align: center;
        background-color: #156FF6;
        color: #fff;
        border: none;
        border-radius: 5px;
        font-size: 16px;
        margin-top: 10px;
        padding: 5px 0;
    }

    .back-icon {
        width: 16px;
        height: 16px;
        position: absolute;
        left: 10px;
        top: 19px;
        color: #156FF6;
    }
}
</style>