<template>
<div id="register-container">
    <div class="sign-up">Sign Up</div>
    <span class="back-icon" @click='back'>
        <i class="icon-chevron-left"></i>
    </span>
    <v-container>
        <span class="d-flex center-h">
            <div v-if="!this.isAvatarSet">
                <v-avatar size="148" class="d-flex justify-center mb-6" @click="changeAvatar">
                    <v-img :src="awsS3RequestUrl + this.user.avatar" aspect-ratio="2"></v-img>
                </v-avatar>
            </div>
            <div v-else>
                <v-avatar size="148" class="d-flex justify-center mb-6">
                    <v-img :src="this.awsS3RequestUrl + this.user.avatar" aspect-ratio="2"></v-img>
                </v-avatar>
            </div>
        </span>
        <v-file-input label="Select Your Avatar" truncate-length="50" @change="file => uploadFile(file)"></v-file-input>
        <v-text-field label="Username" placeholder="Username" v-model="user.username" outlined :rules="[v => !!v || 'Username is required']"></v-text-field>
        <v-text-field label="Password" placeholder="Password" v-model="user.password" outlined :rules="[v => !!v || 'Password is required']" type="password"></v-text-field>
        <v-btn style="background-color: #156FF6" color="primary" x-large dark width="100%" @click="register">Sign Up</v-btn>
        <v-btn class="mt-4" text width="100%" style="color: #156FF6" @click="loginRedirect">Sign In</v-btn>
    </v-container>

    <!-- Snackbars -->
    <v-snackbar v-model="uploadAvatarSnackbar" :timeout="2000">
        {{ uploadAvatarMsg }}
    </v-snackbar>
    <v-snackbar v-model="validationSnackbar" :timeout="2000" color="error">
        {{ validationMsg }}
    </v-snackbar>
</div>
</template>


<script>
import {mapActions, mapState} from 'vuex'
import NavBar from "@/components/NavBar/NavBar.vue";

export default {
    name: 'Register',

    components: {
        NavBar
    },
    data() {
        return {
            user: {
                id: this.$common.getRandomMongoId(),
                username: '',
                password: '',
                avatar: 'group1/M00/00/02/CgAEDGZvMWqAQoDMAACf56sfwHE812.png',
                role: 1
            },

            isAvatarSet: false,

            defaultAvatar: [
                'public/image/common/default-avatar/AGI2.webp',
                'public/image/common/default-avatar/apple-art-2a-3x4.webp',
                'public/image/common/default-avatar/Building-an-early-warning-system-for-LLM-aided-biological-threat-creation.webp',
                'public/image/common/default-avatar/Business.jpg',
                'public/image/common/default-avatar/CustomBlogCover.avif',
                'public/image/common/default-avatar/DALL_E.jpg',
                'public/image/common/default-avatar/introducing_the_gpt_store.webp',
                'public/image/common/default-avatar/Mac_App_Hero.jpg',
                'public/image/common/default-avatar/practices-for-governing-agentic-ai-systems.avif',
                'public/image/common/default-avatar/start-building-and-api-call.webp',
                'public/image/common/default-avatar/WechatIMG1886.jpg',
            ],

            uploadAvatarMsg: '',
            uploadAvatarSnackbar: false,

            validationMsg: '',
            validationSnackbar: false,
        }
    },

    computed: {
        ...mapState([
            "awsS3ImagePaths",
            "awsS3RequestUrl"
        ])
    },
    mounted() {
        this.setRandomAvatar();
    },
    methods: {
        back() {
            this.$router.push('/');
        },
        ...mapActions(['setUserIdAction']),
        setRandomAvatar() {
            const randomIndex = Math.floor(Math.random() * this.defaultAvatar.length);
            this.user.avatar = this.defaultAvatar[randomIndex];
        },
        changeAvatar() {
            this.setRandomAvatar();
        },
        register() {
            this.$api.user.register({
                id: this.user.id,
                username: this.user.username,
                password: this.user.password,
                avatar: this.user.avatar,
                role: this.user.role
            })
            .then(data => {
                if(data.data.status == 200) {
                    this.setUserIdAction({data: this.user.username})
                    window.history.back();
                } else {
                    alert(data.data.msg)
                }
            })
        },

        uploadFile(file) {
            var path = this.awsS3ImagePaths.user + this.user.id
            this.$api.file.uploadFile({file: file, path: path})
            .then((data) => {
                if(data.data.status === 200 && data.data.data) {
                    this.user.avatar = data.data.data;
                    this.uploadAvatarSnackbar = true;
                    this.uploadAvatarMsg = data.data.msg;
                    this.isAvatarSet = true;
                } else {
                    throw new Error('Failed to upload image or bad data received');
                }
            })
        },

        // Redirects
        loginRedirect() {
            this.$router.push("/login");
        }

    },
}
</script>


<style lang="less" scoped>
#register-container {
    width: 300px;
    margin: 80px auto 0 auto;

    .sign-up {
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