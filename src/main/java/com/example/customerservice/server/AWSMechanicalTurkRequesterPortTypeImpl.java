package com.example.customerservice.server;

import java.math.BigDecimal;
import java.util.List;

import com.amazonaws.mturk.requester.doc._2012_03_25.AWSMechanicalTurkRequesterPortType;
import com.amazonaws.mturk.requester.doc._2012_03_25.ApproveAssignment;
import com.amazonaws.mturk.requester.doc._2012_03_25.ApproveAssignmentResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.ApproveRejectedAssignment;
import com.amazonaws.mturk.requester.doc._2012_03_25.ApproveRejectedAssignmentResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.AssignQualification;
import com.amazonaws.mturk.requester.doc._2012_03_25.AssignQualificationResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.BlockWorker;
import com.amazonaws.mturk.requester.doc._2012_03_25.BlockWorkerResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.ChangeHITTypeOfHIT;
import com.amazonaws.mturk.requester.doc._2012_03_25.ChangeHITTypeOfHITResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.CreateHIT;
import com.amazonaws.mturk.requester.doc._2012_03_25.CreateHITRequest;
import com.amazonaws.mturk.requester.doc._2012_03_25.CreateHITResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.CreateQualificationType;
import com.amazonaws.mturk.requester.doc._2012_03_25.CreateQualificationTypeResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.DisableHIT;
import com.amazonaws.mturk.requester.doc._2012_03_25.DisableHITResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.DisposeHIT;
import com.amazonaws.mturk.requester.doc._2012_03_25.DisposeHITResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.DisposeQualificationType;
import com.amazonaws.mturk.requester.doc._2012_03_25.DisposeQualificationTypeResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.ExtendHIT;
import com.amazonaws.mturk.requester.doc._2012_03_25.ExtendHITResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.ForceExpireHIT;
import com.amazonaws.mturk.requester.doc._2012_03_25.ForceExpireHITResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.GetAccountBalance;
import com.amazonaws.mturk.requester.doc._2012_03_25.GetAccountBalanceResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.GetAccountBalanceResult;
import com.amazonaws.mturk.requester.doc._2012_03_25.GetAssignment;
import com.amazonaws.mturk.requester.doc._2012_03_25.GetAssignmentResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.GetAssignmentsForHIT;
import com.amazonaws.mturk.requester.doc._2012_03_25.GetAssignmentsForHITResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.GetBlockedWorkers;
import com.amazonaws.mturk.requester.doc._2012_03_25.GetBlockedWorkersResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.GetBonusPayments;
import com.amazonaws.mturk.requester.doc._2012_03_25.GetBonusPaymentsResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.GetFileUploadURL;
import com.amazonaws.mturk.requester.doc._2012_03_25.GetFileUploadURLResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.GetHIT;
import com.amazonaws.mturk.requester.doc._2012_03_25.GetHITResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.GetHITsForQualificationType;
import com.amazonaws.mturk.requester.doc._2012_03_25.GetHITsForQualificationTypeResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.GetQualificationRequests;
import com.amazonaws.mturk.requester.doc._2012_03_25.GetQualificationRequestsResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.GetQualificationScore;
import com.amazonaws.mturk.requester.doc._2012_03_25.GetQualificationScoreResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.GetQualificationType;
import com.amazonaws.mturk.requester.doc._2012_03_25.GetQualificationTypeResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.GetQualificationsForQualificationType;
import com.amazonaws.mturk.requester.doc._2012_03_25.GetQualificationsForQualificationTypeResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.GetRequesterStatistic;
import com.amazonaws.mturk.requester.doc._2012_03_25.GetRequesterStatisticResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.GetRequesterWorkerStatistic;
import com.amazonaws.mturk.requester.doc._2012_03_25.GetRequesterWorkerStatisticResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.GetReviewResultsForHIT;
import com.amazonaws.mturk.requester.doc._2012_03_25.GetReviewResultsForHITResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.GetReviewableHITs;
import com.amazonaws.mturk.requester.doc._2012_03_25.GetReviewableHITsResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.GrantBonus;
import com.amazonaws.mturk.requester.doc._2012_03_25.GrantBonusResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.GrantQualification;
import com.amazonaws.mturk.requester.doc._2012_03_25.GrantQualificationResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.HIT;
import com.amazonaws.mturk.requester.doc._2012_03_25.Help;
import com.amazonaws.mturk.requester.doc._2012_03_25.HelpResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.NotifyWorkers;
import com.amazonaws.mturk.requester.doc._2012_03_25.NotifyWorkersResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.OperationRequest;
import com.amazonaws.mturk.requester.doc._2012_03_25.Price;
import com.amazonaws.mturk.requester.doc._2012_03_25.RegisterHITType;
import com.amazonaws.mturk.requester.doc._2012_03_25.RegisterHITTypeResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.RejectAssignment;
import com.amazonaws.mturk.requester.doc._2012_03_25.RejectAssignmentResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.RejectQualificationRequest;
import com.amazonaws.mturk.requester.doc._2012_03_25.RejectQualificationRequestResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.Request;
import com.amazonaws.mturk.requester.doc._2012_03_25.RevokeQualification;
import com.amazonaws.mturk.requester.doc._2012_03_25.RevokeQualificationResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.SearchHITs;
import com.amazonaws.mturk.requester.doc._2012_03_25.SearchHITsResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.SearchQualificationTypes;
import com.amazonaws.mturk.requester.doc._2012_03_25.SearchQualificationTypesResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.SendTestEventNotification;
import com.amazonaws.mturk.requester.doc._2012_03_25.SendTestEventNotificationResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.SetHITAsReviewing;
import com.amazonaws.mturk.requester.doc._2012_03_25.SetHITAsReviewingResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.SetHITTypeNotification;
import com.amazonaws.mturk.requester.doc._2012_03_25.SetHITTypeNotificationResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.UnblockWorker;
import com.amazonaws.mturk.requester.doc._2012_03_25.UnblockWorkerResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.UpdateQualificationScore;
import com.amazonaws.mturk.requester.doc._2012_03_25.UpdateQualificationScoreResponse;
import com.amazonaws.mturk.requester.doc._2012_03_25.UpdateQualificationType;
import com.amazonaws.mturk.requester.doc._2012_03_25.UpdateQualificationTypeResponse;

public class AWSMechanicalTurkRequesterPortTypeImpl  implements AWSMechanicalTurkRequesterPortType{

	@Override
	public NotifyWorkersResponse notifyWorkers(NotifyWorkers body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExtendHITResponse extendHIT(ExtendHIT body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetRequesterStatisticResponse getRequesterStatistic(
			GetRequesterStatistic body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetBlockedWorkersResponse getBlockedWorkers(GetBlockedWorkers body) {
		// TODO Auto-generated method stub
		return null;
	}

	//TODO Implement
	@Override
	public CreateQualificationTypeResponse createQualificationType(
			CreateQualificationType body) {
		// TODO Auto-generated method stub
		return null;
	}

	//TODO Implement
	@Override
	public DisposeQualificationTypeResponse disposeQualificationType(
			DisposeQualificationType body) {
		// TODO Auto-generated method stub
		return null;
	}

	//TODO Implement
	@Override
	public GetHITsForQualificationTypeResponse getHITsForQualificationType(
			GetHITsForQualificationType body) {
		// TODO Auto-generated method stub
		return null;
	}

	
	//TODO Implement
	@Override
	public GetQualificationRequestsResponse getQualificationRequests(
			GetQualificationRequests body) {
		// TODO Auto-generated method stub
		return null;
	}

	//TODO Implement
	@Override
	public CreateHITResponse createHIT(CreateHIT body) {
		System.out.println("CreateHit Method Call recieved");
		List<CreateHITRequest> requests = body.getRequest();
		for (CreateHITRequest createHITRequest : requests) {
			System.out.println("\t----- Request ----");
			System.out.println("\tTitle: "+createHITRequest.getTitle());
			System.out.println("\tDescription: "+ createHITRequest.getDescription());
			System.out.println("\tQuestion: "+createHITRequest.getQuestion());
			System.out.println("\tMax Assignments: " + createHITRequest.getMaxAssignments());
			System.out.println("\tReward:"+createHITRequest.getReward().getCurrencyCode()+ createHITRequest.getReward().getAmount());
		}
		
		CreateHITResponse response = new CreateHITResponse();
		OperationRequest operationRequest = new OperationRequest();
		operationRequest.setRequestId("b262c39f-9e1f-48a9-bf8b-c216c9b313fd");
		HIT hit = new HIT(); 
		Request request = new Request();
		request.setIsValid("True");
		hit.setRequest(request);
		hit.setHITId("36FFXPMST9OACHKMUWLAEINLNE2OHV");
		hit.setHITTypeId("36FFXPMST9OACHKMUWLAEINLNE2OHV");
		response.getHIT().add(hit);
		response.setOperationRequest(operationRequest);
		return response;
	}

	@Override
	public HelpResponse help(Help body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetRequesterWorkerStatisticResponse getRequesterWorkerStatistic(
			GetRequesterWorkerStatistic body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetBonusPaymentsResponse getBonusPayments(GetBonusPayments body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UnblockWorkerResponse unblockWorker(UnblockWorker body) {
		// TODO Auto-generated method stub
		return null;
	}

	//TODO: Implement
	@Override
	public GetReviewableHITsResponse getReviewableHITs(GetReviewableHITs body) {
		// TODO Auto-generated method stub
		return null;
	}

	//TODO: 
	@Override
	public GetReviewResultsForHITResponse getReviewResultsForHIT(
			GetReviewResultsForHIT body) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public GetQualificationScoreResponse getQualificationScore(
			GetQualificationScore body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetHITResponse getHIT(GetHIT body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ForceExpireHITResponse forceExpireHIT(ForceExpireHIT body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AssignQualificationResponse assignQualification(
			AssignQualification body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GrantBonusResponse grantBonus(GrantBonus body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetAccountBalanceResponse getAccountBalance(GetAccountBalance body) {
		// TODO Auto-generated method stub
		
		
		System.out.println("GetAcccontBalance("+body+")");
		System.out.flush();
		
		GetAccountBalanceResponse response = new GetAccountBalanceResponse();
		
		
		OperationRequest or = new OperationRequest();
		or.setRequestId("97ab508e-c445-4117-a9df-fb0cb184a0ba");
		response.setOperationRequest(or);
		
		GetAccountBalanceResult balanceResult = new GetAccountBalanceResult();
		Request request = new Request();
		request.setIsValid("True");
		balanceResult.setRequest(new Request());
		Price p = new Price();
		p.setAmount(new BigDecimal(10000));
		p.setCurrencyCode("USD");
		p.setFormattedPrice("$10,000.00");
		balanceResult.setAvailableBalance(p);
		
		response.getGetAccountBalanceResult().add(balanceResult);
		
		System.out.println("List " + response.getGetAccountBalanceResult().size());
		return response;
	}

	@Override
	public RevokeQualificationResponse revokeQualification(
			RevokeQualification body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetQualificationsForQualificationTypeResponse getQualificationsForQualificationType(
			GetQualificationsForQualificationType body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SendTestEventNotificationResponse sendTestEventNotification(
			SendTestEventNotification body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApproveAssignmentResponse approveAssignment(ApproveAssignment body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChangeHITTypeOfHITResponse changeHITTypeOfHIT(ChangeHITTypeOfHIT body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RejectQualificationRequestResponse rejectQualificationRequest(
			RejectQualificationRequest body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SearchHITsResponse searchHITs(SearchHITs body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetQualificationTypeResponse getQualificationType(
			GetQualificationType body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetAssignmentsForHITResponse getAssignmentsForHIT(
			GetAssignmentsForHIT body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DisableHITResponse disableHIT(DisableHIT body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SetHITAsReviewingResponse setHITAsReviewing(SetHITAsReviewing body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpdateQualificationTypeResponse updateQualificationType(
			UpdateQualificationType body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BlockWorkerResponse blockWorker(BlockWorker body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SearchQualificationTypesResponse searchQualificationTypes(
			SearchQualificationTypes body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegisterHITTypeResponse registerHITType(RegisterHITType body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SetHITTypeNotificationResponse setHITTypeNotification(
			SetHITTypeNotification body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GrantQualificationResponse grantQualification(GrantQualification body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApproveRejectedAssignmentResponse approveRejectedAssignment(
			ApproveRejectedAssignment body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetAssignmentResponse getAssignment(GetAssignment body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DisposeHITResponse disposeHIT(DisposeHIT body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpdateQualificationScoreResponse updateQualificationScore(
			UpdateQualificationScore body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetFileUploadURLResponse getFileUploadURL(GetFileUploadURL body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RejectAssignmentResponse rejectAssignment(RejectAssignment body) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
