<template>
  <v-dialog
      @input="onClose"
      :value="visible"
  >
    <v-card>
      <h1>Service</h1>
      <v-card>
        <label>Car</label><br/>
        <label>{{appointment.car.matNb + " " + appointment.car.vin}}</label><br/>
        <label>Date</label><br/>
        <label>{{appointment.dateTime}}</label><br/>
        <AppointmentsHistory :appointmentHistory="appointmentHistory"></AppointmentsHistory>
        <v-text-field v-model="appointment.details" label="Details" />
        <v-checkbox v-model="appointment.finished" label="Finished" />
        <v-btn @click="Update">Save</v-btn>
      </v-card>
    </v-card>
  </v-dialog>
</template>

<script>
import AppointmentsHistory from "@/components/AppointmentsHistory";
import api from "@/api";
export default {
  name: "AppointmentsEditDialog",
  components: {AppointmentsHistory},
  props: {
    appointment: Object,
    appointmentHistory: Array,
    visible: Boolean,
    onClose: Function,
  },
  methods: {
    Update() {
      api.appointments.update(this.appointment).then(() => this.$router.go());
    }
  }
}
</script>

<style scoped>

</style>