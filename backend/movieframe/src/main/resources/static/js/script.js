function loadContent(callback){
    $.ajax({
        url: 'http://localhost:8081/reviews/?page=1&size=10',
    })
    .then(response => {
        if(response.status !== 200){
            console.log("Looks like there was a problem. Status Code: " + response.status);
            return;
        }
        response.ajax().done(data => {//function(callback) that is executed once the response is received
            //console.log("Response received");
            console.log("Data:",JSON.stringify(data));
            callback(data);
        }).catch(function(err){
            console.log("AJAX parse Error :-S",err);
        });
    })
    .catch(function(err){
        console.log("Error :-S",err);
    });
    //console.log("Applying performed");
    //mostrar spinner
}

$(document).ready(function () {

    loadItems(function (items) {
        //When items are loaded from server
        for (var i = 0; i < items.length; i++) {
            showItem(items[i]);
        }
    });
    //upload more elements when pressed button "more results"
    