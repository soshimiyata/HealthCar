const API_BASE_URL = import.meta.env.VITE_API_BASE_URL

export async function getCurrentUser() {
  const token = localStorage.getItem('accessToken')

  const response = await fetch(`${API_BASE_URL}/api/users/me`, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`
    }
  })

  const data = await response.json()

  console.log('status:', response.status)
  console.log('response:', data)

  return data
}