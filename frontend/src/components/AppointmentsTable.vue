<template>
  <v-card>
    <v-card-title>
      Appointments
    </v-card-title>
    <v-data-table
        :headers="appointmentsTableHeader"
        :items="appointments"
        :search="appointmentSearch"
        @click:row="SelectAppointment"
    >
      <template v-slot:top>
        <v-text-field
            v-model="appointmentSearch"
            label="Search"
            class="mx-4"
        ></v-text-field>
      </template>

    </v-data-table>
    <v-btn @click="CreateAppointment">
      Create Appointment
    </v-btn>
    <AppointmentsCreator :appointment="selectedAppointment" :visible="showAppointmentDialog" :onClose="onClose" :createAppointment="isAppointmentCreated" :CheckIn="CheckIn"/>
  </v-card>
</template>
<script>

import AppointmentsCreator from "@/components/AppointmentsCreator";
import api from "@/api";
export default {
  name: "AppointmentsTable",
  components: {AppointmentsCreator},
  props: {
    CheckIn: Function,
  },
  data() {
    return {
      appointmentSearch: "",
      appointments: [],
      appointmentsTableHeader: [
        {
          text: "Date",
          value: "dateTime",
        },
        {
          text: "Car",
          value: "car.matNb"
        },
        {
          text: "Mechanic",
          value: "user.username"
        },
      ],
      selectedAppointment: {'car':{}, 'user':{}},
      showAppointmentDialog: false,
      isAppointmentCreated: false,
    }
  },
  methods: {
    SelectAppointment(appointment) {
      this.selectedAppointment = appointment;
      this.isAppointmentCreated = false;
      this.showAppointmentDialog = true;
    },
    CreateAppointment() {
      this.selectedAppointment = {'car':{}, 'user':{}};
      this.isAppointmentCreated = true;
      this.showAppointmentDialog = true;
    },
    onClose() {
      this.showAppointmentDialog = false;
    }
  },
  async created() {
    this.appointments = await api.appointments.getAll();
    let date = new Date();
    this.appointmentSearch = date.getFullYear() + "-" + (date.getMonth() + 1 < 10 ? "0"+ (date.getMonth() + 1) : (date.getMonth() + 1)) + "-" + (date.getDate() < 10 ? "0"+ date.getDate() : date.getDate());
  }
}
</script>

<style scoped></style>