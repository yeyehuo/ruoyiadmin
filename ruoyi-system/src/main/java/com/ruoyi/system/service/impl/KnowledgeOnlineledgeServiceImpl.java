package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.KnowledgeOnlineledgeMapper;
import com.ruoyi.system.domain.KnowledgeOnlineledge;
import com.ruoyi.system.service.IKnowledgeOnlineledgeService;

/**
 * 在线知识库Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-05
 */
@Service
public class KnowledgeOnlineledgeServiceImpl implements IKnowledgeOnlineledgeService 
{
    @Autowired
    private KnowledgeOnlineledgeMapper knowledgeOnlineledgeMapper;

    /**
     * 查询在线知识库
     * 
     * @param linkId 在线知识库主键
     * @return 在线知识库
     */
    @Override
    public KnowledgeOnlineledge selectKnowledgeOnlineledgeByLinkId(Long linkId)
    {
        return knowledgeOnlineledgeMapper.selectKnowledgeOnlineledgeByLinkId(linkId);
    }

    /**
     * 查询在线知识库列表
     * 
     * @param knowledgeOnlineledge 在线知识库
     * @return 在线知识库
     */
    @Override
    public List<KnowledgeOnlineledge> selectKnowledgeOnlineledgeList(KnowledgeOnlineledge knowledgeOnlineledge)
    {
        return knowledgeOnlineledgeMapper.selectKnowledgeOnlineledgeList(knowledgeOnlineledge);
    }

    /**
     * 新增在线知识库
     * 
     * @param knowledgeOnlineledge 在线知识库
     * @return 结果
     */
    @Override
    public int insertKnowledgeOnlineledge(KnowledgeOnlineledge knowledgeOnlineledge)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        if(null!=user.getUserId()){
            knowledgeOnlineledge.setCreateUserid(user.getUserId());
            knowledgeOnlineledge.setUpdateUserid(user.getUserId());
        }
        knowledgeOnlineledge.setCreateTime(DateUtils.getNowDate());
        return knowledgeOnlineledgeMapper.insertKnowledgeOnlineledge(knowledgeOnlineledge);
    }

    /**
     * 修改在线知识库
     * 
     * @param knowledgeOnlineledge 在线知识库
     * @return 结果
     */
    @Override
    public int updateKnowledgeOnlineledge(KnowledgeOnlineledge knowledgeOnlineledge)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        if(null!=user.getUserId()){
            knowledgeOnlineledge.setUpdateUserid(user.getUserId());
        }
        knowledgeOnlineledge.setUpdateTime(DateUtils.getNowDate());
        return knowledgeOnlineledgeMapper.updateKnowledgeOnlineledge(knowledgeOnlineledge);
    }

    /**
     * 批量删除在线知识库
     * 
     * @param linkIds 需要删除的在线知识库主键
     * @return 结果
     */
    @Override
    public int deleteKnowledgeOnlineledgeByLinkIds(Long[] linkIds)
    {
        return knowledgeOnlineledgeMapper.deleteKnowledgeOnlineledgeByLinkIds(linkIds);
    }

    /**
     * 删除在线知识库信息
     * 
     * @param linkId 在线知识库主键
     * @return 结果
     */
    @Override
    public int deleteKnowledgeOnlineledgeByLinkId(Long linkId)
    {
        return knowledgeOnlineledgeMapper.deleteKnowledgeOnlineledgeByLinkId(linkId);
    }
}
