<#import "tags/nav.ftl" as u>
<html>
<head><title> Home </title>
    <property name="exposeSessionAttributes"><value>true</value></property>

<body>
<div id="header">
<@u.page/>
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
                            <button type="submit" class="btn btn-primary">Book ticket</button>
                        </td>
                    </form>
                </#if>
            </td>
        </tr>
    </table>
</div>
</body>
</html>