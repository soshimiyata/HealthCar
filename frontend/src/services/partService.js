const API_BASE_URL = import.meta.env.VITE_API_BASE_URL;

export async function getParts(carId) {
  const token = localStorage.getItem("accessToken");

  const response = await fetch(`${API_BASE_URL}/api/cars/${carId}/parts`, {
    method: "GET",
    headers: {
      Authorization: `Bearer ${token}`
    }
  });

  const data = await response.json();

  return data;
}

export async function getPart(carId, partId) {
  const token = localStorage.getItem("accessToken");

  const response = await fetch(`${API_BASE_URL}/api/cars/${carId}/parts/${partId}`, {
    method: "GET",
    headers: {
      Authorization: `Bearer ${token}`
    }
  });

  const data = await response.json();

  return data;
}
