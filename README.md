# cathayTest

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

查詢幣別：<host>:<port>/searchCurrency
requestBody:

{
    "code":"EUR"
}