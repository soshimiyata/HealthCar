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

export async function getMaintenanceTypes() {
  const token = localStorage.getItem('accessToken')

  const response = await fetch(
    `${API_BASE_URL}/api/maintenance-types`,
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

export async function createMaintenance(carId, maintenance) {
  const token = localStorage.getItem('accessToken')

  const response = await fetch(
    `${API_BASE_URL}/api/cars/${carId}/maintenances`,
    {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
      },
      body: JSON.stringify(maintenance)
    }
  )

  const data = await response.json()

  return data
}