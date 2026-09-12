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

const category = ref("");
const name = ref("");
const maker = ref("");
const installedAt = ref("");
const price = ref("");
const description = ref("");
const status = ref("0");
const imageFile = ref(null);

watch(
  () => props.initialData,
  (part) => {
    if (!part) {
      return;
    }

    category.value = String(part.category ?? "");
    name.value = part.name ?? "";
    maker.value = part.maker ?? "";
    installedAt.value = part.installedAt ?? "";
    price.value = part.price ?? "";
    description.value = part.description ?? "";
    status.value = String(part.status ?? 0);
  },
  { immediate: true }
);

const handleImageChange = (event) => {
  imageFile.value = event.target.files[0];
};

const handleSubmit = () => {
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

  emit("submit", formData);
};
</script>

<template>
  <form @submit.prevent="handleSubmit">
    <!-- カテゴリ -->
    <div class="mb-4">
      <label class="block font-semibold mb-1"> パーツカテゴリ </label>

      <select v-model="category" required class="w-full border rounded-lg p-2">
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
        @change="handleImageChange"
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
      {{ submitLabel }}
    </button>
  </form>
</template>