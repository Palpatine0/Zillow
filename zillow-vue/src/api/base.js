// base.js
const base = {
    baseUrl: "/api",

    // file
    getBanners: '/file/getBanners',
    uploadFile: '/file/uploadFile',
    uploadImageNoPrefix: '/file/uploadImageNoPrefix',

    // trendy
    getTrendies: '/trendy/getTrendies',

    // recommendation
    getRecommendations: "/recommendation/getRecommendations",

    // item
    getItemById: '/item/getItemById',
    getItemsByCity: '/item/getItemsByCity',

    // comment
    getCommentsByItemId: '/comment/getCommentsByItemId',
    addComment: "/comment/addComment",

    // search
    searchByKeyWord: "/search/searchByKeyWord",
    searchByCity: "/search/searchByCity",

    // user
    sendVerificationCode: '/user/sendVerificationCode',
    login: '/user/login',
    register: '/user/register',
    getUsers: '/user/getUsers',
    getUserByUsername: '/user/getUserByUsername',
    getUserById: '/user/getUserById',

    // buyAction
    buyAction: "/buyAction/buyAction",

    // buytime
    getBuyTime: '/buyTime/getBuyTime',

    // order
    getOrdersByUserId: "/order/getOrdersByUserId"
}

export default base;