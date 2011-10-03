/**
 * <p> File: org.openmrs.module.patientqueue.web.controller.autocomplete.AutoCompleteController.java </p>
 * <p> Project: standard-omod </p>
 * <p> Copyright (c) 2011 HISP Technologies. </p>
 * <p> All rights reserved. </p>
 * <p> Author: Nguyen manh chuyen </p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Jan 26, 2011 5:15:41 PM </p>
 * <p> Update date: Jan 26, 2011 5:15:41 PM </p>
 **/

package org.openmrs.module.patientqueue.web.controller.autocomplete;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.openmrs.Drug;
import org.openmrs.api.context.Context;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p> Class: AutoCompleteController </p>
 * <p> Package: org.openmrs.module.patientqueue.web.controller.autocomplete </p> 
 * <p> Author: Nguyen manh chuyen </p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Jan 26, 2011 5:15:41 PM </p>
 * <p> Update date: Jan 26, 2011 5:15:41 PM </p>
 **/
@Controller("PatientQueueAutoCompleteController")
public class AutoCompleteController {
	
	@RequestMapping(value="/module/patientqueue/autoCompleteDrugCoreList.htm", method=RequestMethod.GET)
	public String drugCoreGet(@RequestParam(value="term",required=false) String name, Model model) {
		List<Drug> drugs = new ArrayList<Drug>();
		if(!StringUtils.isBlank(name)){
			drugs = Context.getConceptService().getDrugs(name);
		}else{
			drugs = Context.getConceptService().getAllDrugs();
		}
			model.addAttribute("drugs",drugs);
		return "/module/patientqueue/autocomplete/autoCompleteDrugCoreList";
	}
	@RequestMapping(value="/module/standard/autoCompleteDrugCoreList.htm", method=RequestMethod.POST)
	public String drugCorePost(@RequestParam(value="term",required=false) String name, Model model) {
		List<Drug> drugs = new ArrayList<Drug>();
		if(!StringUtils.isBlank(name)){
			drugs = Context.getConceptService().getDrugs(name);
		}else{
			drugs = Context.getConceptService().getAllDrugs();
		}
			model.addAttribute("drugs",drugs);
		return "/module/patientqueue/autocomplete/autoCompleteDrugCoreList";
	}
	@RequestMapping("/module/patientqueue/checkSession.htm")
	public String checkSession(HttpServletRequest request,Model model) {
		 if( Context.getAuthenticatedUser() != null &&  Context.getAuthenticatedUser().getId() != null){
			 model.addAttribute("session","1");
		 }else{
			 model.addAttribute("session","0");
		 }
		
		return "/module/patientqueue/session/checkSession";
	}
}
