<template>
  <v-container>
    <Header/>
    <v-layout row justify-center pt-3>
      <v-flex xs40 class="grey lighten-4">
        <v-container class="text-xs-center">
          <v-card flat>
            <v-card-title primary-title>
              <h4>Secretary panel</h4>
            </v-card-title>
            <v-btn @click="() => {SwitchView(0)}" v-if="viewMode !== 0">
              Appointments
            </v-btn>
            <v-btn @click="() => {SwitchView(1)}" v-if="viewMode !== 1">
              Cars
            </v-btn>
            <v-btn @click="() => {SwitchView(2)}" v-if="viewMode !== 2">
              Clients
            </v-btn>
          </v-card>
          <v-card v-if="viewMode === 1">
            <CarTable/>
          </v-card>
          <v-card v-else-if="viewMode === 2">
            <ClientTable/>
          </v-card>
          <v-card v-else>
              <AppointmentsTable :CheckIn="CheckInBtn"></AppointmentsTable>
          </v-card>
        </v-container>
      </v-flex>
    </v-layout>
  </v-container>
</template>
<script>
import ClientTable from "@/components/ClientTable";
import CarTable from "@/components/CarTable";
import api from "@/api";
import Header from "@/components/Header";
import AppointmentsTable from "@/components/AppointmentsTable";
import router from "@/router";

export default {
  name: "Secretary",
  components: {AppointmentsTable, Header, ClientTable, CarTable},
  data() {
    return {
      viewMode: 0,
      connected: false,
    };
  },
  methods: {
    SwitchView(newView) {
      this.viewMode = newView;
    },
    CheckInBtn(carMatNb, hour, mechanicName) {
      api.webSocket.CheckIn(carMatNb, hour, mechanicName);
    },
  },
  async created() {
    if(this.$store.state.auth.user.role !== "SECRETARY") {
      await this.$store.dispatch("auth/logout");
      await router.push("/");
    }
    else {
      this.connected = api.webSocket.connect();
    }
  }
}
</script>
<style scoped></style>
