<template>
    <div>
        <v-container>
            <v-row justify="center">
                <v-dialog v-model="updateStatus_dialog" max-width="600px" persistent>
                    <v-card>
                        <v-card-title>
                            <span class="text-h5">Item Profile</span>
                        </v-card-title>
                        <v-card-text>
                            <v-container>
                                <v-row>
                                    <v-col cols="12">
                                        <v-select v-model="computedRecommendation" :items="['No', 'Yes']" label="Recommend" required></v-select>
                                    </v-col>
                                    <v-col cols="12">
                                        <v-select v-model="computedIsRented" :items="['No', 'Yes']" label="Recommend" required></v-select>
                                    </v-col>
                                </v-row>
                            </v-container>
                        </v-card-text>
                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn color="blue darken-1" text @click="updateStatus_dialog = false">
                                Close
                            </v-btn>
                            <v-btn color="blue darken-1" text @click="updateItemStatusById">
                                Submit
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                </v-dialog>
            </v-row>
        </v-container>

        <v-snackbar v-model="updateItemStatusById_snackbar" :timeout="2000">
            {{ updateItemStatusById_msg }}
        </v-snackbar>
    </div>
</template>

<script>
export default {
    data() {
        return {


            // page vars
            updateItemStatusById_msg: false,
            updateItemStatusById_snackbar: false,
        }
    },
    methods: {
        updateItemStatusById() {
            console.log(this.itemInfo.recommendation, this.itemInfo.isRented)
            this.$api.updateItemStatusById({
                id: this.itemInfo.id,
                recommendation: this.itemInfo.recommendation,
                isRented: this.itemInfo.isRented,
            })
            .then((data) => {
                if (data.data.status == 200) {
                    this.updateStatus_dialog = false;
                    this.updateItemStatusById_msg = data.data.data;
                    console.log(this.updateItemStatusById_msg)
                    this.updateItemStatusById_snackbar = true
                }
            })
        }
    },
    props: ['updateStatus_dialog', 'itemInfo'],

    computed: {
        computedRecommendation: {
            get() {
                return this.itemInfo.recommendation ? 'Yes' : 'No';
            },
            set(newValue) {
                this.itemInfo.recommendation = newValue === 'Yes';
            }
        },
        computedIsRented: {
            get() {
                return this.itemInfo.isRented ? 'Yes' : 'No';
            },
            set(newValue) {
                this.itemInfo.isRented = newValue === 'Yes';
            }
        }
    }
}
</script>


<style scoped>

</style>
