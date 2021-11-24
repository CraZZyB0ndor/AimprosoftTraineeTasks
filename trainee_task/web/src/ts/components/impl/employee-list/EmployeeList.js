"use strict";
exports.__esModule = true;
var EmployeeService_1 = require("../../../services/impl/EmployeeService");
var EmployeeList = /** @class */ (function () {
    function EmployeeList() {
        this.employeeService = new EmployeeService_1["default"]();
    }
    EmployeeList.prototype.render = function (params) {
        //TODO() Get Promise obj from services via JQuery.
        console.log("Int Render() DepartmentList");
        this.employeeService.getAll(2).done(function (result) { return console.log(result); });
    };
    return EmployeeList;
}());
exports["default"] = EmployeeList;
//# sourceMappingURL=EmployeeList.js.map