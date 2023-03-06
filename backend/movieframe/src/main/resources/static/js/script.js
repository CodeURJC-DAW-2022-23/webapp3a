function loadContent(callback, size){
    $.ajax({
        url: 'http://localhost:8081/reviews/?page=1&size='+size,
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

//Create item in server
function createItem(item, callback) {
    $.ajax({
        method: "POST",
        url: 'http://localhost:8081/reviews/',
        data: JSON.stringify(item),
        processData: false,
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (item) {
        console.log("Item created: " + JSON.stringify(item));
        callback(item);
    })
}

//Delete item from server
function deleteItem(itemId) {
    $.ajax({
        method: 'DELETE',
        url: 'http://localhost:8081/reviews/' + itemId
    }).done(function (item) {
        console.log("Deleted item " + itemId)
    })
}

$(document).ready(function () {

    loadItems(function (items) {
        //When items are loaded from server
        for (var i = 0; i < items.length; i++) {
            showItem(items[i]);
        }
    });
    //upload more elements when pressed button "more results"
})
