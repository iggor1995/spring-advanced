<html>
<head><title> Home </title>
    <property name="exposeSessionAttributes"><value>true</value></property>
    <style>
        .button {
            background-color: #4CAF50;
            border: none;
            color: white;
            padding: 15px 25px;
            text-align: center;
            font-size: 16px;
            cursor: pointer;
        }

        .button:hover {
            background-color: green;
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
        fieldset { width: 300px; padding: 5px; margin-bottom: 0px; }
        legend { font-weight: bold; }
    </style>

<body>
<div id="header">
    <H2>
        FreeMarker Spring MVC Hello World
    </H2>
    <form name="user" action="registerPage" method="get">
        <input type="submit" value="   Register   " /><br/>
    </form>
</div>
<#if Session.registeredUser??>
<H2>
    Hello! ${Session.registeredUser.name}
</H2>
<#else>
<fieldset>
    <form name="login" action="login" method="post">
        Email:  <input type="text" name="email"><br/>
        Password: <input type="password" name="password"><br/>
        <input type="submit" value="Log In"><br/>
    </form>
</fieldset>
</#if>

<div id="content">

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

    <br/>
    <form name="addEvent" action="pageAddEvent" method="get">
        <input type="submit" value="   Add Movie   " /><br/>
    </form>
    <fieldset>
        <h3>Book Ti</h3>
    </fieldset>
    <fieldset>
        <form name="PDF" action="report" method="post">
            <input type="text" name="eventName" value="a"><br/>
            <input type="text" name="auditorium" value="Blue hall"><br/>
            <input type="datetime-local" name="date" value="2017-06-01T08:30"><br/>
            <input type="submit" value="   Get tickets PDF   " /><br/>
        </form>
    </fieldset>
</div>
</body>
</html>