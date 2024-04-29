<template>
    <div class="dashboard">
        <h1 class="subheading grey--text">Dashboard</h1>

        <v-container class="my-5">
            <v-layout row justify-start class="mb-3">

                <v-card class="mx-auto" max-width="344">
                    <v-img height="200px" src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg" cover></v-img>

                    <v-card-title>
                        Top western road trips
                    </v-card-title>

                    <v-card-subtitle>
                        1,000 miles of wonder
                    </v-card-subtitle>

                    <v-card-actions>
                        <v-btn color="orange-lighten-2" text="Explore"></v-btn>

                        <v-spacer></v-spacer>

                        <v-btn :icon="show ? 'mdi-chevron-up' : 'mdi-chevron-down'" @click="show = !show"></v-btn>
                    </v-card-actions>

                    <v-expand-transition>
                        <div v-show="show">
                            <v-divider></v-divider>

                            <v-card-text>
                                I'm a thing. But, like most politicians, he promised more than he could deliver. You won't have time
                                for sleeping, soldier, not with all the bed making you'll be doing. Then we'll go with that data
                                file! Hey, you add a one and two zeros to that or we walk! You're going to do his laundry? I've got
                                to find a way to escape.
                            </v-card-text>
                        </div>
                    </v-expand-transition>
                </v-card>

                <v-card :disabled="loading" :loading="loading" class="mx-auto my-12" max-width="374">
                    <template v-slot:loader="{ isActive }">
                        <v-progress-linear :active="isActive" color="deep-purple" height="4" indeterminate></v-progress-linear>
                    </template>

                    <v-img height="250" src="https://cdn.vuetifyjs.com/images/cards/cooking.png" cover></v-img>

                    <v-card-item>
                        <v-card-title>Cafe Badilico</v-card-title>

                        <v-card-subtitle>
                            <span class="me-1">Local Favorite</span>
                            <v-icon color="error" icon="mdi-fire-circle" size="small"></v-icon>
                        </v-card-subtitle>
                    </v-card-item>

                    <v-card-text>
                        <v-row align="center" class="mx-0">
                            <v-rating :model-value="4.5" color="amber" density="compact" size="small" half-increments readonly></v-rating>

                            <div class="text-grey ms-4">
                                4.5 (413)
                            </div>
                        </v-row>

                        <div class="my-4 text-subtitle-1">
                            $ • Italian, Cafe
                        </div>

                        <div>Small plates, salads & sandwiches - an intimate setting with 12 indoor seats plus patio
                            seating.
                        </div>
                    </v-card-text>

                    <v-divider class="mx-4 mb-1"></v-divider>

                    <v-card-title>Tonight's availability</v-card-title>

                    <div class="px-4 mb-2">
                        <v-chip-group v-model="selection" selected-class="bg-deep-purple-lighten-2">
                            <v-chip>5:30PM</v-chip>

                            <v-chip>7:30PM</v-chip>

                            <v-chip>8:00PM</v-chip>

                            <v-chip>9:00PM</v-chip>
                        </v-chip-group>
                    </div>

                    <v-card-actions>
                        <v-btn color="deep-purple-lighten-2" text="Reserve" block border @click="reserve"></v-btn>
                    </v-card-actions>
                </v-card>
            </v-layout>


            <v-card flat v-for="project in projects" :key="project.title">
                <v-layout row wrap :class="`pa-3 project ${project.status}`">
                    <v-flex xs12 md6>
                        <div class="caption grey--text">Project title</div>
                        <div>{{ project.title }}</div>
                    </v-flex>
                    <v-flex xs6 sm4 md2>
                        <div class="caption grey--text">Person</div>
                        <div>{{ project.person }}</div>
                    </v-flex>
                    <v-flex xs6 sm4 md2>
                        <div class="caption grey--text">Due by</div>
                        <div>{{ project.due }}</div>
                    </v-flex>
                    <v-flex xs2 sm4 md2>
                        <div class="right">
                            <v-chip small :class="`${project.status} white--text my-2 caption`">{{ project.status }}
                            </v-chip>
                        </div>
                    </v-flex>
                </v-layout>
                <v-divider></v-divider>
            </v-card>

            <v-pagination :length="pagination" style="float: right" v-model="page" @input="search"></v-pagination>
        </v-container>

        <v-container class="my-5">
            <v-layout row justify-start class="mb-3">
                <v-tooltip top>
                    <v-btn small flat color="grey" @click="sortBy('title')" slot="activator">
                        <v-icon small left>folder</v-icon>
                        <span class="caption text-lowercase">By project name</span>
                    </v-btn>
                    <span>Sort by project name</span>
                </v-tooltip>
                <v-tooltip top>
                    <v-btn small flat color="grey" @click="sortBy('person')" slot="activator">
                        <v-icon small left>person</v-icon>
                        <span class="caption text-lowercase">By Person</span>
                    </v-btn>
                    <span>Sort by project author</span>
                </v-tooltip>
            </v-layout>

            <v-card flat v-for="project in projects" :key="project.title">
                <v-layout row wrap :class="`pa-3 project ${project.status}`">
                    <v-flex xs12 md6>
                        <div class="caption grey--text">Project title</div>
                        <div>{{ project.title }}</div>
                    </v-flex>
                    <v-flex xs6 sm4 md2>
                        <div class="caption grey--text">Person</div>
                        <div>{{ project.person }}</div>
                    </v-flex>
                    <v-flex xs6 sm4 md2>
                        <div class="caption grey--text">Due by</div>
                        <div>{{ project.due }}</div>
                    </v-flex>
                    <v-flex xs2 sm4 md2>
                        <div class="right">
                            <v-chip small :class="`${project.status} white--text my-2 caption`">{{ project.status }}
                            </v-chip>
                        </div>
                    </v-flex>
                </v-layout>
                <v-divider></v-divider>
            </v-card>

        </v-container>

    </div>
</template>

<script>
import db from '@/fb'

export default {
    data() {
        return {
            city: 'Dallas',
            pagination: 0,
            page: 1,
            totalCount_item: 0,
            searchListData: [],
            projects: [
                {
                    id: '1',
                    title: 'Project 1',
                    description: 'This is a description for Project 1',
                    person: 'John Doe',
                    due: '2023-12-31',
                    status: 'ongoing'
                },
                {
                    id: '2',
                    title: 'Project 2',
                    description: 'This is a description for Project 2',
                    person: 'Jane Doe',
                    due: '2023-11-30',
                    status: 'complete'
                },
                {
                    id: '3',
                    title: 'Project 3',
                    description: 'This is a description for Project 3',
                    person: 'Bob Smith',
                    due: '2023-10-31',
                    status: 'overdue'
                }
            ],

        }
    },
    methods: {
        sortBy(prop) {
            this.projects.sort((a, b) => a[prop] < b[prop] ? -1 : 1)
        },
        search() {
            this.$api.search({city: this.city, page: this.page - 1})
            .then((data) => {
                this.searchListData = []
                this.totalCount_item = data.data.data[0].totalCount
                this.pagination = Math.ceil(this.totalCount_item / 5); // Calculate the number of pages
                this.searchListData = this.searchListData.concat(data.data.data)
                console.log(this.searchListData)
                return data;
            })
        }

    },
    mounted() {
        this.search()
    },
    created() {
        db.collection('projects').onSnapshot(res => {
            const changes = res.docChanges();

            changes.forEach(change => {
                if (change.type === 'added') {
                    this.projects.push({
                        ...change.doc.data(),
                        id: change.doc.id
                    })
                }
            })
        })

    }
}
</script>


