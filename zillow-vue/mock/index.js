const express = require("express");
const app = express();
const router = require("./router")
const bodyParser = require("body-parser");
let path = require('path')

app.use('/static', express.static(path.join(__dirname, 'imgs')))

// post请求获取参数的方案
app.use(bodyParser.urlencoded({
    extended: true
}));

app.use("/", router);

app.listen(4006, function () {
    console.log('Express server listening on port = ' + 4006);
})