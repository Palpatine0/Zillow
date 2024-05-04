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
                                      <v-img :src="this.img_prefix + imgUrls.img1" aspect-ratio="2"></v-img>
                                      <v-file-input show-size truncate-length="50"
                                                    @change="file => uploadImageNoPrefix(file, 'img1')"></v-file-input>
                                    </v-col>
                                    <v-col cols="12">
                                      <v-img :src="this.img_prefix + imgUrls.img2" aspect-ratio="2"></v-img>
                                      <v-file-input show-size truncate-length="50"
                                                    @change="file => uploadImageNoPrefix(file, 'img2')"></v-file-input>
                                    </v-col>
                                    <v-col cols="12">
                                      <v-img :src="this.img_prefix + imgUrls.img3" aspect-ratio="2"></v-img>
                                      <v-file-input show-size truncate-length="50"
                                                    @change="file => uploadImageNoPrefix(file, 'img3')"></v-file-input>
                                    </v-col>
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

      <v-snackbar v-model="uploadItemBanner_snackbar" :timeout="2000">
        {{ uploadItemBanner_msg }}
      </v-snackbar>
    </div>
</template>

<script>
import {mapState} from "vuex";

export default {
    data() {
        return {
          imgUrls: {
            img1: '',
            img2: '',
            img3: ''
          },

            // page vars
          updateItemBannerById_msg: '',
            updateItemBannerById_snackbar: false,

          uploadItemBanner_msg: '',
          uploadItemBanner_snackbar: false,
        }
    },
    methods: {
        updateItemBannerById() {
          console.log(this.imgUrls.img1)
          console.log(this.imgUrls.img2)
          console.log(this.imgUrls.img3)
            this.$api.updateItemBannerById({
                id: this.itemInfo.id,
              img1: this.imgUrls.img1,
              img2: this.imgUrls.img2,
              img3: this.imgUrls.img3
            })
            .then((data) => {
                if (data.data.status == 200) {
                    console.log(this.updateItemBannerById_msg)
                  this.updateItemBannerById_msg = data.data.msg;
                    this.updateItemBannerById_snackbar = true
                  this.bannerUpdate_dialog = false;
                }
            })
        },
      uploadImageNoPrefix(file, imgKey) {
        this.$api.uploadImageNoPrefix({file: file})
            .then((data) => {
              if (data.data.status === 200 && data.data.data) {
                this.imgUrls[imgKey] = data.data.data;
                this.uploadItemBanner_msg = "Image uploaded successfully!";
                this.uploadItemBanner_snackbar = true;
              } else {
                throw new Error('Failed to upload image or bad data received');
              }
            })
            .catch((error) => {
              this.uploadItemBanner_msg = "Failed to upload image, try again.";
              this.uploadItemBanner_snackbar = true;
            });
        },
      assignImgUrls() {
        if (this.itemInfo) {
          this.imgUrls.img1 = this.itemInfo.imgs[0] || '';
          this.imgUrls.img2 = this.itemInfo.imgs[1] || '';
          this.imgUrls.img3 = this.itemInfo.imgs[2] || '';
        }
        console.log("this.imgUrls.img3")
        console.log(this.imgUrls.img3)
      },
    },
    props: ['bannerUpdate_dialog', 'itemInfo'],

    computed: {
        ...mapState(['img_prefix']),
    },
  created() {
    this.assignImgUrls();
    console.log(this.itemInfo)
  },
}
</script>


<style scoped>

</style>
