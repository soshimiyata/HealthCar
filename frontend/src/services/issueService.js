const API_BASE_URL = import.meta.env.VITE_API_BASE_URL

export async function getIssues(carId) {
  const token = localStorage.getItem('accessToken')

  const response = await fetch(
    `${API_BASE_URL}/api/cars/${carId}/issues`,
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

export async function getIssue(carId, issueId) {
  const token = localStorage.getItem('accessToken')

  const response = await fetch(
    `${API_BASE_URL}/api/cars/${carId}/issues/${issueId}`,
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

export async function createIssue(carId, formData) {
  const token = localStorage.getItem("accessToken");

  const response = await fetch(
    `${API_BASE_URL}/api/cars/${carId}/issues`,
    {
      method: "POST",
      headers: {
        Authorization: `Bearer ${token}`,
      },
      body: formData,
    }
  );

  const data = await response.json();

  return data;
}