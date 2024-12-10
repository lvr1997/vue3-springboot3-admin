<script setup>
import { ref, reactive, onMounted } from "vue";
import { employeeApi } from "@/api/system/employee.js";
import {ElMessage,ElMessageBox} from "element-plus";

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
  employeeApi.selectByPage(data.currentPage, data.pageSize, query).then(res => {
    data.tableData = res.data.list
    data.total = res.data.total
  })
}
const formRef = ref()
const form = reactive({
    data: {
      id: null,
      username: '',
      password: '',
      name: '',
      no: '',
      sex: 'M',
      description: '',
      birthday: '',
      departmentId: '',
    },
    dialogVisible: false,
    dialogTitle: '新增员工',
})

const submitForm = () => {
  formRef.value.validate((valid) => {
    if(valid) {
      form.data.id ? edit() : add()
    }
  })
}
const handleAdd = () => {
  form.dialogVisible = true
  form.dialogTitle = '新增员工'
  setTimeout(()=>{
    formRef.value.resetField
  }, 200)
}
const handleEdit = (row) => {
  form.data = row
  form.dialogVisible = true
  form.dialogTitle = '编辑员工'
}
const add = () => {
  employeeApi.addOne(form.data).then(res => {
    if (res.code === '200') {
      ElMessage.success("添加成功")
      form.dialogVisible = false
      loadData()
    }
  })
}

const edit = () => {
  employeeApi.updateById(form.data).then(res => {
    if (res.code === '200') {
      ElMessage.success("修改成功")
      form.dialogVisible = false
      loadData()
    }
  })
}

const handleDelete = (row) => {
  ElMessageBox.confirm(
      '确定删除该员工吗?',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }
  )
      .then(() => {
        employeeApi.deleteById(row.id).then(res=>{
          if(res.code === '200') {
            ElMessage.success("删除成功")
          }
        }).finally(() => {
          loadData()
        })
      })
      .catch(() => {
        ElMessage({
          type: 'info',
          message: '已取消删除',
        })
      })
}

const rules = reactive({
  username: [
    { required: true, message: '请输入账号', trigger: 'blur' },
    { min: 3, max: 5, message: 'Length should be 3 to 5', trigger: 'blur' },
  ],
  name: [
    { required: true, message: '请输入员工姓名', trigger: 'blur' },
    { min: 2, max: 20, message: 'Length should be 2 to 5', trigger: 'blur' },
  ],
  password: [
    { required: true, message: '请输入登录密码', trigger: 'blur' },
    { min: 3, max: 6, message: 'Length should be 3 to 5', trigger: 'blur' },
  ],
  role: [
    {
      required: true,
      message: 'Please select role',
      trigger: 'change',
    },
  ],
  sex: [
    {
      required: true,
      message: 'Please select sex',
      trigger: 'change',
    },
  ],
  departmentId: [
    {
      required: true,
      message: 'Please select department',
      trigger: 'change',
    },
  ],
  birthday: [
    {
      type: 'date',
      required: true,
      message: 'Please pick a date',
      trigger: 'change',
    },
  ],
})

onMounted(() => {
  loadData()
})
</script>

<template>
  <div class="card mb-8">
    <el-input style="width: 200px; margin-right: 10px" v-model="query.name" placeholder="请输入姓名搜索"/>
    <el-button type="primary" @click="loadData">查询</el-button>
    <el-button>重置</el-button>
  </div>
  <div class="card">
    <div class="buttons">
      <el-button type="primary" @click="handleAdd">新增</el-button>
      <el-button type="warning">修改</el-button>
      <el-button type="danger">删除</el-button>
      <el-button type="info">导入</el-button>
      <el-button>导出</el-button>
    </div>
    <el-table :data="data.tableData">
      <el-table-column prop="no" label="工号" width="180" />
      <el-table-column prop="name" label="姓名" width="180" />
      <el-table-column prop="username" label="账号" width="180" />
      <el-table-column prop="role" label="角色" width="180" />
      <el-table-column prop="sex" label="性别" />
      <el-table-column prop="description" label="个人介绍" show-overflow-tooltip/>
      <el-table-column prop="birthday" label="生日" />
      <el-table-column prop="departmentId" label="部门" />
      <el-table-column fixed="right" label="操作">
        <template #default="scope">
          <el-button link type="primary" size="small" @click="handleEdit(scope.row)">
            编辑
          </el-button>
          <el-button link type="danger" size="small" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
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

    <el-dialog v-model="form.dialogVisible" :title="form.dialogTitle" width="500" destroy-on-close>
      <el-form ref="formRef" :model="form.data" label-width="100" :rules="rules">
        <el-form-item label="员工账号" prop="username">
          <el-input v-model="form.data.username" autocomplete="off" />
        </el-form-item>
        <el-form-item label="员工密码" prop="password">
          <el-input v-model="form.data.password" type="password" autocomplete="off" />
        </el-form-item>
        <el-form-item label="员工姓名" prop="name">
          <el-input v-model="form.data.name" autocomplete="off" />
        </el-form-item>
        <el-form-item label="员工工号" prop="no">
          <el-input v-model="form.data.no"/>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-radio-group v-model="form.data.sex">
            <el-radio :value="'M'">男</el-radio>
            <el-radio :value="'F'">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="生日" prop="birthday">
          <el-date-picker
              v-model="form.data.birthday"
              type="date"
              placeholder="Pick a day"
              value-format="YYYY-MM-DD"
          />
        </el-form-item>
        <el-form-item label="简介" prop="description">
          <el-input v-model="form.data.description" type="textarea"/>
        </el-form-item>
        <el-form-item label="部门" prop="departmentId">
          <el-select v-model="form.data.departmentId">
            <el-option label="采购部" :value="1" />
            <el-option label="技术部" :value="2" />
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="form.dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="submitForm">保存</el-button>
        </div>
      </template>
    </el-dialog>
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