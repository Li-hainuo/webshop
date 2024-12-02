<template>
  <div class="grid grid-cols-2 h-screen">
    <!--左边栏-->
    <div class="col-span-2 order-2 md:col-span-1 md:order-1 bg-slate-900">
      <!-- 指定为 flex 布局，并设置为屏幕垂直水平居中，高度为 100% -->
      <div class="flex justify-center items-center h-full animate__animated animate__bounceInLeft animate__fast">
        <!-- 默认占两列，order 用于指定排列顺序，md 用于适配非移动端（PC 端） -->
        <div class="col-span-2 order-2 p-10 md:col-span-1 md:order-1">
          <!-- 指定为 flex 布局，并设置为屏幕垂直水平居中，高度为 100% -->
          <div class="flex justify-center items-center h-full flex-col">
            <h2 class="font-bold text-4xl mb-7 text-white">WebShop 用户注册</h2>
            <p class="text-white">一款由 Spring Boot + Mybaits Plus + Vue 3.5 + Vite 4</p>
            <p class="text-white">开发的前后端分离网上商店</p>
            <!-- 指定图片宽度为父级元素的 1/2 -->
            <img src="@/assets/shop.svg" class="w-1/2">
            <p class="text-white">作者:lihainuo</p>
          </div>
        </div>
      </div>
    </div>

    <!--右边栏-->
    <div class="col-span-2 order-1 md:col-span-1 md:order-2 bg-white">
      <div class="flex justify-center items-center h-full flex-col animate__animated animate__bounceInRight animate__fast">
        <!-- 大标题，设置字体粗细、大小、下边距 -->
        <h1 class="font-bold text-4xl mb-5">用户注册</h1>
        <!-- 设置 flex 布局，内容垂直水平居中，文字颜色，以及子内容水平方向 x 轴间距 -->
        <div class="flex items-center justify-center mb-7 text-gray-400 space-x-2">
          <!-- 左边横线，高度为 1px, 宽度为 16，背景色设置 -->
          <span class="h-[1px] w-16 bg-gray-200"></span>
          <span>用户注册</span>
          <!-- 右边横线 -->
          <span class="h-[1px] w-16 bg-gray-200"></span>
        </div>
        <!-- 引入 Element Plus 表单组件，移动端设置宽度为 5/6，PC 端设置为 2/5 -->
        <el-form class="w-5/6 md:w-2/5" ref="formRef" :rules="rules" :model="form">
          <el-form-item prop="username">
            <!-- 输入框组件 -->
            <el-input size="large" v-model="form.username" placeholder="请输入用户名" :prefix-icon="User" clearable/>
          </el-form-item>
          <el-form-item prop="password">
            <!-- 密码框组件 -->
            <el-input size="large" type="password" v-model="form.password" placeholder="输入密码" :prefix-icon="Lock" clearable show-password/>
          </el-form-item>
          <el-form-item>
            <!-- 登录按钮，宽度设置为 100% -->
            <el-button class="w-full" size="large" type="primary" color="#0f172a" @click="onSubmit">注册</el-button>
          </el-form-item>
        </el-form>
        <div class="flex items-center justify-center mb-7 text-gray-400 space-x-2">
          <!-- 左边横线，高度为 1px, 宽度为 16，背景色设置 -->
          <span class="h-[1px] w-16 bg-gray-200"></span>
          <span><button @click="router.push('/login')">返回登录页</button></span>
          <!-- 右边横线 -->
          <span class="h-[1px] w-16 bg-gray-200"></span>
        </div>
      </div>
    </div>

  </div>
</template>


<script setup>
// 引入 Element Plus 中的用户、锁图标
import {User, Lock} from '@element-plus/icons-vue'
import {register} from '@/api/backdesk/user'
import {useRouter} from 'vue-router'
import {showMessage} from '@/composables/util'
import { ref, reactive, onMounted, onBeforeUnmount } from 'vue'

// 按回车键后，执行登录事件
function onKeyUp(e) {
  console.log(e)
  if (e.key == 'Enter') {
    onSubmit()
  }
}
// 添加键盘监听
onMounted(() => {
  console.log('添加键盘监听')
  document.addEventListener('keyup', onKeyUp)
})

// 移除键盘监听
onBeforeUnmount(() => {
  document.removeEventListener('keyup', onKeyUp)
})

const router = useRouter()
// 表单引用
const formRef = ref(null)
// 表单验证规则
const rules = {
  username: [
    {
      required: true,
      message: '用户名不能为空',
      trigger: 'blur'
    }
  ],
  password: [
    {
      required: true,
      message: '密码不能为空',
      trigger: 'blur',
    },
  ]
}

// 定义响应式的表单对象
const form = reactive({
  username: '',
  password: ''
})

// 注册
const onSubmit = () => {
  console.log('注册')
  // 先验证 form 表单字段
  formRef.value.validate((valid) => {
    if (!valid) {
      console.log('表单验证不通过')
      return false
    }
    register(form.username, form.password).then((res) => {
      console.log(res)
      // 判断是否成功
      if (res.data.success === true) {
        showMessage('注册成功')
        // 跳转到登录首页
        router.push('/login')

      } else {
        // 获取服务端返回的错误消息
        let message = res.data.message
        showMessage(message, 'error')
      }
    })
  })
}
</script>

<style scoped>

</style>