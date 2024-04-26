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
    search: "/search/search",

    // user
    sendVerificationCode: '/user/sendVerificationCode',
    login: '/user/login',
    register: '/user/Register',

    // buyAction
    buyAction: "/buyAction/buyAction",

    // buytime
    getBuyTime: '/buyTime/getBuyTime',

    // order
    order: "/order/getOrder"
}

export default base;