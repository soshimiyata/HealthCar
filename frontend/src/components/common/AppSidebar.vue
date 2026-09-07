<template>
  <aside class="w-64 min-h-screen bg-slate-800 text-white">
    <div class="p-6">
      <h1 class="text-2xl font-bold">
        🚗 HealthCar
      </h1>
    </div>

    <nav>
      <ul>
        <li>
          <RouterLink
            to="/"
            class="block px-6 py-3 hover:bg-slate-700"
          >
            🏠 ダッシュボード
          </RouterLink>
        </li>

        <li>
          <RouterLink
            to="/cars"
            class="block px-6 py-3 hover:bg-slate-700"
          >
            🚗 車両一覧
          </RouterLink>
        </li>

        <li>
          <RouterLink
            to="/maintenances"
            class="block px-6 py-3 hover:bg-slate-700"
          >
            🔧 メンテナンス
          </RouterLink>
        </li>

        <li>
          <RouterLink
            to="/customs"
            class="block px-6 py-3 hover:bg-slate-700"
          >
            🛠️ カスタム履歴
          </RouterLink>
        </li>

        <li>
          <RouterLink
            to="/parts"
            class="block px-6 py-3 hover:bg-slate-700"
          >
            🔩 パーツ管理
          </RouterLink>
        </li>

        <li>
          <RouterLink
            to="/issues"
            class="block px-6 py-3 hover:bg-slate-700"
          >
            ⚠️ 車の状態管理
          </RouterLink>
        </li>
      </ul>
    </nav>
  </aside>
</template>