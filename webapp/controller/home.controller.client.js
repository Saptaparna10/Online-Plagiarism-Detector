(function () {
    angular
        .module("PlagiarismDetector")
        .controller("HomeController", homeController);
    function homeController() {
        var vm = this;

        /*event handlers*/
        vm.openNav = openNav;
        vm.closeNav = closeNav;

        function init() {
            $("a.carousel-control").click(function(e){
                e.preventDefault();
                $(this).parent().carousel($(this).data("slide"));
            });

            $("#myCarousel").carousel({
                interval : 5000,
                pause: false
            });

            vm.images = [
                "data/neu1.jpg",
                "data/neu2.jpg",
                "data/neu3.jpg",
                "data/neu4.jpg"
            ];

            openNav();
        }
        init();

        function openNav() {
            vm.open = null;
            document.getElementById("mySidenav").style.width = "290px";
            document.getElementById("main").style.marginLeft = "290px";
        }

        function closeNav() {
            vm.open = "open";
            document.getElementById("mySidenav").style.width = "0";
            document.getElementById("main").style.marginLeft = "0";
        }
    }
})();