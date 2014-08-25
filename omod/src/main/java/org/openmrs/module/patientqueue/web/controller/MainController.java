/**
 *  Copyright 2010 Society for Health Information Systems Programmes, India (HISP India)
 *
 *  This file is part of Patient-queue module.
 *
 *  Patient-queue module is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.

 *  Patient-queue module is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Patient-queue module.  If not, see <http://www.gnu.org/licenses/>.
 *
 **/

package org.openmrs.module.patientqueue.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.openmrs.Concept;
import org.openmrs.ConceptAnswer;
import org.openmrs.Role;
import org.openmrs.User;
import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.util.ConceptAnswerComparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("PatientQueueMainControllerStandard")
@RequestMapping("/module/patientqueue/main.htm")
public class MainController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String firstView(@RequestParam(value="opdId",required=false) Integer opdId, Model model, HttpSession session){
		String roleName="";
		User usr = Context.getAuthenticatedUser();
		Set<Role> rl = usr.getRoles();
		for(Role r:rl){
			if(r.getName().equalsIgnoreCase("Triage User")){
				roleName="triageUser";
				Concept triageConcept = Context.getConceptService().getConceptByName("TRIAGE");
				List<ConceptAnswer> list = (triageConcept!= null ?  new ArrayList<ConceptAnswer>(triageConcept.getAnswers()) : null);
				if(CollectionUtils.isNotEmpty(list)){
					Collections.sort(list, new ConceptAnswerComparator());
				}
				model.addAttribute("listOPD",list);
				
				if( opdId == null ){
					opdId =  (Integer) session.getAttribute("opdRoomId");
				}else{
					session.setAttribute("opdRoomId", opdId);
				}
				model.addAttribute("opdId", opdId);
			}
			else if(r.getName().equalsIgnoreCase("Doctor")){
				roleName="doctor";
				Concept opdWardConcept = Context.getConceptService().getConceptByName("OPD WARD");
				List<ConceptAnswer> list = (opdWardConcept!= null ?  new ArrayList<ConceptAnswer>(opdWardConcept.getAnswers()) : null);
				if(CollectionUtils.isNotEmpty(list)){
					Collections.sort(list, new ConceptAnswerComparator());
				}
				model.addAttribute("listOPD",list);
				
				if( opdId == null ){
					opdId =  (Integer) session.getAttribute("opdRoomId");
				}else{
					session.setAttribute("opdRoomId", opdId);
				}
				model.addAttribute("opdId", opdId);
			}
			else{
				roleName="sd";
				Concept opdWardConcept = Context.getConceptService().getConceptByName("OPD WARD");
				Concept triageConcept = Context.getConceptService().getConceptByName("TRIAGE");
				List<ConceptAnswer> tList = (triageConcept!= null ?  new ArrayList<ConceptAnswer>(triageConcept.getAnswers()) : null);
				List<ConceptAnswer> oList = (opdWardConcept!= null ?  new ArrayList<ConceptAnswer>(opdWardConcept.getAnswers()) : null);
				oList.addAll(tList);
				if(CollectionUtils.isNotEmpty(oList)){
					Collections.sort(oList, new ConceptAnswerComparator());
				}
				model.addAttribute("listOPD",oList);
				
				if( opdId == null ){
					opdId =  (Integer) session.getAttribute("opdRoomId");
				}else{
					session.setAttribute("opdRoomId", opdId);
				}
				model.addAttribute("opdId", opdId);
			}
		}
		if(roleName.equals("triageUser")){
			return "module/patientqueue/mainTriage";	
		}
		else{
			return "module/patientqueue/mainOPD";	
		}
		
	}
}
