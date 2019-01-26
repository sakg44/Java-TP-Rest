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
			<form class="form-inline" action="rest/maire/create" method="post">
				<div class="form-group">
					<label for="textarea">Nom :</label> <input type="text" name="nom" />
					<label for="textarea"> Prénom :</label> <input type="text"
						name="prenom" />
				</div>
				<button type="submit" class="btn btn-success">Créer Maire</button>


			</form>
		</div>

		<div class="col-lg-6 col-md-6  col-xs-6 col-sm-6">
			<h4>Modification</h4>
			<form action="rest/maire/modifie" method="post">
				<div class="form-group">
					<label for="textarea">Nouveau Nom :</label> <input type="text"
						name="nom" /><br> <label for="textarea"> Nouveau
						Prénom :</label> <input type="text" name="prenom" /><br> <label
						for="textarea"> Id :</label> <input type="text" name="id" /><br>
				</div>
				<button type="submit" class="btn btn-warning">Update Maire</button>


			</form>
		</div>
	</div>
	<div data-ng-controller="myMaireCTRL"
		class="col-lg-10 col-md-10  col-xs-10 col-sm-10">
		<p>Liste des Maires</p>

		<table class="table table-bordered">
			<tr>
				<th>Id</th>
				<th>Nom</th>
				<th>Prénom</th>
			</tr>
			<tr data-ng-repeat="x in communes">
				<th>{{x.id }}</th>
				<th>{{x.nom }}</th>
				<th>{{x.prenom }}</th>
				<th>
					<button data-ng-click="removeMaire(x.id)">
						<span class="glyphicon glyphicon-remove"></span>
					</button>
			</tr>
		</table>



	</div>
	<script>
   var app= angular.module('monApp', []);
   
   function getALLMaire($scope,$http){
   var url="http://localhost:8080/tp-java-rest/rest/maire/retrieveAll";
  
    $http.get(url).then(successCallback, errorCallback);
    
		    function successCallback (response) {
				 console.log("success: " + response.data);
				  $scope.communes =[];
				  response.data.forEach(function(element) {
		  			 $scope.communes.push(element);});         
			}
	
			 function errorCallback(response) {
					console.log('err',err);
			}
				
	$scope.removeMaire = function(id) {
      	
      	var url="http://localhost:8080/tp-java-rest/rest/maire/delete/"+id;
  
    $http.get(url).then(successCallback1, errorCallback1);
      	function successCallback1 (response) {
				 console.log("success: " + response.data);
				// $scope.communes.splice(id+1, 1);
				//$route.reload();
				 }
   function errorCallback1(response) {
					console.log('err',err);
			}
      	
      	
      	
      
    };
   
   }
   
  
      	
   app.controller('myMaireCTRL', getALLMaire);
   
</script>

</body>
</html>