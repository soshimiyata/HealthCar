<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from 'vue-router';
import { getCars } from "@/services/carService";

const router = useRouter()
const cars = ref([]);

onMounted(async () => {
  const response = await getCars();

  cars.value = response.content;
});
</script>

<template>
  <div>
    <h1 class="text-3xl font-bold mb-6">車両一覧</h1>

    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
      <div
        v-for="car in cars"
        :key="car.id"
        class="bg-white border rounded-lg p-4 cursor-pointer"
        @click="router.push(`/cars/${car.id}`)"
      >
        <div class="mb-4">
          <img
            v-if="car.imageUrl"
            :src="car.imageUrl"
            :alt="`${car.maker} ${car.carModel}`"
            class="w-full h-40 object-cover rounded-lg"
          />

          <div
            v-else
            class="w-full h-40 bg-gray-200 rounded-lg flex items-center justify-center"
          >
            🚗
          </div>
        </div>

        <h2 class="text-xl font-bold">
          {{ car.maker }}
        </h2>

        <p class="text-lg">
          {{ car.carModel }}
        </p>

        <p class="text-sm text-gray-500 mt-2">車両ID: {{ car.id }}</p>
      </div>
    </div>
  </div>
</template>
