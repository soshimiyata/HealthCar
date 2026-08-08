| メソッド   | URL                       | 概要      |
| ------ | ------------------------- | ------- |
| POST   | `/api/cars/{carId}/parts` | パーツ登録   |
| GET    | `/api/cars/{carId}/parts` | パーツ一覧取得 |
| GET    | `/api/cars/{carId}/parts/{id}`         | パーツ詳細取得 |
| PUT    | `/api/cars/{carId}/parts/{id}`         | パーツ更新   |
| DELETE | `/api/cars/{carId}/parts/{id}`         | パーツ削除   |

---

# POST /api/cars/{carId}/parts

## 概要

指定した車両にパーツを登録する。

**認証：必要**

**認可：認証ユーザーが所有する車両のみ登録可能。**

## リクエスト

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
| category    | Integer  | ○  | パーツカテゴリ |
| name        | String  | ○  | パーツ名    |
| maker       | String  | ×  | メーカー名   |
| installedAt | Date    | ×  | 装着日     |
| price       | Integer | ×  | 購入価格    |
| description | String  | ×  | メモ      |
| status      | Integer | ○  | 装着状態    |

#### リクエスト例

```json
{
  "category": 0,
  "name": "OZ Racing Volcano",
  "maker": "OZ Racing",
  "installedAt": "2026-08-01",
  "price": 80000,
  "description": "17インチ 8J +35",
  "status": 0
}
```

---

## レスポンス

### 201 Created

| 項目   | 型      | 説明    |
| ---- | ------ | ----- |
| id   | Long   | パーツID |
| name | String | パーツ名  |

#### レスポンス例

```json
{
  "id": 1,
  "name": "OZ Racing Volcano"
}
```

---

## ステータスコード

| コード | 説明       |
| --- | -------- |
| 201 | 登録成功     |
| 400 | 入力値エラー   |
| 401 | 未認証      |
| 404 | 車両が存在しない |

---

## バリデーション

| 項目       | 条件             |
| -------- | -------------- |
| category | 必須・定義されたカテゴリのみ |
| name     | 必須・100文字以内     |
| maker    | 100文字以内        |
| price    | 0以上            |
| status   | 必須・定義されたステータスのみ |

---

# GET /api/cars/{carId}/parts

## 概要

指定した車両のパーツ一覧を取得する。

**認証：必要**

**認可：認証ユーザーが所有する車両のみ取得可能。**

## リクエスト

### リクエストヘッダー

| 項目            | 値            | 必須 | 説明          |
| ------------- | ------------ | -- | ----------- |
| Authorization | Bearer {JWT} | ○  | JWTアクセストークン |

---

### パスパラメータ

| 項目    | 型    | 必須 | 説明   |
| ----- | ---- | -- | ---- |
| carId | Long | ○  | 車両ID |

---

### クエリパラメータ

| 項目       | 型       | 必須 | 説明                        |
| -------- | ------- | -- | ------------------------- |
| page     | Integer | ×  | ページ番号（0始まり、デフォルト0）        |
| size     | Integer | ×  | 取得件数（デフォルト20）             |
| sort     | String  | ×  | ソート条件（例：installedAt,desc） |
| status   | Integer | ×  | 装着状態で絞り込み                 |
| category | Integer  | ×  | パーツカテゴリで絞り込み              |

#### リクエスト例

```text
GET /api/cars/1/parts?page=0&size=20&sort=installedAt,desc&status=0&category=WHEEL
```

---

## レスポンス

### 200 OK

| 項目            | 型       | 説明       |
| ------------- | ------- | -------- |
| content       | Array   | パーツ一覧    |
| page          | Integer | 現在のページ番号 |
| size          | Integer | 取得件数     |
| totalElements | Long    | 総件数      |
| totalPages    | Integer | 総ページ数    |

#### レスポンス例

```json
{
  "content": [
    {
      "id": 1,
      "category": 0,
      "name": "OZ Racing Volcano",
      "maker": "OZ Racing",
      "installedAt": "2026-08-01",
      "price": 80000,
      "description": "17インチ 8J +35",
      "status": 0
    },
    {
      "id": 2,
      "category": 2,
      "name": "HKS Hi-Power",
      "maker": "HKS",
      "installedAt": "2026-07-15",
      "price": 120000,
      "description": null,
      "status": 0
    }
  ],
  "page": 0,
  "size": 20,
  "totalElements": 2,
  "totalPages": 1
}
```

---

## ステータスコード

| コード | 説明       |
| --- | -------- |
| 200 | 取得成功     |
| 401 | 未認証      |
| 404 | 車両が存在しない |

---

# GET /api/cars/{carId}/parts/{id}

## 概要

指定した車両のパーツ詳細情報を取得する。

**認証：必要**

**認可：認証ユーザーが所有する車両のパーツのみ取得可能。**

## リクエスト

### リクエストヘッダー

| 項目            | 値            | 必須 | 説明          |
| ------------- | ------------ | -- | ----------- |
| Authorization | Bearer {JWT} | ○  | JWTアクセストークン |

---

### パスパラメータ

| 項目    | 型    | 必須 | 説明    |
| ----- | ---- | -- | ----- |
| carId | Long | ○  | 車両ID  |
| id    | Long | ○  | パーツID |

---

## レスポンス

### 200 OK

| 項目          | 型       | 説明      |
| ----------- | ------- | ------- |
| id          | Long    | パーツID   |
| category    | Integer  | パーツカテゴリ |
| name        | String  | パーツ名    |
| maker       | String  | メーカー名   |
| installedAt | Date    | 装着日     |
| price       | Integer | 購入価格    |
| description | String  | メモ      |
| status      | Integer | 装着状態    |

#### レスポンス例

```json
{
  "id": 1,
  "category": 0,
  "name": "OZ Racing Volcano",
  "maker": "OZ Racing",
  "installedAt": "2026-08-01",
  "price": 80000,
  "description": "17インチ 8J +35",
  "status": 0
}
```

---

## ステータスコード

| コード | 説明             |
| --- | -------------- |
| 200 | 取得成功           |
| 401 | 未認証            |
| 404 | 車両またはパーツが存在しない |

---

# PUT /api/cars/{carId}/parts/{id}

## 概要

指定した車両のパーツ情報を更新する。

**認証：必要**

**認可：認証ユーザーが所有する車両のパーツのみ更新可能。**

## リクエスト

### リクエストヘッダー

| 項目            | 値                | 必須 | 説明          |
| ------------- | ---------------- | -- | ----------- |
| Content-Type  | application/json | ○  | JSON形式で送信   |
| Authorization | Bearer {JWT}     | ○  | JWTアクセストークン |

---

### パスパラメータ

| 項目    | 型    | 必須 | 説明    |
| ----- | ---- | -- | ----- |
| carId | Long | ○  | 車両ID  |
| id    | Long | ○  | パーツID |

---

### リクエストボディ

| 項目          | 型       | 必須 | 説明      |
| ----------- | ------- | -- | ------- |
| category    | Integer  | ○  | パーツカテゴリ |
| name        | String  | ○  | パーツ名    |
| maker       | String  | ×  | メーカー名   |
| installedAt | Date    | ×  | 装着日     |
| price       | Integer | ×  | 購入価格    |
| description | String  | ×  | メモ      |
| status      | Integer | ○  | 装着状態    |

#### リクエスト例

```json
{
  "category": 0,
  "name": "OZ Racing Volcano",
  "maker": "OZ Racing",
  "installedAt": "2026-08-01",
  "price": 85000,
  "description": "17インチ 8J +35、タイヤ交換済み",
  "status": 0
}
```

---

## レスポンス

### 200 OK

| 項目   | 型      | 説明    |
| ---- | ------ | ----- |
| id   | Long   | パーツID |
| name | String | パーツ名  |

#### レスポンス例

```json
{
  "id": 1,
  "name": "OZ Racing Volcano"
}
```

---

## ステータスコード

| コード | 説明             |
| --- | -------------- |
| 200 | 更新成功           |
| 400 | 入力値エラー         |
| 401 | 未認証            |
| 404 | 車両またはパーツが存在しない |

---

## バリデーション

| 項目       | 条件             |
| -------- | -------------- |
| category | 必須・定義されたカテゴリのみ |
| name     | 必須・100文字以内     |
| maker    | 100文字以内        |
| price    | 0以上            |

---

# DELETE /api/cars/{carId}/parts/{id}

## 概要

指定した車両のパーツを削除する。

**認証：必要**

**認可：認証ユーザーが所有する車両のパーツのみ削除可能。**

## リクエスト

### リクエストヘッダー

| 項目            | 値            | 必須 | 説明          |
| ------------- | ------------ | -- | ----------- |
| Authorization | Bearer {JWT} | ○  | JWTアクセストークン |

---

### パスパラメータ

| 項目    | 型    | 必須 | 説明    |
| ----- | ---- | -- | ----- |
| carId | Long | ○  | 車両ID  |
| id    | Long | ○  | パーツID |

---

### リクエストボディ

なし

---

## レスポンス

### 204 No Content

レスポンスボディなし

---

## ステータスコード

| コード | 説明             |
| --- | -------------- |
| 204 | 削除成功           |
| 401 | 未認証            |
| 404 | 車両またはパーツが存在しない |