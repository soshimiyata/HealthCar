const API_BASE_URL = import.meta.env.VITE_API_BASE_URL

export async function getCars() {
  const token = localStorage.getItem('accessToken')

  const response = await fetch(`${API_BASE_URL}/api/cars`, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`
    }
  })

  const data = await response.json()

  return data
}

export async function getCar(carId) {
  const token = localStorage.getItem('accessToken')

  const response = await fetch(
    `${API_BASE_URL}/api/cars/${carId}`,
    {
      method: 'GET',
      headers: {
        'Authorization': `Bearer ${token}`
      }
    }
  )

  const data = await response.json()

  return data
}