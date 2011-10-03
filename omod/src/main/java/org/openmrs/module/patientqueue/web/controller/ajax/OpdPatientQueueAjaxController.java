/**
 * <p> File: org.openmrs.module.patientqueue.web.controller.ajax.OpdPatientQueueAjaxController.java </p>
 * <p> Project: patientqueue-omod </p>
 * <p> Copyright (c) 2011 HISP Technologies. </p>
 * <p> All rights reserved. </p>
 * <p> Author: Nguyen manh chuyen </p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Feb 17, 2011 5:20:08 PM </p>
 * <p> Update date: Feb 17, 2011 5:20:08 PM </p>
 **/

package org.openmrs.module.patientqueue.web.controller.ajax;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.PatientQueueService;
import org.openmrs.module.hospitalcore.model.OpdPatientQueue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p> Class: OpdPatientQueueAjaxController </p>
 * <p> Package: org.openmrs.module.patientqueue.web.controller.ajax </p> 
 * <p> Author: Nguyen manh chuyen </p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Feb 17, 2011 5:20:08 PM </p>
 * <p> Update date: Feb 17, 2011 5:20:08 PM </p>
 **/
@Controller("OpdPatientQueueAjaxController")
public class OpdPatientQueueAjaxController {
	@RequestMapping(value="/module/patientqueue/opdPatientQueueAjax.htm" , method=RequestMethod.GET)
	public String viewOpdPatientQueue(
            @RequestParam("opdId") Integer opdId,
            Map<String, Object> model, HttpServletRequest request){
		if( opdId != null && opdId > 0 ) {
			PatientQueueService patientQueueService = Context.getService(PatientQueueService.class);
			List<OpdPatientQueue> patientQueues = patientQueueService.listOpdPatientQueue(null, opdId, "",0, 0);
			model.put("patientQueues", patientQueues );

		}
		return "/module/patientqueue/ajax/opdPatientQueueAjax";
	}
	
	
}
