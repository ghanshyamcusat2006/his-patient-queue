<%@ include file="/WEB-INF/template/include.jsp" %>

<openmrs:require privilege="Add/Edit example" otherwise="/login.htm" redirect="/module/patientqueue/example.htm" />

<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="../includes/js_css.jsp" %>
<h2>Example page</h2>

<form method="post" id="formExample">
<div  class="box">


Drug core(minimum 3 charaters) <input type="text" id="drugCore" name="drugCore"  size="35" />
<br/>
<div id="tabs">
	<ul>
		<li><a href="#tabs-1">First</a></li>
		<li><a href="#tabs-2">Second</a></li>
		<li><a href="#tabs-3">Third</a></li>
	</ul>
	<div id="tabs-1">Lorem </div>
	<div id="tabs-2">Phasellus </div>
	<div id="tabs-3">Nam dui </div>
</div>

</div>
</form>
<%@ include file="/WEB-INF/template/footer.jsp" %>