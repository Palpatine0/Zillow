<template>
<div>
    <v-container>
        <v-row justify="center">
            <v-dialog v-model="addItem_dialog" max-width="600px" persistent>
                <template v-slot:activator="{ on, attrs }">
                    <v-btn color="rgb(224, 242, 255)" dark style="color: rgb(13, 69, 153);" v-bind="attrs" v-on="on">
                        Add a Item for this district
                    </v-btn>
                </template>
                <v-card>
                    <v-card-title>
                        <span class="text-h5">Item Profile</span>
                    </v-card-title>
                    <v-card-text>
                        <v-container>
                            <v-row>
                                <v-col cols="12">
                                    <v-text-field v-model="item.title" label="Title" required></v-text-field>
                                </v-col>

                                <v-col cols="12">
                                    <v-text-field v-model="item.price" label="Price / mo" required type="number"></v-text-field>
                                </v-col>


                                <v-col cols="12" md="6" sm="6">
                                    <v-select v-model="item.rentType" :items="['Whole Rental', 'Room Rental', 'Shared Rental']" label="Rent Type" required></v-select>
                                </v-col>
                                <v-col cols="12" md="6" sm="6">
                                    <v-select v-model="item.recommendation" :items="['No', 'Yes']" label="Recommend" required></v-select>
                                </v-col>

                                <v-col cols="12" md="4" sm="6">
                                    <v-select v-model="item.info.orientation" :items="['North', 'South', 'West','East']" label="Orientation" required></v-select>
                                </v-col>
                                <v-col cols="12" md="4" sm="6">
                                    <v-text-field v-model="item.info.level" label="Level" required type="number"></v-text-field>
                                </v-col>
                                <v-col cols="12" md="4" sm="6">
                                    <v-select v-model="item.info.style" :items="['Fully Furnished', 'Partially Furnished', 'Bare']" label="Condition" required></v-select>
                                </v-col>

                                <v-col cols="12" md="6" sm="6">
                                    <v-select v-model="item.info.type" :items="['Single Family Residence','Condominium']" label="Type" required></v-select>
                                </v-col>
                                <v-col cols="12" md="6" sm="6">
                                    <v-text-field v-model="item.info.years" label="Build Year" required type="number"></v-text-field>
                                </v-col>

                                <v-col cols="12" md="4" sm="6">
                                    <v-text-field v-model="item.info.beds" label="Beds" required type="number"></v-text-field>
                                </v-col>
                                <v-col cols="12" md="4" sm="6">
                                    <v-text-field v-model="item.info.baths" label="Baths" required type="number"></v-text-field>
                                </v-col>
                                <v-col cols="12" md="4" sm="6">
                                    <v-text-field v-model="item.info.area" label="Area" required type="number"></v-text-field>
                                </v-col>

                                <v-col cols="12">
                                    <div v-if="this.item.imgs.img1!=''">
                                        <v-img :src="this.awsS3RequestUrl + this.item.imgs.img1" aspect-ratio="2"></v-img>
                                    </div>
                                    <v-file-input show-size truncate-length="50" @change="file => uploadFile(file, 'img1')"></v-file-input>
                                </v-col>
                                <v-col cols="12">
                                    <div v-if="this.item.imgs.img2!=''">
                                        <v-img :src="this.awsS3RequestUrl + this.item.imgs.img2" aspect-ratio="2"></v-img>
                                    </div>
                                    <v-file-input show-size truncate-length="50" @change="file => uploadFile(file, 'img2')"></v-file-input>
                                </v-col>
                                <v-col cols="12">
                                    <div v-if="this.item.imgs.img3!=''">
                                        <v-img :src="this.awsS3RequestUrl + this.item.imgs.img3" aspect-ratio="2"></v-img>
                                    </div>
                                    <v-file-input show-size truncate-length="50" @change="file => uploadFile(file, 'img3')"></v-file-input>
                                </v-col>
                            </v-row>
                        </v-container>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="blue darken-1" text @click="addItem_dialog = false">
                            Close
                        </v-btn>
                        <v-btn color="blue darken-1" text @click="addItem">
                            Submit
                        </v-btn>
                    </v-card-actions>
                </v-card>
            </v-dialog>
        </v-row>
    </v-container>

    <v-snackbar v-model="addItem_snackbar" :timeout="2000">
        {{ addItem_msg }}
    </v-snackbar>

    <v-snackbar v-model="uploadItemShowcases_add_snackbar" :timeout="2000">
        {{ uploadItemShowcases_add_msg }}
    </v-snackbar>
</div>
</template>

<script>
import {mapState} from "vuex";

export default {
    data() {
        return {

            // item
            item: {
                id: this.$common.getRandomMongoId(),
                title: '',
                sales: 300,
                recommendation: false,
                weight: 0,
                price: '',
                city: this.$store.state.city,
                rentType: '',
                houseType: '',
                info: {
                    area: '',
                    orientation: '',
                    baths: '',
                    level: '',
                    style: '',
                    type: '',
                    beds: '',
                    years: '',
                },
                buytime: '',
                isRented: false,
                link: '',
                imgs: {
                    img1: '',
                    img2: '',
                    img3: ''
                },
                houseType4Search: '',
            },

            // page vars
            uploadItemShowcases_add_msg: '',
            uploadItemShowcases_add_snackbar: false,

            addItem_dialog: false,
            addItem_msg: false,
            addItem_snackbar: false,
        }
    },
    methods: {
        addItem() {
            this.$api.addItem({
                id: this.item.id,
                title: this.item.title,
                sales: this.item.sales,
                recommendation: this.item.recommendation,
                weight: this.item.weight,
                price: this.item.price,
                city: this.$store.state.city,
                rentType: this.item.rentType,
                houseType: this.item.houseType,

                area: this.item.info.area,
                orientation: this.item.info.orientation,
                baths: this.item.info.baths,
                level: this.item.info.level,
                style: this.item.info.style,
                type: this.item.info.type,
                beds: this.item.info.beds,
                years: this.item.info.years,

                buytime: this.item.buytime,
                isRented: this.item.isRented,
                link: this.item.link,
                img1: this.item.imgs.img1,
                img2: this.item.imgs.img2,
                img3: this.item.imgs.img3,
                houseType4Search: this.item.houseType4Search,
            })
            .then((data) => {
                console.log(data)
                if(data.data.status == 200) {
                    this.addItem_dialog = false;
                    this.addItem_msg = data.data.data;
                    this.addItem_snackbar = true
                }
            })
        },
        uploadFile(file, imgKey) {
            var path = this.awsS3ImagePaths.item + this.item.id
            this.$api.uploadFile({file: file, path: path})
            .then((data) => {
                if(data.data.status === 200 && data.data.data) {
                    this.item.imgs[imgKey] = data.data.data;
                    this.uploadItemShowcases_add_snackbar = true;
                    this.uploadItemShowcases_add_msg = data.data.msg;
                } else {
                    throw new Error('Failed to upload image or bad data received');
                }
            })
        },
    },
    computed: {
        ...mapState(['awsS3RequestUrl']),
        ...mapState(['awsS3ImagePaths']),
    },
    watch: {
        'recommendation'(newVal) {
            if(newVal === 'Yes') {
                this.recommendation = true;
            } else if(newVal === 'No') {
                this.recommendation = false;
            }
        }
    },
}
</script>


<style scoped>

</style>
