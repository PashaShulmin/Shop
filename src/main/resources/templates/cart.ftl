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
    <#list purchases?filter(x -> x.status == 'NOT_ORDERED') as purchase>
        <div id="purchase">
<#--            <#if purchase.status == 'NOT_ORDERED'>-->
                <div id="purchase${purchase.id}">
                    <a href="/cart/${purchase.id}/delete" class="delete-from-cart-button">удалить из корзины</a>
                    <div class="text">Название: ${purchase.itemDto.title}</div>
                    <div class="text">Количество: ${purchase.amount}</div>
                    <div class="text">Цена: ${purchase.price}</div>
                    <img class="photo" alt="IMAGE" src="/files/${purchase.itemDto.itemPictureStorageName}"/>
                    <#if purchase_index < purchases?filter(x -> x.status == 'NOT_ORDERED')?size - 1>
                        <div class="divider"></div>
                    </#if>
                </div>
<#--            </#if>-->
        </div>
    </#list>
    <div style="text-align: center">Всего <span id="purchasesCount">${purchases?filter(x -> x.status == 'NOT_ORDERED')?size}</span> товаров</div>
    <br>
    <#if (purchases?filter(x -> x.status == 'NOT_ORDERED')?size > 0)>
        <a href="/cart/buy" class="order-button">Оформить заказ</a><br>
    <#else>
        <div class="empty-cart-text">Корзина пуста</div>
    </#if>
</div>
</body>
</html>