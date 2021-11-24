"use strict";
exports.__esModule = true;
var DAO_1 = require("../../helpers/DAO");
var DepartmentService = /** @class */ (function () {
    function DepartmentService() {
    }
    DepartmentService.prototype.getAll = function () {
        return DAO_1["default"].get('/department');
    };
    DepartmentService.prototype.getById = function (id) {
        return DAO_1["default"].get('/department/id', { 'id': id });
    };
    DepartmentService.prototype.createDepartment = function (department) {
        DAO_1["default"].create('/department', department);
    };
    DepartmentService.prototype.deleteById = function (id) {
        DAO_1["default"]["delete"]('/department', { 'id': id });
    };
    return DepartmentService;
}());
exports["default"] = DepartmentService;
//# sourceMappingURL=DepartmentService.js.map