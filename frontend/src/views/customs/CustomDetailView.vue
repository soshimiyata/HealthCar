<script setup>
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";
import { getCustom } from "@/services/customService";

const API_BASE_URL = import.meta.env.VITE_API_BASE_URL

const route = useRoute();

const custom = ref(null);

onMounted(async () => {
  custom.value = await getCustom(route.params.carId, route.params.id);
});
</script>

<template>
  <div>
    <h1 class="text-3xl font-bold mb-6">カスタム詳細</h1>

    <div v-if="custom?.imageUrl" class="mb-6">
      <img
        :src="`${API_BASE_URL}${custom.imageUrl}`"
        alt="カスタム画像"
        class="w-full max-w-xl rounded-lg"
      />
    </div>

    <div v-if="custom" class="bg-white border rounded-lg p-6">
      <h2 class="text-xl font-bold mb-4">
        {{ custom.title }}
      </h2>

      <p>カスタムID: {{ custom.id }}</p>

      <p>実施日: {{ custom.customDate }}</p>

      <p>費用: {{ custom.cost }} 円</p>

      <p>カスタム内容: {{ custom.description }}</p>
    </div>
    <!-- 最近の履歴 -->
    <h2 class="text-2xl font-bold mt-8 mb-4">カスタム履歴</h2>

    <div
      v-for="item in custom?.recentCustoms"
      :key="item.id"
      class="bg-white border rounded-lg p-4 mb-3"
    >
      <h3 class="font-bold">
        {{ item.title }}
      </h3>

      <p>実施日: {{ item.customDate }}</p>

      <p>費用: {{ item.cost }} 円</p>
    </div>
  </div>
</template>
