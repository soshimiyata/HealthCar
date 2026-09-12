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
const customDate = ref("");
const cost = ref("");
const imageFile = ref(null);

watch(
  () => props.initialData,
  (custom) => {
    if (!custom) {
      return;
    }

    title.value = custom.title ?? "";
    description.value = custom.description ?? "";
    customDate.value = custom.customDate ?? "";
    cost.value = custom.cost ?? "";
  },
  { immediate: true }
);

const handleImageChange = (event) => {
  imageFile.value = event.target.files[0];
};

const handleSubmit = () => {
  const custom = {
    title: title.value,
    description: description.value,
    customDate: customDate.value,
    cost: cost.value === "" ? null : Number(cost.value)
  };

  const formData = new FormData();

  formData.append(
    "custom",
    new Blob([JSON.stringify(custom)], { type: "application/json" })
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
      <label class="block font-semibold mb-1"> カスタム名 </label>

      <input
        v-model="title"
        type="text"
        maxlength="100"
        required
        class="w-full border rounded-lg p-2"
      />
    </div>

    <div class="mb-4">
      <label class="block font-semibold mb-1"> カスタム内容・メモ </label>

      <textarea
        v-model="description"
        class="w-full border rounded-lg p-2"
      ></textarea>
    </div>

    <div class="mb-4">
      <label class="block font-semibold mb-1"> 実施日 </label>

      <input
        v-model="customDate"
        type="date"
        required
        class="w-full border rounded-lg p-2"
      />
    </div>

    <div class="mb-6">
      <label class="block font-semibold mb-1"> 費用(円) </label>

      <input
        v-model="cost"
        type="number"
        min="0"
        class="w-full border rounded-lg p-2"
      />
    </div>

    <div class="mb-6">
      <label class="block font-semibold mb-1"> カスタム画像 </label>

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
