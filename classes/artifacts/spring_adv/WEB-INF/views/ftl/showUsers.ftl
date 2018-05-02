<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js"
        integrity="sha384-a5N7Y/aK3qNeh15eJKGWxsqtnX/wWdSZSKp+81YjTmS15nvnvxKHuzaWwXHDli+4"
        crossorigin="anonymous"></script>
<head><title> Registeration</title>

    <style>
        body, input {
            font-family: Calibri, Arial;
            margin: 0px;
            padding: 0px;
        }
        #header h2 {
            color: white;
            background-color: #3275A8;
            height: 50px;
            padding: 5px 0 0 5px;
            font-size: 20px;
        }

        .datatable {margin-bottom:5px;border:1px solid #eee;border-collapse:collapse;width:400px;max-width:100%;font-family:Calibri}
        .datatable th {padding:3px;border:1px solid #888;height:30px;background-color:#B2D487;text-align:center;vertical-align:middle;color:#444444}
        .datatable tr {border:1px solid #888}
        .datatable tr.odd {background-color:#eee}
        .datatable td {padding:2px;border:1px solid #888}
        #content { padding 5px; margin: 5px; text-align: center; solid-color: green }
        fieldset { width: 1000px; margin-bottom: 0px; }
        legend { font-weight: bold; }
    </style>

<body>
<div id="header">
    <H2>
        FreeMarker Spring MVC Cinema
    </H2>
</div>
<#if Session.registeredUser??>
<H2>
    Hello! ${Session.registeredUser.name}
</H2>
    <form name="logout" action="logout" method="get">
        <input type="submit" value="   Logout  " /><br/>
    </form>
</#if>
<div id="content">

    <table class="datatable">
        <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Birthday</th>
        </tr>

    <#if model["users"]??>
        <#list model["users"]! as user>
            <tr>
                <td>${user.name}</td>
                <td>${user.email}</td>
                <td>${user.birthday}</td>
            </tr>
        </#list>
    </#if>
    </table>

<#--TABLE-->
<#if  model["users"]?has_content>
<div class="container">
    <table class="table table-dark table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Birthday</th>
        </tr>
        </thead>
        <tbody>
            <#if model["users"]??>
                <#list model["users"]! as user>
                <tr>
                    <td>${user.name}</td>
                    <td>${user.email}</td>
                    <td>${user.birthday}</td>
                </tr>
                </#list>
            </#if>
        </tbody>
    </table>
</#if>
</div>
<#--/TABLE-->

    <h1>Please upload a file for users</h1>
    <form method="post" action="usersUpload" enctype="multipart/form-data">
        <input type="file" name="file"/>
        <input type="submit"/>
    </form>

    <fieldset>
        <form name="showUsers" action="home" method="get">
            <input type="submit" value="   Home  " /><br/>
        </form>
    </fieldset>
</div>
</body>
</html>