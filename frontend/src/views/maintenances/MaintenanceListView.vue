<script setup>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { getMaintenances } from '@/services/maintenanceService'

const route = useRoute()

const maintenances = ref([])

onMounted(async () => {
  const response = await getMaintenances(route.params.carId)

  maintenances.value = response.content
})
</script>

<template>
  <div>
    <h1 class="text-3xl font-bold mb-6">
      メンテナンス
    </h1>

    <RouterLink
      v-for="maintenance in maintenances"
      :key="maintenance.id"
      :to="`/cars/${route.params.carId}/maintenances/${maintenance.id}`"
      class="block bg-white border rounded-lg p-4 mb-4"
    >
      <h2 class="text-xl font-bold">
        {{ maintenance.maintenanceTypeName }}
      </h2>

      <p>
        実施日: {{ maintenance.maintenanceDate }}
      </p>

      <p>
        走行距離: {{ maintenance.odometer }} km
      </p>

      <p>
        費用: {{ maintenance.cost }} 円
      </p>
    </RouterLink>
  </div>
</template>