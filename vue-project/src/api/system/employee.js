import http from "@/api/request.js";

const selectAll = (data) => http.get("/employee/selectAll", data)
const selectByPage = (pageNum, pageSize, data) => http.post(`/employee/selectByPage?pageNum=${pageNum}&pageSize=${pageSize}`,data)

const updateById = (data) => http.put("/employee/update", data)

const addOne = (data) => http.post("/employee/add", data)

const deleteById = (id) => http.delete(`/employee/delete/${id}`)

export const employeeApi = {
    selectAll,
    selectByPage,
    updateById,
    addOne,
    deleteById
}