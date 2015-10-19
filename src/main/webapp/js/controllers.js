var app = angular.module('hemoterapia.controllers', [ "hemoterapia.services" ]);

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
					companions: 0,
			}
			$scope.savePerson = function(person) {
				PersonFactory.save(person);
			}
		} ]);

//Solo permitimos numeros en aquellos tags html que tengan la palabra numericOnly
app.directive('numericOnly', function(){
    return {
        require: 'ngModel',
        link: function(scope, element, attrs, modelCtrl) {
            modelCtrl.$parsers.push(function (inputValue) {
                var transformedInput = inputValue ? inputValue.replace(/[^\d.-]/g,'') : null;

                if (transformedInput!=inputValue) {
                    modelCtrl.$setViewValue(transformedInput);
                    modelCtrl.$render();
                }

                return transformedInput;
            });
        }
    };
});

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
