(function () {
    angular
        .module("PlagiarismDetector")
        .controller("UploadController", uploadController);
    function uploadController(UploadService, FileUploader, $routeParams, HwService, currentUser, $location, UserService) {
        var vm = this;
        if(currentUser){
            vm.userId = currentUser.id;
            vm.user = currentUser;
        }
        vm.hwId = $routeParams['hwid'];
        vm.courseId = $routeParams['cid'];
        vm.uploader = new FileUploader({url: "/api/student/"+vm.userId+"/hw/"+vm.hwId+"/upload"});
        vm.uploader.onCompleteItem = onCompleteItem ;
        vm.logout = logout;

        function logout() {
            UserService.logout()
                .then(function (value) {
                    $location.url("/login");
                }, function (reason) {
                    vm.error = "There was a prob logging out. Please contact admin.";
                });
        }

        function onCompleteItem  (item, response, status, headers){
            if(status == 200){
                vm.message = "Files uploaded successfully";
                vm.error = null;
            }
            else{
                vm.error = "There was a problem with file upload";
                vm.message = null;
            }
        }

        vm.uploader.filters.push({
            name: 'pyFilter',
            fn: function(item) {
                var type = '|' + item.name.slice(item.name.lastIndexOf('.') + 1) + '|';
                return '|py|'.indexOf(type) !== -1;
            }
        });

        /*event handlers*/
        vm.openNav = openNav;
        vm.closeNav = closeNav;

        function init() {
            UploadService.findUploadForStudent(vm.userId, vm.hwId)
                .then(function (submission) {
                    if(submission.data.length != 0) {
                        vm.info = "You have already submitted for this Homework.";
                    }
                    vm.uploaded = submission.data;
                });
            HwService.findHwById(vm.hwId)
                .then(function (hw) {
                    vm.hw = hw.data;
                });
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
})();