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
                            <v-chip small :class="`${project.status} white--text my-2 caption`">{{ project.status }}
                            </v-chip>
                        </div>
                    </v-flex>
                </v-layout>
                <v-divider></v-divider>
            </v-card>

        </v-container>
        <v-container class="my-5">
            <v-expansion-panel>
                <v-expansion-panel-content v-for="project in myProjects" :key="project.title">
                    <div slot="header" class="py-1">{{ project.title }}</div>
                    <v-card>
                        <v-card-text class="px-4 grey--text">
                            <div class="font-weight-bold">Due by {{ project.due }}</div>
                            <div>{{ project.content }}</div>
                        </v-card-text>
                    </v-card>
                </v-expansion-panel-content>
            </v-expansion-panel>
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
            projects_b: [
                {
                    title: 'Project 1',
                    due: '2023-12-31',
                    content: 'This is the content for Project 1',
                    person: 'The Net Ninja',
                    status: 'in progress'
                },
                {
                    title: 'Project 2',
                    due: '2024-01-31',
                    content: 'This is the content for Project 2',
                    person: 'The Net Ninja',
                    status: 'not started'
                },
                {
                    title: 'Project 3',
                    due: '2024-02-28',
                    content: 'This is the content for Project 3',
                    person: 'The Net Ninja',
                    status: 'complete'
                }
            ]

        }
    },
    methods: {
        sortBy(prop) {
            this.projects.sort((a, b) => a[prop] < b[prop] ? -1 : 1)
        },
        myProjects() {
            return this.projects_b.filter(project => {
                return project.person === 'The Net Ninja' && project.status != 'complete'
            })
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
        }),
        db.collection('projects').onSnapshot(res => {
            const changes = res.docChanges();

            changes.forEach(change => {
                if (change.type === 'added') {
                    this.projects_b.push({
                        ...change.doc.data(),
                        id: change.doc.id
                    })
                }
            })
        })

    }
}
</script>


