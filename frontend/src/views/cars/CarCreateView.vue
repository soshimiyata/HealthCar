<script setup>
import { useRouter } from "vue-router";
import CarForm from "@/components/cars/CarForm.vue";
import { createCar } from "@/services/carService";

const router = useRouter();

const handleSubmit = async (formData) => {
  const response = await createCar(formData);

  router.push(`/cars/${response.id}`);
};
</script>

<template>
  <div>
    <h1 class="text-3xl font-bold mb-6">車両登録</h1>

    <CarForm submit-label="登録" @submit="handleSubmit" />
  </div>
</template>
