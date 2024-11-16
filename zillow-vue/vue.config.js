module.exports = {
    devServer: {
        port: 3010,
        proxy: {
            '/api': {
                target: 'http://localhost:4006',
                changeOrigin: true,
                pathRewrite: {
                    '^/api': ''
                }
            }
        }
    },
    transpileDependencies: [
        'vuetify'
    ]
}
