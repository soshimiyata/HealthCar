<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { createCar } from "@/services/carService";

const router = useRouter();

const maker = ref("");
const carModel = ref("");
const modelYear = ref("");
const description = ref("");
const odometer = ref("");
const status = ref(0);
const imageFile = ref(null)

const submit = async () => {
  const car = {
    maker: maker.value,
    carModel: carModel.value,
    modelYear: modelYear.value || null,
    description: description.value,
    odometer: odometer.value || null,
    status: status.value
  };

  const formData = new FormData();

  formData.append(
    "car",
    new Blob([JSON.stringify(car)], { type: "application/json" })
  );

  if (imageFile.value) {
    formData.append("image", imageFile.value);
  }

  const response = await createCar(formData);

  router.push(`/cars/${response.id}`);
};
</script>

<template>
  <div>
    <h1 class="text-3xl font-bold mb-6">車両登録</h1>

    <form
      @submit.prevent="submit"
      class="bg-white border rounded-lg p-6 max-w-xl"
    >
      <div class="mb-4">
        <label class="block font-semibold mb-1"> メーカー </label>

        <input
          v-model="maker"
          type="text"
          class="w-full border rounded-lg p-2"
          required
        />
      </div>

      <div class="mb-4">
        <label class="block font-semibold mb-1"> 車種 </label>

        <input
          v-model="carModel"
          type="text"
          class="w-full border rounded-lg p-2"
          required
        />
      </div>

      <div class="mb-4">
        <label class="block font-semibold mb-1"> 年式 </label>

        <input
          v-model="modelYear"
          type="number"
          class="w-full border rounded-lg p-2"
        />
      </div>

      <div class="mb-4">
        <label class="block font-semibold mb-1"> 走行距離 </label>

        <input
          v-model="odometer"
          type="number"
          class="w-full border rounded-lg p-2"
        />
      </div>

      <div class="mb-4">
        <label class="block font-semibold mb-1"> 車両メモ </label>

        <textarea
          v-model="description"
          class="w-full border rounded-lg p-2"
        ></textarea>
      </div>

      <div class="mb-4">
        <label class="block font-semibold mb-1"> 状態 </label>

        <select v-model="status" class="w-full border rounded-lg p-2">
          <option :value="0">所有中</option>
          <option :value="1">売却済み</option>
          <option :value="2">廃車</option>
        </select>
      </div>

      <div class="mb-6">
        <label class="block font-semibold mb-1"> 車両画像 </label>

        <input
          type="file"
          accept="image/*"
          class="w-full border rounded-lg p-2"
          @change="imageFile = $event.target.files[0]"
        />
      </div>

      <button type="submit" class="bg-blue-600 text-white px-4 py-2 rounded-lg">
        登録する
      </button>
    </form>
  </div>
</template>
