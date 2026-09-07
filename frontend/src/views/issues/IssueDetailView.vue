<script setup>
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";
import { getIssue } from "@/services/issueService";

const route = useRoute();

const issue = ref(null);

onMounted(async () => {
  issue.value = await getIssue(route.params.carId, route.params.id);
});
</script>

<template>
  <div>
    <h1 class="text-3xl font-bold mb-6">不具合詳細</h1>

    <div v-if="issue" class="bg-white border rounded-lg p-6">
      <h2 class="text-2xl font-bold mb-4">
        {{ issue.title }}
      </h2>

      <p>不具合ID: {{ issue.id }}</p>

      <p>詳細・症状: {{ issue.description }}</p>

      <p>対応状況: {{ issue.status }}</p>

      <p>優先度: {{ issue.priority }}</p>

      <p>発生日: {{ issue.occurredAt }}</p>

      <p>解決日: {{ issue.resolvedAt }}</p>

      <div v-if="issue.imageUrl" class="mt-4">
        <img :src="issue.imageUrl" alt="不具合画像" class="max-w-md rounded-lg" />
      </div>
    </div>
  </div>
</template>
