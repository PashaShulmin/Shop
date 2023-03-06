<!DOCTYPE html>
<html lang="en">
<#import "spring.ftl" as spring/>
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/sign-up.css">
</head>
<body>

<div class="container">

    <form action="/signUp" class="form-center-content" enctype="multipart/form-data" method="post">
        <div class="form-signup-heading" style="font-family: Butcherman; font-size: 40px">Sign up</div>
        <label class="form-control">
            <@spring.formInput "signUpDto.firstName" "placeholder='firstName'"/>
            <#list spring.status.errorMessages as error>
                <span class="error">${error}</span>
                <br>
            </#list>
        </label>
<#--        <label>-->
<#--            <input class="form-control" name="firstName" type="text" placeholder="Firstname" required>-->
<#--        </label>-->
        <label>
            <input class="form-control" name="lastName" type="text" placeholder="Lastname" required>
        </label>
        <label>
            <input class="form-control" name="email" type="email" placeholder="Email" required>
        </label>
        <label>
            <input class="form-control" name="password" type="password" placeholder="Password" required>
        </label>
        <label>Avatar
            <input name="file" type="file" accept=".png, .jpg, .jpeg" value="Choose File">
        </label>
        <input class="register-button" type="submit" value="SIGN UP">
    </form>

    <a href="/signIn" class="back-sign-in">Back to sign-in</a>

</div>
</body>
</html>