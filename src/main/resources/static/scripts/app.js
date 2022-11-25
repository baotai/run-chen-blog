/**utils*/
var appUtil = angular.module('app.util', ['toastr']);
/**service module 定义*/
var appService = angular.module('app.service', ['ngResource']);
//login
var login_module = angular.module('login', ['toastr', 'app.service', 'app.util']);
/** directive */
var directive_module = angular.module('runchen.directive', ['app.service', 'app.util', 'toastr']);
// 首页
var index_module = angular.module('index', ['toastr', 'app.service', 'runchen.directive', 'app.util', 'angular-loading-bar']);
//博文
var blog_module = angular.module('blog', ['toastr', 'app.service', 'runchen.directive', 'app.util', 'angular-loading-bar']);
//评论
var comment_module = angular.module('comment', ['toastr', 'app.service', 'runchen.directive', 'app.util', 'angular-loading-bar']);
