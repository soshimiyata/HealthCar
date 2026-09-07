<script setup>
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";
import { getMaintenance } from "@/services/maintenanceService";

const route = useRoute();

const maintenance = ref(null);

onMounted(async () => {
  maintenance.value = await getMaintenance(route.params.carId, route.params.id);
  console.log("maintenance:", maintenance.value);
});
</script>

<template>
  <div>
    <h1 class="text-3xl font-bold mb-6">メンテナンス詳細</h1>

    <!-- 現在のメンテナンス -->
    <div v-if="maintenance" class="bg-white border rounded-lg p-6">
      <h2 class="text-xl font-bold mb-4">
        {{ maintenance.maintenanceTypeName }}
      </h2>

      <p>メンテナンスID: {{ maintenance.id }}</p>

      <p>メンテナンス種別ID: {{ maintenance.maintenanceTypeId }}</p>

      <p>実施日: {{ maintenance.maintenanceDate }}</p>

      <p>走行距離: {{ maintenance.odometer }} km</p>

      <p>費用: {{ maintenance.cost }} 円</p>

      <p>作業内容: {{ maintenance.description }}</p>
    </div>

    <!-- 最近の履歴 -->
    <h2 class="text-2xl font-bold mt-8 mb-4">メンテナンス履歴</h2>

    <div
      v-for="item in maintenance?.recentMaintenances"
      :key="item.id"
      class="bg-white border rounded-lg p-4 mb-3"
    >
      <h3 class="font-bold">
        {{ item.maintenanceTypeName }}
      </h3>

      <p>実施日: {{ item.maintenanceDate }}</p>

      <p>走行距離: {{ item.odometer }} km</p>

      <p>費用: {{ item.cost }} 円</p>
    </div>
  </div>
</template>
