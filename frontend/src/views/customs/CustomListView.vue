<script setup>
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";
import { getCustoms } from "@/services/customService";

const route = useRoute();

const customs = ref([]);

onMounted(async () => {
  const response = await getCustoms(route.params.carId);

  customs.value = response.content;
});
</script>

<template>
  <div>
    <h1 class="text-3xl font-bold mb-6">カスタム履歴</h1>

    <RouterLink
      v-for="custom in customs"
      :key="custom.id"
      :to="`/cars/${route.params.carId}/customs/${custom.id}`"
      class="block bg-white border rounded-lg p-4 mb-4"
    >
      <h2 class="text-xl font-bold">
        {{ custom.title }}
      </h2>

      <p>実施日: {{ custom.customDate }}</p>

      <p>費用: {{ custom.cost }} 円</p>
    </RouterLink>
  </div>
</template>
