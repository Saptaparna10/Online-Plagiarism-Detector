(function () {
    angular
        .module("PlagiarismDetector")
        .controller("ReportController", reportController);
    function reportController($routeParams, UserService, UploadService, HwService, NgTableParams, currentUser, $location) {
        var vm = this;
        if(currentUser){
            vm.userId = currentUser.id;
            vm.user = currentUser;
        }
        vm.hwId = $routeParams['hwid'];
        vm.studentId = $routeParams['sid'];
        vm.courseId = $routeParams['cid'];
        vm.algoId = $routeParams['aid'];
        vm.file1Id = $routeParams['f1id'];
        vm.file2Id = $routeParams['f2id'];

        /*event handlers*/
        vm.openNav = openNav;
        vm.closeNav = closeNav;
        vm.logout = logout;
        vm.getReport = getReport;

        function logout() {
            UserService.logout()
                .then(function (value) {
                    $location.url("/login");
                }, function (reason) {
                    vm.error = "There was a prob logging out. Please contact admin.";
                });
        }

        function plagiarismReport() {
            UploadService.plagiarismReport(vm.file1Id, vm.file2Id)
                .then(function (resp) {
                    vm.report = resp.data;
                }, function (reason) {
                });

        }

        function init() {
            plagiarismReport();
            UserService.findUserById(vm.studentId)
                .then(function (student) {
                    vm.student = student.data;
                });
            HwService.findHwById(vm.hwId)
                .then(function (hw) {
                    vm.hw = hw.data;
                }, function (err) {
                    vm.error = err.data.message;
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

        function getReport() {
            vm.report.htmlSuspected = vm.report.htmlSuspected.replace(/code/g, "code2");
            vm.report.htmlOriginal = vm.report.htmlOriginal.replace(/code/g, "code2");

            html2canvas($("#report")).then(function() {

                html2canvas($("#report")).then(function(canvas) {

                    var imgData = canvas.toDataURL('image/png');

                    var imgWidth = 210;
                    var pageHeight = 295;
                    var imgHeight = canvas.height * imgWidth / canvas.width;
                    var heightLeft = imgHeight;

                    var doc = new jsPDF('p', 'mm');
                    var position = 0;

                    doc.addImage(imgData, 'PNG', 0, position, imgWidth, imgHeight);
                    heightLeft -= pageHeight;

                    while (heightLeft >= 0) {
                        position = heightLeft - imgHeight;
                        doc.addPage();
                        doc.addImage(imgData, 'PNG', 0, position, imgWidth, imgHeight);
                        heightLeft -= pageHeight;
                    }
                    var name = vm.report.uploadSuspected.student.id + '_' + vm.report.uploadOriginal.student.id;
                    doc.save("PlagiarismReport_"+ name + '.pdf');

                    plagiarismReport();
                });
            });
        }
    }
})();