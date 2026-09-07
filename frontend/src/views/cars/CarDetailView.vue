<script setup>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { getCar } from '@/services/carService'

const route = useRoute()

const car = ref(null)

onMounted(async () => {
  const response = await getCar(route.params.carId)

  car.value = response
})
</script>

<template>
  <div v-if="car">

    <h1 class="text-3xl font-bold mb-6">
      {{ car.maker }} {{ car.carModel }}
    </h1>

    <div class="bg-white border rounded-lg p-6 mb-6">
      <h2 class="text-xl font-bold mb-4">
        車両情報
      </h2>

      <p>メーカー: {{ car.maker }}</p>
      <p>車種: {{ car.carModel }}</p>
      <p>年式: {{ car.modelYear }}</p>
      <p>走行距離: {{ car.odometer }} km</p>
      <p>メモ: {{ car.description }}</p>
      <p>状態: {{ car.status }}</p>
    </div>

    <div class="bg-white border rounded-lg p-6 mb-6">
      <h2 class="text-xl font-bold mb-4">
        集計
      </h2>

      <p>メンテナンス件数: {{ car.summary.maintenanceCount }}</p>
      <p>カスタム件数: {{ car.summary.customCount }}</p>
      <p>装着中パーツ: {{ car.summary.partsCount }}</p>

      <p>
        メンテナンス費用:
        {{ car.summary.maintenanceCost }} 円
      </p>

      <p>
        カスタム費用:
        {{ car.summary.customCost }} 円
      </p>

      <p>
        パーツ費用:
        {{ car.summary.partsCost }} 円
      </p>

      <p>
        総費用:
        {{ car.summary.totalCost }} 円
      </p>
    </div>

    <div class="bg-white border rounded-lg p-6 mb-6">
      <h2 class="text-xl font-bold mb-4">
        最近のメンテナンス
      </h2>

      <div
        v-for="item in car.recentMaintenances"
        :key="item.id"
      >
        {{ item.maintenanceDate }}
        {{ item.maintenanceTypeName }}
      </div>
    </div>

    <div class="bg-white border rounded-lg p-6 mb-6">
      <h2 class="text-xl font-bold mb-4">
        最近のカスタム
      </h2>

      <div
        v-for="item in car.recentCustoms"
        :key="item.id"
      >
        {{ item.customDate }}
        {{ item.title }}
      </div>
    </div>

    <div class="bg-white border rounded-lg p-6 mb-6">
      <h2 class="text-xl font-bold mb-4">
        現在装着中のパーツ
      </h2>

      <div
        v-for="item in car.currentParts"
        :key="item.id"
      >
        {{ item.category }}
        {{ item.name }}
      </div>
    </div>

    <div class="bg-white border rounded-lg p-6">
      <h2 class="text-xl font-bold mb-4">
        不具合・気になる点
      </h2>

      <div
        v-for="item in car.recentCarIssues"
        :key="item.id"
      >
        {{ item.title }}
        status: {{ item.status }}
        priority: {{ item.priority }}
      </div>
    </div>

  </div>
</template>