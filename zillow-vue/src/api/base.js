// base.js
const base = {
    baseUrl: "/api",

    // file
    getBanners: '/file/getBanners',
    uploadImageNoPrefix: '/file/uploadImageNoPrefix',

    // trendy
    getTrendies: '/trendy/getTrendies',

    // recommendation
    getRecommendations: "/recommendation/getRecommendations",

    // item
    getItemByID: '/item/getItemByID',

    // comment
    getComment: '/comment/getComment',
    addComment: "/comment/addComment",

    // search
    searchByKeyWord: "/search/searchByKeyWord",
    searchByCity: "/search/searchByCity",

    // user
    sendVerificationCode: '/user/sendVerificationCode',
    login: '/user/login',
    register: '/user/Register',
    getUsers: '/user/getUsers',
    getUserByUsername: '/user/getUserByUsername',
    getUserById: '/user/getUserById',

    // buyAction
    buyAction: "/buyAction/buyAction",

    // buytime
    getBuyTime: '/buyTime/getBuyTime',

    // order
    order: "/order/getOrder"
}

export default base;