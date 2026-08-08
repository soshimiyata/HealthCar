| メソッド   | URL                       | 概要      |
| ------ | ------------------------- | ------- |
| POST   | `/api/cars/{carId}/parts` | パーツ登録   |
| GET    | `/api/cars/{carId}/parts` | パーツ一覧取得 |
| GET    | `/api/parts/{id}`         | パーツ詳細取得 |
| PUT    | `/api/parts/{id}`         | パーツ更新   |
| DELETE | `/api/parts/{id}`         | パーツ削除   |

---

## POST /api/cars/{carId}/parts

### 概要

指定した車両にパーツを登録する。

**認証：必要**

**認可：認証ユーザーが所有する車両のみ登録可能。**

---

### リクエストヘッダー

| 項目            | 値                | 必須 | 説明          |
| ------------- | ---------------- | -- | ----------- |
| Content-Type  | application/json | ○  | JSON形式で送信   |
| Authorization | Bearer {JWT}     | ○  | JWTアクセストークン |

---

### パスパラメータ

| 項目    | 型    | 必須 | 説明   |
| ----- | ---- | -- | ---- |
| carId | Long | ○  | 車両ID |

---

### リクエストボディ

| 項目          | 型       | 必須 | 説明      |
| ----------- | ------- | -- | ------- |
| category    | String  | ○  | パーツカテゴリ |
| name        | String  | ○  | パーツ名    |
| maker       | String  | ×  | メーカー名   |
| installedAt | Date    | ×  | 装着日     |
| price       | Integer | ×  | 購入価格    |
| description | String  | ×  | メモ      |
| status      | Integer | ○  | 装着状態    |

#### リクエスト例

```json
{
  "category": "ホイール",
  "name": "OZ Racing Volcano",
  "maker": "OZ Racing",
  "installedAt": "2026-08-01",
  "price": 80000,
  "description": "17インチ 8J +35",
  "status": 0
}
```

---

### レスポンス

#### 201 Created

```json
{
  "id": 1,
  "name": "OZ Racing Volcano"
}
```

---

### ステータスコード

| コード | 説明       |
| --- | -------- |
| 201 | 登録成功     |
| 400 | 入力値エラー   |
| 401 | 未認証      |
| 404 | 車両が存在しない |

---

### バリデーション

| 項目       | 条件         |
| -------- | ---------- |
| category | 必須・50文字以内  |
| name     | 必須・100文字以内 |
| maker    | 100文字以内    |
| price    | 0以上        |

---

## GET /api/cars/{carId}/parts

クエリパラメータ

* page
* size
* sort

| 項目       | 型       | 必須 | 説明        |
| -------- | ------- | -- | --------- |
| status   | Integer | ×  | 装着状態で絞り込み |
| category | String  | ×  | カテゴリで絞り込み |

例

```text
GET /api/cars/1/parts?page=0&size=20&status=0
```

---

## GET /api/parts/{id}

詳細取得。

レスポンス例

```json
{
  "id": 1,
  "category": "ホイール",
  "name": "OZ Racing Volcano",
  "maker": "OZ Racing",
  "installedAt": "2026-08-01",
  "price": 80000,
  "description": "17インチ 8J +35",
  "status": 0
}
```

---

## PUT /api/parts/{id}

レスポンス

```json
{
  "id": 1,
  "name": "OZ Racing Volcano"
}
```

---

## DELETE /api/parts/{id}

削除。

レスポンス

```
204 No Content
```

---