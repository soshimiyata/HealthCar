<script setup>
import { ref } from 'vue'
import { login } from '@/services/authService'
import { useRouter } from 'vue-router'

const router = useRouter()

const email = ref('')
const password = ref('')

const handleLogin = async () => {
  const response = await login(email.value, password.value)

  localStorage.setItem('accessToken', response.accessToken)

  router.push('/cars')
}
</script>

<template>
  <div>
    <h1>HealthCar</h1>

    <form @submit.prevent="handleLogin">
      <div>
        <label for="email">メールアドレス</label>
        <input id="email" type="email" v-model="email" />
      </div>

      <div>
        <label for="password">パスワード</label>
        <input id="password" type="password" v-model="password" />
      </div>

      <button type="submit">ログイン</button>
    </form>
  </div>
</template>

<style scoped>
</style>