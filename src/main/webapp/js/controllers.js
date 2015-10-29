var app = angular.module('hemoterapia.controllers', [ "hemoterapia.services" ])

app.controller('DefaultPersonController', [ '$scope', 'PersonFactory',
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
				companions : 0,
			}
			$scope.savePerson = function(person) {
				PersonFactory.save(person);
			}
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
			addmodify:'&'
		},
		templateUrl:"partials/search-results-directive.html",
		controller:function ($scope, $attrs){
			console.log("Hola que tal");
		
			$scope.modifyline = function (tabledata){
				console.log("Dir - en el controlador invocando a modificar");
				console.log("el valor de tabledata es: " + tabledata);
				$scope.addmodify({tabledata:tabledata});
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
    	console.log("que es tabledata: " + data)
		$scope.editingData[data.idPerson] = true
    };

    $scope.update = function(data) {
    	$scope.editingData[data.idPerson] = false;
    }
} ]);




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