<script setup>
import { reactive, onMounted } from "vue";
import { employeeApi } from "@/api/system/employee.js";

const query = reactive({
  name: ''
})

const data = reactive({
  tableData: [],
  total: 0,
  currentPage: 1,
  pageSize: 10
})


const loadData = () => {
  employeeApi.selectByPage(data.currentPage - 1, data.pageSize, query).then(res => {
    data.tableData = res.data.list
    data.total = res.data.total
  })
}

onMounted(() => {
  loadData()
})
</script>

<template>
  <div class="card mb-8">
    <el-input style="width: 200px; margin-right: 10px" v-model="query.name" placeholder="请输入姓名搜索"/>
    <el-button type="primary">查询</el-button>
    <el-button>重置</el-button>
  </div>
  <div class="card">
    <div class="buttons">
      <el-button type="primary">新增</el-button>
      <el-button type="warning">修改</el-button>
      <el-button type="danger">删除</el-button>
      <el-button type="info">导入</el-button>
      <el-button>导出</el-button>
    </div>
    <el-table :data="data.tableData">
      <el-table-column prop="no" label="工号" width="180" />
      <el-table-column prop="name" label="姓名" width="180" />
      <el-table-column prop="sex" label="性别" />
      <el-table-column prop="description" label="个人介绍" show-overflow-tooltip/>
      <el-table-column prop="birthday" label="生日" />
      <el-table-column prop="departmentId" label="部门" />
    </el-table>
    <el-pagination
        style="margin-top: 10px"
        v-model:current-page="data.currentPage"
        v-model:page-size="data.pageSize"
        :page-sizes="[10, 50, 80, 100]"
        layout="total, sizes, prev, pager, next, jumper"
        :total="data.total"
        @current-change="loadData"
        @size-change="loadData"
    />
  </div>
</template>

<style scoped>
.mb-8 {
  margin-bottom: 8px;
}
.buttons {
  margin-bottom: 5px;
}
</style>