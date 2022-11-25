/** navbar */
directive_module.directive('runchennav', function ($http, $compile, $window, toastr, AppUtil) {
    return {
        restrict: 'E',
        templateUrl: '../../views/common/nav.html',
        transclude: true,
        replace: true,
        link: function (scope, element, attrs) {

            scope.test = test;

            function test() {
                console.log("test")
            }
        }
    }
});
/** navbar */
directive_module.directive('runchenaside', function ($http, $compile, $window, toastr, AppUtil) {
    return {
        restrict: 'E',
        templateUrl: '../../views/common/sidebar.html',
        transclude: true,
        replace: true,

        link: function (scope, element, attrs) {

            // sidebar.config.ts
            var menus = [
                {
                    text: '首页', icon: 'glyphicon glyphicon-home', href: '/index.html',
                    nodes: []
                },
                {
                    text: '管理中心', selectable: false, icon: 'glyphicon glyphicon-th-large',
                    nodes: [
                        {text: '文章管理', icon: 'glyphicon glyphicon-folder-open', href: '/blog.html' },
                        {text: '评论管理', icon: 'glyphicon glyphicon-edit',}
                    ]
                },
                {
                    text: '系统中心', selectable: false, icon: 'glyphicon glyphicon-cog',
                    nodes: [
                        {text: '个人信息', icon: 'glyphicon glyphicon-user',}
                    ]
                }
            ];
            //init treeview
            $('#treeview').treeview({
                color: "#797979",
                showBorder: true,
                data: menus,
                levels: 99,
                expandIcon: '',
                collapseIcon: '',
                onNodeSelected: function (event, data) {

                    var url = data.href;
                    if (url && url !== '') {
                        $window.location.href = data.href
                    }
                }
            });
        }
    }
});
