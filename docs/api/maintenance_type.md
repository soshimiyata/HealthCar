# GET /api/maintenance-types

## 概要

登録されているメンテナンス種別一覧を取得する。

**認証：必要**

---

## リクエスト

### リクエストヘッダー

| 項目            | 値            | 必須 | 説明          |
| ------------- | ------------ | -- | ----------- |
| Authorization | Bearer {JWT} | ○  | JWTアクセストークン |

---

## クエリパラメータ

なし

---

## レスポンス

### 200 OK

| 項目                      | 型       | 説明         |
| ----------------------- | ------- | ---------- |
| id                      | Long    | メンテナンス種別ID |
| name                    | String  | メンテナンス種別名  |
| category                | String  | メンテナンスカテゴリ |
| recommendedDistanceKm   | Integer | 推奨交換距離(km) |
| recommendedPeriodMonths | Integer | 推奨交換期間(月)  |

#### レスポンス例

```json
[
  {
    "id": 1,
    "name": "オイル交換",
    "category": "ENGINE",
    "recommendedDistanceKm": 5000,
    "recommendedPeriodMonths": 6
  },
  {
    "id": 2,
    "name": "オイルフィルター交換",
    "category": "ENGINE",
    "recommendedDistanceKm": 10000,
    "recommendedPeriodMonths": 12
  },
  {
    "id": 3,
    "name": "タイヤローテーション",
    "category": "SUSPENSION",
    "recommendedDistanceKm": 5000,
    "recommendedPeriodMonths": null
  }
]
```

---

## ステータスコード

| コード | 説明   |
| --- | ---- |
| 200 | 取得成功 |
| 401 | 未認証  |