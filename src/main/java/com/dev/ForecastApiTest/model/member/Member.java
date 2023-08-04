package com.dev.ForecastApiTest.model.member;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.dev.ForecastApiTest.model.quest.QuestManageLog;

import lombok.Data;

@Entity
@Table(name="TB_MEMBER")
@Data
public class Member {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MEMBER_ID")
	private Long id;
	
	@Column(name="MEMBER_WALLET")
	private String memberWallet;
	
	@Column(name="MEMBER_JOINDATE")
	private Date memberJoinDate;
	
	// Dao 여부 판단
	@Column(name="MEMBER_AUTHORIZATION")
	private Boolean memberAuthorization;
	
	@OneToMany(
			fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			mappedBy = "accessLogMemberId"
			)
	private List<MemberAccessLog> memberAccessLogs;
	
	@OneToMany(
			fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			mappedBy = "transferingLogMemberId"
			)
	private List<MemberTransferingLog> memberTransferingLogs;
	
	@OneToMany(
			fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			mappedBy = "votingLogMemberId"
			)
	private List<MemberVotingLog> memberVotingLogs;
	
	@OneToMany(
			fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			mappedBy = "actionLogMemberId"
			)
	private List<MemberActionLog> memberActionLogs;
	
	@OneToMany(
			fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			mappedBy = "manageLogMemberId"
			)
	private List<QuestManageLog> questManageLog;
}




















