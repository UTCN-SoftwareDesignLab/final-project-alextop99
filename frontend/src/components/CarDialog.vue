<template>
  <v-dialog
      @input="onClose"
      :value="visible"
  >
    <v-card>
      <h1>{{ createCar ? "Create Car" : "Delete Car" }}</h1>
      <v-form v-if="createCar">
        <v-text-field v-model="car.make" label="Make" />
        <v-text-field v-model="car.model" label="Model" />
        <v-text-field v-model="car.matNb" label="Matriculation Number" />
        <v-text-field v-model="car.vin" label="VIN" />
        <v-text-field v-model="car.year" label="Year" />
        <v-select :items="clientsList" @input="SelectClient"></v-select>
      </v-form>
      <v-card v-else height="35vh">
        <label>Make</label><br/>
        <label>{{car.make}}</label><br/>
        <label>Model</label><br/>
        <label>{{car.model}}</label><br/>
        <label>Matriculation Number</label><br/>
        <label>{{car.matNb}}</label><br/>
        <label>VIN</label><br/>
        <label>{{car.vin}}</label><br/>
        <label>Year</label><br/>
        <label>{{car.year.substring(0, 4)}}</label><br/>
      </v-card>
      <v-card-actions>
        <v-btn v-if="createCar && clientSelected" @click="Execute">
          Create Car
        </v-btn>
        <v-btn v-if="!createCar" @click="Delete">Delete Car</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "CarDialog",
  props: {
    car: Object,
    onClose: Function,
    createCar: Boolean,
    visible: Boolean,
  },
  data() {
    return {
      clients: [],
      clientsList: [],
      client: {},
      clientSelected: false,
    };
  },
  methods: {
    Execute() {
      api.cars
          .create({
            id: null,
            make: this.car.make,
            model: this.car.model,
            matNb: this.car.matNb,
            vin: this.car.vin,
            year: this.car.year + "-01-01",
            client: this.client,
          })
          .then(() => this.$router.go());
    },
    SelectClient(client) {
      this.clientSelected = true;
      this.client = client;
    },
    Delete() {
      api.cars.deleteById(this.car.id).then(() => this.$router.go());
    },
  },
  async created() {
    this.clients = await api.clients.getAll();
    this.clientsList = this.clients.map(client => ({'value': client,
      'text': (client.firstname + " " + client.lastname + " " + client.cnp),
    }));
  }
}
</script>

<style scoped>

</style>