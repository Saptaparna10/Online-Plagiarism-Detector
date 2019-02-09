(function () {
    angular
        .module("PlagiarismDetector")
        .controller("CourseEditController", courseEditController);
    function courseEditController($routeParams, $location, CourseService, currentUser, UserService) {
        var vm = this;
        if(currentUser){
            vm.userId = currentUser.id;
            vm.user = currentUser;
        }
        vm.courseId = $routeParams['cid'];

        /*event handlers*/
        vm.openNav = openNav;
        vm.closeNav = closeNav;
        vm.createUpdateCourse = createUpdateCourse;
        vm.logout = logout;

        function logout() {
            UserService.logout()
                .then(function (value) {
                    $location.url("/login");
                }, function (reason) {
                    vm.error = "There was a prob logging out. Please contact admin.";
                });
        }

        function init() {
            CourseService.findCourseByProfessorId(vm.userId)
                .then(function (courses) {
                    if(courses.data.length == 0) {
                        vm.message = "No Course";
                    }
                    vm.courses = courses.data;
                });
            if(vm.courseId != "new"){
                CourseService.findCourseById(vm.courseId)
                    .then(function (course) {
                        if(course.data.length == 0) {
                            vm.message = "No Course";
                        }
                        vm.course = course.data;
                    });
            }
            $(window).width(function() {
                if ($(this).width() <= 768) {
                    closeNav();
                }
                else{
                    openNav();
                }
            });
            closeNav();
        }
        init();

        function openNav() {
            vm.open = null;
            document.getElementById("mySidenav").style.width = "250px";
            document.getElementById("main").style.marginLeft = "250px";
        }

        function createUpdateCourse(course) {
            if(vm.courseId != "new"){
                CourseService.updateCourse(course)
                    .then(function (resp) {
                        $location.url("/course");
                    }, function (err) {
                        vm.error = err.data.message;
                    });
            } else{
                CourseService.createCourse(vm.userId, course)
                    .then(function (resp) {
                        $location.url("/course");
                    }, function (err) {
                        vm.error = err.data.message;
                    });
            }
        }

        function closeNav() {
            vm.open = "open";
            document.getElementById("mySidenav").style.width = "0";
            document.getElementById("main").style.marginLeft = "0";
        }
    }
})();