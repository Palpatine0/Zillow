<template>
    <div class="projects">
        <h1 class="subheading grey--text">Projects</h1>

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
    computed: {
        myProjects() {
            return this.projects_b.filter(project => {
                return project.person === 'The Net Ninja' && project.status != 'complete'
            })
        }
    },
    created() {
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
