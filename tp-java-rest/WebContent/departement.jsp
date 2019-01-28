<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="angular.min.js"></script>

<%@ include file="menu.jsp"%>
</head>
<body data-ng-app="monApp">

	<div class="row container">
		<div class="col-lg-6 col-md-6  col-xs-6 col-sm-6">

			<h4>Creation</h4>
			<form class="form-inline" action="rest/departement/create" method="post">
				<div class="form-group">
					<label for="textarea">Nom :</label> <input type="text" name="nom" />
					<label for="textarea"> Code Postal :</label> <input type="text"
						name="code" />
				</div>
				<button type="submit" class="btn btn-success">Créer departement</button>


			</form>
		</div>

		<div class="col-lg-6 col-md-6  col-xs-6 col-sm-6">
			<h4>Modification</h4>
			<form action="rest/departement/modifie" method="post">
				<div class="form-group">
					<label for="textarea">Nouveau Nom :</label> <input type="text"
						name="nom" /><br> <label for="textarea"> Nouveau
						Code Postal :</label> <input type="text" name="code" /><br> <label
						for="textarea"> Id :</label> <input type="text" name="id" /><br>
				</div>
				<button type="submit" class="btn btn-warning">Update departement</button>


			</form>
		</div>
	</div>
	<div data-ng-controller="mydepartementCTRL"
		class="col-lg-10 col-md-10  col-xs-10 col-sm-10">
		<p>Liste des departements</p>

		<table class="table table-bordered">
			<tr>
				<th>Id</th>
				<th>Nom</th>
				<th>Code Postal</th>
			</tr>
			<tr data-ng-repeat="x in departements">
				<th>{{x.id }}</th>
				<th>{{x.nom }}</th>
				<th>{{x.code }}</th>
				<th>
					<button data-ng-click="removedepartement(x.id)">
						<span class="glyphicon glyphicon-remove"></span>
					</button>
			</tr>
		</table>



	</div>
	<script>
   var app= angular.module('monApp', []);
   
   function getAllDepartement($scope,$http){
   var url="http://localhost:8080/tp-java-rest/rest/departement/retrieveAll";
  
    $http.get(url).then(successCallback, errorCallback);
    
		    function successCallback (response) {
				 console.log("success: " + response.data);
				  $scope.departements =[];
				  response.data.forEach(function(element) {
		  			 $scope.departements.push(element);});         
			}
	
			 function errorCallback(response) {
					console.log('err',err);
			}
				
	$scope.removedepartement = function(id) {
      	
      	var url="http://localhost:8080/tp-java-rest/rest/departement/delete/"+id;
  
    $http.get(url).then(successCallback1, errorCallback1);
      	function successCallback1 (response) {
      		location.reload(); 
				 console.log("success: " + response.data);				
				 
				 }
   function errorCallback1(response) {
					console.log('err',err);
					 location.reload();
			}
      	
      	
      	
      
    };
   
   }
   
  
      	
   app.controller('mydepartementCTRL', getAllDepartement);
   
</script>

</body>
</html>