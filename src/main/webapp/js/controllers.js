var app = angular.module('hemoterapia.controllers', [ "hemoterapia.services" ])

app.controller('DefaultPersonController', ['$scope', 'PersonFactory',
	function($scope, PersonFactory) {
		PersonFactory.query({}, function(personFactory) {
			$scope.name = personFactory.name;
			$scope.surname = personFactory.surname;
			$scope.companions = personFactory.companions;
			$scope.certificate = personFactory.certificate;
		})
	} ]);

app.controller('SavePersonController', [ '$scope', 'PersonFactory',
	function($scope, PersonFactory) {
		$scope.person = {
			certificate: {idCertificate: 1},
			lodgings: {lodgings_type: 'hemoterapia.domain.WithoutLodgings'},
			companions : 0,
			email: 'me@example.com',
			address:''			
		};
		$scope.email = /^[a-z]+[a-z0-9._]+@[a-z]+\.[a-z.]{2,5}$/;
		$scope.savePerson = function(person) {
			PersonFactory.save(person, function(response){
				console.log(response);
				$scope.person = {amount: response.amount};
			});
		};
		$scope.resetForm = function() {			
	        $scope.person= {
        		name: '',
        		surname: '',
        		email: 'me@example.com',
        		address: '',
        		certificate: {idCertificate: 1},
    			lodgings: {lodgings_type: 'hemoterapia.domain.WithoutLodgings'},
    			companions : 0
	        }
	    };
	    	    
	} ]);


// Solo permitimos numeros en aquellos tags html que tengan la palabra
// numericOnly
app.directive('numericOnly', function() {
	return {
		require : 'ngModel',
		link : function(scope, element, attrs, modelCtrl) {
			modelCtrl.$parsers.push(function(inputValue) {
				var transformedInput = inputValue ? inputValue.replace(
						/[^\d.-]/g, '') : null;
				if (transformedInput != inputValue) {
					modelCtrl.$setViewValue(transformedInput);
					modelCtrl.$render();
				}
				return transformedInput;
			});
		}
	};
});

app.directive("mySearchResults", function(){
	console.log("Dir - mySearchResults");
	return {
		restrict: "E",
		scope: {
			tablesdata:'=',
			modifyperson:'&',
			updateperson:'&',
			deleteperson:'&'
		},
		templateUrl:"partials/search-results-directive.html",
		controller:function ($scope, $attrs){
			console.log("Hola que tal");
			
			
			$scope.modifylineperson = function (tabledata){
				console.log("Dir - en el controlador invocando a modificar");
				console.log("el valor de tabledata es: " + tabledata);
				
				$scope.modifyperson({tabledata:tabledata});
			};
			$scope.updatelineperson = function (tabledata){
				console.log("Dir - en el controlador invocando a update");
			};
			$scope.deletelineperson = function (tabledata){
				console.log("Dir - en el controlador invocando a delete");
				$scope.deleteperson({tabledata:tabledata});
			}
		}
	};
})

app.directive("myTableResults", function(){
	console.log("Dir - myTableResults");
	return {
		restrict: "E",
		scope:{
			botonmodificar:'&',
			data:'='
		}
	}
})
app.controller('SearchPersonController', [ '$scope', 'PersonFactory',
                                           'searchService', function($scope, PersonFactory, searchService) {
	$scope.editingData = {};
	
	$scope.getPersons = function(person) {
		console.log(person.name);
		console.log(person.surname);
		var result = PersonFactory.search({
			name : person.name,
			surname : person.surname
		});
	
		searchService.setResults(result);
	};
	$scope.resetForm = function() {
		console.log("Ctrl - Borrando formulaio");
        $scope.person.name = '';  
        $scope.person.surname = '';
        $scope.editingData = {};
    };
    
    $scope.getResultsForTable=function(){
    	console.log ("Ctrl - Obteniendo resultados para la tabla");
    	
    	$scope.tablesdata = searchService.getResults();
    	for (var i = 0, length = $scope.tablesdata.length; i < length; i++) {    		
    		$scope.editingData[$scope.tablesdata[i].idPerson]= false;
    	}
   
    	console.log("que carajo sos editingData?? " + $scope.editingData);
    	return $scope.tablesdata;
    };
    
    $scope.setModifyButton= function(data){
    	console.log("Ctrl - Seteando resultados para la tabla");
    	console.log("que es tabledata: " + data);
		$scope.editingData[data.idPerson] = true;
    };

    $scope.updatePerson = function(data) {
    	$scope.editingData[data.idPerson] = false;
    };
    
    $scope.deletePerson = function(tabledata){
    	console.log("Ctrl - Delete resultados para la tabla");
//    	delete($scope.editingData[tabledata.idPerson]);
    	var indice = 0;
    	var keys = Object.keys($scope.editingData);
    	for(var i = keys.length - 1; i >= 0; i--){
    	    if(keys[i] == tabledata.idPerson) {
    	    	indice = keys[i];
    	    }
    	};
    	console.log("eliminando de editingData");
    	console.log("el indice es: " + indice);
    	delete($scope.editingData).indice;    	
    	
    	indice = 0;
    	for(var i = $scope.tablesdata.length - 1; i >= 0; i--) {
    	    if($scope.tablesdata[i] == tabledata) {
    	    	indice = i;
    	    }
    	};
    	console.log("eliminando de tablesdata");
    	console.log("el indice es: " + indice);
    	($scope.tablesdata).splice(indice, 1);
    	
    	searchService.deleteAResult(tabledata.idPerson);
    	PersonFactory.deletePer({id:tabledata.idPerson});
    }
} ]);

app.controller('StatisticsController', ['$scope', 'StatisticsFactory',
                                           function($scope, StatisticsFactory) {
		StatisticsFactory.query({}, function(staticsResults){
			$scope.professionalsQty= staticsResults.professionalQTy;
			$scope.professionalWithLodgingsQty= staticsResults.professionalWithLodgingsQty
			$scope.professionalWithoutLodgingsQty= staticsResults.professionalWithoutLodgingsQty
			
			$scope.technicianQty= staticsResults.technicianQTy;
			$scope.technicianWithLodgingsQty= staticsResults.technicianWithLodgingsQty
			$scope.technicianWithoutLodgingsQty= staticsResults.technicianWithoutLodgingsQty
			
			$scope.guestsQty= staticsResults.guestQTy;
			$scope.companionsQty= staticsResults.companionsQty;
			$scope.personsWithLodgingsQty= staticsResults.personsWithLodgingsQty;
			$scope.totalPersons= staticsResults.totalPersons;
			$scope.totalAmount= staticsResults.totalAmount;
		})
}]);


//app.controller('modifyPersonCtrl', ['$scope', '$rootScope', 'searchService',
//	function($scope, $rootScope, searchService) {
//		$rootScope.$on('llenartabla', function(result) {
//			console.log("ADENTRO");
//			$scope.tablesData = searchService.results;
//		
//			$scope.editingData = [];
//			for (var i = 0, length = $scope.tablesData.length; i < length; i++) {
//				$scope.editingData[$scope.tablesData[i].id] = false;
//			}
//			$scope.modify = function(tableData) {
//				$scope.editingData[tableData.id] = true;
//			};
//			$scope.update = function(tableData) {
//				$scope.editingData[tableData.id] = false;
//			}
//		});
//} ]);

// app.controller('SavePersonController', ['$scope', 'saveService',
// function($scope, saveService){
// $scope.person= {};
//	
// this.savePerson= function(person){
// saveService.savePerson(person).success(function() {
// alert('saved successfully!!!');
// }).error(function(){
// alert('something went wrong!!!');
// });
// }
// }]);

//app.directive('myNotebook', function ($log) {
//    return {
//        restrict:"E",
//        scope:{
//            notes:'=',
//            ondelete:'&'
//        },
//        templateUrl:"partials/ejemplo.html",
//        controller:function ($scope, $attrs) {
//        	console.log("antes del deleteNotedirective");
//        	var algo = $scope
//            $scope.deleteNoteDirective = function (id) {
//            	console.log("entre a delete Note Directive");
//                $scope.ondelete({id:id});
//            }
//        }
//    };
//})
//app.directive('myNote', function () {
//    return {
//        restrict:'E',
//        scope:{
//            delete:'&',
//            note:'='
//        },
//        link:function (scope, element, attrs) {
//            // Since this project pulls in jQuery, element is already
//            // a wrapped jQuery object, so there is no need to do 
//            // something like:  var $el = $(element);
//            // We can use element directly with jQuery methods.
//
//        	console.log(element);
//        	 $(element).hide().fadeIn('slow');

//            $('.thumbnails').sortable({
//                placeholder:"ui-state-highlight", forcePlaceholderSize:true
//            });
//        }, 
//    };
//})
//app.controller('NotebookCtrl', ['$scope', 'notesService', function ($scope, notesService) {
//$scope.getNotes = function () {
//    return notesService.notes();
//};
//
//$scope.addNote = function (noteTitle) {
//	console.log("agrego nota");
//    if(noteTitle != '') {
//        notesService.addNote(noteTitle);
//    }
//};
//
//$scope.deleteNote = function (id) {
//    notesService.deleteNote(id);
//};
//
//$scope.resetForm = function() {
//    $scope.noteTitle = '';
//};
//}]);