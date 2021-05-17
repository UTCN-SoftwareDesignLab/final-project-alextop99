import Vue from "vue";
import VueRouter from "vue-router";
import Admin from "@/pages/Admin";
import Login from "@/pages/Login";
import Secretary from "@/pages/Secretary";
import Mechanic from "@/pages/Mechanic";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Login",
    component: Login,
  },
  {
    path: "/admin",
    name: "Admin",
    component: Admin,
  },
  {
    path: "/secretary",
    name: "Secretary",
    component: Secretary,
  },
  {
    path: "/mechanic",
    name: "Mechanic",
    component: Mechanic,
  },
];

const router = new VueRouter({
  routes,
  mode: 'history',
});

export default router;
