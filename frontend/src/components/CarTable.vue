<template>
  <v-card>
    <v-card-title>
      Cars
    </v-card-title>
    <v-data-table
        :headers="carTableHeader"
        :items="cars"
        :search="carSearch"
        @click:row="SelectCar"
    >
      <template v-slot:top>
        <v-text-field
            v-model="carSearch"
            label="Search"
            class="mx-4"
        ></v-text-field>
      </template>
    </v-data-table>
    <v-btn @click="CreateCar">
      Create Car
    </v-btn>
    <CarDialog :car="selectedCar" :clients="clients" :visible="showCarDialog" :onClose="OnClose" :createCar="isCarCreated"/>
  </v-card>
</template>
<script>

import CarDialog from "@/components/CarDialog";
import api from "@/api";
export default {
  name: "CarTable",
  components: {CarDialog},
  props: {
  },
  data() {
    return {
      clients: [],
      cars: [],
      carSearch: "",
      carTableHeader: [
        {
          text: "Make",
          value: "make",
        },
        {
          text: "Model",
          value: "model"
        },
        {
          text: "Matriculation Number",
          value: "matNb"
        },
        {
          text: "VIN",
          value: "vin"
        },
        {
          text: "Year",
          value: "year"
        },
        {
          text: "Client",
          value: "client.lastname"
        },
      ],
      selectedCar: {'client':{}, 'year':'0000-00-00 00:00:00'},
      showCarDialog: false,
      isCarCreated: false,
    }
  },
  methods: {
    SelectCar(car) {
      this.selectedCar = car;
      this.isCarCreated = false;
      this.showCarDialog = true;
    },
    CreateCar() {
      this.selectedCar = {'client':{}};
      this.isCarCreated = true;
      this.showCarDialog = true;
    },
    OnClose() {
      this.showCarDialog = false;
    }
  },
  async created() {
    this.clients = await api.clients.getAll();
    this.cars = await api.cars.getAll();
  }
}
</script>

<style scoped></style>