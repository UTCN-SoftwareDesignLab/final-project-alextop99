<template>
  <v-dialog
      @input="onClose"
      :value="visible"
  >
    <v-card>
      <h1>{{ createClient ? "Create Client" : "Edit Client" }}</h1>
      <v-form>
        <v-text-field v-model="client.firstname" label="Firstname" />
        <v-text-field v-model="client.lastname" label="Lastname" />
        <v-text-field v-model="client.idcardnumber" label="Id Card Number" />
        <v-text-field v-model="client.cnp" label="CNP" />
        <v-text-field v-model="client.email" label="Email" />
        <v-text-field v-model="client.address" label="Address" />
      </v-form>
      <v-card-actions>
        <v-btn @click="Execute">
          {{ createClient ? "Create" : "Save" }}
        </v-btn>
        <v-btn v-if="!createClient" @click="Delete">Delete Client</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "ClientDialog",
  props: {
    client: Object,
    onClose: Function,
    createClient: Boolean,
    visible: Boolean,
  },
  methods: {
    Execute() {
      if (this.createClient) {
        api.clients
            .create({
              id : null,
              firstname: this.client.firstname,
              lastname: this.client.lastname,
              idcardnumber: this.client.idcardnumber,
              cnp: this.client.cnp,
              email: this.client.email,
              address: this.client.address,
            })
            .then(() => this.$router.go());
      } else {
        api.clients
            .update({
              id: this.client.id,
              firstname: this.client.firstname,
              lastname: this.client.lastname,
              idcardnumber: this.client.idcardnumber,
              cnp: this.client.cnp,
              email: this.client.email,
              address: this.client.address,
            })
            .then(() => this.$router.go());
      }
    },
    Delete(){
      api.clients.deleteById(this.client.id).then(() => this.$router.go());
    },
  },
};
</script>

<style scoped></style>
