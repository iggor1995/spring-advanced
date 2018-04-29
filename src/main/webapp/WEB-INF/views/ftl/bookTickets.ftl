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
            <th>Name</th>
            <th>Auditorium</th>
            <th>Rate</th>
            <th>Price</th>
            <th>Date</th>
            <th>Seat</th>
            <th>Book</th>
        </tr>
        <tr>
            <#assign event=.data_model["event"]>
            <td>${event.name}</td>
            <td>${event.auditorium.name}</td>
            <td>${event.rate}</td>
            <td>${event.basePrice}</td>
            <td>${event.dateTime}</td>
                <#if Session.registeredUser??>
                    <form name="PDF" action="bookTickets" method="post">
                        <input type="hidden" name="eventId" value="${event.id}">
                        <input type="hidden" name="userId" value="${Session.registeredUser.id}">
                        <td>
                            <select name="seat">
                                <#assign x=.data_model["seats"]>
                                <#list 1..x as i>
                                    <option>${i}</option>
                                </#list>
                            </select>
                        </td>
                        <td>
                            <input type="submit" value="   BookTicket   " /><br/>
                        </td>
                    </form>
                </#if>
            </td>
        </tr>
    </table>
</div>
</body>
</html>