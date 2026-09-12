<script setup>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";

import PartsForm from "@/components/parts/PartsForm.vue";

import { getPart, updatePart } from "@/services/partService";

const route = useRoute();
const router = useRouter();

const carId = route.params.carId;
const partId = route.params.id;

const part = ref(null);

onMounted(async () => {
  part.value = await getPart(carId, partId);
});

const handleSubmit = async (formData) => {
  await updatePart(carId, partId, formData);

  router.push(`/cars/${carId}/parts/${partId}`);
};
</script>

<template>
  <div>
    <h1 class="text-3xl font-bold mb-6">パーツ情報更新</h1>

    <PartsForm
      v-if="part"
      :initial-data="part"
      submit-label="更新"
      @submit="handleSubmit"
    />
  </div>
</template>