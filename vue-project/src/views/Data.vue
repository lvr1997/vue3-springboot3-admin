<script setup>
import { reactive } from "vue";

const tableData = [
  {
    date: '2016-05-03',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-02',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-04',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-01',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
]
const data = reactive({
  input: '',
  tableData: tableData,
  total: 400,
  currentPage: 1,
  pageSize: 10
})
</script>

<template>
  <div class="card mb-8">
    <el-input style="width: 200px; margin-right: 10px" v-model="data.input" placeholder="请输入内容"/>
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
      <el-table-column prop="date" label="日期" width="180" />
      <el-table-column prop="name" label="名称" width="180" />
      <el-table-column prop="address" label="地址" />
    </el-table>
    <el-pagination
        style="margin-top: 10px"
        v-model:current-page="data.currentPage"
        v-model:page-size="data.pageSize"
        :page-sizes="[10, 50, 80, 100]"
        layout="total, sizes, prev, pager, next, jumper"
        :total="400"
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