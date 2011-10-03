<%@ include file="/WEB-INF/template/include.jsp" %>
<openmrs:require privilege="View Patient Queue" otherwise="/login.htm" redirect="index.htm" />
<div id="searchbox"></div>
<div id="patientResult"></div>

<script type="text/javascript">

	jQuery(document).ready(function() {
		
		jQuery("#searchbox").showPatientSearchBox({					
			resultView: "/module/patientqueue/patientsearch/patientqueue",		
			target: "#patientResult",
			success: function(data){
				QUEUE.initTableHover();
			} 
		});	
    });
</script>	