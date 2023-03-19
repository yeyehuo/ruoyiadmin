package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.KnowledgeOnlineledge;

/**
 * 在线知识库Service接口
 * 
 * @author ruoyi
 * @date 2023-03-05
 */
public interface IKnowledgeOnlineledgeService 
{
    /**
     * 查询在线知识库
     * 
     * @param linkId 在线知识库主键
     * @return 在线知识库
     */
    public KnowledgeOnlineledge selectKnowledgeOnlineledgeByLinkId(Long linkId);

    /**
     * 查询在线知识库列表
     * 
     * @param knowledgeOnlineledge 在线知识库
     * @return 在线知识库集合
     */
    public List<KnowledgeOnlineledge> selectKnowledgeOnlineledgeList(KnowledgeOnlineledge knowledgeOnlineledge);

    /**
     * 新增在线知识库
     * 
     * @param knowledgeOnlineledge 在线知识库
     * @return 结果
     */
    public int insertKnowledgeOnlineledge(KnowledgeOnlineledge knowledgeOnlineledge);

    /**
     * 修改在线知识库
     * 
     * @param knowledgeOnlineledge 在线知识库
     * @return 结果
     */
    public int updateKnowledgeOnlineledge(KnowledgeOnlineledge knowledgeOnlineledge);

    /**
     * 批量删除在线知识库
     * 
     * @param linkIds 需要删除的在线知识库主键集合
     * @return 结果
     */
    public int deleteKnowledgeOnlineledgeByLinkIds(Long[] linkIds);

    /**
     * 删除在线知识库信息
     * 
     * @param linkId 在线知识库主键
     * @return 结果
     */
    public int deleteKnowledgeOnlineledgeByLinkId(Long linkId);
}
