<%@ include file="/WEB-INF/template/include.jsp" %>
<openmrs:require privilege="View Patient Queue" otherwise="/login.htm" redirect="index.htm" />
<openmrs:globalProperty key="hospitalcore.hospitalName" defaultValue="ddu" var="hospitalName"/>
<div id="searchbox"></div>
<div id="patientResult"></div>

<script type="text/javascript">

	jQuery(document).ready(function() {
		
		jQuery("#searchbox").showPatientSearchBox({		
			searchBoxView: "${hospitalName}/default",
			resultView: "/module/patientqueue/patientsearch/${hospitalName}/patientqueue",		
			target: "#patientResult",
			success: function(data){
				QUEUE.initTableHover();
			} 
		});	
    });
</script>	