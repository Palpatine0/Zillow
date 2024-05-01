<template>
    <v-app style="padding: 20px">
        <v-subheader class="subheading grey--text" as="h1">Users</v-subheader>
        <v-container class="my-5">
            <v-row>
                <v-col v-for="user in users" :key="user.name" cols="12" sm="6" md="4" lg="3">
                    <v-card flat class="d-flex flex-column align-center justify-center ma-3">
                        <v-avatar size="100" class="grey lighten-2 mb-2">
                            <img src="/avatar-1.png">
                        </v-avatar>
                        <v-card-text class="text-center">
                            <div class="subheading">{{ user.username }}</div>
                            <div class="grey--text">{{ user.phone }}</div>
                        </v-card-text>
                        <v-card-actions class="justify-center">
                            <v-btn text color="grey">
                                <v-icon left small>mdi-message</v-icon>
                                Message
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                </v-col>
            </v-row>
        </v-container>
    </v-app>
</template>


<script>
export default {
    data() {
        return {
            users: []
        }
    },
    mounted() {
        this.$api.getUser()
        .then(data => {
            console.log(data)
            if (data.data.status) {
                this.users = data.data.data
            }
        })

    },
}
</script>