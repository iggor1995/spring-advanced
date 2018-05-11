<#import "tags/nav.ftl" as u>
<#import "tags/carousel.ftl" as c>
<#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] />
<html>
<style>
    body {
        padding-top: 100px;
    }
    #mainNav {
        opacity: 0.8;
        background: steelblue;
    }
</style>
<body>
<@u.page/>
<@c.page/>

<#if model["discount"]??>
    <h2><p style="color: white">Today you have discount - ${model["discount"]}. So you've got some cash back!</p></h2>
</#if>
<#if model["userCash"]?has_content>
    <h2><p style="color: white">Account ${model["userCash"]}</p></h2>
</#if>

<#--TABLE-->
<#if  model["events"]?has_content>
<div class="container">
    <table class="table table-dark table-striped">
        <thead>
        <tr>
            <th>Auditorium</th>
            <th>Name</th>
            <th>Rate</th>
            <th>Price</th>
            <th>Date</th>
        </tr>
        </thead>
        <tbody>
        <#list model["events"] as event>
        <tr>
            <td>${event.auditorium.name}</td>
            <td>${event.name}</td>
            <td>${event.rate}</td>
            <td>${event.basePrice}</td>
            <td>${event.dateTime}</td>
            <td>
            <@security.authorize  access="hasRole('ROLE_REGISTERED_USER')">
                    <form name="booking" action="user/bookTicketsPage" method="post">
                        <input type="hidden" name="eventId" value=${event.id}><br/>
                        <button type="submit" class="btn btn-primary">Book ticket</button>
                    </form>
            </@security.authorize>
            </td>
        </tr>
        </#list>
        </tbody>
    </table>
</div>
</#if>
<#--/TABLE-->

<#if model["error"]??>
    <h2<p style="color: white">${model["error"]}</p></h2>
</#if>
<div style="background-color:#171a1d;padding:10px;opacity: 0.7">
    <p style="color: #0c5460">If there is no data - upload files events.json and users.json from: /resources/json </p>
</div><br/>

<#--UPLOAD-->
    <div class="row align-items-center justify-content-center">
        <@security.authorize  access="hasRole('ROLE_BOOKING_MANAGER')">
            <div class="span6" style="background-color: #171a1d; border: 1px solid black" align="center" >
                    <form method="post" action="eventsUpload" enctype="multipart/form-data">
                        <h2 class="form-signin-heading"><p style="color: #0c5460">Upload events</p></h2>
                        <input type="file" name="file" style="color: #0c5460"/>
                        <button type="submit" class="btn btn-primary" style="background-color: #0c5460">Load events</button>
                    </form>
            </div>
        </@security.authorize>
            <div class="span6" style="background-color: #171a1d; border: 1px solid black" align="center">
                <form method="post" action="usersUpload" enctype="multipart/form-data">
                    <h2 class="form-signin-heading"><p style="color: #0c5460">Upload users</p></h2>
                    <input type="file" name="file" style="color: #0c5460"/>
                    <button type="submit" class="btn btn-primary" style="background-color: #0c5460">Load users</button>
                </form>
            </div>
    </div>
<#--/UPLOAD-->

<div class="row align-items-center justify-content-center" style="background-color: grey; opacity: 0.8;" >
<@security.authorize  access="hasRole('ROLE_BOOKING_MANAGER')">
        <form method="post" action="manager/report" name="PDF"  >
            <button type="submit" class="btn btn-primary" style="background-color: #0c5460">Get tickets [PDF]</button>
        </form>
</@security.authorize>
</div>
</body>
</html>