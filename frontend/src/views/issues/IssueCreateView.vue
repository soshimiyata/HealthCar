<script setup>
import { useRoute, useRouter } from "vue-router";

import IssueForm from "@/components/issues/IssueForm.vue";
import { createIssue } from "@/services/issueService";

const route = useRoute();
const router = useRouter();

const carId = route.params.carId;

const handleSubmit = async (formData) => {
  const response = await createIssue(carId, formData);

  router.push(`/cars/${carId}/issues/${response.id}`);
};
</script>

<template>
  <div>
    <h1 class="text-3xl font-bold mb-6">不具合登録</h1>

    <IssueForm submit-label="登録" @submit="handleSubmit" />
  </div>
</template>