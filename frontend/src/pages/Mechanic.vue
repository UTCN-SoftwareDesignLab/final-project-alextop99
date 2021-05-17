<template>
  <v-container>
    <Header/>
    <v-layout row justify-center pt-3>
      <v-flex xs40 class="grey lighten-4">
        <v-container class="text-xs-center">
          <v-card flat>
            <v-card-title primary-title>
              <h4>Mechanic panel</h4>
            </v-card-title>
          </v-card>
          <v-card>
            <AppointmentsViewTable/>
          </v-card>
        </v-container>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import api from "@/api";
import Header from "@/components/Header";
import AppointmentsViewTable from "@/components/AppointmentsViewTable";
import router from "@/router";

export default {
  name: "Mechanic",
  components: {AppointmentsViewTable, Header},
  data() {
    return {
      appointments: [],
    };
  },
  async created() {
    if(this.$store.state.auth.user.role !== "MECHANIC") {
      await this.$store.dispatch("auth/logout");
      await router.push("/");
    }
    else {
      api.webSocket.connectAndSubscribe(this.$store.state.auth.user.username, this.$alert);
    }
  }
}
</script>

<style scoped>

</style>