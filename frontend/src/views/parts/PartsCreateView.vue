<script setup>
import { ref } from "vue";
import { useRoute, useRouter } from "vue-router";
import { createPart } from "@/services/partService";

const route = useRoute();
const router = useRouter();

const carId = route.params.carId;

const category = ref("");
const name = ref("");
const maker = ref("");
const installedAt = ref("");
const price = ref("");
const description = ref("");
const status = ref("0");
const imageFile = ref(null);

const submit = async () => {
  const part = {
    category: Number(category.value),
    name: name.value,
    maker: maker.value,
    installedAt: installedAt.value || null,
    price: price.value === "" ? null : Number(price.value),
    description: description.value,
    status: Number(status.value)
  };

  const formData = new FormData();

  formData.append(
    "part",
    new Blob([JSON.stringify(part)], { type: "application/json" })
  );

  if (imageFile.value) {
    formData.append("image", imageFile.value);
  }

  const response = await createPart(carId, formData);

  router.push(`/cars/${carId}/parts/${response.id}`);
};
</script>

<template>
  <div>
    <h1 class="text-3xl font-bold mb-6">パーツ登録</h1>

    <form @submit.prevent="submit">
      <!-- カテゴリ -->
      <div class="mb-4">
        <label class="block font-semibold mb-1"> パーツカテゴリ </label>

        <select
          v-model="category"
          required
          class="w-full border rounded-lg p-2"
        >
          <option value="">選択してください</option>
          <option value="0">ホイール</option>
          <option value="1">タイヤ</option>
          <option value="2">マフラー</option>
          <option value="3">サスペンション</option>
          <option value="4">ブレーキ</option>
          <option value="5">エンジン</option>
          <option value="6">電装</option>
          <option value="7">エアロ</option>
          <option value="8">内装</option>
          <option value="9">その他</option>
        </select>
      </div>

      <!-- パーツ名 -->
      <div class="mb-4">
        <label class="block font-semibold mb-1"> パーツ名 </label>

        <input
          v-model="name"
          type="text"
          maxlength="100"
          required
          class="w-full border rounded-lg p-2"
        />
      </div>

      <!-- メーカー -->
      <div class="mb-4">
        <label class="block font-semibold mb-1"> メーカー </label>

        <input
          v-model="maker"
          type="text"
          maxlength="100"
          class="w-full border rounded-lg p-2"
        />
      </div>

      <!-- 装着日 -->
      <div class="mb-4">
        <label class="block font-semibold mb-1"> 装着日 </label>

        <input
          v-model="installedAt"
          type="date"
          class="w-full border rounded-lg p-2"
        />
      </div>

      <!-- 購入価格 -->
      <div class="mb-4">
        <label class="block font-semibold mb-1"> 購入価格(円) </label>

        <input
          v-model="price"
          type="number"
          min="0"
          class="w-full border rounded-lg p-2"
        />
      </div>

      <!-- メモ -->
      <div class="mb-4">
        <label class="block font-semibold mb-1"> メモ </label>

        <textarea
          v-model="description"
          class="w-full border rounded-lg p-2"
        ></textarea>
      </div>

      <!-- 画像 -->
      <div class="mb-4">
        <label class="block font-semibold mb-1"> パーツ画像 </label>

        <input
          type="file"
          accept="image/*"
          class="w-full border rounded-lg p-2"
          @change="imageFile = $event.target.files[0]"
        />
      </div>

      <!-- 装着状態 -->
      <div class="mb-6">
        <label class="block font-semibold mb-1"> 装着状態 </label>

        <select v-model="status" required class="w-full border rounded-lg p-2">
          <option value="0">装着中</option>
          <option value="1">保管中</option>
          <option value="2">売却済み</option>
          <option value="3">購入予定</option>
          <option value="4">廃棄</option>
        </select>
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
