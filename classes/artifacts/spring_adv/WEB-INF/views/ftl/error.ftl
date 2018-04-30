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
        <p>If there is no data - there are files attached: events.json and users.json</p>
        <p>Upload those file using tools in this page</p>
    </div><br/>
    <form name="user" action="registerPage" method="get">
        <input type="submit" value="   Register   " />
    </form>
</div>
<#if Session.registeredUser??>
<H2>
    Hello! ${Session.registeredUser.name}, you've got en error
</H2>
<form name="logout" action="logout" method="get">
    <input type="submit" value="   Logout  " /><br/>
</form>
</#if>
<fieldset>
    <h3>${model["url"]}</h3>
    <h3>${model["exception"]}</h3>
</fieldset>
<form name="logout" action="home" method="get">
    <input type="submit" value="   Home  " /><br/>
</form>
<fieldset>
    <form name="showUsers" action="getUsers" method="get">
        <input type="submit" value="   See all Users   " /><br/>
    </form>
</fieldset>
</body>
</html>