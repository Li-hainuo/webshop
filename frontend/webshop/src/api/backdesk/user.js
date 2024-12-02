import axios from "@/axios";

// 登录接口
export function login(username, password) {
    return axios.post("/login", {username, password})
}

//注册接口
export function register(username, password) {
    return axios.post("/register", {username, password})
}

//找回接口
export function recover(username, password) {
    return axios.post("/recover", {username, password})
}

