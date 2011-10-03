<%@ include file="/WEB-INF/template/include.jsp" %>
<openmrs:require privilege="View Patient Queue" otherwise="/login.htm" redirect="index.htm" />
<table>
<tr>
   <td>Enter Patient ID/Name: </td>
   <td><input type="text" id="queueText" value="${identifier}" /></td>
   <td><input type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value="Search" onClick="QUEUE.searchQueue();"/></td>
</tr>

</table>
<c:choose>
	<c:when test="${not empty patientQueues}" >
	<span class="boxHeader">List Patients</span> 
	<table class="box">
		<tr>
			<th>Identifier</th>
			<th>Name</th>
			<th>Age</th>
		</tr>
		<c:forEach items="${patientQueues }" var="queue" varStatus="varStatus">
			<tr class='${varStatus.index % 2 == 0 ? "oddRow" : "evenRow" } '  onclick="QUEUE.selectPatientInQueue(${queue.id})">
				<td >${queue.patientIdentifier}</td>
				<td> ${queue.patientName} </td>
             	<td>${queue.age }</td>
			</tr>
		</c:forEach>
	</table>
	</c:when>
	<c:otherwise>
	No Patient found.
	</c:otherwise>
</c:choose>