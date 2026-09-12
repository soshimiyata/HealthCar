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

const maker = ref("");
const carModel = ref("");
const modelYear = ref("");
const description = ref("");
const odometer = ref("");
const status = ref("0");
const imageFile = ref(null);

watch(
  () => props.initialData,
  (car) => {
    if (!car) {
      return;
    }

    maker.value = car.maker ?? "";
    carModel.value = car.carModel ?? "";
    modelYear.value = car.modelYear ?? "";
    description.value = car.description ?? "";
    odometer.value = car.odometer ?? "";
    status.value = String(car.status ?? 0);
  },
  { immediate: true }
);

const handleImageChange = (event) => {
  imageFile.value = event.target.files[0];
};

const handleSubmit = () => {
  const car = {
    maker: maker.value,
    carModel: carModel.value,
    modelYear: modelYear.value || null,
    description: description.value,
    odometer: odometer.value || null,
    status: Number(status.value)
  };

  const formData = new FormData();

  formData.append(
    "car",
    new Blob([JSON.stringify(car)], { type: "application/json" })
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
      <label class="block font-semibold mb-1"> メーカー </label>

      <input
        v-model="maker"
        type="text"
        maxlength="50"
        required
        class="w-full border rounded-lg p-2"
      />
    </div>

    <div class="mb-4">
      <label class="block font-semibold mb-1"> 車種 </label>

      <input
        v-model="carModel"
        type="text"
        maxlength="100"
        required
        class="w-full border rounded-lg p-2"
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
      <label class="block font-semibold mb-1"> 走行距離(km) </label>

      <input
        v-model="odometer"
        type="number"
        min="0"
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

      <select v-model="status" required class="w-full border rounded-lg p-2">
        <option value="0">所有中</option>
        <option value="1">売却済み</option>
        <option value="2">廃車</option>
      </select>
    </div>

    <div class="mb-6">
      <label class="block font-semibold mb-1"> 車両画像 </label>

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
