(function () {
    angular
        .module("PlagiarismDetector")
        .factory("StatsService", statsService);
    function statsService($http) {
        var api = {
            "findStatsForProf": findStatsForProf
        };
        return api;
        
        function findStatsForProf(profId) {
            return $http.get("/api/prof/"+profId+"/stats");
        }
    }
})();