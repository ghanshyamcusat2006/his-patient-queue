

var EVT =
{
	ready : function()
	{
		/**
		 * Page Actions
		 */
		var enableCheck = true;
		var pageId = jQuery("#pageId").val();
		if(enableCheck && pageId != undefined && eval("CHECK." + pageId))
		{
			eval("CHECK." + pageId + "()");
		}
		jQuery('.date-pick').datepicker({minDate: '-100y', dateFormat: 'dd/mm/yy'});

		/**
		 * Ajax Indicator when send and receive data
		 */
		if(jQuery.browser.msie)
		{
			jQuery.ajaxSetup({cache: false});
		}
	
	}
};

var CHECK = 
{
	
	
	formExample : function()
	{
		jQuery("#drugCore").autocomplete({source: 'autoCompleteDrugCoreList.htm', minLength: 3 } );
		jQuery('#tabs').tabs();
		var validator = jQuery("#formExample").validate(
		{
			event : "blur",
			rules : 
			{
			
				"drugCore" : { required : true}
				
			}
		});
	},
	patientQueue : function()
	{
		/*var opdId = jQuery.cookie("opdId");
		if( opdId != null && opdId.length> 0)
		{
			jQuery("#opdCombo").val(opdId);
		}*/	
		
		jQuery("#tabs").tabs({cache: true, 
            load : function(event, ui)
            { 
			 	var t = jQuery("#tabs").tabs();
				var selected = t.tabs('option', 'selected');
				
				if( selected == 0 ){
					// patient queue
						
					QUEUE.initTableHover();
					
					clearInterval(jQuery("#intervalId").val()) ;
					var intervalId = setInterval("QUEUE.refreshQueue()"
					, 10000);
					jQuery("#intervalId").val(intervalId);
				}
				if( selected == 1 ){
					// search in queue
					
				}
				if( selected == 2 ){
					// search in system
					
				}
            },
			select : function(event,ui)
			{
				var t = jQuery("#tabs").tabs();
				var selected = t.tabs('option', 'selected');
            	if( selected == 0 ){
					// patient queue
						
					QUEUE.initTableHover();
					
					clearInterval(jQuery("#intervalId").val()) ;
					var intervalId = setInterval("QUEUE.refreshQueue()"
					, 10000);
					jQuery("#intervalId").val(intervalId);
				}
				if( selected == 1 ){
					// search in queue
					
				}
				if( selected == 2 ){
					// search in system
					
				}
			}
            });
	}
	
};

/**
 * Pageload actions trigger
 */

jQuery(document).ready(
	function() 
	{
		EVT.ready();
	}
);



