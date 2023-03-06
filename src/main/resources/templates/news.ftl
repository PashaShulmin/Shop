<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Новости</title>
    <link href="/resources/css/style.css" rel="stylesheet">
    <link href="/resources/css/menu.css" rel="stylesheet">

    <script src="/resources/js/jquery.min.js"></script>
    <script src="/resources/js/news.js"></script>
</head>
<body>


<#include "menu.ftl">

<div class="center-content">
    <div class="container">
        <#if user.role == "admin">
            <form id="add-post-form" action="/add-post" method="post">
                <label>
                    Опубликовать новость:
                    <textarea id="content" class="input_green" required name="content"></textarea>
                </label>
                <input class="button1" value="Отправить" type="submit">
            </form>
            <div class="divider"></div>
        </#if>

        <div class="divider"></div>

        <div id="post-list">
            <#list posts as post>
                <div id="post${post.id}">
                    <div class="light_blue text">${post.createdAt?string("dd MMMM yyyy 'г.,' HH:mm")}</div>
                    <div class="text">${post.content}</div>
                    <#if post_index < posts?size - 1>
                        <div class="divider"></div>
                    </#if>
                </div>
            </#list>
            <div style="text-align: center">Всего <span id="postsCount">${posts?size}</span> записей</div>
        </div>
    </div>
</div>

</body>
</html>