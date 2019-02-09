(function () {
    angular
        .module("PlagiarismDetector")
        .factory("UploadService", uploadService);
    function uploadService($http) {
        var api = {
            "findUploadForHw": findUploadForHw,
            "findUploadForStudent": findUploadForStudent,
            "checkPlagiarism" : checkPlagiarism,
            "plagiarismReport" : plagiarismReport
        };
        return api;

        function findUploadForHw(hwId) {
            return $http.get("/api/hw/"+hwId+"/upload");
        }

        function plagiarismReport(file1Id, file2Id) {
            return $http.get("/api/file1/"+file1Id+"/file2/"+file2Id+"/report");
        }

        function checkPlagiarism(hwId, studentId, algoId) {
            return $http.get("/api/hw/"+hwId+"/student/"+studentId+"/percent/"+algoId);
        }

        function findUploadForStudent(studentId, hwId) {
            return $http.get("/api/hw/"+hwId+"/student/"+studentId);
        }
    }
})();