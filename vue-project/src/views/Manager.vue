<script setup>
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import { House,DataAnalysis,Setting } from '@element-plus/icons-vue'
import { useRoute } from "vue-router";

const route = useRoute()
</script>

<template>
  <el-config-provider :locale="zhCn">
  <div class="common-layout">
    <el-container>
      <el-header class="header">
        <div class="logo">
          <img src="@/assets/logo.png" alt="">
          <span>后台管理系统</span>
        </div>
        <div class="center"></div>
        <div class="right">
          <el-avatar> user </el-avatar>
          <span> 超级管理员</span>
        </div>
      </el-header>
      <el-container>
        <el-aside class="aside" width="200px">
          <el-menu router :default-active="route.path" :default-openeds="['1']">
            <el-menu-item index="/manager/home"><el-icon><House /></el-icon>首页</el-menu-item>
            <el-menu-item index="/manager/data"><el-icon><DataAnalysis /></el-icon>数据统计</el-menu-item>
            <el-sub-menu index="1">
              <template #title>
                <el-icon><Setting /></el-icon>
                <span>系统管理</span>
              </template>
              <el-menu-item index="/manager/employee">员工管理</el-menu-item>
              <el-menu-item index="/manager/user">系统用户</el-menu-item>
            </el-sub-menu>
          </el-menu>
        </el-aside>
        <el-main class="main">
          <RouterView/>
        </el-main>
      </el-container>
    </el-container>
  </div>
  </el-config-provider>
</template>

<style scoped lang="scss">
.header {
  background: #2188fa;
  line-height: 60px;
  color: #fff;
  display: flex;
  .logo {
    width: 200px;
    display: flex;
    align-items: center;
    img {
      width: 30px;
      margin-right: 5px;
    }
    span {
      font-size: 20px;
    }
  }
  .center {
    flex: 1;
  }
  .right {
    width: fit-content;
  }
}
.aside {
  border-right: 1px solid #ddd;
  min-height: calc(100vh - 60px);
}
.main {
  background: #f2f6fa;
  padding: 10px;
}
.el-menu-item.is-active {
  background: rgb(232, 243, 254) ;
}
</style>