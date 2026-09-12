<script setup>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";

import CustomForm from "@/components/customs/CustomForm.vue";

import { getCustom, updateCustom } from "@/services/customService";

const route = useRoute();
const router = useRouter();

const carId = route.params.carId;
const customId = route.params.id;

const custom = ref(null);

onMounted(async () => {
  custom.value = await getCustom(carId, customId);
});

const handleSubmit = async (formData) => {
  await updateCustom(carId, customId, formData);

  router.push(`/cars/${carId}/customs/${customId}`);
};
</script>

<template>
  <div>
    <h1 class="text-3xl font-bold mb-6">カスタム情報更新</h1>

    <CustomForm
      v-if="custom"
      :initial-data="custom"
      submit-label="更新"
      @submit="handleSubmit"
    />
  </div>
</template>
