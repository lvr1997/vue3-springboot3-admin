import axios from "axios";
import { ElMessage } from "element-plus";

// 创建axios实例
const http = axios.create({
    baseURL: '/api',
    timeout: 6000,
});

http.defaults.headers.post['Content-Type'] = 'application/json';

// 请求拦截
http.interceptors.request.use((config) => {
    // config.headers["Authorization"] = "Bearer " + JSON.parse(localStorage.getItem("remember_token")) || null;    // 配置完成将config返回
    return config;
}, (err) => {
    // 当请求失败时做一些处理 抛出错误
    throw new Error(err);
});

// 响应拦截
http.interceptors.response.use((res) => {
        let { code, message } = res.data;
        //key = value  status等于200就执行200下的代码 执行完break退出循环
        switch (code) {
            case '400':
                ElMessage.error(message)
                break;
            case '500':
                ElMessage.error(message)
                break;
        }
        return res.data;
    },(err) => {
        // 当响应失败时做一些处理 抛出错误
        throw new Error(err);
    }
);
// 导出 http实例 方便调用
export default http;