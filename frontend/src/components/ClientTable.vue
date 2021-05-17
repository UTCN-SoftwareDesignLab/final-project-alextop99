<template>
  <v-card>
    <v-card-title>
      Clients
    </v-card-title>
    <v-data-table
        :headers="clientTableHeader"
        :items="clients"
        :search="clientSearch"
        @click:row="SelectClient"
    >
      <template v-slot:top>
        <v-text-field
            v-model="clientSearch"
            label="Search"
            class="mx-4"
        ></v-text-field>
      </template>
    </v-data-table>
    <v-btn @click="CreateClient">
      Create Client
    </v-btn>
    <ClientDialog :client="selectedClient" :visible="showClientDialog" :onClose="OnClose" :createClient="isClientCreated"/>
  </v-card>
</template>
<script>

import ClientDialog from "@/components/ClientDialog";
import api from "@/api";
export default {
  name: "ClientTable",
  components: {ClientDialog},
  data() {
    return {
      clients: [],
      clientSearch: "",
      clientTableHeader: [
        {
          text: "Firstname",
          value: "firstname",
        },
        {
          text: "Lastname",
          value: "lastname"
        },
        {
          text: "Id Card Nb",
          value: "idcardnumber"
        },
        {
          text: "CNP",
          value: "cnp"
        },
        {
          text: "Email",
          value: "email"
        },
        {
          text: "Address",
          value: "address"
        },
      ],
      selectedClient: {},
      showClientDialog: false,
      isClientCreated: false,
    }
  },
  methods: {
    SelectClient(client) {
      this.selectedClient = client;
      this.isClientCreated = false;
      this.showClientDialog = true;
    },
    CreateClient() {
      this.selectedClient = {};
      this.isClientCreated = true;
      this.showClientDialog = true;
    },
    OnClose() {
      this.showClientDialog = false;
    }
  },
  async created() {
    this.clients = await api.clients.getAll();
  }
}
</script>

<style scoped></style>