package com.zzl.web.util;//package com.cnhutong.cs.mobile.web.util;
//
//import com.cnhutong.staff.enums.source.SourceLevel;
//
//import javax.persistence.Column;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//
//public class SourceData extends BaseData{
//
//	/**
//	 *
//	 */
//	private static final long serialVersionUID = 7186210776649702228L;
//
//	/**渠道来源*/
//	private String name;
//
//    /** 上级id **/
//    private Long sourceFid;
//
//    /**渠道等级*/
//    @Enumerated(EnumType.ORDINAL)
//    private SourceLevel level;
//
//    /**
//     * 是否需要审批
//     */
//    @Column(name = "is_need_audit")
//    private boolean needAudit;
//
//    /** 是否给cc跟进工具使用*/
//    @Column(name = "is_cc_follow_use",nullable=false,columnDefinition="bit default 0")
//    private boolean ccFollowUse;
//
//    /** 排序--cc跟进工具使用*/
//    private int sequence;
//
//    public SourceData(){}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public Long getSourceFid() {
//		return sourceFid;
//	}
//
//	public void setSourceFid(Long sourceFid) {
//		this.sourceFid = sourceFid;
//	}
//
//	public SourceLevel getLevel() {
//		return level;
//	}
//
//	public void setLevel(SourceLevel level) {
//		this.level = level;
//	}
//
//	public boolean isNeedAudit() {
//		return needAudit;
//	}
//
//	public void setNeedAudit(boolean needAudit) {
//		this.needAudit = needAudit;
//	}
//
//	public boolean isCcFollowUse() {
//		return ccFollowUse;
//	}
//
//	public void setCcFollowUse(boolean ccFollowUse) {
//		this.ccFollowUse = ccFollowUse;
//	}
//
//	public int getSequence() {
//		return sequence;
//	}
//
//	public void setSequence(int sequence) {
//		this.sequence = sequence;
//	}
//
//}
