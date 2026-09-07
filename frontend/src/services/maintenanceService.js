const API_BASE_URL = import.meta.env.VITE_API_BASE_URL

export async function getMaintenances(carId) {
  const token = localStorage.getItem('accessToken')

  const response = await fetch(
    `${API_BASE_URL}/api/cars/${carId}/maintenances`,
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

export async function getMaintenance(carId, maintenanceId) {
  const token = localStorage.getItem('accessToken')

  const response = await fetch(
    `${API_BASE_URL}/api/cars/${carId}/maintenances/${maintenanceId}`,
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