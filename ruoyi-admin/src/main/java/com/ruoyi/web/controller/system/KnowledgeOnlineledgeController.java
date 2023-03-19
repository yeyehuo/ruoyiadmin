package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.KnowledgeOnlineledge;
import com.ruoyi.system.service.IKnowledgeOnlineledgeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 在线知识库Controller
 * 
 * @author ruoyi
 * @date 2023-03-05
 */
@RestController
@RequestMapping("/system/onlineledge")
public class KnowledgeOnlineledgeController extends BaseController
{
    @Autowired
    private IKnowledgeOnlineledgeService knowledgeOnlineledgeService;

    /**
     * 查询在线知识库列表
     */
    @PreAuthorize("@ss.hasPermi('system:onlineledge:list')")
    @GetMapping("/list")
    public TableDataInfo list(KnowledgeOnlineledge knowledgeOnlineledge)
    {
        startPage();
        List<KnowledgeOnlineledge> list = knowledgeOnlineledgeService.selectKnowledgeOnlineledgeList(knowledgeOnlineledge);
        return getDataTable(list);
    }

    /**
     * 导出在线知识库列表
     */
    @PreAuthorize("@ss.hasPermi('system:onlineledge:export')")
    @Log(title = "在线知识库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, KnowledgeOnlineledge knowledgeOnlineledge)
    {
        List<KnowledgeOnlineledge> list = knowledgeOnlineledgeService.selectKnowledgeOnlineledgeList(knowledgeOnlineledge);
        ExcelUtil<KnowledgeOnlineledge> util = new ExcelUtil<KnowledgeOnlineledge>(KnowledgeOnlineledge.class);
        util.exportExcel(response, list, "在线知识库数据");
    }

    /**
     * 获取在线知识库详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:onlineledge:query')")
    @GetMapping(value = "/{linkId}")
    public AjaxResult getInfo(@PathVariable("linkId") Long linkId)
    {
        return success(knowledgeOnlineledgeService.selectKnowledgeOnlineledgeByLinkId(linkId));
    }

    /**
     * 新增在线知识库
     */
    @PreAuthorize("@ss.hasPermi('system:onlineledge:add')")
    @Log(title = "在线知识库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KnowledgeOnlineledge knowledgeOnlineledge)
    {
        return toAjax(knowledgeOnlineledgeService.insertKnowledgeOnlineledge(knowledgeOnlineledge));
    }

    /**
     * 修改在线知识库
     */
    @PreAuthorize("@ss.hasPermi('system:onlineledge:edit')")
    @Log(title = "在线知识库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KnowledgeOnlineledge knowledgeOnlineledge)
    {
        return toAjax(knowledgeOnlineledgeService.updateKnowledgeOnlineledge(knowledgeOnlineledge));
    }

    /**
     * 删除在线知识库
     */
    @PreAuthorize("@ss.hasPermi('system:onlineledge:remove')")
    @Log(title = "在线知识库", businessType = BusinessType.DELETE)
	@DeleteMapping("/{linkIds}")
    public AjaxResult remove(@PathVariable Long[] linkIds)
    {
        return toAjax(knowledgeOnlineledgeService.deleteKnowledgeOnlineledgeByLinkIds(linkIds));
    }
}
