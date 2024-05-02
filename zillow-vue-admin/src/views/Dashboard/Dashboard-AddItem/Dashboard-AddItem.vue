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
                                        <v-text-field v-model="title" label="Title" required></v-text-field>
                                    </v-col>

                                    <v-col cols="12">
                                        <v-text-field v-model="price" label="Price / mo" required type="number"></v-text-field>
                                    </v-col>


                                    <v-col cols="12" md="6" sm="6">
                                        <v-select v-model="rentType" :items="['Whole Rental', 'Room Rental', 'Shared Rental']" label="Rent Type" required></v-select>
                                    </v-col>
                                    <v-col cols="12" md="6" sm="6">
                                        <v-select v-model="recommendation" :items="['No', 'Yes']" label="Recommend" required></v-select>
                                    </v-col>

                                    <v-col cols="12" md="4" sm="6">
                                        <v-select v-model="orientation" :items="['North', 'South', 'West','East']" label="Orientation" required></v-select>
                                    </v-col>
                                    <v-col cols="12" md="4" sm="6">
                                        <v-text-field v-model="level" label="Floor" required type="number"></v-text-field>
                                    </v-col>
                                    <v-col cols="12" md="4" sm="6">
                                        <v-select v-model="style" :items="['Fully Furnished', 'Partially Furnished', 'Bare']" label="Condition" required></v-select>
                                    </v-col>

                                    <v-col cols="12" md="6" sm="6">
                                        <v-select v-model="type" :items="['Single Family Residence','Condominium']" label="Type" required></v-select>
                                    </v-col>
                                    <v-col cols="12" md="6" sm="6">
                                        <v-text-field v-model="years" label="Build Year" required type="number"></v-text-field>
                                    </v-col>

                                    <v-col cols="12" md="4" sm="6">
                                        <v-text-field v-model="beds" label="Beds" required type="number"></v-text-field>
                                    </v-col>
                                    <v-col cols="12" md="4" sm="6">
                                        <v-text-field v-model="baths" label="Baths" required type="number"></v-text-field>
                                    </v-col>
                                    <v-col cols="12" md="4" sm="6">
                                        <v-text-field v-model="area" label="Area" required type="number"></v-text-field>
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
            // l1
            title: '',
            sales: 300,
            recommendation: false,
            weight: 0,
            price: '',
            city: this.$store.state.city,
            rentType: '',
            houseType: '',
            buytime: '',
            isRented: false,
            link: '',
            img: '',
            houseType4Search: '',
            // info
            orientation: '',
            level: '',
            style: '',
            type: '',
            years: '',
            beds: '',
            baths: '',
            area: '',

            // page vars
            addItem_dialog: false,
            addItem_msg: false,
            addItem_snackbar: false,
        }
    },
    methods: {
        addItem() {
            this.$api.addItem({
                title: this.title,
                sales: this.sales,
                recommendation: this.recommendation,
                weight: this.weight,
                price: this.price,
                city: this.$store.state.city,
                rentType: this.rentType,
                houseType: this.houseType,
                orientation: this.orientation,
                level: this.level,
                style: this.style,
                type: this.type,
                years: this.years,
                imgs: this.imgs,
                buytime: this.buytime,
                isRented: this.isRented,
                link: this.link,
                img: this.img,
                houseType4Search: this.houseType4Search,
                beds: this.beds,
                baths: this.baths,
                area: this.area,
            })
            .then((data) => {
                if (data.data.status == 200) {
                    this.addItem_dialog = false;
                    this.addItem_msg = data.data.data;
                    console.log(this.addItem_msg)
                    this.addItem_snackbar = true
                }
            })
        }
    },
    watch: {
        // Watch for changes in recommendation
        'recommendation'(newVal) {
            // Update the value based on selection
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
