<script setup>
import { ref } from "vue";
import { useRoute, useRouter } from "vue-router";
import { createCustom } from "@/services/customService";

const route = useRoute();
const router = useRouter();

const carId = route.params.carId;

const title = ref("");
const description = ref("");
const customDate = ref("");
const cost = ref("");
const imageFile = ref(null);

const submit = async () => {
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

  const response = await createCustom(carId, formData);

  router.push(`/cars/${carId}/customs/${response.id}`);
};
</script>

<template>
  <div>
    <h1 class="text-3xl font-bold mb-6">カスタム登録</h1>

    <form @submit.prevent="submit">
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
