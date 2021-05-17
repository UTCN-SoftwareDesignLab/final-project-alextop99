<template>
  <v-card>
    <v-card-title>
      Appointments
    </v-card-title>
    <v-data-table
        :headers="appointmentsTableHeader"
        :items="appointments"
        @click:row="SelectAppointment"
    >
    </v-data-table>
    <AppointmentsEditDialog :visible="showAppointmentDialog" :onClose="onClose" :appointmentHistory="appointmentHistory" :appointment="selectedAppointment"></AppointmentsEditDialog>
  </v-card>
</template>

<script>
import api from "@/api";
import AppointmentsEditDialog from "@/components/AppointmentsEditDialog";

export default {
  name: "AppointmentsViewTable",
  components: {AppointmentsEditDialog},
  data() {
    return {
      appointments: [],
      appointmentsTableHeader: [
        {
          text: "Date",
          value: "dateTime",
          sortable: false,
        },
        {
          text: "Car",
          value: "car.matNb",
          sortable: false,
        },
      ],
      appointmentHistory: [],
      selectedAppointment: {'car':{}, 'user':{}},
      showAppointmentDialog: false,
    }
  },
  methods: {
    async SelectAppointment(appointment) {
      this.selectedAppointment = appointment;
      this.appointmentHistory = await api.appointments.getAllByCar(appointment.car.id, appointment.dateTime);
      this.showAppointmentDialog = true;
    },
    onClose() {
      this.showAppointmentDialog = false;
    }
  },
  async created() {
    let date = new Date();
    let dateString = date.getFullYear() + "-" + (date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : (date.getMonth() + 1)) + "-" + (date.getDate() < 10 ? "0" + date.getDate() : date.getDate());
    this.appointments = await api.appointments.getAllByDateAndMechanic(dateString, this.$store.state.auth.user.id);
  }
}
</script>

<style scoped>
</style>