import axios from "axios"
import {getToken} from "@/composables/auth.js"
import {showMessage} from "@/composables/util.js"

// 创建 Axios 实例
const instance = axios.create({
    baseURL: "/api", // 你的 API 基础 URL
    timeout: 7000, // 请求超时时间
})

//添加请求拦截器
instance.interceptors.request.use(function (config) {
    // 在发送请求之前做些什么
    const token = getToken()
    console.log('统一添加请求头中的 Token:' + token)

    // 当 token 不为空时
    if (token) {
        // 添加请求头, key 为 Authorization，value 值的前缀为 'Bearer '
        config.headers['Authorization'] = 'Bearer ' + token
    }

    return config;
}, function (error) {
    // 对请求错误做些什么
    return Promise.reject(error)
});

// 添加响应拦截器
instance.interceptors.response.use(function (response) {
    // 对响应数据做点什么
    return response;
}, function (error) {
    // 对响应错误做点什么
    let status = error.response.status
    console.log('错误响应==========》' + status)
    if (status === 401 || status === 402) {
        // 退出登录
        console.log('401-------------')
        store.dispatch('logout').finally(() => location.reload())
        // location.href = '/login';

    }

    let isSuccess = error.response.data.success
    console.log('错误响应==========》' + isSuccess)
    if (!isSuccess) {
        console.log('error: ' + error.response.data.message)
        let message = error.response.data.message || '请求失败'

        // todo 失效的情况
        // location.href = '/login';
        // notification(message, 'error')
        showMessage(messsage, 'error')
    }

    return Promise.reject(error);
});

// 暴露出去
export default instance;
