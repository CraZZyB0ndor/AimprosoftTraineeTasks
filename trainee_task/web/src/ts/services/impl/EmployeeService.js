"use strict";
exports.__esModule = true;
var DAO_1 = require("../../helpers/DAO");
var EmployeeService = /** @class */ (function () {
    function EmployeeService() {
    }
    EmployeeService.prototype.getAll = function (id) {
        return DAO_1["default"].get('/employee', { 'departmentId': id });
    };
    EmployeeService.prototype.getById = function (id) {
        return DAO_1["default"].get('/employee/id', { 'id': id });
    };
    EmployeeService.prototype.createEmployee = function (employee) {
        DAO_1["default"].create('/employee', employee);
    };
    EmployeeService.prototype.deleteById = function (id) {
        DAO_1["default"]["delete"]('/employee', { 'id': id });
    };
    return EmployeeService;
}());
exports["default"] = EmployeeService;
//# sourceMappingURL=EmployeeService.js.map