<%@ include file="./init.jsp" %>

<%
   // ResultSet rs = (ResultSet)request.getAttribute("resultset");
    //rs.last();    // moves cursor to the last row
    //int size = rs.getRow(); // get row id
%>

<div class="table-responsive-sm">
    <table class="table table-autofit table-list table-striped">
        <thead>
            <tr>
                <c:forEach items="${result.get(0).keySet()}" var="col">
                    <th>${col}</th>
                </c:forEach>
            </tr>
        </thead>

        <tbody>
        <c:forEach items="${result}" var="row">

                <tr>
                <c:forEach items="${row.keySet()}" var="col">
                    <td>${row.get(col)}</td>
                </c:forEach>
                </tr>

        </c:forEach>
        </tbody>
    </table>
</div>