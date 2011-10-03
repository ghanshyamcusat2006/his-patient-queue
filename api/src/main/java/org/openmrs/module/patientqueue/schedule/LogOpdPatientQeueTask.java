/**
 * <p> File: org.openmrs.module.inventory.schedule.ExampleTask.java </p>
 * <p> Project: inventory-api </p>
 * <p> Copyright (c) 2011 HISP Technologies. </p>
 * <p> All rights reserved. </p>
 * <p> Author: Nguyen manh chuyen </p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Feb 24, 2011 4:31:57 PM </p>
 * <p> Update date: Feb 24, 2011 4:31:57 PM </p>
 **/

package org.openmrs.module.patientqueue.schedule;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.PatientQueueService;
import org.openmrs.module.hospitalcore.model.OpdPatientQueue;
import org.openmrs.module.hospitalcore.model.OpdPatientQueueLog;
import org.openmrs.scheduler.tasks.AbstractTask;

/**
 * <p> Class: ExampleTask </p>
 * <p> Package: org.openmrs.module.inventory.schedule </p> 
 * <p> Author: Nguyen manh chuyen </p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Feb 24, 2011 4:31:57 PM </p>
 * <p> Update date: Feb 24, 2011 4:31:57 PM </p>
 **/
public class LogOpdPatientQeueTask  extends AbstractTask {
	Log log = LogFactory.getLog(getClass());
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		log.info("Excute LogOpdPatientQeueTask");
		try {
			Context.openSession();
			/*if (!Context.isAuthenticated()) 
			{
				authenticate();
			}*/
			// do something
			
			PatientQueueService queueService = Context.getService(PatientQueueService.class);
			List<OpdPatientQueue> items = queueService.getAllPatientInQueue();
			if( items != null && items.size() > 0 )
			{
				for( OpdPatientQueue item : items ){
					OpdPatientQueueLog log = queueService.copyTo(item);
					queueService.saveOpdPatientQueueLog(log);
					queueService.deleteOpdPatientQueue(item);
				}
			}
			
			Context.closeSession();
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e);
		}
		
	}
	
	@Override
	protected void authenticate() {
		// TODO Auto-generated method stub
		super.authenticate();
	}

	@Override
	public void shutdown() {
		log.info("Shutdown LogOpdPatientQeueTask");
		super.shutdown();
	}

}
