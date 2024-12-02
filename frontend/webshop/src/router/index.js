import Index from '@/pages/frontdesk/index.vue'
import Login from '@/pages/backdesk/login.vue'
import Register from "@/pages/backdesk/register.vue";
import Recover from "@/pages/backdesk/recover.vue";
import AdminIndex from "@/pages/backdesk/adminIndex.vue"
import UserIndex from "@/pages/backdesk/userIndex.vue"
import {createRouter, createWebHashHistory} from 'vue-router'

// 统一在这里声明所有路由
const routes = [
    {
        path: '/', // 路由地址
        component: Index, // 对应组件
        meta: { // meta 信息
            title: '首页' // 页面标题
        }
    },
    {
        path: '/login',
        component: Login,
        meta: {
            title: '登录'
        }
    },
    {
        path: '/register',
        component: Register,
        meta: {
            title: '用户注册'
        }
    },
    {
        path: '/recover',
        component: Recover,
        meta: {
            title: '找回密码'
        }
    },
    {
        path: '/backdesk/adminIndex',
        component: AdminIndex,
        meta: {
            title: '管理员后台首页'
        }
    },
    {
        path: '/backdesk/userIndex',
        component: UserIndex,
        meta: {
            title: '用户后台首页'
        }
    },
]

// 创建路由
const router = createRouter({
    // 指定路由的历史管理方式，hash 模式指的是 URL 的路径是通过 hash 符号（#）进行标识
    history: createWebHashHistory(),
    // routes: routes 的缩写
    routes,
})

// ES6 模块导出语句，它用于将 router 对象导出，以便其他文件可以导入和使用这个对象
export default router
