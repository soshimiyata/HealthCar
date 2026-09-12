<script setup>
import { ref, watch } from "vue";

const props = defineProps({
  initialData: {
    type: Object,
    default: null
  },
  submitLabel: {
    type: String,
    default: "登録"
  }
});

const emit = defineEmits(["submit"]);

const title = ref("");
const description = ref("");
const status = ref("0");
const priority = ref("1");
const occurredAt = ref("");
const resolvedAt = ref("");
const imageFile = ref(null);

watch(
  () => props.initialData,
  (issue) => {
    if (!issue) {
      return;
    }

    title.value = issue.title ?? "";
    description.value = issue.description ?? "";
    status.value = String(issue.status ?? 0);
    priority.value = String(issue.priority ?? 1);
    occurredAt.value = issue.occurredAt ?? "";
    resolvedAt.value = issue.resolvedAt ?? "";
  },
  { immediate: true }
);

watch(status, (newStatus) => {
  if (newStatus !== "2") {
    resolvedAt.value = "";
  }
});

const handleImageChange = (event) => {
  imageFile.value = event.target.files[0];
};

const handleSubmit = () => {
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

  emit("submit", formData);
};
</script>

<template>
  <form @submit.prevent="handleSubmit">
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

      <select v-model="priority" required class="w-full border rounded-lg p-2">
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
        :required="status === '2'"
        class="w-full border rounded-lg p-2"
      />
    </div>

    <div class="mb-6">
      <label class="block font-semibold mb-1"> 不具合画像 </label>

      <input
        type="file"
        accept="image/*"
        class="w-full border rounded-lg p-2"
        @change="handleImageChange"
      />
    </div>

    <button
      type="submit"
      class="rounded-lg bg-blue-600 px-4 py-2 font-semibold text-white"
    >
      {{ submitLabel }}
    </button>
  </form>
</template>