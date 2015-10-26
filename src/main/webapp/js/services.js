var services = angular.module('hemoterapia.services', ['ngResource']);

services.factory('PersonFactory', function ($resource) {
    return $resource('/congreso/rest/person', {}, {
        query: {
            method: 'GET',
            params: {},
            url: '/congreso/rest/default_person',
            isArray: false
        },
    	save: {
	        method: 'POST'
	    },
        search:{
        	method: 'GET',
        	params: {name: "@name", surname: "@surname"},
        	url: '/congreso/rest/all_persons',
        	isArray: true        	
        }
    })
});

services.service('searchService', function(){
	var search_results = [];
	
	this.getResults= function(){
		console.log("Service - invocaron a getResults");
		return search_results;
	}
	this.setResults= function(listOfResults){
		console.log("Service - invocaron a setResults");
		search_results = listOfResults;
	}
});

function resourceErrorHandlerQuery(response){
	console.log(response);
	return response;
}

function resourceErrorHandlerSave(response){
	console.log(response);
	return response;
}

function resourceResponseHandler(response){
	console.log(response);
	return response;
}

//Project.prototype.update = function(cb) {
//    return Project.update({id: this._id.$oid})
//      .$promise.then(
//        //success
//        function( value ){/*Do something with value*/},
//        //error
//        function( error ){/*Do something with error*/}
//      )
//  };


//services.service('saveService',['$http', function($http){
//	  // expose a saveRecipe function from your service
//	  // that takes a recipe object
//	  this.savePerson = function(person){
//	      // return a Promise object so that the caller can handle success/failure
//	      return $http({ method: 'POST', url: '/CongresoHemoterapia/rest/save_person', data: person});
//	  }
//}]);
