<template>
    <div class="dashboard">
        <h1 class="subheading grey--text">Dashboard</h1>

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
                            <v-chip small :class="`${project.status} white--text my-2 caption`">{{project.status }}
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
            curcity: 'Dallas',
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
          recommendation: []

        }
    },
    methods: {
        sortBy(prop) {
            this.projects.sort((a, b) => a[prop] < b[prop] ? -1 : 1)
        }
    },
    mounted() {
        this.$api.hotProduct({
            city: this.curcity
        })
        .then(data => {
            if (data.data.status) {
                this.trendy = data.data.data
            }
        })

        this.$api.recommendation({
            city: this.curcity
        })
        .then(data => {
            if (data.data.status) {
                this.recommendation = data.data.data
            }
        })
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

<style>

.project.complete {
    border-left: 4px solid #3cd1c2;
}

.project.ongoing {
    border-left: 4px solid #ffaa2c;
}

.project.overdue {
    border-left: 4px solid #f83e70;
}

.v-chip.complete {
    background: #3cd1c2;
}

.v-chip.ongoing {
    background: #ffaa2c;
}

.v-chip.overdue {
    background: #f83e70;
}

</style>
