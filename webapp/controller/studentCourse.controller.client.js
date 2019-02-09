(function () {
    angular
        .module("PlagiarismDetector")
        .controller("StudentCourseController", studentCourseController);
    function studentCourseController($routeParams, UserService, CourseService, StudentCourseService, NgTableParams, $location, currentUser, $uibModal) {
        var vm = this;
        if(currentUser){
            vm.userId = currentUser.id;
            vm.user = currentUser;
        }
        vm.courseId = $routeParams['cid'];
        vm.addOrRemove = $routeParams['arid'];

        /*event handlers*/
        vm.openNav = openNav;
        vm.closeNav = closeNav;
        vm.addToCourse = addToCourse;
        vm.removeFromCourse = removeFromCourse;
        vm.logout = logout;

        function logout() {
            UserService.logout()
                .then(function (value) {
                    $location.url("/login");
                }, function (reason) {
                    vm.error = "There was a prob logging out. Please contact admin.";
                });
        }

        function addToCourse(studentId) {
            StudentCourseService.addStudentToCourse(vm.courseId, studentId)
                .then(function (value) {
                    var data = value.data;
                    if(data.length == 0){
                        vm.message = "There are no students to add to this course";
                    }
                    vm.tableParams = new NgTableParams({
                        page: 1,
                        count: 10
                    }, {
                        filterDelay: 0,
                        counts: [],
                        dataset: data
                    });
                });
        }

        function removeFromCourse(studentCourseId) {
            var message = "Are you sure you want to remove?";

            var modalHtml = '<div class="modal-body">' + message + '</div>';
            modalHtml += '<div class="modal-footer"><button class="btn btn-primary btn-md" ng-click="ok()">Remove</button><button class="btn btn-warning" ng-click="cancel()">Cancel</button></div>';

            var modalInstance = $uibModal.open({
                template: modalHtml,
                controller: ModalInstanceCtrl
            });

            modalInstance
                .result
                .then(function() {
                    StudentCourseService.removeStudentFromCourse(studentCourseId, vm.courseId)
                        .then(function (value) {
                            var data = value.data;
                            if(data.length == 0) {
                                vm.message = "There are no registered students to remove from this course";
                            }
                            vm.tableParams = new NgTableParams({
                                page: 1,
                                count: 10
                            }, {
                                filterDelay: 0,
                                counts: [],
                                dataset: data
                            });
                        });
                }, function (reason) {

                });
        }

        function init() {
            CourseService.findCourseById(vm.courseId)
                .then(function (course) {
                    vm.course = course.data;
                });

            if(vm.addOrRemove === "add"){
                UserService.findAllStudentForCourse(vm.courseId)
                    .then(function (resp) {
                        var data = resp.data;
                        if(data.length == 0){
                            vm.message = "There are no students to add to this course";
                        }
                        vm.tableParams = new NgTableParams({
                            page: 1,
                            count: 10
                        }, {
                            filterDelay: 0,
                            counts: [],
                            dataset: data
                        });
                    });
            }else{
                StudentCourseService.findStudentOfCourse(vm.courseId)
                    .then(function (resp) {
                        var data = resp.data;
                        if(data.length == 0) {
                            vm.message = "There are no registered students to remove from this course";
                        }
                        vm.tableParams = new NgTableParams({
                            page: 1,
                            count: 10
                        }, {
                            filterDelay: 0,
                            counts: [],
                            dataset: data
                        });
                    });
            }

            openNav();
            $(window).width(function() {
                if ($(this).width() <= 768) {
                    closeNav();
                }
                else{
                    openNav();
                }
            });
        }
        init();

        function openNav() {
            vm.open = null;
            document.getElementById("mySidenav").style.width = "250px";
            document.getElementById("main").style.marginLeft = "250px";
        }

        function closeNav() {
            vm.open = "open";
            document.getElementById("mySidenav").style.width = "0";
            document.getElementById("main").style.marginLeft = "0";
        }
    }

    var ModalInstanceCtrl = function($scope, $uibModalInstance) {
        $scope.ok = function() {
            $uibModalInstance.close();
        };

        $scope.cancel = function() {
            $uibModalInstance.dismiss('cancel');
        };
    };
})();