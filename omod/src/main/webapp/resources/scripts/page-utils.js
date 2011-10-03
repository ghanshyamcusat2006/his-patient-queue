
QUEUE={
		load : function(url , container)
		{
			jQuery(container).load(url);
		},
		initTableHover : function()
		{
			jQuery("tr").each(function(){
				if( jQuery(this).hasClass("evenRow") || jQuery(this).hasClass("oddRow") )
				{
					jQuery(this).hover(
							function(){jQuery(this).addClass("hover");},
							function(){jQuery(this).removeClass("hover");}
							);
				}
			});
		},
		changeOPD : function(this_)
		{
			var x = jQuery(this_).val();
			if(x != null && x != ''){
				// set cookie
				jQuery.cookie("opdId", x);
				ACT.go("main.htm?opdId="+x);
			
			}
			
		},
		refreshQueue : function()
		{
			jQuery("#Patient_queue").load("opdPatientQueueAjax.htm?opdId="+jQuery("#opdCombo").val(), function(){	QUEUE.initTableHover(); }); 
		},
		searchQueue : function()
		{
			if(jQuery("#queueText").val() != '' && jQuery("#queueText").val() != null && jQuery("#opdCombo").val() !='' && jQuery("#opdCombo").val()!= null ){
				jQuery("#Search_patient_in_queue").load('searchPatientInQueue.htm', {text: jQuery("#queueText").val(), opdId: jQuery("#opdCombo").val() },
					function(){	QUEUE.initTableHover();});
			}else{
				alert('Please enter text for search or choose opd first!');
			}
		},
		searchSystem : function()
		{
			if(jQuery("#phrase").val() != '' && jQuery("#phrase").val() != null && jQuery("#opdCombo").val() !='' && jQuery("#opdCombo").val()!= null ){
				jQuery("#Search_patient_in_system").load('searchPatientInSystem.htm',{phrase: jQuery("#phrase").val(),opdId: jQuery("#opdCombo").val() },
						function(){	QUEUE.initTableHover();  });
			}else{
				alert('Please enter text for search or choose opd first!');
			}
		},
		
		selectPatientInQueue : function(queueId)
		{
			ACT.go("selectPatientInQueue.htm?id="+queueId);
		},
		selectPatientInSystem : function(patientId)
		{
			ACT.go("selectPatientInSystem.htm?id="+patientId+"&opdId="+jQuery("#opdCombo").val());
		}
		
};


