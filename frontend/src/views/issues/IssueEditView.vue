<script setup>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";

import IssueForm from "@/components/issues/IssueForm.vue";

import { getIssue, updateIssue } from "@/services/issueService";

const route = useRoute();
const router = useRouter();

const carId = route.params.carId;
const issueId = route.params.id;

const issue = ref(null);

onMounted(async () => {
  issue.value = await getIssue(carId, issueId);
});

const handleSubmit = async (formData) => {
  await updateIssue(carId, issueId, formData);

  router.push(`/cars/${carId}/issues/${issueId}`);
};
</script>

<template>
  <div>
    <h1 class="text-3xl font-bold mb-6">不具合情報更新</h1>

    <IssueForm
      v-if="issue"
      :initial-data="issue"
      submit-label="更新"
      @submit="handleSubmit"
    />
  </div>
</template>