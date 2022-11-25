index_module.controller('IndexController', ['$scope', '$window', 'toastr', 'AppUtil',
    IndexController]);

function IndexController($scope, $window, toastr, AppUtil) {

    init();

    function init() {
        initMenus();
    }

    function initMenus() {
        // sidebar.config.ts
        var menus = [
            {
                text: '首页', icon: 'glyphicon glyphicon-home', href: '/healthcheck.html',
                nodes: []
            },
            {
                text: '管理中心', selectable: false, icon: 'glyphicon glyphicon-th-large',
                nodes: [
                    {text: '文章管理', icon: 'glyphicon glyphicon-folder-open', href: '/blog.html' },
                    {text: '评论管理', icon: 'glyphicon glyphicon-edit', href: '/comment.html'}
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
                // $('#app-list').removeChild();
                $('#app-list').load(data.href);

            }
        });
    }
}
