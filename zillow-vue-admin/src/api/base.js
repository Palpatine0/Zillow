// base.js
const base = {
    baseUrl: "/api",

    // file
    getBanner: '/file/getBanner',

    // trendy
    getTrendy: '/trendy/getTrendy',

    // recommendation
    getRecommendation: "/recommendation/getRecommendation",

    // details
    getDetail: '/detail/getDetail',

    // comment
    comment: '/comment/getComment',
    feelback: "/comment/addComment",

    // search
    searchByKeyWord: "/search/searchByKeyWord",
    searchByCity: "/search/searchByCity",
    adminSearchByCity: "/search/adminSearchByCity",

    // user
    sendVerificationCode: '/user/sendVerificationCode',
    login: '/user/login',
    register: '/user/Register',
    getUser: '/user/getUser',
    deleteUser: '/user/deleteUser',

    // buyAction
    buyAction: "/buyAction/buyAction",

    // buytime
    getBuyTime: '/buyTime/getBuyTime',

    // order
    order: "/order/getOrder"
}

export default base;