(function () {
    angular
        .module("PlagiarismDetector")
        .factory("HwService", hwService);
    function hwService($http) {
        var api = {
            "findHwByCourseId": findHwByCourseId,
            "createHw": createHw,
            "findHwById": findHwById,
            "updateHw": updateHw
        };
        return api;

        function findHwByCourseId(courseId) {
            return $http.get("/api/course/"+courseId+"/hw");
        }

        function updateHw(hw) {
            return $http.put("/api/hw", hw);
        }

        function findHwById(hwId) {
            return $http.get("/api/hw/"+hwId);
        }

        function createHw(courseId, hw) {
            return $http.post("/api/course/"+courseId+"/hw", hw);
        }
    }
})();