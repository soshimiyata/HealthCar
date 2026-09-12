const API_BASE_URL = import.meta.env.VITE_API_BASE_URL;

export async function getCustoms(carId) {
  const token = localStorage.getItem("accessToken");

  const response = await fetch(`${API_BASE_URL}/api/cars/${carId}/customs`, {
    method: "GET",
    headers: { Authorization: `Bearer ${token}` },
  });

  const data = await response.json();

  return data;
}

export async function getCustom(carId, customId) {
  const token = localStorage.getItem("accessToken");

  const response = await fetch(`${API_BASE_URL}/api/cars/${carId}/customs/${customId}`, {
    method: "GET",
    headers: { Authorization: `Bearer ${token}` },
  });

  const data = await response.json();

  return data;
}

export async function createCustom(carId, formData) {
  const token = localStorage.getItem("accessToken");

  const response = await fetch(
    `${API_BASE_URL}/api/cars/${carId}/customs`,
    {
      method: "POST",
      headers: {
        Authorization: `Bearer ${token}`
      },
      body: formData
    }
  );
  const data = await response.json();

  return data;
}