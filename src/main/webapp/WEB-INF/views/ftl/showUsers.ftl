<#import "tags/nav.ftl"as u>
<html>

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
</div>
</body>
</html>