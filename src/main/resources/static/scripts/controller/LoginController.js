login_module.controller('LoginController',
                       ['$scope', '$window', '$location', 'toastr', 'AppUtil',
                        LoginController]);

function LoginController($scope, $window, $location, toastr, AppUtil) {

    $scope.toLogin = function () {
        var username = document.getElementsByName("username")[0].value;
        var password = document.getElementsByName("password")[0].value;
        var imageCode = document.getElementsByName("imageCode")[0].value;
        if (!username) {
            $scope.info = "请填入名称";
            return;
        }
        if (!password) {
            $scope.info = "请填入密码";
            return;
        }
        if (!imageCode) {
            $scope.info = "请填入验证码";
            return;
        }
        console.log(111)
    }
}
