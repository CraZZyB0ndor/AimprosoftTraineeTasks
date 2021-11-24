"use strict";
exports.__esModule = true;
var DepartmentService_1 = require("../../../services/impl/DepartmentService");
var DepartmentList = /** @class */ (function () {
    function DepartmentList() {
        this.departmentService = new DepartmentService_1["default"]();
        this.commands = new Command();
    }
    DepartmentList.prototype.render = function (params) {
        var frontCommand = this.commands.commands(params);
        this.departmentService.getAll().done(function (result) {
            $('#app').html(frontCommand.getContent(result));
        });
    };
    return DepartmentList;
}());
exports["default"] = DepartmentList;
//# sourceMappingURL=DepartmentList.js.map