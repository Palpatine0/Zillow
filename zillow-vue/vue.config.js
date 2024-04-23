module.exports = {

    devServer: {
        port: 8080,
        proxy: {
            '/api': {
                target: 'http://localhost:4006',
                changeOrigin: true,
                pathRewrite: {
                    '^/api': ''
                }
            }
        }
    }
}
