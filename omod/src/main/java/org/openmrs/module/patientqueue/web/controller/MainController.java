package org.openmrs.module.patientqueue.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.openmrs.Concept;
import org.openmrs.ConceptAnswer;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.HospitalCoreService;
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
		/*List<Patient> patients = Context.getService(HospitalCoreService.class).searchPatient("test","M",28 , 2,"01/01/1983", 0,"");
		System.out.println("patients: "+patients);
		if(CollectionUtils.isNotEmpty(patients)){
			for(Patient patient : patients){
				System.out.println("patient iden: "+patient.getPatientIdentifier());
				System.out.println("patient gen: "+patient.getGender());
				System.out.println("patient birtday: "+patient.getBirthdate());
			}
		}*/
		model.addAttribute("opdId", opdId);
		return "module/patientqueue/main";
	}
	
	//D:\TOOL\mvn_repo\org\openmrs\module\hospitalcore-omod\1.4.8.10\hospitalcore-omod-1.4.8.10.omod
}
