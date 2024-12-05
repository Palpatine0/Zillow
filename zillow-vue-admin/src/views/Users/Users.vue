<template>
    <v-app style="padding: 20px">
        <v-subheader as="h1" class="subheading grey--text">Users</v-subheader>
        <v-container class="my-5">
            <v-row>
                <v-col v-for="user in users" :key="user.name" cols="12" lg="3" md="4" sm="6">
                    <v-card class="d-flex flex-column align-center justify-center ma-3" flat>
                        <v-avatar class="grey lighten-2 mt-5" size="100">
                            <img src="/avatar-1.png">
                        </v-avatar>
                        <v-card-text class="text-center">
                            <div class="subheading">{{ user.username }}</div>
                            <div class="grey--text">{{ user.phone }}</div>
                            <div hidden>{{ user.id }}</div>
                        </v-card-text>
                        <v-card-actions style="width: 100%; display: flex; justify-content: space-between">
                            <v-btn color="grey" text>
                                <v-icon left small>mdi-message</v-icon>
                                Message
                            </v-btn>
                            <v-btn color="grey" text @click="setDeleteId(user.id),snackbar = true">
                                <v-icon>mdi-trash-can-outline</v-icon>
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                </v-col>

                <v-snackbar v-model="snackbar" :multi-line="true">
                    Confirm your option.
                    <template v-slot:action="{ attrs }">
                        <v-btn color="red" text v-bind="attrs" @click="snackbar = false,deleteUser()">
                            Confirm
                        </v-btn>
                        <v-btn text v-bind="attrs" @click="snackbar = false">
                            Cancel
                        </v-btn>
                    </template>
                </v-snackbar>
            </v-row>
        </v-container>
    </v-app>
</template>


<script>

export default {
    data() {
        return {
            users: [],
            snackbar: false,
            userId_delete: ''
        }
    },
    methods: {
        setDeleteId(id) {
            this.userId_delete = id;
        },
        deleteUser() {
            this.$api.user.deleteUserById({
                id: this.userId_delete
            })
            .then(data => {
                if (data.status == 200) {
                    window.location.reload();
                }
            })
        }
    },
    mounted() {
        this.$api.item.getUser()
        .then(data => {
            if (data.data.status) {
                this.users = data.data.data
            }
        })

    },
}
</script>
<style>
* {
//outline: 1px solid red;
}

</style>