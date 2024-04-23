// router.js
const express = require("express");
const router = express();
const banner = require('./data/banner/banner')
const hotProduct = require("./data/home/hotProduct");
const recommendation = require("./data/home/recommendation");
const searchData = require("./data/search")
const url = require("url");
const detailsData = require("./data/details")
const commentData = require("./data/comment")
const order = require("./data/order")

// 轮播图图片
router.get("/banner", function (req, res) {
    res.send(banner);
})

// 热销产品
router.get("/hotProduct", function (req, res) {
    const city = url.parse(req.url, true).query.city;
    res.send(hotProduct)
})

// 热门推荐
router.get("/recommendation", function (req, res) {
    const city = url.parse(req.url, true).query.city;
    res.send(recommendation)
})

router.get("/search", function (req, res) {
    const city = url.parse(req.url, true).query.city;
    const content = url.parse(req.url, true).query.content;
    const page = url.parse(req.url, true).query.page;
    console.log("page=" + page, "content=" + content);
    res.send(searchData);
})

// 详情页
router.get("/details", function (req, res) {
    const id = url.parse(req.url, true).query.id;
    // console.log("商品id="+id);
    res.send(detailsData);
})

router.get("/comment", function (req, res) {
    const id = url.parse(req.url, true).query.id;
    const page = url.parse(req.url, true).query.page;
    // console.log("商品id="+id);
    res.send(commentData);
})

/**
 * 订单信息
 */
router.get("/order", function (req, res) {
    // 用户信息
    const user = url.parse(req.url, true).query.user;
    console.log("用户信息:" + user);
    res.send(order);
})

/**
 * 提交评价
 */

router.post("/feelback", function (req, res) {
    const feelback = req.body.feelback;
    const orderId = req.body.orderId;
    console.log("comment:" + feelback + "," + orderId);
    if (feelback) {
        res.send({
            msg: "Done rating"
        })
    } else {
        res.send({
            msg: "Comment failed to send"
        })
    }
})

// 登录
router.post("/login", function (req, res) {
    const username = req.body.username;
    const password = req.body.password;
    // console.log(req.body);
    if (username) {
        res.send({
            status: 200,
            msg: "Login success"
        })
    } else {
        res.send({
            status: 500,
            msg: "Login failed"
        })
    }
})


// 购买倒计时
router.get('/buytime', function (req, res) {
    const id = url.parse(req.url, true).query.id;
    let wei = new Date().getTime() + 10 * 1000;
    res.send({
        time: wei
    })
})

// 预订是否成功
router.get('/buyaction', function (req, res) {
    // console.log(req.query.id)
    if (req.query.id % 2 === 0) {
        res.send({
            msg: '预订成功'
        })
    } else {
        res.send({
            msg: '手太慢，已经被抢订了，预订失败'
        })
    }

})

router.post('/sendyzm', function (req, res) {
    let phone = req.query.phone;
    res.send({
        status: 200
    })
})


module.exports = router;