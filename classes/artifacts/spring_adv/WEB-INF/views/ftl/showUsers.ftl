<#import "tags/nav.ftl" as u>
<#import "tags/carousel.ftl" as c>
<#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] />

<html>
<body>
<@u.page/>
<div id="content">
<#--TABLE-->
<#if  model["users"]?has_content>
<div class="container">
    <table class="table table-dark table-striped">
        <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Birthday</th>
            <th>Role</th>
            <@security.authorize  access="hasRole('ROLE_BOOKING_MANAGER')">
                <th>Refill</th>
            </@security.authorize>
        </tr>
                <#list model["users"]! as user>
                <tr>
                    <td>${user.name}</td>
                    <td>${user.email}</td>
                    <td>${user.birthday}</td>
                    <td>${user.role}</td>
                    <@security.authorize  access="hasRole('ROLE_BOOKING_MANAGER')">
                        <form name="refillCash" action="/spring_adv/manager/refill" method="post" style="width: 300px">
                            <input type="hidden" name="userId" value="${user.id}">
                            <td>
                                <input id="cash"  type="number" name="cash" value="0">
                            </td>
                            <td>
                                <button type="submit" class="btn btn-primary" style="background-color: #9fcdff">
                                    Refill</button>
                            </td>
                        </form>
                    </@security.authorize>
                </tr>
                </#list>
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