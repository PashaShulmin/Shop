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

<#if user.role == 'ADMIN'>
    <form id="add-product-form" action="/items/addItem" method="post" enctype="multipart/form-data">
        <label>Название</label>
        <input name="title" type="text" placeholder="Введите название товара"/>
        <label>Описание</label>
        <input name="description" type="text" placeholder="Введите описание"/>
        <label>Количество</label>
        <input name="amount" type="text" placeholder="Введите количество"/>
        <label>Цена</label>
        <input name="price" type="text" placeholder="Введите цену"/>
        <label>Загрузить фото</label>
        <input name="file" type="file" accept=".jpg, .png, .jpeg">
        <input class="submit-button" type="submit" value="Загрузить">
        <a href="#" class="close">Закрыть</a>
    </form>
    <div class="divider"></div>
</#if>

<div id="items">
    <#list items as item>
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
            <a href="/items/add-to-favourites/${item.id}" class="add-to-fav">добавить в избранное</a>
        </div>
        <#if item_index < items?size - 1>
            <div class="divider"></div>
        </#if>
    </#list>
    <div style="text-align: center">Всего <span id="itemsCount">${items?size}</span> товаров</div>
</div>
</body>
</html>