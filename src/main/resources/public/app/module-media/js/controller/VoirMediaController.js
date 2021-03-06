
// Récupération du module des catalogue pour y ajouter le controller
angular.module('ModuleMedia').controller('VoirMediaController', ['$routeParams','$rootScope','MediaService', function($routeParams, $rootScope, MediaService) {

	var myCtrl = this;
	
	$rootScope.page = $rootScope.page || {};
	$rootScope.page.titre = "Voir Media";

	myCtrl.media = undefined;
	var idMedia  = $routeParams.id;
	
	MediaService.getMedia(idMedia).then(function(response){
		myCtrl.media = response;
	}, function(){
		myCtrl.medias = -1;
	});
	
}]);