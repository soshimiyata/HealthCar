# POST /api/cars/{carId}/maintenances

## 概要

指定した車両にメンテナンス履歴を登録する。

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

| 項目              | 型       | 必須 | 説明          |
| --------------- | ------- | -- | ----------- |
| maintenanceTypeId | Long    | ○  | メンテナンス種別ID  |
| description     | String  | ×  | 作業内容・メモ     |
| maintenanceDate | Date    | ○  | 実施日         |
| odometer        | Integer | ×  | 実施時走行距離(km) |
| cost            | Integer | ×  | 費用(円)       |

#### リクエスト例

```json
{
  "maintenanceTypeId": 1,
  "description": "エンジンオイル・オイルフィルター交換",
  "maintenanceDate": "2026-08-05",
  "odometer": 82500,
  "cost": 7800
}
```

---

## レスポンス

### 201 Created

| 項目                | 型    | 説明         |
| ----------------- | ---- | ---------- |
| id                | Long | メンテナンスID   |
| maintenanceTypeId | Long | メンテナンス種別ID |

#### レスポンス例

```json
{
  "id": 12,
  "maintenanceTypeId": 1
}
```
---

## ステータスコード

| コード | 説明       |
| --- | -------- |
| 201 | 登録成功     |
| 400 | 入力値エラー   |
| 401 | 未認証      |
| 404 | 車両またはメンテナンス種別が存在しない |

---

## バリデーション

| 項目              | 条件         |
| --------------- | ---------- |
| title           | 必須・100文字以内 |
| maintenanceTypeId | 必須  |
| maintenanceDate | 必須         |
| odometer        | 0以上        |
| cost            | 0以上        |

---

# GET /api/cars/{carId}/maintenances

## 概要

指定した車両のメンテナンス履歴一覧を取得する。

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

| 項目   | 型       | 必須 | 説明                            |
| ---- | ------- | -- | ----------------------------- |
| page | Integer | ×  | ページ番号（0始まり、デフォルト0）            |
| size | Integer | ×  | 取得件数（デフォルト20）                 |
| sort | String  | ×  | ソート条件（例：maintenanceDate,desc） |

#### リクエスト例

```text
GET /api/cars/1/maintenances?page=0&size=20&sort=maintenanceDate,desc
```

---

## レスポンス

### 200 OK

| 項目            | 型       | 説明       |
| ------------- | ------- | -------- |
| content       | Array   | メンテナンス一覧 |
| page          | Integer | 現在のページ番号 |
| size          | Integer | 取得件数     |
| totalElements | Long    | 総件数      |
| totalPages    | Integer | 総ページ数    |

#### レスポンス例

```json
{
  "content": [
    {
      "id": 12,
      "maintenanceTypeId": 1,
      "maintenanceTypeName": "オイル交換",
      "maintenanceDate": "2026-08-05",
      "odometer": 82500,
      "cost": 7800
    },
    {
      "id": 11,
      "maintenanceTypeId": 2,
      "maintenanceTypeName": "タイヤローテーション",
      "maintenanceDate": "2026-06-15",
      "odometer": 80000,
      "cost": 3000
    }
  ],
  "page": 0,
  "size": 20,
  "totalElements": 12,
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

# GET /api/maintenances/{id}

## 概要

指定したメンテナンス履歴の詳細情報を取得する。

**認証：必要**

**認可：認証ユーザーが所有するメンテナンスのみ取得可能。**

---

## リクエスト

### リクエストヘッダー

| 項目            | 値            | 必須 | 説明          |
| ------------- | ------------ | -- | ----------- |
| Authorization | Bearer {JWT} | ○  | JWTアクセストークン |

---

### パスパラメータ

| 項目 | 型    | 必須 | 説明       |
| -- | ---- | -- | -------- |
| id | Long | ○  | メンテナンスID |

---

## レスポンス

### 200 OK

| 項目                  | 型       | 説明          |
| ------------------- | ------- | ----------- |
| id                  | Long    | メンテナンスID    |
| maintenanceTypeId   | Long    | メンテナンス種別ID  |
| maintenanceTypeName | String  | メンテナンス種別名   |
| description         | String  | 作業内容・メモ     |
| maintenanceDate     | Date    | 実施日         |
| odometer            | Integer | 実施時走行距離(km) |
| cost                | Integer | 費用(円)       |

#### レスポンス例

```json
{
  "id": 12,
  "maintenanceTypeId": 1,
  "maintenanceTypeName": "オイル交換",
  "description": "エンジンオイル・オイルフィルター交換",
  "maintenanceDate": "2026-08-05",
  "odometer": 82500,
  "cost": 7800
}
```

---

## ステータスコード

| コード | 説明           |
| --- | ------------ |
| 200 | 取得成功         |
| 401 | 未認証          |
| 404 | メンテナンスが存在しない |

---

# PUT /api/maintenances/{id}

## 概要

指定したメンテナンス履歴を更新する。

**認証：必要**

**認可：認証ユーザーが所有するメンテナンスのみ更新可能。**

---

## リクエスト

### リクエストヘッダー

| 項目            | 値                | 必須 | 説明          |
| ------------- | ---------------- | -- | ----------- |
| Content-Type  | application/json | ○  | JSON形式で送信   |
| Authorization | Bearer {JWT}     | ○  | JWTアクセストークン |

---

### パスパラメータ

| 項目 | 型    | 必須 | 説明       |
| -- | ---- | -- | -------- |
| id | Long | ○  | メンテナンスID |

---

### リクエストボディ

| 項目                | 型       | 必須 | 説明          |
| ----------------- | ------- | -- | ----------- |
| maintenanceTypeId | Long    | ○  | メンテナンス種別ID  |
| description       | String  | ×  | 作業内容・メモ     |
| maintenanceDate   | Date    | ○  | 実施日         |
| odometer          | Integer | ×  | 実施時走行距離(km) |
| cost              | Integer | ×  | 費用(円)       |

#### リクエスト例

```json
{
  "maintenanceTypeId": 1,
  "description": "オイル・エレメント交換",
  "maintenanceDate": "2026-08-05",
  "odometer": 82600,
  "cost": 8500
}
```

---

## レスポンス

### 200 OK

| 項目                  | 型      | 説明         |
| ------------------- | ------ | ---------- |
| id                  | Long   | メンテナンスID   |
| maintenanceTypeId   | Long   | メンテナンス種別ID |
| maintenanceTypeName | String | メンテナンス種別名  |
| maintenanceDate     | Date   | 実施日        |

#### レスポンス例

```json
{
  "id": 12,
  "maintenanceTypeId": 1,
  "maintenanceTypeName": "オイル交換",
  "maintenanceDate": "2026-08-05"
}
```

---

## ステータスコード

| コード | 説明           |
| --- | ------------ |
| 200 | 更新成功         |
| 400 | 入力値エラー       |
| 401 | 未認証          |
| 404 | メンテナンスが存在しない |

---

## バリデーション

| 項目                | 条件              |
| ----------------- | --------------- |
| maintenanceTypeId | 必須・存在するメンテナンス種別 |
| maintenanceDate   | 必須              |
| odometer          | 0以上             |
| cost              | 0以上             |

---

# DELETE /api/maintenances/{id}

## 概要

指定したメンテナンス履歴を削除する。

**認証：必要**

**認可：認証ユーザーが所有するメンテナンスのみ削除可能。**

---

## リクエスト

### リクエストヘッダー

| 項目            | 値            | 必須 | 説明          |
| ------------- | ------------ | -- | ----------- |
| Authorization | Bearer {JWT} | ○  | JWTアクセストークン |

---

### パスパラメータ

| 項目 | 型    | 必須 | 説明       |
| -- | ---- | -- | -------- |
| id | Long | ○  | メンテナンスID |

---

## リクエストボディ

なし

---

## レスポンス

### 204 No Content

レスポンスボディなし

---

## ステータスコード

| コード | 説明           |
| --- | ------------ |
| 204 | 削除成功         |
| 401 | 未認証          |
| 404 | メンテナンスが存在しない |