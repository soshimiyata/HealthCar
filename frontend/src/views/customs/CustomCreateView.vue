<script setup>
import { useRoute, useRouter } from "vue-router";
import CustomForm from "@/components/customs/CustomForm.vue";
import { createCustom } from "@/services/customService";

const route = useRoute();
const router = useRouter();

const carId = route.params.carId;

const handleSubmit = async (formData) => {
  const response = await createCustom(carId, formData);

  router.push(`/cars/${carId}/customs/${response.id}`);
};
</script>

<template>
  <div>
    <h1 class="text-3xl font-bold mb-6">カスタム登録</h1>

    <CustomForm submit-label="登録" @submit="handleSubmit" />
  </div>
</template>
