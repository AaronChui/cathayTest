# cathayTest

呼叫 coindeskAPI：<host>:<port>/coindeskAPI

呼叫 coindeskAPI 調整後版本：<host>:<port>/info

新增幣別：<host>:<port>/newCurrency
requestBody:

{
    "code":"EUR",
    "chName":"歐元",
    "engName":"Pound sterling",
    "symbol" : "&euro;"
}
更新幣別：<host>:<port>/updateCurrency
requestBody:

{
    "code":"EUR",
    "chName":"歐元",
    "engName":"Pound test",
    "symbol" : "&euro;"
}
刪除幣別：<host>:<port>/deleteCurrency
requestBody:

{
    "code":"EUR"
}

查詢幣別(可查詢條件為 code、chName、engName)：<host>:<port>/searchCurrency
requestBody:

{
    "code":"EUR"
}

