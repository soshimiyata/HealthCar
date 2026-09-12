<script setup>
import { useRoute, useRouter } from "vue-router";

import PartsForm from "@/components/parts/PartsForm.vue";
import { createPart } from "@/services/partService";

const route = useRoute();
const router = useRouter();

const carId = route.params.carId;

const handleSubmit = async (formData) => {
  const response = await createPart(carId, formData);

  router.push(`/cars/${carId}/parts/${response.id}`);
};
</script>

<template>
  <div>
    <h1 class="text-3xl font-bold mb-6">パーツ登録</h1>

    <PartsForm submit-label="登録" @submit="handleSubmit" />
  </div>
</template>