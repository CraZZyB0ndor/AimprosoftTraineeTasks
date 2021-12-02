"use strict";
exports.__esModule = true;
var DataService = /** @class */ (function () {
    function DAO() {
    }
    DAO.get = function (query, id) {
        return $.ajax({
            url: query,
            data: id,
            type: 'GET',
            async: true
        });
    };
    DAO.create = function (query, model) {
        $.ajax({
            url: query,
            data: model,
            type: 'POST',
            async: true
        });
    };
    DAO["delete"] = function (query, id) {
        $.ajax({
            url: query,
            data: id,
            type: 'DELETE'
        });
    };
    return DAO;
}());
exports["default"] = DataService;
//# sourceMappingURL=DAO.js.map