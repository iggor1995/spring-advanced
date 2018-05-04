<#import "tags/nav.ftl" as u>
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
<#--TABLE-->
<#if  model["events"]?has_content>
<div class="container">
    <table class="table table-light table-striped">
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
                <#if Session.registeredUser??>
                    <form name="booking" action="bookTicketsPage" method="post">
                        <input type="hidden" name="eventId" value=${event.id}><br/>
                        <button type="submit" class="btn btn-primary">Book ticket</button>
                    </form>
                </#if>
            </td>
        </tr>
        </#list>
        </tbody>
    </table>
</#if>
</div>
<#--/TABLE-->

<div style="background-color:#9fcdff;color:white;padding:20px;">
    <p>If there is no data - upload files events.json and users.json from: /resources/json </p>
</div><br/>

<#--UPLOAD-->
    <div class="row align-items-center justify-content-center" >
        <div class="span6" style="background-color: #9fcdff; border: 2px solid deepskyblue" align="center" >
                <form method="post" action="eventsUpload" enctype="multipart/form-data">
                    <h2 class="form-signin-heading">Upload events</h2>
                    <input type="file" name="file"/>
                    <button type="submit" class="btn btn-primary">Load events</button>
                </form>
        </div>
        <div class="span6" style="background-color: #9fcdff; border: 2px solid deepskyblue" align="center">
                <form method="post" action="usersUpload" enctype="multipart/form-data">
                    <h2 class="form-signin-heading">Upload users</h2>
                    <input type="file" name="file"/>
                    <button type="submit" class="btn btn-primary">Load users</button>
                </form>
        </div>
    </div>
<#--/UPLOAD-->
</body>
</html>