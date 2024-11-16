<template>
    <div id="login-container">
        <router-Link to="/register">
            <div class="sign_up">
                Sign Up
            </div>
        </router-Link>

        <div class="sign_in">
            Sign In
        </div>

        <span class="back-icon" @click='back'>
            <i class="icon-chevron-left"></i>
        </span>
        <v-text-field label="Username" placeholder="Enter Username" v-model="username" outlined class="mt-5"></v-text-field>
        <v-text-field label="Password" placeholder="Password" v-model="password" outlined type="password"></v-text-field>
        <v-btn :disabled='!disableclick' :style="{background:!disableclick?'#156FF6':'#156FF6'}" color="primary" x-large dark width="100%" @click="login">
            Sign In
        </v-btn>
        <NavBar/>
    </div>
</template>


<script>
import {mapActions} from 'vuex'

export default {
    name: 'Login',
    data() {
        return {
            username: '',
            password: '',
            disableclick: true,
            time: 60,
            timer: null
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
        dtimer() {
            let that = this;
            that.timer = setInterval(function () {
                if (that.time > 0) {
                    that.disableclick = true;
                    that.time = that.time - 1;
                } else {
                    that.disableclick = false;
                    that.timer = null;
                }
            }, 1000)
        },
        login() {
            this.$api.login({
                username: this.username,
                password: this.password
            })
            .then(data => {
                if (data.data.status == 200) {
                    this.getUserByUsername();
                    this.$router.push("/shopcar");
                } else {
                    alert(data.data.msg)
                }
            })
        },
        getUserByUsername() {
            this.$api.getUserByUsername({
                username: this.username,
            })
            .then(data => {
                this.setUserIdAction({data: data.data.data.id})
                window.history.back();
            })
        }
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

    .sign_up {
        position: absolute;
        top: 5px;
        right: 5px;
        float: right;
        color: #156FF6;
    }

    .sign_in {
        position: relative;
        width: 118px;
        height: 40px;
        margin: 0 auto;
        margin-bottom: 10px;
        font-size: 35px;
        font-weight: bold;
        display: block;
        color: #156FF6;
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