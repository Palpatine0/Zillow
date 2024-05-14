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
        <div class="input-container phone-container">
            <svg height="16" viewBox="0 0 448 512" width="14" xmlns="http://www.w3.org/2000/svg">
                <path d="M224 256A128 128 0 1 0 224 0a128 128 0 1 0 0 256zm-45.7 48C79.8 304 0 383.8 0 482.3C0 498.7 13.3 512 29.7 512H418.3c16.4 0 29.7-13.3 29.7-29.7C448 383.8 368.2 304 269.7 304H178.3z"
                      fill="#1470f5"/>
            </svg>
            <input v-model="username" placeholder="Username"/>
        </div>
        <div class="input-container password-container">
            <div>
                <svg height="16" style="height:16px !important;width: 16px !important;" viewBox="0 0 512 512" width="14"
                     xmlns="http://www.w3.org/2000/svg">
                    <path d="M336 352c97.2 0 176-78.8 176-176S433.2 0 336 0S160 78.8 160 176c0 18.7 2.9 36.8 8.3 53.7L7 391c-4.5 4.5-7 10.6-7 17v80c0 13.3 10.7 24 24 24h80c13.3 0 24-10.7 24-24V448h40c13.3 0 24-10.7 24-24V384h40c6.4 0 12.5-2.5 17-7l33.3-33.3c16.9 5.4 35 8.3 53.7 8.3zM376 96a40 40 0 1 1 0 80 40 40 0 1 1 0-80z"
                          fill="#1470f5"/>
                </svg>
            </div>
            <input v-model="password" placeholder="Password" type="text"/>
        </div>
        <button :disabled='!disableclick' :style="{background:!disableclick?'#156FF6':'#156FF6'}" class="btn-login" @click="login">Sign In
        </button>
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
                console.log("data")
                console.log(data)
                if (data.data.status == 200) {
                    this.getUserByUsername();
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
                console.log("hit hit hit ")
                console.log(data.data.data.id)
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