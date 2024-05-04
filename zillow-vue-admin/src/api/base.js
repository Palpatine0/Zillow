// base.js
const base = {
    baseUrl: "/api",

    // file
    getBanner: '/file/getBanner',
    uploadImageNoPrefix: '/file/uploadImageNoPrefix',
    uploadTest: '/file/uploadTest',

    // trendy
    getTrendy: '/trendy/getTrendy',

    // recommendation
    getRecommendation: "/recommendation/getRecommendation",

    //item
    getItemByID: '/item/getItemByID',
    addItem: '/item/addItem',
    adminGetItemsByCity: '/item/adminGetItemsByCity',
 
    updateItemStatusById: '/item/updateItemStatusById',
    updateItemInfoById: '/item/updateItemInfoById',
    updateItemShowcasesById: '/item/updateItemShowcasesById',


    // comment
    comment: '/comment/getComment',
    feedback: "/comment/addComment",
 

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