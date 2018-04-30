<html>
<head><title> Home </title>
    <property name="exposeSessionAttributes"><value>true</value></property>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
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
        #content { padding 5px; margin: 5px; text-align: center}
        legend { font-weight: bold; }
    </style>

<body>
<div id="header">
    <H2>
        FreeMarker Spring MVC Cinema
    </H2>
    <div style="background-color:black;color:white;padding:20px;">
        <p>If there is no data - there are files attached: /resources/json events.json and users.json</p>
        <p>Upload those files using tools in this page</p>
    </div><br/>
    <form name="user" action="registerPage" method="get">
        <input type="submit" value="   Register   " />
    </form>
</div>
<#if Session.registeredUser??>
<H2>
    Hello! ${Session.registeredUser.name}
</H2>
    <form name="logout" action="logout" method="get">
        <input type="submit" value="   Logout  " /><br/>
    </form>
<#else>
<fieldset style="margin: right">
    <form name="login" action="login" method="post" style="background-color: #4CAF50">
        Email:  <input type="text" name="email"><br/>
        Password: <input type="password" name="password"><br/>
        <input type="submit" value="Log In"><br/>
    </form>
</fieldset>
</#if>
<#if  model["events"]?has_content>
<div id="content" align="left">
    <br/>
    <table class="datatable">
        <tr>
            <th>Auditorium</th>
            <th>Name</th>
            <th>Rate</th>
            <th>Price</th>
            <th>Date</th>

        </tr>
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
                        <input type="submit" value="   Book ticket   " /><br/>
                    </form>
                </#if>
            </td>
        </tr>
    </#list>
    </table>
</#if>
    <br/>
    <fieldset>
        <form name="addEvent" action="pageAddEvent" method="get">
            <input type="submit" value="   Add Movie   " /><br/>
        </form>
    </fieldset>
    <fieldset>
        <h3>Get tickets in PDF</h3>
    </fieldset>
    <fieldset>
        <form name="PDF" action="report" method="post">
            <input type="submit" value="   Get tickets PDF   " /><br/>
        </form>
    </fieldset>
</div>
<div align="center">
<fieldset>
    <h1>Please upload a file for users</h1>
    <form method="post" action="usersUpload" enctype="multipart/form-data">
        <input type="file" name="file"/>
        <input type="submit"/>
    </form>
        <h1>Please upload a file for events</h1>
        <form method="post" action="eventsUpload" enctype="multipart/form-data">
            <input type="file" name="file"/>
            <input type="submit"/>
        </form>
    </div>
</fieldset>
<fieldset>
    <form name="showUsers" action="getUsers" method="get">
        <input type="submit" value="   See all Users   " /><br/>
    </form>
</fieldset>
</body>
</html>