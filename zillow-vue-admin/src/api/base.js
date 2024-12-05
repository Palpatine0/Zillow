// base.js
const base = {
    baseUrl: "/api",

    // file
    file:{
        uploadFile: '/file/uploadFile',
    },

    // item
    item:{
        getItemById: '/item/getItemById',
        addItem: '/item/addItem',
        deleteItemById: '/item/deleteItemById',
        getItemsByCity: '/item/getItemsByCity',
        updateItemStatusById: '/item/updateItemStatusById',
        updateItemInfoById: '/item/updateItemInfoById',
        updateItemShowcasesById: '/item/updateItemShowcasesById',
    },

    // user
    user:{
        login: '/user/login',
        register: '/user/Register',
        getUser: '/user/getUsers',
        deleteUserById: '/user/deleteUserById',
    },

    // order
    order:{
        getOrders: "/order/getOrders"
    }
}

export default base;