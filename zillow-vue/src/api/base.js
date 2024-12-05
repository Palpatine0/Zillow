// base.js
const base = {
    baseUrl: "/api",

    // file
    file: {
        getBanners: '/file/getBanners',
        uploadFile: '/file/uploadFile',
    },

    // trendy
    trendy: {
        getTrendies: '/trendy/getTrendies',
    },

    // recommendation
    recommendation: {
        getRecommendations: "/recommendation/getRecommendations",
    },

    // item
    item:{
        getItemById: '/item/getItemById',
    },

    // comment
    comment:{
        getCommentsByItemId: '/comment/getCommentsByItemId',
        addComment: "/comment/addComment",
    },

    // search
    search:{
        searchByKeyWord: "/search/searchByKeyWord",
        searchByCity: "/search/searchByCity",
    },

    // user
    user:{
        login: '/user/login',
        register: '/user/register',
        getUserByUsername: '/user/getUserByUsername',
    },

    // buyAction
    buyAction:{
        buyAction: "/buyAction/buyAction",
    },

    // order
    order:{
        getOrdersByUserId: "/order/getOrdersByUserId",
    },

    // llm
    llm:{
        chat: "/llm/chat",
    }
}

export default base;