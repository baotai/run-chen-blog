/**utils*/
var appUtil = angular.module('app.util', ['toastr']);
/**service module 定义*/
var appService = angular.module('app.service', ['ngResource']);
//login
var login_module = angular.module('login', ['toastr', 'app.service', 'app.util', 'angular-loading-bar', 'valdr']);
