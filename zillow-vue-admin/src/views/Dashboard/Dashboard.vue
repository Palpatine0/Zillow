<template>
    <v-app style="padding: 20px">

        <v-subheader class="subheading grey--text" as="h1">Dashboard</v-subheader>
        <v-container>
            <v-row style="justify-content: space-between">
                <v-col cols="2">
                    <v-select :items="cities" label="Standard" v-model="selectedCity"></v-select>

                </v-col>
                <div class="mt-5">
                    <v-pagination class="float-right" :length="pagination" v-model="page" @input="adminSearchByCity" color="#156ff6"></v-pagination>
                </div>
            </v-row>
            <v-row>
                <v-col cols="12" md="6" lg="4" v-for="(item, index) in searchListData" :key="index">
                    <v-card class="mx-auto" max-width="400" :href="item.link">
                        <v-img class="white--text align-end" height="200px" :src="getFirstImageUrl(item.imgs)">
                            <v-card-title>{{ getHeadAddr(item.title) }}</v-card-title>
                        </v-img>
                        <v-card-subtitle class="pb-0">
                            {{ getSubAddr(item.title) }}
                        </v-card-subtitle>
                        <v-card-text class="text--primary">
                            <b>
                                <div>{{ item.rentType }}</div>
                            </b>
                            <div>{{ item.aptType }}</div>
                        </v-card-text>
                        <v-card-actions>
                            <v-btn color="orange" text>
                                Share
                            </v-btn>
                            <v-btn color="orange" text>
                                Explore
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                </v-col>
            </v-row>

        </v-container>

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

                    <v-col cols="12" sm="6">
                      <v-text-field v-model="price" label="Price / mo" required type="number"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6">
                      <v-text-field v-model="houseType" label="Size / sqft" required></v-text-field>
                    </v-col>

                    <v-col cols="12" md="6" sm="6">
                      <v-select v-model="rentType" :items="['Whole Rental', 'Room Rental', 'Shared Rental']"
                                label="Rent Type" required></v-select>
                    </v-col>
                    <v-col cols="12" md="6" sm="6">
                      <v-select v-model="recommendation" :items="['No', 'Yes']" label="Recommend" required></v-select>
                    </v-col>

                    <v-col cols="12" md="4" sm="6">
                      <v-select v-model="orientation" :items="['North', 'South', 'West','East']" label="Orientation"
                                required></v-select>
                    </v-col>
                    <v-col cols="12" md="4" sm="6">
                      <v-text-field v-model="level" label="Floor" required type="number"></v-text-field>
                    </v-col>
                    <v-col cols="12" md="4" sm="6">
                      <v-select v-model="orientation" :items="['Fully Furnished', 'Partially Furnished', 'Bare']"
                                label="Condition" required></v-select>
                    </v-col>
                    <v-col cols="12" md="6" sm="6">
                      <v-text-field v-model="type" label="Type (X beds, X baths)" required></v-text-field>
                    </v-col>
                    <v-col cols="12" md="6" sm="6">
                      <v-text-field v-model="years" label="Build Year" required type="number"></v-text-field>
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


    </v-app>
</template>

<script>
import {mapState} from "vuex";

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


          //
          page: 1,
          addItem_dialog: false,
          addItem_msg: false,
          addItem_snackbar: false,
            pagination: 0,
            totalCount_item: 0,
            searchListData: [],
        }
    },
    methods: {
        adminSearchByCity() {
            this.$api.adminSearchByCity({city: this.selectedCity, page: this.page - 1})
            .then((data) => {
                this.searchListData = []
                this.totalCount_item = data.data.data[0].totalCount
                this.pagination = Math.ceil(this.totalCount_item / 6);
                this.searchListData = this.searchListData.concat(data.data.data)
                return data;
            })
        },
        getFirstImageUrl(imgs) {
            const matches = imgs.match(/\[(.*?)\]/);
            if (matches && matches.length > 1) {
                const firstUrlPart = matches[1].split(',')[0].trim();
                const imageUrl = `http://111.231.19.137:8888/${firstUrlPart}`;
                return imageUrl;
            } else {
                return "default-image-url"; // Fallback URL
            }
        },
        getSubAddr(fullAddress) {
            if (!fullAddress) {
                return "No address available";
            }
            const parts = fullAddress.split(',');
            return parts.length > 1 ? parts.slice(1).join(',').trim() : fullAddress;
        },
        getHeadAddr(fullAddress) {
            if (!fullAddress) {
                return "Unknown Location"; // Default title if none provided
            }
            const parts = fullAddress.split(',');
            return parts[0]; // Return the first part before the comma
        },
      uploadImage(file) {
        console.log("Files received:", file);
        if (file.length === 0) {
          console.log("No file selected.");
          return;
        }

        this.$api.uploadImageNoPrefix(file)
            .then(data => {
              if (data.data && data.data.path) {
                this.imgs.push(data.data.path);
                console.log('Image uploaded successfully:', data.data.path);
              } else {
                console.error('No path returned from the server');
              }
            })
            .catch(error => {
              console.error('Error uploading image:', error);
            });
      },
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
    mounted() {
        this.adminSearchByCity();
    },
    computed: {
        ...mapState(['cities']),
        ...mapState(['city']),
        selectedCity: {
            get() {
                return this.$store.state.city; // Getter to return the current city
            },
            set(value) {
                this.$store.commit('setCity', value); // Setter to update the city in the Vuex store
                this.adminSearchByCity(); // Refresh data when city changes
            }
        },
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
