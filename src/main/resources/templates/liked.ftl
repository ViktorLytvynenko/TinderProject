<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>People list</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
          integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link href="/assets/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="/assets/style.css" type="text/css">
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-8 offset-2">
            <div class="panel panel-default user_panel">
                <div class="panel-heading">
                    <h3 class="panel-title">User List</h3>
                </div>
                <div class="panel-body">
                    <div class="table-container">
                        <table class="table-users table" border="0">
                            <tbody>
                            <#list likedUsers as user>
                                <tr onclick="window.open('/messages/${user.getId()}', '_self')">
                                    <td width="10">
                                        <div class="avatar-img">
                                            <img class="img-circle" alt="${user.getName()}" title="${user.getName()}"
                                                 src="${user.getPhoto()}"/>  
                                        </div>

                                    </td>
                                    <td class="align-middle">
                                        ${user.getName()}
                                    </td>
                                    <td class="align-middle">
                                        Builder Sales Agent
                                    </td>
                                    <td class="align-middle">
                                        Last Login: 6/10/2017<br><small class="text-muted">5 days ago</small>
                                    </td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>