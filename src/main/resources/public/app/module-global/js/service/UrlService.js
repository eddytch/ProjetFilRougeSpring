angular.module('ModuleGlobal').service('UrlService', [ function() {
	
	var self = this;
	
	var serveur	= "localhost";
	var port 	= "8080";
	var url 	= "http://" + serveur + ":" + port + "/api/";
	
	self.media						= url + "/media";
	self.media_accession			= url + "/media.accession";
	self.media_creation				= url + "/media.creation";
	self.media_recherche			= url + "/media.recherche";
	self.media_recherche_taille		= url + "/media.recherche.taille";
	
	self.adherent					= url + "/adherent";
	self.adherent_accession			= url + "/adherent.accession";
	self.adherent_creation			= url + "/adherent.creation";
	self.adherent_recherche			= url + "/adherent.recherche";
	self.adherent_recherche_taille	= url + "/adherent.recherche.taille";;
	
	self.login						= url + "/connexion.login";
}]);