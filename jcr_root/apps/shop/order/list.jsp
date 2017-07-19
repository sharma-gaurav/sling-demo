<%@ include file="header.jsp" %>

<%@ page import="java.util.*" %>
<%@ page import="org.apache.sling.api.resource.*" %>
<%@ page import="org.apache.sling.api.request.*" %>
<%@ taglib prefix="sling"
           uri="http://sling.apache.org/taglibs/sling/1.0" %>
<sling:defineObjects/>

<%
	HashMap<String, Integer> items = new HashMap<>();
	items.put("Cappuccino",20);
	items.put("Latte",20);
	items.put("Tea",10);
    Iterator<Resource> orders = resource.listChildren();
    List<Resource> pendingOrders = new ArrayList<>();
    while (orders.hasNext()) {

        Resource current = orders.next();
        ValueMap valueMap = current.getValueMap();

        if ("pending".equalsIgnoreCase(valueMap.get("status").toString())) {
            pendingOrders.add(current);
        }
    }
%>
<table class="table table-striped">
 	<tr>
    <td>Order No.</td>
    <td>Item Name</td>
    <td>Price</td>
    <td>Quantity</td>
    <td>Action</td>
    </tr>
    <%
        if(pendingOrders.size() > 0) {
        for (Resource current : pendingOrders) {
            ValueMap valueMap = current.getValueMap();
    %>
    <tr>
        <form method="POST" action="records/<%=valueMap.get("title")%>" enctype="multipart/form-data">
        	<input type="hidden" name=":redirect" value="/content/order/records.list.html"/>
            <input type="hidden" name="status" value="Processed"/>
            <td><%=valueMap.get("title")%></td>
            <td><%=valueMap.get("item")%></td>
            <td><%=items.get(valueMap.get("item"))%></td>
            <td><%=valueMap.get("quantity")%></td>
            <td>
                <button type="submit" class="btn btn-info">Complete Order</button>
            </td>
        </form>
    </tr>
    <%
        }
        } else {
    %>
    	<tr>
    		<td colspan="6" style="text-align:center">No Pending Order</td>
    	</tr>
    <%
        }
    %>
</table>
<%@ include file="footer.jsp" %>