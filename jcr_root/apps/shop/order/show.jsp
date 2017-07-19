<%@ include file="header.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.sling.api.resource.*" %>
<%@ page import="org.apache.sling.api.request.*" %>
<%@ taglib prefix="sling" uri="http://sling.apache.org/taglibs/sling/1.0" %>
<sling:defineObjects/>
<%
    // Code to initialise products
	HashMap<String, Integer> items = new HashMap<>();
	items.put("Cappuccino",20);
	items.put("Latte",20);
	items.put("Tea",10);
    ValueMap valueMap = resource.getValueMap();
%>


<%if (valueMap != null) { %>
<table class="table table-striped">
    <tr>
        <td colspan="2"><strong>Thank you for your order!</strong></td>
    </tr>
    <tr>
        <td><label>Item Name</label></td>
        <td><%=valueMap.get("item")%>
        </td>
    </tr>

    <tr>
        <td><label>Item Price</label></td>
        <td><%=items.get(valueMap.get("item"))%>
        </td>
    </tr>
    <tr>
        <td><label>Quatity</label></td>
        <td ><%=valueMap.get("quantity")%>
        </td>
    </tr>
    <tr>
        <td><strong>Total:</strong></td>
        <td><strong><%=Integer.parseInt(valueMap.get("quantity").toString()) * items.get(valueMap.get("item"))%></strong>
        </td>
    </tr>


</table>
<%}%>
<%@ include file="footer.jsp" %>