<template>
    <div>
        <v-container>
            <v-row justify="center">
                <v-dialog v-model="updateInfo_dialog" max-width="600px" persistent>
                    <v-card>
                        <v-card-title>
                            <span class="text-h5">Item Profile</span>
                        </v-card-title>
                        <v-card-text>
                            <v-container>
                                <v-row>
                                    <v-col cols="12">
                                        <v-text-field v-model="computedTitle" label="Title" required></v-text-field>
                                    </v-col>

                                    <v-col cols="12" md="6" sm="6">
                                        <v-text-field v-model="computedPrice" label="Price / mo" required type="number"></v-text-field>
                                    </v-col>
                                    <v-col cols="12" md="6" sm="6">
                                        <v-select v-model="computedRentType" :items="['Whole Rental', 'Room Rental', 'Shared Rental']" label="Rent Type" required></v-select>
                                    </v-col>

                                    <v-col cols="12" md="4" sm="6">
                                        <v-select v-model="computedOrientation" :items="['North', 'South', 'West','East']" label="Orientation" required></v-select>
                                    </v-col>
                                    <v-col cols="12" md="4" sm="6">
                                        <v-text-field v-model="computedLevel" label="Floor" required type="number"></v-text-field>
                                    </v-col>
                                    <v-col cols="12" md="4" sm="6">
                                        <v-select v-model="computedStyle" :items="['Fully Furnished', 'Partially Furnished', 'Bare']" label="Condition" required></v-select>
                                    </v-col>

                                    <v-col cols="12" md="6" sm="6">
                                        <v-select v-model="computedType" :items="['Single Family Residence','Condominium']" label="Type" required></v-select>
                                    </v-col>
                                    <v-col cols="12" md="6" sm="6">
                                        <v-text-field v-model="computedYears" label="Build Year" required type="number"></v-text-field>
                                    </v-col>

                                    <v-col cols="12" md="4" sm="6">
                                        <v-text-field v-model="computedBeds" label="Beds" required type="number"></v-text-field>
                                    </v-col>
                                    <v-col cols="12" md="4" sm="6">
                                        <v-text-field v-model="computedBaths" label="Baths" required type="number"></v-text-field>
                                    </v-col>
                                    <v-col cols="12" md="4" sm="6">
                                        <v-text-field v-model="computedArea" label="Area" required type="number"></v-text-field>
                                    </v-col>

                                    <v-file-input label="Upload Images" outlined dense @change="uploadImage($event)" chips md="4"></v-file-input>
                                    <v-file-input label="Upload Images" outlined dense @change="uploadImage($event)" chips md="4"></v-file-input>
                                    <v-file-input label="Upload Images" outlined dense @change="uploadImage($event)" chips md="4"></v-file-input>

                                </v-row>
                            </v-container>
                        </v-card-text>
                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn color="blue darken-1" text @click="updateInfo_dialog = false">
                                Close
                            </v-btn>
                            <v-btn color="blue darken-1" text @click="updateItemInfoById">
                                Submit
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                </v-dialog>
            </v-row>
        </v-container>

        <v-snackbar v-model="updateItemInfoById_snackbar" :timeout="2000">
            {{ updateItemInfoById_msg }}
        </v-snackbar>
    </div>
</template>

<script>
export default {
    data() {
        return {
            // page vars
            updateItemInfoById_msg: false,
            updateItemInfoById_snackbar: false,
        }
    },
    methods: {
        updateItemInfoById() {
            this.$api.updateItemInfoById({
                id: this.itemInfo.id,
                recommendation: this.itemInfo.recommendation,
                isRented: this.itemInfo.isRented,
                // item
                title: this.itemInfo.title,
                sales: this.itemInfo.sales,
                weight: this.itemInfo.weight,
                price: this.itemInfo.price,
                city: this.$store.state.city,
                rentType: this.itemInfo.rentType,
                houseType: this.itemInfo.houseType,
                buytime: this.itemInfo.buytime,
                link: this.itemInfo.link,
                img: this.itemInfo.img,
                houseType4Search: this.itemInfo.houseType4Search,
                // item-info
                area: this.itemInfo.info.area,
                orientation: this.itemInfo.info.orientation,
                baths: this.itemInfo.info.baths,
                level: this.itemInfo.info.level,
                style: this.itemInfo.info.style,
                type: this.itemInfo.info.type,
                beds: this.itemInfo.info.beds,
                years: this.itemInfo.info.years,
                // item-img
                img1: this.itemInfo.imgs[0],
                img2: this.itemInfo.imgs[1],
                img3: this.itemInfo.imgs[2],
            })
            .then((data) => {
                if (data.data.status == 200) {
                    this.updateInfo_dialog = false;
                    this.addItem_msg = data.data.data;
                    console.log(this.addItem_msg)
                    this.addItem_snackbar = true
                }
            })
        }
    },
    props: ['updateInfo_dialog', 'itemInfo'],

    computed: {
        computedTitle: {
            get() {
                return this.itemInfo.title;
            },
            set(newValue) {
                this.itemInfo.title = newValue;
            }
        },
        computedPrice: {
            get() {
                return this.itemInfo.price;
            },
            set(newValue) {
                this.itemInfo.price = newValue;
            }
        },
        computedRentType: {
            get() {
                return this.itemInfo.rentType;
            },
            set(newValue) {
                this.itemInfo.rentType = newValue;
            }
        },
        computedOrientation: {
            get() {
                return this.itemInfo.info.orientation;
            },
            set(newValue) {
                this.itemInfo.info.info.orientation = newValue;
            }
        },
        computedLevel: {
            get() {
                return this.itemInfo.info.level;
            },
            set(newValue) {
                this.itemInfo.info.level = newValue;
            }
        },
        computedStyle: {
            get() {
                return this.itemInfo.info.style;
            },
            set(newValue) {
                this.itemInfo.info.style = newValue;
            }
        },
        computedType: {
            get() {
                return this.itemInfo.info.type;
            },
            set(newValue) {
                this.itemInfo.info.type = newValue;
            }
        },
        computedYears: {
            get() {
                return this.itemInfo.info.years;
            },
            set(newValue) {
                this.itemInfo.info.years = newValue;
            }
        },
        computedBeds: {
            get() {
                return this.itemInfo.info.beds;
            },
            set(newValue) {
                this.itemInfo.info.beds = newValue;
            }
        },
        computedBaths: {
            get() {
                return this.itemInfo.info.baths;
            },
            set(newValue) {
                this.itemInfo.info.baths = newValue;
            }
        },
        computedArea: {
            get() {
                return this.itemInfo.info.area;
            },
            set(newValue) {
                this.itemInfo.info.area = newValue;
            }
        },
    }
}
</script>


