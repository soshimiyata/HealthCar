<script setup>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import {
  getMaintenanceTypes,
  createMaintenance
} from "@/services/maintenanceService";

const route = useRoute();
const router = useRouter();

const maintenanceTypes = ref([]);

const maintenanceTypeId = ref("");
const description = ref("");
const maintenanceDate = ref("");
const odometer = ref("");
const cost = ref("");

onMounted(async () => {
  maintenanceTypes.value = await getMaintenanceTypes();
});

const submit = async () => {
  const maintenance = {
    maintenanceTypeId: Number(maintenanceTypeId.value),
    description: description.value,
    maintenanceDate: maintenanceDate.value,
    odometer: odometer.value || null,
    cost: cost.value || null
  };

  const response = await createMaintenance(route.params.carId, maintenance);

  router.push(`/cars/${route.params.carId}/maintenances/${response.id}`);
};
</script>

<template>
  <div>
    <h1 class="text-3xl font-bold mb-6">メンテナンス登録</h1>

    <form @submit.prevent="submit">
      <div class="mb-4">
        <label class="block font-semibold mb-1"> メンテナンス種別 </label>

        <select
          v-model="maintenanceTypeId"
          required
          class="w-full border rounded-lg p-2"
        >
          <option value="" disabled>選択してください</option>

          <option
            v-for="type in maintenanceTypes"
            :key="type.typeId"
            :value="type.typeId"
          >
            {{ type.typeName }}
          </option>
        </select>
      </div>

      <div class="mb-4">
        <label class="block font-semibold mb-1"> 作業内容・メモ </label>

        <textarea
          v-model="description"
          class="w-full border rounded-lg p-2"
        ></textarea>
      </div>

      <div class="mb-4">
        <label class="block font-semibold mb-1"> 実施日 </label>

        <input
          v-model="maintenanceDate"
          type="date"
          required
          class="w-full border rounded-lg p-2"
        />
      </div>

      <div class="mb-4">
        <label class="block font-semibold mb-1"> 実施時走行距離(km) </label>

        <input
          v-model="odometer"
          type="number"
          min="0"
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

      <button
        type="submit"
        class="rounded-lg bg-blue-600 px-4 py-2 font-semibold text-white"
      >
        登録
      </button>
    </form>
  </div>
</template>
