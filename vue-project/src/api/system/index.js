import http from "@/api/request.js";

const login = (data) => http.post(`/login`,data)

export const systemApi = {
    login
}