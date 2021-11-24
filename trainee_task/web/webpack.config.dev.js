const HtmlWebpackPlugin = require('html-webpack-plugin');
const Webpack = require('webpack');
const path = require('path');

module.exports = {
    entry: [
        './src/ts/index.ts',
    ],
    mode: "development",
    output: {
        path: path.resolve(__dirname, 'target/webpack/dist'),
        publicPath: '',
        filename: 'bundle.js'
    },
    resolve: {
        extensions: ['.tsx', '.ts', '.js'],
    },
    devServer: {
        proxy: {
            '/': 'http://localhost:8080/'
        },
        headers: {
            "Access-Control-Allow-Origin": "*",
            "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, PATCH, OPTIONS",
            "Access-Control-Allow-Headers": "X-Requested-With, content-type, Authorization"
        },
        client: {
            reconnect: true,
        },
        compress: true,
        port: 8081,
    },
    module: {
        rules: [
            {
                test: /\.tsx?$/,
                use: {
                    loader: "ts-loader"
                }
            },
            {
                test: /\.js$/,
                exclude: /node_modules/,
                use: {
                    loader: "script-loader"
                },
            },
            {
                test: /\.css$/,
                use: [
                    {
                        loader: "style-loader"
                    },
                    {
                        loader: "css-loader"
                    }
                ]
            }
        ]
    },
    plugins: [
        new Webpack.ProvidePlugin({
            $: 'jquery',
            jQuery: 'jquery'
        }),
        new HtmlWebpackPlugin({
            filename: "index.html", // Write the file to <public-path>/graphql/index.html
            favicon: 'public/favicon.ico',
            template: 'src/html/index.html'
        })
    ]
};
// filename: "./src/aimprosoft/html/index.html",
