# POST /api/cars/{carId}/issues

## 概要

指定した車両に不具合や気になる点を登録する。

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

| 項目 | 型 | 必須 | 説明 |
|------|----|------|------|
| title | String | ○ | 不具合名 |
| description | String | × | 詳細・症状・メモ |
| status | Integer | × | 対応状況（0:未着手、1:対応中、2:解決済み） |
| priority | Integer | × | 優先度（0:高、1:中、2:低） |
| occurredAt | Date | × | 発生日・気付いた日 |
| resolvedAt | Date | × | 解決日 |

#### リクエスト例

```json
{
  "title": "異音",
  "description": "低速走行時にフロントから異音がする",
  "status": 0,
  "priority": 0,
  "occurredAt": "2026-08-08"
}
```

---

## レスポンス

### 201 Created

| 項目         | 型       | 説明        |
| ---------- | ------- | --------- |
| id         | Long    | 不具合ID     |
| title      | String  | 不具合名      |
| status     | Integer | 対応状況      |
| priority   | Integer | 優先度       |
| occurredAt | Date    | 発生日・気付いた日 |
| resolvedAt | Date    | 解決日       |


#### レスポンス例

```json
{
  "id": 1,
  "title": "異音",
  "status": 0,
  "priority": 0,
  "occurredAt": "2026-08-08",
  "resolvedAt": null
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

| 項目          | 条件                                      |
| ----------- | --------------------------------------- |
| title       | 必須・100文字以内                              |
| description | 任意                                      |
| status      | 任意・0〜2                                  |
| priority    | 任意・0〜2                                  |
| occurredAt  | 任意                                      |
| resolvedAt  | `status=2` の場合は必須、`status=0/1` の場合は指定不可 |

---

# GET /api/cars/{carId}/issues

## 概要

指定した車両の不具合一覧を取得する。

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

| 項目   | 型       | 必須 | 説明                    |
| ---- | ------- | -- | --------------------- |
| page | Integer | ×  | ページ番号（0始まり、デフォルト0）    |
| size | Integer | ×  | 取得件数（デフォルト20）         |
| sort | String  | ×  | ソート条件（例：priority,asc） |

#### リクエスト例

```http
GET /api/cars/1/issues?page=0&size=20&sort=priority,asc
```

---

## レスポンス

### 200 OK

| 項目            | 型       | 説明       |
| ------------- | ------- | -------- |
| content       | Array   | 不具合一覧    |
| page          | Integer | 現在のページ番号 |
| size          | Integer | 取得件数     |
| totalElements | Long    | 総件数      |
| totalPages    | Integer | 総ページ数    |

#### レスポンス例

```json
{
  "content": [
    {
      "id": 2,
      "title": "異音",
      "status": 0,
      "priority": 0,
      "occurredAt": "2026-08-01"
    },
    {
      "id": 1,
      "title": "エンジン警告灯",
      "status": 1,
      "priority": 1,
      "occurredAt": "2026-07-20"
    }
  ],
  "page": 0,
  "size": 20,
  "totalElements": 8,
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

# GET /api/cars/{carId}/issues/{id}

## 概要

指定した不具合の詳細情報を取得する。

**認証：必要**

**認可：認証ユーザーが所有する車両の不具合のみ取得可能。**

## リクエスト

### リクエストヘッダー

| 項目            | 値            | 必須 | 説明          |
| ------------- | ------------ | -- | ----------- |
| Authorization | Bearer {JWT} | ○  | JWTアクセストークン |

---

### パスパラメータ

| 項目 | 型    | 必須 | 説明    |
| -- | ---- | -- | ----- |
| carId | Long | ○ | 車両ID |
| id | Long | ○ | 不具合ID |

---

## レスポンス

### 200 OK

| 項目          | 型       | 説明        |
| ----------- | ------- | --------- |
| id          | Long    | 不具合ID     |
| title       | String  | 不具合名      |
| description | String  | 詳細・症状・メモ  |
| status      | Integer | 対応状況      |
| priority    | Integer | 優先度       |
| occurredAt  | Date    | 発生日・気付いた日 |
| resolvedAt  | Date    | 解決日       |

#### レスポンス例

```json
{
  "id": 2,
  "title": "異音",
  "description": "低速走行時に右前方から異音がする",
  "status": 1,
  "priority": 0,
  "occurredAt": "2026-08-01",
  "resolvedAt": null
}
```

---

## ステータスコード

| コード | 説明        |
| --- | --------- |
| 200 | 取得成功      |
| 401 | 未認証       |
| 404 | 不具合が存在しない |

---

# PUT /api/cars/{carId}/issues/{id}

## 概要

指定した不具合の情報を更新する。

**認証：必要**

**認可：認証ユーザーが所有する車両の不具合のみ更新可能。**

## リクエスト

### リクエストヘッダー

| 項目            | 値                | 必須 | 説明          |
| ------------- | ---------------- | -- | ----------- |
| Content-Type  | application/json | ○  | JSON形式で送信   |
| Authorization | Bearer {JWT}     | ○  | JWTアクセストークン |

---

### パスパラメータ

| 項目 | 型    | 必須 | 説明    |
| -- | ---- | -- | ----- |
| carId | Long | ○ | 車両ID |
| id | Long | ○ | 不具合ID |

---

### リクエストボディ

| 項目          | 型       | 必須 | 説明        |
| ----------- | ------- | -- | --------- |
| title       | String  | ○  | 不具合名      |
| description | String  | ×  | 詳細・症状・メモ  |
| status      | Integer | ○  | 対応状況      |
| priority    | Integer | ○  | 優先度       |
| occurredAt  | Date    | ×  | 発生日・気付いた日 |
| resolvedAt  | Date    | ×  | 解決日       |

#### リクエスト例

```json
{
  "title": "異音",
  "description": "右前方からの異音。原因を確認中",
  "status": 1,
  "priority": 0,
  "occurredAt": "2026-08-01",
  "resolvedAt": null
}
```

---

## レスポンス

### 200 OK

| 項目       | 型       | 説明    |
| -------- | ------- | ----- |
| id       | Long    | 不具合ID |
| title    | String  | 不具合名  |
| status   | Integer | 対応状況  |
| priority | Integer | 優先度   |

#### レスポンス例

```json
{
  "id": 2,
  "title": "異音",
  "status": 1,
  "priority": 0
}
```

---

## ステータスコード

| コード | 説明        |
| --- | --------- |
| 200 | 更新成功      |
| 400 | 入力値エラー    |
| 401 | 未認証       |
| 404 | 不具合が存在しない |

---

## バリデーション

| 項目          | 条件         |
| ----------- | ---------- |
| title       | 必須・100文字以内 |
| description | 任意         |
| status      | 必須・0〜2     |
| priority    | 必須・0〜2     |
| occurredAt  | 任意         |
| resolvedAt  | 任意         |

---

# DELETE /api/cars/{carId}/issues/{id}

## 概要

指定した不具合を削除する。

**認証：必要**

**認可：認証ユーザーが所有する車両の不具合のみ削除可能。**

## リクエスト

### リクエストヘッダー

| 項目            | 値            | 必須 | 説明          |
| ------------- | ------------ | -- | ----------- |
| Authorization | Bearer {JWT} | ○  | JWTアクセストークン |

---

### パスパラメータ

| 項目 | 型    | 必須 | 説明    |
| -- | ---- | -- | ----- |
| carId | Long | ○ | 車両ID |
| id | Long | ○ | 不具合ID |

---

### リクエストボディ

なし

---

## レスポンス

### 204 No Content

レスポンスボディなし

---

## ステータスコード

| コード | 説明        |
| --- | --------- |
| 204 | 削除成功      |
| 401 | 未認証       |
| 404 | 不具合が存在しない |