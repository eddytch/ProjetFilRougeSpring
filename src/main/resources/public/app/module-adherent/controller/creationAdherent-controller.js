var moduleAdherent = angular.module("ModuleAdherent") ;
moduleAdherent.controller('CreationAdherentController',['$scope', '$rootScope','$filter','ServiceAdherent',function($scope, $rootScope, $filter, ServiceAdherent){
	
	$rootScope.page = $rootScope.page || {};
	$rootScope.page.titre = "Création adhérent";

    var self = this ;
    this.service = ServiceAdherent ;

    this.addAdherent = function(isValid){
        if(!isValid) { return }
        var dateNConv = $filter('date')($scope.dateN,"dd/MM/yyyy") ;
        var dateCotisConv = $filter('date')($scope.dateCotis,"dd/MM/yyyy") ;

        var object = {
            "lastname" : $scope.lastname,
            "firstname" : $scope.firstname,
            "email" : $scope.email,
            "birthday" : $scope.birthday,
            "payment_date" : $scope.payment_date,
            "amount" : $scope.amount,
            "address" : {
                "num_street" : $scope.num_street,
                "name_street" : $scope.name_street,
                "pc_town" : $scope.pc_town,
                "town" : $scope.town,
                "country" : $scope.country
            }
        }
        self.service.addAdherent(object) ;
    };

    this.dateIncAnnee = function(date) {
        return date.substr(0,6)+(parseInt(date.substr(6,4))+1)

    }
}]);