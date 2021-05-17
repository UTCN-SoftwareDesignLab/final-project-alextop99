<template>
  <v-card>
    <v-card-title>
      History
    </v-card-title>
    <v-data-table
        :headers="appointmentsTableHeader"
        :items="appointmentHistory"
    >
    </v-data-table>
  </v-card>
</template>

<script>
export default {
  name: "AppointmentsHistory",
  props: {
    appointmentHistory: Array,
  },
  data() {
    return {
      appointmentsTableHeader: [
        {
          text: "Date",
          value: "dateTime",
          sortable: false,
        },
        {
          text: "Mechanic",
          value: "user.username",
          sortable: false,
        },
        {
          text: "Details",
          value: "details",
          sortable: false,
        },
      ],
    }
  },
}
</script>

<style scoped>

</style>