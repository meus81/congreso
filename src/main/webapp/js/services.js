var services = angular.module('hemoterapia.services', ['ngResource']);

services.factory('PersonFactory', function ($resource) {
    return $resource('./rest/person', {}, {
        query: {
            method: 'GET',
            params: {},
            url: './rest/default_person',
            isArray: false
        },
    	save: {
	        method: 'POST',
	        isArray: false 
	    },
        search: {
        	method: 'GET',
        	params: {name: "@name", surname: "@surname"},
        	url: './rest/all_persons',
        	isArray: true        	
        },
	    deletePerson: {
	    	method: 'DELETE',
	    	params:{},
	    	url: './rest/person/:id',
	    	isArray: false
	    },
	    updatePerson: {
	    	method: 'POST',
	    	isArray: false,
	    	url: './rest/updatePerson'
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
		debugger;
		console.log("Service - invocaron a setResults");
		search_results = listOfResults;
	}
	this.deleteAResult= function(data){
		console.log("Service - se invoco a deleteAResult")
	}
});

services.factory('StatisticsFactory', function ($resource) {
	
	return $resource('./rest/statistics', {}, {
		query: {
            method: 'GET',
            isArray: false
        }
    })
});

//function resourceErrorHandlerQuery(response){
//	console.log(response);
//	return response;
//}
//
//function resourceErrorHandlerSave(response){
//	console.log(response);
//	return response;
//}
//
//function resourceResponseHandler(response){
//	console.log(response);
//	return response;
//}

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

//EJEMPLO
//services.service('notesService', function () {
//	console.log("en el servicio");
//    var data = [{id:1, title:'Note 1'},
//                {id:2, title:'Note 2'},
//                {id:3, title:'Note 3'},
//                {id:4, title:'Note 4'},
//                {id:5, title:'Note 5'},
//                {id:6, title:'Note 6'},
//                {id:7, title:'Note 7'},
//                {id:8, title:'Note 8'}];
//
//    return {
//        notes:function () {
//            return data;
//        },
//        addNote:function (noteTitle) {
//            var currentIndex = data.length + 1;
//            data.push({
//                id:currentIndex, title:noteTitle
//            });
//        },
//        deleteNote:function (id) {
//            var oldNotes = data;
//            data = [];
//
//            angular.forEach(oldNotes, function (note) {
//                if (note.id !== id) data.push(note);
//            });
//        }
//    };
//})