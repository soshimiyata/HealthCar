<script setup>
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";
import { getIssues } from "@/services/issueService";

const route = useRoute();

const issues = ref([]);

onMounted(async () => {
  const response = await getIssues(route.params.carId);

  issues.value = response.content;
});
</script>

<template>
  <div>
    <h1 class="text-3xl font-bold mb-6">車の状態管理</h1>

    <RouterLink
      v-for="issue in issues"
      :key="issue.id"
      :to="`/cars/${route.params.carId}/issues/${issue.id}`"
      class="block bg-white border rounded-lg p-4 mb-4"
    >
      <h2 class="text-xl font-bold">
        {{ issue.title }}
      </h2>

      <p>対応状況: {{ issue.status }}</p>

      <p>優先度: {{ issue.priority }}</p>

      <p>発生日: {{ issue.occurredAt }}</p>
    </RouterLink>
  </div>
</template>
