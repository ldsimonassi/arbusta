package org.arbusta.operations;

import java.util.HashSet;

import org.arbusta.model.account.Price;
import org.arbusta.model.hits.Hit;
import org.arbusta.model.hits.HitType;
import org.arbusta.model.qualifications.QualificationRequirement;
import org.arbusta.model.qualifications.QualificationType;

public class Operations {
	/**
	 * Question Way to create a hit.
	 * @param hitTypeId
	 * @param question
	 * @param lifeTimeInSeconds
	 * @param requesterAnnotation
	 * @return HITs
	 * @throws Exception 
	 */
	public  Hit CreateHit(
			Long hitTypeId, String question, long lifeTimeInSeconds,
			Integer maxAssignments, String requesterAnnotation) throws Exception{
		Hit hit = new Hit(hitTypeId, question, lifeTimeInSeconds, maxAssignments, requesterAnnotation);
		hit.save();
		return hit;
	}

	public HitType RegisterHitType(
			String title, 
			String description, 
			Price reward, 
			String keywords, 
			long assignmentDurationInSeconds, 
			long autoApprovalDelayInSeconds, 
			HashSet<QualificationRequirement> qualificationRequirements) throws Exception {
		HitType hitType = new HitType(
									title, description, 
									reward, keywords, 
									assignmentDurationInSeconds, 
									autoApprovalDelayInSeconds);
		hitType.save();

		for (QualificationRequirement qualificationRequirement : qualificationRequirements) {
			qualificationRequirement.setHitTypeId(hitType.getId());
			qualificationRequirement.save();
		}
		return hitType;
	}

	public QualificationType CreateQualificationType(String name, String description, 
			String keywords, long retryDalyInSeconds, String qualificationTypeStatus, 
			String test, String answerKey, long testDurationInSeconds, 
			boolean autoGranted, Integer autoGrantedValue) throws Exception {
		QualificationType qualificationType = new QualificationType(name, description, keywords, retryDalyInSeconds, qualificationTypeStatus, test, answerKey, testDurationInSeconds, autoGranted, autoGrantedValue);
		qualificationType.save();
		return qualificationType;
	}

	public boolean AssignQualification(String qualificationTypeId, 
												String workerId, 
												int value, 
												boolean sendNotification) {
		return false;
	}
	
	public static void main(String[] args) {
		try {
			Operations oper = new Operations();

			// Create a Qualification Type
			QualificationType qt = oper.CreateQualificationType("Conocimientos de cocina", "Se solicita a los aspirantes que cuenten con conocimientos basicos decocina", "cocina, basico", 3600L*7, "Active", "", "", 3600L, false, null);

			System.out.println("Created qualification: " + qt.getId());
			System.out.println(qt);
			System.out.println("Loading again...!");

			QualificationType qt2 = QualificationType.load(qt.getId());

			System.out.println(qt2);

			//Create the hit type and the qualification requirements.
			HashSet<QualificationRequirement> requirements = new HashSet<QualificationRequirement>();
			requirements.add(new QualificationRequirement(0, 10, QualificationRequirement.GREATER_THAN, 5));
			HitType ht = oper.RegisterHitType("Categorizacion de imagenes", 
								 "Elija la imagen que mejor describa el producto que se muestra", 
								 new Price(0.1), "imagenes, categorizacion", 3600L, 
								 3600L*48, requirements);
			System.out.println(ht);
			Hit hit = oper.CreateHit(ht.getId(), "", 3600, 3, "REF:0023");
			System.out.println(hit);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}