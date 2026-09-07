<script setup>
import AppHeader from '@/components/common/AppHeader.vue'
import AppSidebar from '@/components/common/AppSidebar.vue'
</script>

<template>
  <div class="min-h-screen bg-gray-50">
    <div class="flex min-h-screen">

      <AppSidebar />

      <div class="flex flex-1 flex-col">
        <AppHeader />

        <main class="flex-1 p-6">
          <RouterView />
        </main>
      </div>

    </div>
  </div>
</template>