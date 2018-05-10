<#import "tags/nav.ftl" as u>
<#import "tags/carousel.ftl" as c>
<html>

<body>
<@u.page/>
<@c.page/>

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

    <div class="row align-items-center justify-content-center" >
        <h1>You can upload file with users below</h1><br/>
        <div class="span6" style="background-color: #9fcdff; border: 2px solid deepskyblue" align="center">
            <form method="post" action="usersUpload" enctype="multipart/form-data">
                <h2 class="form-signin-heading">Upload users</h2>
                <input type="file" name="file"/>
                <button type="submit" class="btn btn-primary">Load users</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>