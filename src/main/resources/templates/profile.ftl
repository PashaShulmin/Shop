<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Профиль</title>

    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/menu.css" rel="stylesheet">
    <link href="/css/profile.css" rel="stylesheet">
</head>
<body>
<#include "menu.ftl">
<div class="container">

    <div class="center-content">
        <div class="container">
            <div class="title">Профиль</div>
            <div id="profile" class="white-container">
                <img class="user-avatar" alt="IMAGE" src="/files/${user.avatarStorageName}"/>
                <div class="user-info-text">
                    <div class="user-info">${user.firstName}</div>
                    <div class="user-info">${user.lastName}</div>
                    <div class="user-info">${user.email}</div>
                </div>
            </div>

            <form action="/profile/updateAvatar" method="post" enctype="multipart/form-data" class="button_load">
                <input type="file" name="file" accept=".jpg, .png, .jpeg">
                <input type="submit" value="Загрузить" name="load">
            </form>
        </div>
    </div>

</div>
</body>
</html>