<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="style.css" type="text/css">
    <title>Users</title>
</head>
<body>
<div class="container">
    <form class="users" method="post" action="/users?userId=${id}">
        <p>${name}</p>
        <img src="${img_url}" alt="photo" title="photo">
        <button type="submit" name="vote" value="true">Yes</button>
        <button type="submit" name="vote" value="false">No</button>
    </form>
</div>
</body>

</html>