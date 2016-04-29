
// Récupération du module des catalogue pour y ajouter le controller
angular.module('ModuleMedia').controller('CreerMediaController', ['$location','$rootScope', '$scope', '$http', 'MediaService','UrlService', function($location, $rootScope, $scope, $http, MediaService,UrlService) {

	var myCtrl = this;
	
	$rootScope.page = $rootScope.page || {};
	$rootScope.page.titre = "Création Media";
	
	myCtrl.medias = undefined;
	
	// Liste de type de media
	myCtrl.types = [
	               {key:'DVD', label:'DVD'},
	               {key:'CD', label:'CD'},
	               {key:'Livre', label:'Livres'}
	               ];		
	
	// Ajout d'un media
	myCtrl.addMedia = function() {
		
		if ($scope.myForm.$invalid) {
			console.warn('Erreur : formulaire invalide');
		} else {
			var author = {
					firstName : myCtrl.ajout.inputAuteurPrenom,
					lastName : myCtrl.ajout.inputAuteurNom
			}
			var media = {
					title  : myCtrl.ajout.inputTitre,
					type   : myCtrl.ajout.inputType,
					author : author
			};
			$http.post(UrlService.media, media).then(function() {
				console.info('Données sauvegardées');
			}), function() {
				console.warn('Erreur dans la sauvegarde ...');
			}
			console.info('Formulaire valide ...');
		}
		
		MediaService.add(media);
		myCtrl.ajout = {};	
		
		console.log(media);
	}
	
}]);