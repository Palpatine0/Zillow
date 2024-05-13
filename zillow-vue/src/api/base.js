// base.js
const base = {
    baseUrl: "/api",

    // file
    getBanner: '/file/getBanner',
    uploadImageNoPrefix: '/file/uploadImageNoPrefix',

    // trendy
    getTrendy: '/trendy/getTrendy',

    // recommendation
    getRecommendation: "/recommendation/getRecommendation",

    // item
    getItemByID: '/item/getItemByID',

    // comment
    comment: '/comment/getComment',
    feelback: "/comment/addComment",

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