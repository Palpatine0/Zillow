<template>
<div id="login-container">
    <div class="sign-in">Sign In</div>
    <span class="back-icon" @click='back'>
        <i class="icon-chevron-left"></i>
    </span>
    <v-container>
        <v-text-field label="Username" placeholder="Username" v-model="user.username" outlined :rules="[v => !!v || 'Username is required']"></v-text-field>
        <v-text-field label="Password" placeholder="Password" v-model="user.password" outlined :rules="[v => !!v || 'Password is required']" type="password"></v-text-field>
        <v-btn style="background-color: #156FF6" color="primary" x-large dark width="100%" @click="login">Sign In</v-btn>
        <v-btn class="mt-4" text width="100%" style="color: #156FF6" @click="registerRedirect">Sign Up</v-btn>
    </v-container>

    <!-- Snackbars -->
    <v-snackbar v-model="validationSnackbar" :timeout="2000" color="error">
        {{ validationMsg }}
    </v-snackbar>
</div>
</template>


<script>
import {mapActions, mapState} from 'vuex'
import NavBar from "@/components/NavBar/NavBar.vue";

export default {
    name: 'Login',
    components: {NavBar},
    computed: {
        ...mapState([
            "awsS3ImagePaths",
            "awsS3RequestUrl"
        ])
    },
    data() {
        return {
            user: {
                username: '',
                password: '',
            },

            validationMsg: '',
            validationSnackbar: false,
        }
    },
    methods: {
        ...mapActions(['setUserAction']),
        back() {
            this.$router.push('/');
        },
        login() {
            this.$api.user.login({
                username: this.user.username,
                password: this.user.password
            })
            .then(async data => {
                if(data.data.status == 200) {
                    await this.getUserByUsername();
                    this.$router.push("/mine");
                } else {
                    this.validationSnackbar = true;
                    this.validationMsg = data.data.msg;
                }
            })
        },
        async getUserByUsername() {
            const getUserByUsername = () => {
                return new Promise((resolve, reject) => {
                    this.$api.user.getUserByUsername({
                        username: this.user.username,
                    })
                    .then(data => {
                        const user = data.data.data
                        this.setUserAction(user)
                        window.history.back();
                    })
                })
            }
            await getUserByUsername()
        },

        // Redirects
        registerRedirect() {
            this.$router.push("/register");
        }
    },
}
</script>


<style lang="less" scoped>
#login-container {
    width: 300px;
    margin: 80px auto 0 auto;

    .sign-in {
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