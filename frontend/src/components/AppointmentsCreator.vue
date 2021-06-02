<template>
  <v-dialog
      @input="onClose"
      :value="visible"
  >
    <v-card>
      <h1>{{ createAppointment ? "Create Appointment" : "Remove Appointment" }}</h1>
      <v-card v-if="createAppointment" height="60vh">
        <label>Pick a Date</label>
        <datepicker :value="date" @selected="SelectedDate"></datepicker>
        <label v-if="dateSelected">Pick a Car</label>
        <v-select v-if="dateSelected" :items="carsList" @input="SelectCar"></v-select>
        <label v-if="carSelected">Pick a Mechanic</label>
        <v-select v-if="carSelected" :items="mechanicsList" @input="SelectMechanic"></v-select>
        <label v-if="mechanicSelected">Pick the Hour</label>
        <v-select v-if="mechanicSelected" :items="hours" @input="SelectHour"></v-select>
        <v-btn v-if="hourSelected" @click="CreateAppointment">Create Appointment</v-btn>
      </v-card>
      <v-card v-else height="60vh">
        <label>Date</label><br/>
        <label>{{appointment.dateTime}}</label><br/>
        <label>Car</label><br/>
        <label>{{appointment.car.matNb + " " + appointment.car.vin}}</label><br/>
        <label>Mechanic</label><br/>
        <label>{{appointment.user.username}}</label><br/>
        <v-btn @click="DeleteAppointment">Delete Appointment</v-btn>
        <v-btn @click="CheckInBtn">Check-In</v-btn><br/>
        <label v-if="appointment.finished">Details</label><br/>
        <label v-if="appointment.finished">{{appointment.details}}</label><br/>
        <v-text-field v-if="appointment.finished" v-model="price" label="Price" />
        <v-btn v-if="appointment.finished" @click="GetReceipt">GenerateReceipt</v-btn>
      </v-card>
    </v-card>
  </v-dialog>
</template>
<script>

import Datepicker from "vuejs-datepicker";
import api from "@/api";

export default {
  name: "AppointmentsCreator",
  components: {
    Datepicker
  },
  props: {
    appointment: Object,
    onClose: Function,
    createAppointment: Boolean,
    visible: Boolean,
    CheckIn: Function,
  },
  data() {
    return {
      cars: [],
      mechanics: [],
      date: new Date(),
      carsList: [],
      mechanicsList: [],
      hours: ["08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30"],
      availableHours: this.hours,
      car: {},
      mechanic: {},
      hour: "",
      dateSelected: false,
      carSelected: false,
      mechanicSelected: false,
      hourSelected: false,
      price: 0.00,
    };
  },
  methods: {
    SelectedDate(date) {
      this.date = date;
      this.dateSelected = true;
      this.carSelected = false;
      this.mechanicSelected = false;
      this.hourSelected = false;
     },
    SelectCar(car) {
      this.carSelected = true;
      this.mechanicSelected = false;
      this.hourSelected = false;
      this.car = car;
    },
    SelectMechanic(mechanic) {
      this.mechanicSelected = true;
      this.hourSelected = false;
      this.mechanic = mechanic;
      this.FindOccupiedHours();
    },
    SelectHour(hour) {
      this.hourSelected = true;
      this.hour = hour;
    },
    CreateAppointment() {
      let datestring = this.date.getFullYear() + "-" + ((this.date.getMonth() + 1) < 10 ? "0"+ (this.date.getMonth() + 1) : (this.date.getMonth() + 1)) + "-" + (this.date.getDate() < 10 ? "0"+ this.date.getDate() : this.date.getDate());
      let timestring = this.hour + ":00"

      let localCar = {
        id: this.car.id,
        make: this.car.make,
        model: this.car.model,
        matNb: this.car.matNb,
        vin: this.car.vin,
        year: this.car.year,
        client: this.car.client,
      };

      let localMechanic = {
        id: this.mechanic.id,
        username: this.mechanic.username,
        email: this.mechanic.email,
        password: this.mechanic.password,
        role: this.mechanic.role,
      }

      api.appointments
          .create({
            id : null,
            dateTime: datestring + "T" + timestring,
            car: localCar,
            user: localMechanic,
            details: "",
            finished: false,
          })
          .then(() => this.$router.go());
    },
    DeleteAppointment() {
      api.appointments.deleteById(this.appointment.id).then(() => this.$router.go());
    },
    CheckInBtn() {
      this.CheckIn(this.appointment.car.matNb + " " + this.appointment.car.vin, this.appointment.dateTime, this.appointment.user.username);
    },
    GetReceipt() {
      api.appointments.getReceipt({
        car: this.appointment.car,
        price: this.price,
      });
    },
    async FindOccupiedHours() {
      let datestring = this.date.getFullYear() + "-" + ((this.date.getMonth() + 1) < 10 ? "0"+ (this.date.getMonth() + 1) : (this.date.getMonth() + 1)) + "-" + (this.date.getDate() < 10 ? "0"+ this.date.getDate() : this.date.getDate());
      let queryResult = await api.appointments.getAllByDateAndMechanic(datestring, this.mechanic.id);
      let occupiedHours = [];
      queryResult.forEach(element => occupiedHours.push(element.dateTime.substring(11, 16)));
      this.availableHours = this.hours;

      for(let i = 0; i < this.availableHours.length; i++){
        occupiedHours.forEach(element => {
          if (element === this.availableHours[i]) {
            this.availableHours.splice(i, 1);
          }
        });
      }
      this.availableHours = this.hours;
    },
  },
  async created() {
    this.cars = await api.cars.getAll();
    this.carsList = this.cars.map(car =>  ({'value': car,
                                                        'text': car.matNb + " " + car.vin,
                                                      }));
    this.mechanics = await api.users.getAllMechanics();
    this.mechanicsList = this.mechanics.map(mechanic => ({'value': mechanic,
                                                    'text': mechanic.username,
                                                      }));
  }
}
</script>

<style scoped>

</style>