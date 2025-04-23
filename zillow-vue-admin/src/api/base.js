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
        getUsers: '/user/getUsers',
        deleteUserById: '/user/deleteUserById',
    },

    // order
    order:{
        getOrders: "/order/getOrders"
    },

    // llm
    llm:{
        getNewConstructionStatValues: "/llm/getNewConstructionStatValues",
        getNewConstructionStatSummary: "/llm/getNewConstructionStatSummary",
        getHousingVacanciesStatSummary: "/llm/getHousingVacanciesStatSummary",
        getHomeownershipStatSummary: "/llm/getHomeownershipStatSummary",
        getEstimatedRentedUnitsStatSummary: "/llm/getEstimatedRentedUnitsStatSummary",
    }
}

export default base;