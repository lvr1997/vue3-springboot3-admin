import { defineStore } from 'pinia'
export const useUserStore = defineStore('user', {
    // 为了完整类型推理，推荐使用箭头函数
    state: () => {
        return {
            // 所有这些属性都将自动推断出它们的类型
            userInfo: null
        }
    },
    actions: {
        setUserInfo(data) {
            this.userInfo = data
        }
    }
})