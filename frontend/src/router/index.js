import { createRouter, createWebHistory } from "vue-router";
import LoginView from "@/views/auth/LoginView.vue";
import CarListView from "@/views/cars/CarListView.vue";
import CarDetailView from "@/views/cars/CarDetailView.vue";
import MaintenanceListView from "@/views/maintenances/MaintenanceListView.vue";
import MaintenanceDetailView from "@/views/maintenances/MaintenanceDetailView.vue";
import CustomListView from "@/views/customs/CustomListView.vue";
import CustomDetailView from "@/views/customs/CustomDetailView.vue";
import PartsListView from "@/views/parts/PartsListView.vue";
import PartsDetailView from "@/views/parts/PartsDetailView.vue";
import IssueListView from "@/views/issues/IssueListView.vue";
import IssueDetailView from "@/views/issues/IssueDetailView.vue";
import AppLayout from "@/layouts/AppLayout.vue";

const routes = [
  { path: "/login", component: LoginView },
  {
    path: "/",
    component: AppLayout,
    children: [
      { path: "cars", component: CarListView },
      { path: "cars/:carId", component: CarDetailView },
      { path: "cars/:carId/maintenances", component: MaintenanceListView },
      { path: "cars/:carId/maintenances/:id", component: MaintenanceDetailView },
      { path: "cars/:carId/customs", component: CustomListView },
      { path: "cars/:carId/customs/:id", component: CustomDetailView },
      { path: "cars/:carId/parts", component: PartsListView },
      { path: "cars/:carId/parts/:id", component: PartsDetailView },
      { path: "cars/:carId/issues", component: IssueListView },
      { path: "cars/:carId/issues/:id", component: IssueDetailView }
    ]
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
