<script setup>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import CarForm from "@/components/cars/CarForm.vue";
import { getCar, updateCar } from "@/services/carService";

const route = useRoute();
const router = useRouter();

const carId = route.params.carId;

const car = ref(null);

onMounted(async () => {
  car.value = await getCar(carId);
});

const handleSubmit = async (formData) => {
  const response = await updateCar(carId, formData);

  router.push(`/cars/${carId}`);
};
</script>

<template>
  <div>
    <h1 class="text-3xl font-bold mb-6">車両情報更新</h1>

    <CarForm
      v-if="car"
      :initial-data="car"
      submit-label="更新"
      @submit="handleSubmit"
    />
  </div>
</template>
