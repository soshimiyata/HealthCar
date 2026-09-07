<script setup>
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";
import { getPart } from "@/services/partService";

const route = useRoute();

const part = ref(null);

onMounted(async () => {
  part.value = await getPart(route.params.carId, route.params.id);

  console.log("part:", part.value);
});
</script>

<template>
  <div>
    <h1 class="text-3xl font-bold mb-6">パーツ詳細</h1>

    <div v-if="part" class="bg-white border rounded-lg p-6">
      <h2 class="text-2xl font-bold mb-4">
        {{ part.name }}
      </h2>

      <p>パーツID: {{ part.id }}</p>
      <p>カテゴリ: {{ part.category }}</p>
      <p>メーカー: {{ part.maker }}</p>
      <p>装着日: {{ part.installedAt }}</p>
      <p>購入価格: {{ part.price }} 円</p>
      <p>メモ: {{ part.description }}</p>
      <p>ステータス: {{ part.status }}</p>

      <div v-if="part.imageUrl" class="mt-4">
        <img :src="part.imageUrl" alt="パーツ画像" class="max-w-md rounded-lg" />
      </div>
    </div>

    <!-- パーツ履歴 -->
    <h2 class="text-2xl font-bold mt-8 mb-4">最近のパーツ</h2>

    <div v-for="item in part?.currentParts" :key="item.id" class="bg-white border rounded-lg p-4 mb-3">
      <h3 class="font-bold">
        {{ item.name }}
      </h3>

      <p>装着日: {{ item.installedAt }}</p>
      <p>価格: {{ item.price }} 円</p>
      <p>ステータス: {{ item.status }}</p>
    </div>
  </div>
</template>
