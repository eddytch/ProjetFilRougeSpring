angular.module('ModuleGlobal').filter('DateFormat', [ function() {
	return function(input) {
		if (input) {
			return input.split("T")[0];
		}
		return;
	}
}]);

angular.module('ModuleGlobal').filter('LastFirstName', [ function() {
	return function(input) {
		if (input) {
			return input.lastName + " " + input.firstName;
		}
		return;
	}
}]);

angular.module('ModuleGlobal').filter('LastFirstname', [ function() {
	return function(input) {
		if (input) {
			return input.lastname + " " + input.firstname;
		}
		return;
	}
}]);

angular.module('ModuleGlobal').filter('for', [ function() {
	return function(input, total) {
		if(!total){
			input.push(0);
		}else{
			nb = parseInt(total);
			for (var i=0; i<nb; i++) {
				input.push(i);
			}
		}
		return input;
	};
}]);