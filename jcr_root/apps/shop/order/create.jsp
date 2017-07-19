<%@ include file="header.jsp" %>
<%@ page import="java.util.*" %>
<%
    Long time = System.currentTimeMillis();
	HashMap<String, Integer> items = new HashMap<>();
	items.put("Cappuccino",20);
	items.put("Latte",20);
	items.put("Tea",10);
%>
<form method="POST" action="order/records/<%=time%>" enctype="multipart/form-data">
    <input type="hidden" name=":order" value="first"/>
    <input type="hidden" name=":redirect" value="/content/order/records/<%=time%>.show.html"/>
    <input type="hidden" name="title" value="<%=time%>"/>
    <input type="hidden" name="status" value="Pending"/>
    <input type="hidden" name="sling:resourceType" value="shop/order"/>
    <table class="table table-bordered">
        <tr class="row">
            <td colspan="2"><h1>Create an Order</h1></td>
        </tr>
        <tr class="row">
            <td>Item</td>
            <td><select name="item">
            	<%
            		for(String item: items.keySet()) {
            	%>
                	<option value="<%=item%>"><%=item%></option>
                <% } %>
            </select></td>
        </tr>
        <tr class="row">
            <td>Quantity</td>
            <td>
                <input type="text" name="quantity" value="1"/>
                <input type="hidden" name="quantity@TypeHint" value="Long"/>
                <input type="hidden" name="quantity@DefaultValue" value="1"/>
            </td>
        </tr>
        <tr class="row">
            <td colspan="2"><input type="submit" value="submit"/></td>
        </tr>
    </table>
</form>
<%@ include file="footer.jsp" %>