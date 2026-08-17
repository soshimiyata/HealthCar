| メソッド | URL | 概要 |
|----------|-----|------|
| POST | `/api/cars` | 車両登録 |
| GET | `/api/cars` | 車両一覧取得 |
| GET | `/api/cars/{id}` | 車両詳細取得 |
| PUT | `/api/cars/{id}` | 車両情報更新 |
| DELETE | `/api/cars/{id}` | 車両削除 |

---

# POST /api/cars

## 概要

新しい車両を登録する。

**認証：必要**

## リクエスト

### リクエストヘッダー

| 項目 | 値 | 必須 | 説明 |
|------|----|------|------|
| Content-Type | application/json | ○ | JSON形式で送信 |
| Authorization | Bearer {JWT} | ○ | JWTアクセストークン |

---

### リクエストボディ

| 項目 | 型 | 必須 | 説明 |
|------|----|------|------|
| maker | String | ○ | メーカー名 |
| carModel | String | ○ | 車種名 |
| modelYear | Integer | × | 年式 |
| description | String | × | 車両メモ |
| odometer | Integer | × | 走行距離(km) |
| status | Integer | 〇 | 状態 |

#### リクエスト例

```json
{
  "maker": "MAZDA",
  "carModel": "デミオ",
  "modelYear": 2016,
  "description": "普段乗り",
  "odometer": 82000,
  "status": 0
}
```

---

## レスポンス

### 201 Created

| 項目 | 型 | 説明 |
|------|----|------|
| id | Long | 車両ID |
| maker | String | メーカー名 |
| carModel | String | 車種名 |

#### レスポンス例

```json
{
  "id": 1,
  "maker": "MAZDA",
  "carModel": "デミオ"
}
```

---

## ステータスコード

| コード | 説明 |
|------|------|
| 201 | 登録成功 |
| 400 | 入力値エラー |
| 401 | 未認証 |

---

## バリデーション

| 項目 | 条件 |
|------|------|
| maker | 必須・50文字以内 |
| carModel | 必須・100文字以内 |
| modelYear | 任意 |
| odometer | 0以上 |

---

# GET /api/cars

## 概要

認証ユーザーが所有する車両の一覧を取得する。

**認証：必要**

**認可：認証ユーザーが所有する車両のみ取得可能。**

---

## リクエスト

### リクエストヘッダー

| 項目            | 値            | 必須 | 説明          |
| ------------- | ------------ | -- | ----------- |
| Authorization | Bearer {JWT} | ○  | JWTアクセストークン |

---

### クエリパラメータ

| 項目   | 型       | 必須 | 説明                   |
| ---- | ------- | -- | -------------------- |
| page | Integer | ×  | ページ番号（0始まり、デフォルト0）   |
| size | Integer | ×  | 取得件数（デフォルト20）        |
| sort | String  | ×  | ソート条件（例：`maker,asc`） |

### リクエスト例

```text
GET /api/cars?page=0&size=20&sort=maker,asc
```

---

# レスポンス

## 200 OK

### content

| 項目                 | 型      | 説明    |
| ------------------ | ------ | ----- |
| content            | Array  | 車両一覧  |
| content[].id       | Long   | 車両ID  |
| content[].maker    | String | メーカー名 |
| content[].carModel | String | 車種名   |

### ページング情報

| 項目                  | 型       | 説明           |
| ------------------- | ------- | ------------ |
| pageable            | Object  | ページング情報      |
| pageable.pageNumber | Integer | 現在のページ番号     |
| pageable.pageSize   | Integer | 1ページあたりの取得件数 |
| pageable.offset     | Long    | データの開始位置     |
| pageable.paged      | Boolean | ページングが有効か    |
| pageable.unpaged    | Boolean | ページングが無効か    |

### ページ情報

| 項目               | 型       | 説明            |
| ---------------- | ------- | ------------- |
| totalPages       | Integer | 総ページ数         |
| totalElements    | Long    | 総件数           |
| last             | Boolean | 最終ページか        |
| size             | Integer | 1ページあたりの取得件数  |
| number           | Integer | 現在のページ番号      |
| numberOfElements | Integer | 現在のページに含まれる件数 |
| first            | Boolean | 最初のページか       |
| empty            | Boolean | 結果が空か         |

### ソート情報

`sort` は現在のソート状態を表す。

| 項目       | 型       | 説明         |
| -------- | ------- | ---------- |
| sorted   | Boolean | ソートされているか  |
| empty    | Boolean | ソート条件が空か   |
| unsorted | Boolean | ソートされていないか |

---

## レスポンス例

```json
{
  "content": [
    {
      "id": 1,
      "maker": "MAZDA",
      "carModel": "デミオ"
    },
    {
      "id": 2,
      "maker": "TOYOTA",
      "carModel": "86"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 20,
    "sort": {
      "sorted": true,
      "empty": false,
      "unsorted": false
    },
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "totalPages": 1,
  "totalElements": 2,
  "last": true,
  "size": 20,
  "number": 0,
  "sort": {
    "sorted": true,
    "empty": false,
    "unsorted": false
  },
  "numberOfElements": 2,
  "first": true,
  "empty": false
}
```

---

## ステータスコード

| コード | 説明   |
| --- | ---- |
| 200 | 取得成功 |
| 401 | 未認証  |

---

# GET /api/cars/{id}

## 概要

指定した車両の詳細情報を取得する。  
車両情報に加え、最近のメンテナンス・カスタム履歴、現在装着中のパーツ、不具合情報を取得する。

**認証：必要**

**認可：認証ユーザーが所有する車両のみ取得可能。**

---

## パスパラメータ

| 項目 | 型 | 必須 | 説明 |
|------|----|------|------|
| id | Long | ○ | 車両ID |

---

## レスポンス

### 200 OK

| 項目 | 型 | 説明 |
|------|----|------|
| id | Long | 車両ID |
| maker | String | メーカー名 |
| carModel | String | 車種名 |
| modelYear | Integer | 年式 |
| description | String | 車両メモ |
| odometer | Integer | 走行距離(km) |
| status | Integer | 車両状態 |
| summary | Object | 車両の集計情報 |
| recentMaintenances | Array | 最近のメンテナンス（最大3件） |
| recentCustoms | Array | 最近のカスタム（最大3件） |
| currentParts | Array | 現在装着中のパーツ（最大5件） |
| recentCarIssues | Array | 不具合一覧（最大3件） |

#### summary

| 項目               | 型       | 説明           |
| ---------------- | ------- | ------------ |
| maintenanceCount | Integer | メンテナンス件数     |
| customCount      | Integer | カスタム件数       |
| partsCount       | Integer | 現在装着中パーツ数    |
| maintenanceCost  | Integer | メンテナンス総費用(円) |
| customCost       | Integer | カスタム総費用(円)   |
| partsCost        | Integer | パーツ総費用(円)    |
| totalCost        | Integer | 総費用(円)       |


#### レスポンス例

```json
{
  "id": 1,
  "maker": "MAZDA",
  "carModel": "デミオ",
  "modelYear": 2016,
  "description": "普段乗り",
  "odometer": 82000,
  "status": 0,

  "summary": {
    "maintenanceCount": 18,
    "customCount": 26,
    "partsCount": 32,
    "maintenanceCost": 186000,
    "customCost": 820000,
    "partsCost": 910000,
    "totalCost": 1916000
  },

  "recentMaintenances": [
    {
      "id": 12,
      "title": "オイル交換",
      "maintenanceDate": "2026-07-20"
    },
    {
      "id": 8,
      "title": "エアフィルター交換",
      "maintenanceDate": "2026-05-15"
    }
  ],
  "recentCustoms": [
    {
      "id": 5,
      "title": "車高調交換",
      "customDate": "2026-06-10"
    }
  ],
  "currentParts": [
    {
      "id": 3,
      "category": "ホイール",
      "name": "OZ Racing Volcano"
    },
    {
      "id": 7,
      "category": "マフラー",
      "name": "HKS Hi-Power"
    }
  ],
  "recentCarIssues": [
    {
      "id": 2,
      "title": "異音",
      "status": 0,
      "priority": 0
    }
  ]
}
```

---

## ステータスコード

| コード | 説明 |
|------|------|
| 200 | 取得成功 |
| 401 | 未認証 |
| 404 | 車両が存在しない |

---

# PUT /api/cars/{id}

## 概要

指定した車両情報を更新する。

**認証：必要**

**認可：認証ユーザーが所有する車両のみ更新可能。**

---

## リクエスト

### リクエストヘッダー

| 項目 | 値 | 必須 | 説明 |
|------|----|------|------|
| Content-Type | application/json | ○ | JSON形式で送信 |
| Authorization | Bearer {JWT} | ○ | JWTアクセストークン |

---

### リクエストボディ

| 項目 | 型 | 必須 | 説明 |
|------|----|------|------|
| maker | String | ○ | メーカー名 |
| carModel | String | ○ | 車種名 |
| modelYear | Integer | × | 年式 |
| description | String | × | 車両メモ |
| odometer | Integer | × | 走行距離(km) |
| status | Integer | ○ | 状態（0:所有中、1:売却済み、2:廃車） |

#### リクエスト例

```json
{
  "maker": "MAZDA",
  "carModel": "MAZDA2",
  "modelYear": 2019,
  "description": "メインカー",
  "odometer": 95000,
  "status": 0
}
```

---

## レスポンス

### 200 OK

```json
{
  "id": 1,
  "maker": "MAZDA",
  "carModel": "MAZDA2",
  "modelYear": 2019,
  "description": "メインカー",
  "odometer": 95000,
  "status": 0
}
```

---

## ステータスコード

| コード | 説明 |
|------|------|
| 200 | 更新成功 |
| 400 | 入力値エラー |
| 401 | 未認証 |
| 404 | 車両が存在しない |

---

## バリデーション

| 項目 | 条件 |
|------|------|
| maker | 必須・50文字以内 |
| carModel | 必須・100文字以内 |
| modelYear | 任意 |
| odometer | 0以上 |
| status | 0〜2 |

---

# DELETE /api/cars/{id}

## 概要

指定した車両を削除する。

**認証：必要**

**認可：認証ユーザーが所有する車両のみ削除可能。**

---

## リクエスト

### リクエストヘッダー

| 項目 | 値 | 必須 | 説明 |
|------|----|------|------|
| Authorization | Bearer {JWT} | ○ | JWTアクセストークン |

---

## リクエストボディ

なし

---

## レスポンス

### 204 No Content

レスポンスボディなし

---

## ステータスコード

| コード | 説明 |
|------|------|
| 204 | 削除成功 |
| 401 | 未認証 |
| 404 | 車両が存在しない |