<template>
    <div class="team">
      <h1 class="subheading grey--text">Users</h1>

        <v-container class="my-5">

            <v-layout row wrap>

              <v-flex v-for="user in users" :key="user.name" lg3 md4 sm6 xs12>
                    <v-card flat class="text-xs-center ma-3">
                        <v-responsive class="pt-4">
                            <v-avatar size="100" class="grey lighten-2">
                              <!--<img :src="user.avatar">-->
                              <img src="/avatar-1.png">
                            </v-avatar>
                        </v-responsive>
                        <v-card-text>
                          <div class="subheading">{{ user.username }}</div>
                          <div class="grey--text">{{ user.phone }}</div>
                        </v-card-text>
                        <v-card-actions>
                            <v-btn flat color="grey">
                                <v-icon small left>message</v-icon>
                                <span class="">Message</span>
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                </v-flex>

            </v-layout>

        </v-container>

    </div>
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
          console.log("data---------------------------------")
          console.log(data)
          if (data.data.status) {
            this.users = data.data.data
          }
        })

  },
}
</script>