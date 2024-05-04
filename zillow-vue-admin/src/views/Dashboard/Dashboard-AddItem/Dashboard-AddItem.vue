<template>
    <div>
        <v-container>
            <v-row justify="center">
                <v-dialog v-model="addItem_dialog" max-width="600px" persistent>
                    <template v-slot:activator="{ on, attrs }">
                        <v-btn color="rgb(224, 242, 255)" dark style="color: rgb(13, 69, 153);" v-bind="attrs"
                               v-on="on">
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
                                        <v-text-field v-model="item.info.level" label="Floor" required type="number"></v-text-field>
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

                                    <!--<v-file-input label="Upload Images" outlined dense @change="uploadImage($event)" chips md="4"></v-file-input>
                                    <v-file-input label="Upload Images" outlined dense @change="uploadImage($event)" chips md="4"></v-file-input>
                                    <v-file-input label="Upload Images" outlined dense @change="uploadImage($event)" chips md="4"></v-file-input>-->


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
    </div>
</template>

<script>
export default {
    data() {
        return {
            // item
            item: {
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
                img: '',
                houseType4Search: '',
            },


            // page vars
            addItem_dialog: false,
            addItem_msg: false,
            addItem_snackbar: false,
        }
    },
    methods: {
        addItem() {
            this.$api.addItem({
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

                imgs: this.item.imgs,
                buytime: this.item.buytime,
                isRented: this.item.isRented,
                link: this.item.link,
                img: this.item.img,
                houseType4Search: this.item.houseType4Search,
            })
            .then((data) => {
                if (data.data.status == 200) {
                    this.addItem_dialog = false;
                    this.addItem_msg = data.data.data;
                    this.addItem_snackbar = true
                }
            })
        }
    },
    watch: {
        'recommendation'(newVal) {
            if (newVal === 'Yes') {
                this.recommendation = true;
            } else if (newVal === 'No') {
                this.recommendation = false;
            }
        }
    },
}
</script>


<style scoped>

</style>
