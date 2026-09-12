<script setup>
import { ref } from "vue";
import { useRoute, useRouter } from "vue-router";
import { createIssue } from "@/services/issueService";

const route = useRoute();
const router = useRouter();

const carId = route.params.carId;

const title = ref("");
const description = ref("");
const status = ref("0");
const priority = ref("1");
const occurredAt = ref("");
const resolvedAt = ref("");
const imageFile = ref(null);

const submit = async () => {
  const issue = {
    title: title.value,
    description: description.value,
    status: Number(status.value),
    priority: Number(priority.value),
    occurredAt: occurredAt.value || null,
    resolvedAt: resolvedAt.value || null
  };

  const formData = new FormData();

  formData.append(
    "issue",
    new Blob([JSON.stringify(issue)], { type: "application/json" })
  );

  if (imageFile.value) {
    formData.append("image", imageFile.value);
  }

  const response = await createIssue(carId, formData);

  router.push(`/cars/${carId}/issues/${response.id}`);
};
</script>

<template>
  <div>
    <h1 class="text-3xl font-bold mb-6">不具合登録</h1>

    <form @submit.prevent="submit">
      <div class="mb-4">
        <label class="block font-semibold mb-1"> 不具合名 </label>

        <input
          v-model="title"
          type="text"
          maxlength="100"
          required
          class="w-full border rounded-lg p-2"
        />
      </div>

      <div class="mb-4">
        <label class="block font-semibold mb-1"> 詳細・症状・メモ </label>

        <textarea
          v-model="description"
          class="w-full border rounded-lg p-2"
        ></textarea>
      </div>

      <div class="mb-4">
        <label class="block font-semibold mb-1"> 対応状況 </label>

        <select v-model="status" required class="w-full border rounded-lg p-2">
          <option value="0">未着手</option>
          <option value="1">対応中</option>
          <option value="2">解決済み</option>
        </select>
      </div>

      <div class="mb-4">
        <label class="block font-semibold mb-1"> 優先度 </label>

        <select
          v-model="priority"
          required
          class="w-full border rounded-lg p-2"
        >
          <option value="0">高</option>
          <option value="1">中</option>
          <option value="2">低</option>
        </select>
      </div>

      <div class="mb-4">
        <label class="block font-semibold mb-1"> 発生日・気付いた日 </label>

        <input
          v-model="occurredAt"
          type="date"
          class="w-full border rounded-lg p-2"
        />
      </div>

      <div class="mb-4">
        <label class="block font-semibold mb-1"> 解決日 </label>

        <input
          v-model="resolvedAt"
          type="date"
          class="w-full border rounded-lg p-2"
        />
      </div>

      <div class="mb-6">
        <label class="block font-semibold mb-1"> 不具合画像 </label>

        <input
          type="file"
          accept="image/*"
          class="w-full border rounded-lg p-2"
          @change="imageFile = $event.target.files[0]"
        />
      </div>

      <button
        type="submit"
        class="rounded-lg bg-blue-600 px-4 py-2 font-semibold text-white"
      >
        登録
      </button>
    </form>
  </div>
</template>