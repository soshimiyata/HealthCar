<script setup>
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";
import { getParts } from "@/services/partService";

const route = useRoute();

const parts = ref([]);

onMounted(async () => {
  const response = await getParts(route.params.carId);

  parts.value = response.content;
});
</script>

<template>
  <div>
    <h1 class="text-3xl font-bold mb-6">パーツ管理</h1>

    <RouterLink
      v-for="part in parts"
      :key="part.id"
      :to="`/cars/${route.params.carId}/parts/${part.id}`"
      class="block bg-white border rounded-lg p-4 mb-4"
    >
      <h2 class="text-xl font-bold">
        {{ part.name }}
      </h2>

      <p>メーカー: {{ part.maker }}</p>

      <p>装着日: {{ part.installedAt }}</p>

      <p>価格: {{ part.price }} 円</p>

      <p>ステータス: {{ part.status }}</p>
    </RouterLink>
  </div>
</template>
