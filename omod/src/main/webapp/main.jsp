<%@ include file="/WEB-INF/template/include.jsp" %>
<openmrs:require privilege="View Patient Queue" otherwise="/login.htm" redirect="index.htm" />
<%@ include file="/WEB-INF/template/header.jsp" %>

<script type="text/javascript">
	var contextPath = "${pageContext.request.contextPath}";
</script>
<%@ include file="includes/js_css.jsp" %>
<b class="boxHeader">Patient queue</b>
<input type="hidden" id="pageId" value="patientQueue"/>
<div class="box" >
Location: <select id="opdCombo"  onchange="QUEUE.changeOPD(this)">
	<option value="0">-Please select-</option>
	<c:forEach items="${listOPD}" var="opd">
		<option value="${opd.answerConcept.id }"  <c:if test="${opdId == opd.answerConcept.id  }">selected="selected"</c:if>>${opd.answerConcept.name}</option>
	</c:forEach>
</select>
<br/>
<input type="hidden" id="intervalId" value=""/>
<div id="tabs">
     <ul>
         <li><a href="opdPatientQueue.htm?opdId=${opdId}"  title="Patient queue"><span >Patient queue</span></a></li>
         <li><a href="searchPatientInQueue.htm"   title="Search patient in queue"><span>Search patient in queue</span></a></li>
         <li><a href="searchPatientInSystem.htm"  title="Search patient in system"><span >Search patient in system</span></a></li>
     </ul>
     
     <div id="Patient_queue"></div>
     <div id="Search_patient_in_queue"></div>
	 <div id="Search_patient_in_system"></div>
</div>

</div>


<%@ include file="/WEB-INF/template/footer.jsp" %> 