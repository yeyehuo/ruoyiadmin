<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="标题" prop="linkName">
        <el-input
          v-model="queryParams.linkName"
          placeholder="请输入标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属分组" prop="belongGroup">
        <el-input
          v-model="queryParams.belongGroup"
          placeholder="请输入所属分组"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="标签/关键字" prop="belongLabel">
        <el-input
          v-model="queryParams.belongLabel"
          placeholder="请输入标签/关键字"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker clearable
          v-model="queryParams.createTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="修改时间" prop="updateTime">
        <el-date-picker clearable
          v-model="queryParams.updateTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择修改时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="是否允许借阅" prop="isBorrow">
        <el-select v-model="queryParams.isBorrow" placeholder="请选择是否允许借阅" clearable>
          <el-option
            v-for="dict in dict.type.is_borrow"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:onlineledge:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:onlineledge:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:onlineledge:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:onlineledge:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="onlineledgeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="标题" align="center" prop="linkName" />
      <el-table-column label="访问链接" align="center" prop="linkAddress" />
      <el-table-column label="所属分组" align="center" prop="belongGroup" />
      <el-table-column label="标签/关键字" align="center" prop="belongLabel" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="修改时间" align="center" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否允许借阅" align="center" prop="isBorrow">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.is_borrow" :value="scope.row.isBorrow"/>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:onlineledge:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:onlineledge:remove']"
          >删除</el-button>
               <el-button
                      size="mini"
                      type="text"
                      icon="el-icon-search"
                      @click="handlelink(scope.row)"
                      v-hasPermi="['system:onlineledge:gudie']"
                    >访问</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改在线知识库对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="标题" prop="linkName">
          <el-input v-model="form.linkName" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="访问链接" prop="linkAddress">
          <el-input v-model="form.linkAddress" placeholder="请输入访问链接" />
        </el-form-item>
        <el-form-item label="所属分组" prop="belongGroup">
          <el-input v-model="form.belongGroup" placeholder="请输入所属分组" />
        </el-form-item>
        <el-form-item label="标签/关键字" prop="belongLabel">
          <el-input v-model="form.belongLabel" placeholder="请输入标签/关键字" />
        </el-form-item>
        <el-form-item label="是否允许借阅" prop="isBorrow">
          <el-select v-model="form.isBorrow" placeholder="请选择是否允许借阅">
            <el-option
              v-for="dict in dict.type.is_borrow"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listOnlineledge, getOnlineledge, delOnlineledge, addOnlineledge, updateOnlineledge } from "@/api/system/onlineledge";

export default {
  name: "Onlineledge",
  dicts: ['is_borrow'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 在线知识库表格数据
      onlineledgeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        linkName: null,
        belongGroup: null,
        belongLabel: null,
        createTime: null,
        updateTime: null,
        isBorrow: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        linkName: [
          { required: true, message: "标题不能为空", trigger: "blur" }
        ],
        linkAddress: [
          { required: true, message: "访问链接不能为空", trigger: "blur" }
        ],
        belongGroup: [
          { required: true, message: "所属分组不能为空", trigger: "blur" }
        ],
        belongLabel: [
          { required: true, message: "标签/关键字不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询在线知识库列表 */
    getList() {
      this.loading = true;
      listOnlineledge(this.queryParams).then(response => {
        this.onlineledgeList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        linkId: null,
        linkName: null,
        linkAddress: null,
        belongGroup: null,
        belongLabel: null,
        createUserid: null,
        createTime: null,
        updateUserid: null,
        updateTime: null,
        isBorrow: null,
        remark: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.linkId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加在线知识库";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const linkId = row.linkId || this.ids
      getOnlineledge(linkId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改在线知识库";
      });
    },
       /** 修改按钮操作 */
        handlelink(row) {
          this.reset();
          const linkaddress = row.linkAddress+"";
          window.open(linkaddress);
        },

    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.linkId != null) {
            updateOnlineledge(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOnlineledge(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const linkIds = row.linkId || this.ids;
      this.$modal.confirm('是否确认删除在线知识库编号为"' + linkIds + '"的数据项？').then(function() {
        return delOnlineledge(linkIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/onlineledge/export', {
        ...this.queryParams
      }, `onlineledge_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
