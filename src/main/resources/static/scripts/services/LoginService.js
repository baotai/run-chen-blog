appService.service('LoginService', ['$resource', '$q', function ($resource, $q) {
    var login_resource = $resource('', {}, {
        login: {
            method: 'GET',
            isArray: false,
            url: '/signin'
        }
    });
    return {
        login: function (username, password, imageCode) {
            var d = $q.defer();
            login_resource.login({
                username: username,
                password: password,
                verifyCode: imageCode
            }, function (result) {
                d.resolve(result);
            }, function (result) {
                d.reject(result);
            });
            return d.promise;
        }
    }
}]);
