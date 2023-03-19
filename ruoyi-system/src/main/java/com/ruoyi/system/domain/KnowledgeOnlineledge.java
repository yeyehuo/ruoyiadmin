package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 在线知识库对象 knowledge_onlineledge
 * 
 * @author ruoyi
 * @date 2023-03-05
 */
public class KnowledgeOnlineledge extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 链接ID */
    private Long linkId;

    /** 标题 */
    @Excel(name = "标题")
    private String linkName;

    /** 访问链接 */
    @Excel(name = "访问链接")
    private String linkAddress;

    /** 所属分组 */
    @Excel(name = "所属分组")
    private String belongGroup;

    /** 标签/关键字 */
    @Excel(name = "标签/关键字")
    private String belongLabel;

    /** 创建用户ID */
    private Long createUserid;

    /** 修改用户ID */
    private Long updateUserid;

    /** 是否允许借阅（0允许1不允许） */
    @Excel(name = "是否允许借阅", readConverterExp = "0=允许1不允许")
    private Integer isBorrow;

    public void setLinkId(Long linkId) 
    {
        this.linkId = linkId;
    }

    public Long getLinkId() 
    {
        return linkId;
    }
    public void setLinkName(String linkName) 
    {
        this.linkName = linkName;
    }

    public String getLinkName() 
    {
        return linkName;
    }
    public void setLinkAddress(String linkAddress) 
    {
        this.linkAddress = linkAddress;
    }

    public String getLinkAddress() 
    {
        return linkAddress;
    }
    public void setBelongGroup(String belongGroup) 
    {
        this.belongGroup = belongGroup;
    }

    public String getBelongGroup() 
    {
        return belongGroup;
    }
    public void setBelongLabel(String belongLabel) 
    {
        this.belongLabel = belongLabel;
    }

    public String getBelongLabel() 
    {
        return belongLabel;
    }
    public void setCreateUserid(Long createUserid) 
    {
        this.createUserid = createUserid;
    }

    public Long getCreateUserid() 
    {
        return createUserid;
    }
    public void setUpdateUserid(Long updateUserid) 
    {
        this.updateUserid = updateUserid;
    }

    public Long getUpdateUserid() 
    {
        return updateUserid;
    }
    public void setIsBorrow(Integer isBorrow) 
    {
        this.isBorrow = isBorrow;
    }

    public Integer getIsBorrow() 
    {
        return isBorrow;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("linkId", getLinkId())
            .append("linkName", getLinkName())
            .append("linkAddress", getLinkAddress())
            .append("belongGroup", getBelongGroup())
            .append("belongLabel", getBelongLabel())
            .append("createUserid", getCreateUserid())
            .append("createTime", getCreateTime())
            .append("updateUserid", getUpdateUserid())
            .append("updateTime", getUpdateTime())
            .append("isBorrow", getIsBorrow())
            .append("remark", getRemark())
            .toString();
    }
}
