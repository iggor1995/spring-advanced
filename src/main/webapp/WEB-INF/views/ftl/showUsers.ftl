<#import "tags/nav.ftl"as u>
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
<@u.page/>

<div id="content">
<#--TABLE-->
<#if  model["users"]?has_content>
<div class="container">
    <table class="table table-dark table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Birthday</th>
            <th>Role</th>
        </tr>
        </thead>
        <tbody>
                <#list model["users"]! as user>
                <tr>
                    <td>${user.name}</td>
                    <td>${user.email}</td>
                    <td>${user.birthday}</td>
                    <td>${user.role}</td>
                </tr>
                </#list>
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