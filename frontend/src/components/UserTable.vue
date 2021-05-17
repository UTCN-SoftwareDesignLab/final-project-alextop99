<template>
  <v-card>
    <v-card-title>
      Users
    </v-card-title>
    <v-data-table
        :headers="userTableHeader"
        :items="users"
        :search="userSearch"
        @click:row="selectUser"
    >
      <template v-slot:top>
        <v-text-field
            v-model="userSearch"
            label="Search"
            class="mx-4"
        ></v-text-field>
      </template>
    </v-data-table>
    <v-btn @click="createUser">
      Create User
    </v-btn>
    <UserDialog :user="selectedUser" :visible="showUserDialog" :onClose="onClose" :createUser="isUserCreated"/>
  </v-card>
</template>
<script>

import UserDialog from "@/components/UserDialog";
import api from "@/api";
export default {
  name: "UserTable",
  components: {UserDialog},
  data() {
    return {
      users: [],
      userSearch: "",
      userTableHeader: [
        {
          text: "Username",
          value: "username",
        },
        {
          text: "Email",
          value: "email"
        },
        {
          text: "Role",
          value: "role"
        },
      ],
      selectedUser: {},
      showUserDialog: false,
      isUserCreated: false,
    }
  },
  methods: {
    selectUser(user) {
      this.selectedUser = user;
      this.isUserCreated = false;
      this.showUserDialog = true;
    },
    createUser() {
      this.selectedUser = {};
      this.isUserCreated = true;
      this.showUserDialog = true;
    },
    onClose() {
      this.showUserDialog = false;
    }
  },
  async created() {
    this.users = await api.users.getAll();
  }
}
</script>

<style scoped></style>