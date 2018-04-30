<html>
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
    <fieldset>
        <form name="showUsers" action="home" method="get">
            <input type="submit" value="   Home  " /><br/>
        </form>
    </fieldset>
</div>
</body>
</html>