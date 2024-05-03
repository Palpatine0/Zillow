<template>
    <div>
        <v-container>
            <v-row justify="center">
                <v-dialog v-model="bannerUpdate_dialog" max-width="600px" persistent>
                    <v-card>
                        <v-card-title>
                            <span class="text-h5">Item Banners</span>
                        </v-card-title>
                        <v-card-text>
                            <v-container>
                                <v-row>
                                    <v-col cols="12">
                                        <v-img :src="img_prefix+itemInfo.imgs[0]" aspect-ratio="2"></v-img>
                                    </v-col>
                                    <v-file-input show-size truncate-length="50" @change="uploadImageNoPrefix"></v-file-input>
                                    <v-col cols="12">
                                        <v-img :src="img_prefix+itemInfo.imgs[1]" aspect-ratio="2"></v-img>
                                    </v-col>
                                    <v-file-input show-size truncate-length="50"></v-file-input>
                                    <v-col cols="12">
                                        <v-img :src="img_prefix+itemInfo.imgs[2]" aspect-ratio="2"></v-img>
                                    </v-col>
                                    <v-file-input show-size truncate-length="50"></v-file-input>
                                </v-row>
                            </v-container>
                        </v-card-text>
                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn color="blue darken-1" text @click="bannerUpdate_dialog = false">
                                Close
                            </v-btn>
                            <v-btn color="blue darken-1" text @click="updateItemBannerById">
                                Submit
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                </v-dialog>
            </v-row>
        </v-container>

        <v-snackbar v-model="updateItemBannerById_snackbar" :timeout="2000">
            {{ updateItemBannerById_msg }}
        </v-snackbar>
    </div>
</template>

<script>
import {mapState} from "vuex";

export default {
    data() {
        return {
            // page vars
            updateItemBannerById_msg: false,
            updateItemBannerById_snackbar: false,
        }
    },
    methods: {
        updateItemBannerById() {
            this.$api.updateItemBannerById({
                id: this.itemInfo.id,
                recommendation: this.itemInfo.recommendation,
                isRented: this.itemInfo.isRented,
            })
            .then((data) => {
                if (data.data.status == 200) {
                    this.bannerUpdate_dialog = false;
                    this.updateItemBannerById_msg = data.data.data;
                    console.log(this.updateItemBannerById_msg)
                    this.updateItemBannerById_snackbar = true
                }
            })
        },
        uploadImageNoPrefix(file) {
            console.log("entered ib.vue");
            this.$api.uploadTest(file)
            .then((data) => {
                console.log(data);
            })
            .catch((error) => {
                console.error("Error in uploadImageNoPrefix:", error);
            });
        },


    },
    props: ['bannerUpdate_dialog', 'itemInfo'],

    computed: {
        ...mapState(['img_prefix']),
    }
}
</script>


<style scoped>

</style>
