const base = require('./webpack.config.base');

module.exports = base.merge({
    mode: "development",
    devServer: {
        proxy: {
            '/': 'http://localhost:8080/api'
        },
        client: {
            reconnect: true,
        },
        compress: true,
        port: 8081,
    }
});