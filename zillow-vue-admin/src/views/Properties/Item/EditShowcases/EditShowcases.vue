<template>
<div>
    <v-container>
        <v-row justify="center">
            <v-dialog v-model="showcasesUpdate_dialog" max-width="600px" persistent>
                <v-card>
                    <v-card-title>
                        <span class="text-h5">Item Showcases</span>
                    </v-card-title>
                    <v-card-text>
                        <v-container>
                            <v-row>
                                <v-col cols="12">
                                    <v-img :src="awsS3RequestUrl + imgs.img1" aspect-ratio="2"></v-img>
                                    <v-file-input show-size truncate-length="50" @change="file => uploadFile(file, 'img1')"></v-file-input>
                                </v-col>
                                <v-col cols="12">
                                    <v-img :src="awsS3RequestUrl + imgs.img2" aspect-ratio="2"></v-img>
                                    <v-file-input show-size truncate-length="50" @change="file => uploadFile(file, 'img2')"></v-file-input>
                                </v-col>
                                <v-col cols="12">
                                    <v-img :src="awsS3RequestUrl + imgs.img3" aspect-ratio="2"></v-img>
                                    <v-file-input show-size truncate-length="50" @change="file => uploadFile(file, 'img3')"></v-file-input>
                                </v-col>
                            </v-row>
                        </v-container>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="blue darken-1" text @click="showcasesUpdate_dialog = false">
                            Close
                        </v-btn>
                        <v-btn color="blue darken-1" text @click="updateItemShowcasesById">
                            Submit
                        </v-btn>
                    </v-card-actions>
                </v-card>
            </v-dialog>
        </v-row>
    </v-container>

    <v-snackbar v-model="updateItemShowcasesById_snackbar" :timeout="2000">
        {{ updateItemShowcasesById_msg }}
    </v-snackbar>

    <v-snackbar v-model="uploadItemShowcases_snackbar" :timeout="2000">
        {{ uploadItemShowcases_msg }}
    </v-snackbar>
</div>
</template>

<script>
import {mapState} from "vuex";

export default {
    name: "EditShowcases",
    props: [
        'showcasesUpdate_dialog',
        'itemInfo'
    ],
    computed: {
        ...mapState([
            'awsS3RequestUrl',
            'awsS3ImagePaths'
        ]),
    },
    data() {
        return {
            imgs: {
                img1: '',
                img2: '',
                img3: ''
            },

            // page vars
            updateItemShowcasesById_msg: '',
            updateItemShowcasesById_snackbar: false,

            uploadItemShowcases_msg: '',
            uploadItemShowcases_snackbar: false,
        }
    },
    created() {
        this.assignImgUrls();
        console.log(this.itemInfo)
    },

    methods: {
        updateItemShowcasesById() {
            console.log(this.imgs.img1)
            console.log(this.imgs.img2)
            console.log(this.imgs.img3)
            this.$api.item.updateItemShowcasesById({
                id: this.itemInfo.id,
                img1: this.imgs.img1,
                img2: this.imgs.img2,
                img3: this.imgs.img3
            })
            .then((data) => {
                if(data.data.status == 200) {
                    console.log(this.updateItemShowcasesById_msg)
                    this.updateItemShowcasesById_msg = data.data.msg;
                    this.updateItemShowcasesById_snackbar = true
                    this.showcasesUpdate_dialog = false;
                }
            })
        },
        uploadFile(file, imgKey) {
            var path = this.awsS3ImagePaths.item + this.itemInfo.id
            this.$api.file.uploadFile({file: file, path: path})
            .then((data) => {
                if(data.data.status === 200 && data.data.data) {
                    this.imgs[imgKey] = data.data.data;
                    this.uploadItemShowcases_msg = "Image uploaded successfully!";
                    this.uploadItemShowcases_snackbar = true;
                } else {
                    this.uploadItemShowcases_msg = "Sever error, try again.";
                    this.uploadItemShowcases_snackbar = true;
                }
            })
        },
        assignImgUrls() {
            if(this.itemInfo) {
                this.imgs.img1 = this.itemInfo.imgs[0] || '';
                this.imgs.img2 = this.itemInfo.imgs[1] || '';
                this.imgs.img3 = this.itemInfo.imgs[2] || '';
            }
        },
    },
}
</script>


<style scoped>

</style>
