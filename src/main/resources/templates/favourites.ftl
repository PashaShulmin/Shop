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

<div id="favourites">
    <#list favourites as item>
        <div id="item" style="margin-left: 20px">
            <div class="text">Название: ${item.title}</div>
            <div class="text">Описание: ${item.description}</div>
            <div class="text">Цена: ${item.price}</div>
            <div class="text">Количество: ${item.amount}</div>
            <img class="photo" alt="IMAGE" src="/files/${item.itemPictureStorageName}"/>
            <form action="/items/addToCart" method="post">
                <input type="hidden" name="itemId" value = ${item.id}>
                <label>количество:
                    <input name="amount" type="number" min="1" max="${item.amount}" required>
                </label>
                <input type="submit" value="Добавить в корзину">
            </form>
            <a href="/items/delete-from-favourites/${item.id}" class="add-to-fav">удалить из избранного</a>
        </div>
        <#if item_index < favourites?size - 1>
            <div class="divider"></div>
        </#if>
    </#list>
    <div style="text-align: center">Всего <span id="itemsCount">${favourites?size}</span> избранных товаров</div>
</div>
</body>
</html>