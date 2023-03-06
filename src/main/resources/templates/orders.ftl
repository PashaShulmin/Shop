<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Главная</title>
    <link href="/css/main.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<#include "menu.ftl">
<div id="prod-list">
    <#list orders as order>
        <div class="order-text">Номер заказа: ${order.id} </div>
        <div class="order-text">Общая стоимость: ${order.totalPrice} рублей</div> <br>
        <#list order.purchaseDtoList as purchase>
            <div class="text">Название: ${purchase.itemDto.title}</div>
            <div class="text">Количество: ${purchase.amount}</div>
            <div class="text">Цена: ${purchase.price}</div>
            <img class="photo" alt="IMAGE" src="/files/${purchase.itemDto.itemPictureStorageName}"/>
            <#if purchase_index < order.purchaseDtoList?size - 1>
                <div class="divider"></div>
            </#if>
        </#list>
        <div class="order-divider"></div>
    </#list>
    <div style="text-align: center">Всего <span id="itemsCount">${orders?size}</span> заказов</div>
</div>
</div>
</body>
</html>