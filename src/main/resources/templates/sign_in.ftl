<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign In</title>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/sign-in.css">
</head>
<body>
<div class="container">

    <form action="/signIn" class="form-center-content" method="post">
        <div class="form-signin-heading" style="font-family: Butcherman; font-size: 40px">Sign in</div>
        <label>
            <input class="form-control" name="email" type="email" placeholder="Email" required>
        </label>
        <label>
            <input class="form-control" name="password" type="password" placeholder="Password" required>
        </label>
        <label>
            <input type="checkbox" name="rememberMe">Запомнить меня
        </label>
        <input class="login-button" type="submit" value="SIGN IN">
    </form>

    <a href="/signUp" class="go-sign-up">Jump to sign-up</a>

</div>
</body>
</html>