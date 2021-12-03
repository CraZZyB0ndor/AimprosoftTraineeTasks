const HtmlWebpackPlugin = require('html-webpack-plugin');
const Webpack = require('webpack');
const _ = require("lodash");
const path = require('path');
const ESLintPlugin = require('eslint-webpack-plugin');

const defaults = {
    entry: [
        './src/ts/index.ts',
    ],
    output: {
        path: path.resolve(__dirname, 'target/webpack/dist'),
        publicPath: '',
        filename: 'bundle.js'
    },
    resolve: {
        extensions: ['.tsx', '.ts', '.js'],
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
                use: ["script-loader", "eslint-loader", "babel-loader"]
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
            },
        ]
    },
    plugins: [
        new Webpack.ProvidePlugin({
            $: 'jquery',
            jQuery: 'jquery',
            jquery: 'jquery',
            'window.jQuery': 'jquery',
            'window.jquery': 'jquery',
            'window.$': 'jquery',
        }),
        new HtmlWebpackPlugin({
            filename: "index.html",
            favicon: 'public/favicon.ico',
            template: 'src/html/index.html'
        }),
        new ESLintPlugin({
            formatter: 'stylish',
            cache: true,
            emitError: true,
            emitWarning: true,
        }),
    ]
};

module.exports.defaults = defaults;

module.exports.extend = function merge(config) {
    return _.extend({}, defaults, config);
};

module.exports.merge = function merge(config) {
    return _.merge({}, defaults, config);
};