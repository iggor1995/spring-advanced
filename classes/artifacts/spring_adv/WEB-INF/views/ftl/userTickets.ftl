<#import "tags/nav.ftl" as u>
<#import "tags/carousel.ftl" as c>
<html>

<body>
<@u.page/>
<div id="content">
<#--TABLE-->
<#if  model["tickets"]?has_content>
<div class="container">
    <table class="table table-dark table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Auditorium</th>
            <th>Rate</th>
            <th>Price</th>
            <th>Date</th>
            <th>Seat</th>
            <th>Print</th>
        </tr>
        </thead>
        <tbody>
            <#list model["tickets"]! as ticket>
            <tr>
                <td>${ticket.event.name}</td>
                <td>${ticket.event.auditorium.name}</td>
                <td>${ticket.event.rate}</td>
                <td>${ticket.event.basePrice}</td>
                <td>${ticket.event.dateTime}</td>
                <td>${ticket.seats}</td>
                <td>
                    <form name="book" action="/spring_adv/user/report" method="post">
                        <input type="hidden" name="ticketId" value="${ticket.id}">
                        <button type="submit" class="btn btn-primary">Print</button>
                    </form>
                </td>
            </tr>
            </#list>
        </tbody>
    </table>
</#if>
</div>
<#--/TABLE-->
</div>
</body>
</html>